import io.grpc.Server
import io.grpc.ServerBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.example.FileTransfer
import org.example.FileTransfer.Chunk
import org.example.UploadGrpcKt

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
        .addService(HelloWorldService())
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

    internal class HelloWorldService : UploadGrpcKt.UploadCoroutineImplBase() {
        override suspend fun uploadFile(requests: Flow<Chunk>): FileTransfer.UploadStatus {
            val outputDir = File(
                "/home/ax/receivedBytes"
            )
            if (!outputDir.exists()) {
                outputDir.mkdirs()
            }
            val outputFile = File(outputDir, "bytes.png")
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