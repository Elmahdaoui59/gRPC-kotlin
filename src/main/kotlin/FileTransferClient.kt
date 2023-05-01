import com.eldevs.grpctest.FileTransfer
import com.eldevs.grpctest.FileTransfer.FileMetadata
import com.eldevs.grpctest.MetadataGrpcKt
import com.eldevs.grpctest.UploadGrpcKt
import com.google.protobuf.ByteString
import com.google.protobuf.compiler.PluginProtos
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.io.Closeable
import java.io.File
import java.io.FileInputStream
import java.util.concurrent.TimeUnit

suspend fun main() {
    val port = 50052
    val channel = ManagedChannelBuilder.forAddress("localhost", port).usePlaintext().build()
    val client = FileTransferClient(channel)
    client.sendMetadata()
}


class FileTransferClient(
    private val channel: ManagedChannel
) : Closeable {
    private val stub: UploadGrpcKt.UploadCoroutineStub = UploadGrpcKt.UploadCoroutineStub(channel)
    private val metadataStub: MetadataGrpcKt.MetadataCoroutineStub = MetadataGrpcKt.MetadataCoroutineStub(channel)
    private val file = File("lettre.pdf")
    private val extension = file.extension
    private val fileName = file.name
    suspend fun sendMetadata() {
        return try {
            val metadata = FileMetadata.newBuilder().setName(fileName).setExtension(extension).build()
            val response = metadataStub.sendMetadata(metadata)
            when(response.code) {
                FileTransfer.UploadStatus.UploadStatusCode.Ok -> {
                    println("metadata was transferred:\n$fileName \n$extension")
                    sendFile()
                }
                FileTransfer.UploadStatus.UploadStatusCode.Failed -> {
                    throw Exception("can't send file's metadata, ${response.message}")
                }
                else -> {}
            }
        } catch (e:Exception) {
            e.printStackTrace()
        }
    }
    private suspend fun sendFile() {
        return try {
            withContext(Dispatchers.IO) {

                val inS = FileInputStream(file)
                val stream = flow {
                    var count: Int
                    inS.use { stream ->
                        val chunk = ByteArray(1024)
                        count = stream.read(chunk)
                        while (count > 0) {
                            val request = FileTransfer.Chunk.newBuilder().setContent(ByteString.copyFrom(chunk)).build()
                            emit(request)
                            count = stream.read(chunk)
                        }
                    }
                    inS.close()

                }
                    .flowOn(Dispatchers.IO)
                val response = stub.uploadFile(stream)
                when (response.code) {
                    FileTransfer.UploadStatus.UploadStatusCode.Ok -> {
                        println("file sent! ${file.length()/(1024*1024)}MB was transferred!")
                    }

                    FileTransfer.UploadStatus.UploadStatusCode.Failed -> {
                        throw Exception("can't upload file, ${response.message}")
                    }

                    else -> {}
                }
                //val request = FileTransfer.metadata.newBuilder().setExtension(extension)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }
}


