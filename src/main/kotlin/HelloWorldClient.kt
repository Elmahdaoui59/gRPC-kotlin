import com.google.protobuf.ByteString
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import org.example.GreeterGrpcKt
import org.example.route_guide.Chunk
import org.example.route_guide.UploadStatus.UploadStatusCode
import java.io.Closeable
import java.nio.file.Files
import java.nio.file.Paths
import java.util.concurrent.TimeUnit

suspend fun main() {
    val port = 50052
    val channel = ManagedChannelBuilder.forAddress("localhost", port).usePlaintext().build()
    val client = HelloWorldClient(channel)
    client.sendFile()
}


class HelloWorldClient(
    private val channel: ManagedChannel
) : Closeable {
    private val stub: GreeterGrpcKt.GreeterCoroutineStub = GreeterGrpcKt.GreeterCoroutineStub(channel)
    suspend fun sendFile() {
        val bytesArray: ByteArray
        var bytesString: ByteString? = null
        return try {
            withContext(Dispatchers.IO) {
                val fileUri = this.javaClass.classLoader.getResource("el-devs.png")
                fileUri?.let {
                    bytesArray = Files.readAllBytes(Paths.get(it.toURI()))
                    bytesString = ByteString.copyFrom(bytesArray)
                    val request = Chunk.newBuilder().setContent(bytesString).build()
                    val stream = flow {
                        emit(request)
                    }
                    val response = stub.uploadFile(stream)
                    when (response.code) {
                        UploadStatusCode.Ok -> {
                            println("file sent! ${bytesArray.size} was transferred!")
                        }

                        UploadStatusCode.Failed -> {
                            throw Exception("can't upload file, ${response.message}")
                        }

                        else -> {}
                    }
                } ?: throw Exception("file's url is null")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }
}


