package org.example

import io.grpc.CallOptions
import io.grpc.CallOptions.DEFAULT
import io.grpc.Channel
import io.grpc.Metadata
import io.grpc.MethodDescriptor
import io.grpc.ServerServiceDefinition
import io.grpc.ServerServiceDefinition.builder
import io.grpc.ServiceDescriptor
import io.grpc.Status.UNIMPLEMENTED
import io.grpc.StatusException
import io.grpc.kotlin.AbstractCoroutineServerImpl
import io.grpc.kotlin.AbstractCoroutineStub
import io.grpc.kotlin.ClientCalls.clientStreamingRpc
import io.grpc.kotlin.ServerCalls.clientStreamingServerMethodDefinition
import io.grpc.kotlin.StubFor
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlinx.coroutines.flow.Flow
import org.example.UploadGrpc.getServiceDescriptor

/**
 * Holder for Kotlin coroutine-based client and server APIs for org.example.Upload.
 */
object UploadGrpcKt {
  @JvmStatic
  val serviceDescriptor: ServiceDescriptor
    get() = UploadGrpc.getServiceDescriptor()

  val uploadFileMethod: MethodDescriptor<FileTransfer.Chunk, FileTransfer.UploadStatus>
    @JvmStatic
    get() = UploadGrpc.getUploadFileMethod()

  /**
   * A stub for issuing RPCs to a(n) org.example.Upload service as suspending coroutines.
   */
  @StubFor(UploadGrpc::class)
  class UploadCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT
  ) : AbstractCoroutineStub<UploadCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions): UploadCoroutineStub =
        UploadCoroutineStub(channel, callOptions)

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * This function collects the [Flow] of requests.  If the server terminates the RPC
     * for any reason before collection of requests is complete, the collection of requests
     * will be cancelled.  If the collection of requests completes exceptionally for any other
     * reason, the RPC will be cancelled for that reason and this method will throw that
     * exception.
     *
     * @param requests A [Flow] of request messages.
     *
     * @return The single response from the server.
     */
    suspend fun uploadFile(requests: Flow<FileTransfer.Chunk>): FileTransfer.UploadStatus =
        clientStreamingRpc(
      channel,
      UploadGrpc.getUploadFileMethod(),
      requests,
      callOptions,
      Metadata()
    )}

  /**
   * Skeletal implementation of the org.example.Upload service based on Kotlin coroutines.
   */
  abstract class UploadCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext
  ) : AbstractCoroutineServerImpl(coroutineContext) {
    /**
     * Returns the response to an RPC for org.example.Upload.uploadFile.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param requests A [Flow] of requests from the client.  This flow can be
     *        collected only once and throws [java.lang.IllegalStateException] on attempts to
     * collect
     *        it more than once.
     */
    open suspend fun uploadFile(requests: Flow<FileTransfer.Chunk>): FileTransfer.UploadStatus =
        throw
        StatusException(UNIMPLEMENTED.withDescription("Method org.example.Upload.uploadFile is unimplemented"))

    final override fun bindService(): ServerServiceDefinition = builder(getServiceDescriptor())
      .addMethod(clientStreamingServerMethodDefinition(
      context = this.context,
      descriptor = UploadGrpc.getUploadFileMethod(),
      implementation = ::uploadFile
    )).build()
  }
}
