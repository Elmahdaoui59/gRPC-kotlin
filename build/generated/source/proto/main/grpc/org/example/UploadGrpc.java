package org.example;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.47.0)",
    comments = "Source: FileTransfer.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class UploadGrpc {

  private UploadGrpc() {}

  public static final String SERVICE_NAME = "org.example.Upload";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.example.FileTransfer.Chunk,
      org.example.FileTransfer.UploadStatus> getUploadFileMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "uploadFile",
      requestType = org.example.FileTransfer.Chunk.class,
      responseType = org.example.FileTransfer.UploadStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<org.example.FileTransfer.Chunk,
      org.example.FileTransfer.UploadStatus> getUploadFileMethod() {
    io.grpc.MethodDescriptor<org.example.FileTransfer.Chunk, org.example.FileTransfer.UploadStatus> getUploadFileMethod;
    if ((getUploadFileMethod = UploadGrpc.getUploadFileMethod) == null) {
      synchronized (UploadGrpc.class) {
        if ((getUploadFileMethod = UploadGrpc.getUploadFileMethod) == null) {
          UploadGrpc.getUploadFileMethod = getUploadFileMethod =
              io.grpc.MethodDescriptor.<org.example.FileTransfer.Chunk, org.example.FileTransfer.UploadStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "uploadFile"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.example.FileTransfer.Chunk.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.example.FileTransfer.UploadStatus.getDefaultInstance()))
              .setSchemaDescriptor(new UploadMethodDescriptorSupplier("uploadFile"))
              .build();
        }
      }
    }
    return getUploadFileMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UploadStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UploadStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UploadStub>() {
        @java.lang.Override
        public UploadStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UploadStub(channel, callOptions);
        }
      };
    return UploadStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UploadBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UploadBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UploadBlockingStub>() {
        @java.lang.Override
        public UploadBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UploadBlockingStub(channel, callOptions);
        }
      };
    return UploadBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UploadFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UploadFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UploadFutureStub>() {
        @java.lang.Override
        public UploadFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UploadFutureStub(channel, callOptions);
        }
      };
    return UploadFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class UploadImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<org.example.FileTransfer.Chunk> uploadFile(
        io.grpc.stub.StreamObserver<org.example.FileTransfer.UploadStatus> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getUploadFileMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getUploadFileMethod(),
            io.grpc.stub.ServerCalls.asyncClientStreamingCall(
              new MethodHandlers<
                org.example.FileTransfer.Chunk,
                org.example.FileTransfer.UploadStatus>(
                  this, METHODID_UPLOAD_FILE)))
          .build();
    }
  }

  /**
   */
  public static final class UploadStub extends io.grpc.stub.AbstractAsyncStub<UploadStub> {
    private UploadStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UploadStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UploadStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<org.example.FileTransfer.Chunk> uploadFile(
        io.grpc.stub.StreamObserver<org.example.FileTransfer.UploadStatus> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getUploadFileMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class UploadBlockingStub extends io.grpc.stub.AbstractBlockingStub<UploadBlockingStub> {
    private UploadBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UploadBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UploadBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class UploadFutureStub extends io.grpc.stub.AbstractFutureStub<UploadFutureStub> {
    private UploadFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UploadFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UploadFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_UPLOAD_FILE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UploadImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UploadImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_UPLOAD_FILE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.uploadFile(
              (io.grpc.stub.StreamObserver<org.example.FileTransfer.UploadStatus>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class UploadBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UploadBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.example.FileTransfer.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Upload");
    }
  }

  private static final class UploadFileDescriptorSupplier
      extends UploadBaseDescriptorSupplier {
    UploadFileDescriptorSupplier() {}
  }

  private static final class UploadMethodDescriptorSupplier
      extends UploadBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UploadMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (UploadGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UploadFileDescriptorSupplier())
              .addMethod(getUploadFileMethod())
              .build();
        }
      }
    }
    return result;
  }
}
