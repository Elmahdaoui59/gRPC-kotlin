import com.eldevs.grpctest.FileTransfer
import com.eldevs.grpctest.MetadataGrpcKt
import com.eldevs.grpctest.UploadGrpcKt
import io.grpc.Server
import io.grpc.ServerBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

fun main() {
    val port = 50052
    val server = FileTransferServer(port)
    server.start()
    server.blockUntilShutDown()
}

class FileTransferServer(
    private val port: Int
) {
    val server: Server = ServerBuilder.forPort(port)
        .addService(GetFileMetadataService())
        .addService(GetFileService())
        .build()

    fun start() {
        server.start()
        println("Server started , listening on $port")
        Runtime.getRuntime().addShutdownHook(
            Thread {
                println("*** shutting down gRPC server since JVM is shutting down")
                this@FileTransferServer.stop()
                println("*** sever shut down")
            }
        )
    }

    private fun stop() {
        server.shutdown()
    }

    fun blockUntilShutDown() {
        server.awaitTermination()
    }

    var fileName: String? = null
    inner class GetFileMetadataService : MetadataGrpcKt.MetadataCoroutineImplBase() {
        override suspend fun sendMetadata(request: FileTransfer.FileMetadata): FileTransfer.UploadStatus {
            try {
                fileName = request.name
                val fileExtension = request.extension
                println("File name : $fileName\nFile Extension : $fileExtension")
                if (fileName.isNullOrBlank() or fileExtension.isNullOrBlank())
                    throw Exception("Error receiving metadata")

            } catch (e: Exception) {
                e.printStackTrace()
                return FileTransfer.UploadStatus.newBuilder()
                    .setCode(FileTransfer.UploadStatus.UploadStatusCode.Failed)
                    .setMessage(e.message)
                    .build()
            }
            return FileTransfer.UploadStatus.newBuilder()
                .setCode(FileTransfer.UploadStatus.UploadStatusCode.Ok)
                .build()
        }
    }

    inner class GetFileService : UploadGrpcKt.UploadCoroutineImplBase() {
        override suspend fun uploadFile(requests: Flow<FileTransfer.Chunk>): FileTransfer.UploadStatus {

            val outputDir = File(
                "C:\\Users\\dell\\Documents", "FileTransferGrpc"
            )
            if (!outputDir.exists()) {
                outputDir.mkdirs()
            }
            val outputFile = File(outputDir, fileName)
            var out: FileOutputStream? = null
            try {
                withContext(Dispatchers.IO) {
                    out = FileOutputStream(outputFile)
                    out.use { stream ->
                        requests.collect {
                            stream?.write(it.content.toByteArray())
                        }
                        println("bytes written to file at ${outputFile.toURI()}")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                return FileTransfer.UploadStatus.newBuilder()
                    .setCode(FileTransfer.UploadStatus.UploadStatusCode.Failed)
                    .setMessage(e.message)
                    .build()
            } finally {
                out?.let {
                    withContext(Dispatchers.IO) {
                        try {
                            it.flush()
                            it.flush()
                            it.close()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }
            return FileTransfer.UploadStatus.newBuilder().setCode(FileTransfer.UploadStatus.UploadStatusCode.Ok).build()
        }
    }
}