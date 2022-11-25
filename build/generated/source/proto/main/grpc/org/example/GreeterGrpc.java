package org.example;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.47.0)",
    comments = "Source: FileTransfer.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class GreeterGrpc {

  private GreeterGrpc() {}

  public static final String SERVICE_NAME = "org.example.Greeter";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.example.route_guide.Chunk,
      org.example.route_guide.UploadStatus> getUploadFileMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "uploadFile",
      requestType = org.example.route_guide.Chunk.class,
      responseType = org.example.route_guide.UploadStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<org.example.route_guide.Chunk,
      org.example.route_guide.UploadStatus> getUploadFileMethod() {
    io.grpc.MethodDescriptor<org.example.route_guide.Chunk, org.example.route_guide.UploadStatus> getUploadFileMethod;
    if ((getUploadFileMethod = GreeterGrpc.getUploadFileMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getUploadFileMethod = GreeterGrpc.getUploadFileMethod) == null) {
          GreeterGrpc.getUploadFileMethod = getUploadFileMethod =
              io.grpc.MethodDescriptor.<org.example.route_guide.Chunk, org.example.route_guide.UploadStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "uploadFile"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.example.route_guide.Chunk.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.example.route_guide.UploadStatus.getDefaultInstance()))
              .setSchemaDescriptor(new GreeterMethodDescriptorSupplier("uploadFile"))
              .build();
        }
      }
    }
    return getUploadFileMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GreeterStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GreeterStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GreeterStub>() {
        @java.lang.Override
        public GreeterStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GreeterStub(channel, callOptions);
        }
      };
    return GreeterStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GreeterBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GreeterBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GreeterBlockingStub>() {
        @java.lang.Override
        public GreeterBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GreeterBlockingStub(channel, callOptions);
        }
      };
    return GreeterBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GreeterFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GreeterFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GreeterFutureStub>() {
        @java.lang.Override
        public GreeterFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GreeterFutureStub(channel, callOptions);
        }
      };
    return GreeterFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class GreeterImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<org.example.route_guide.Chunk> uploadFile(
        io.grpc.stub.StreamObserver<org.example.route_guide.UploadStatus> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getUploadFileMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getUploadFileMethod(),
            io.grpc.stub.ServerCalls.asyncClientStreamingCall(
              new MethodHandlers<
                org.example.route_guide.Chunk,
                org.example.route_guide.UploadStatus>(
                  this, METHODID_UPLOAD_FILE)))
          .build();
    }
  }

  /**
   */
  public static final class GreeterStub extends io.grpc.stub.AbstractAsyncStub<GreeterStub> {
    private GreeterStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GreeterStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<org.example.route_guide.Chunk> uploadFile(
        io.grpc.stub.StreamObserver<org.example.route_guide.UploadStatus> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getUploadFileMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class GreeterBlockingStub extends io.grpc.stub.AbstractBlockingStub<GreeterBlockingStub> {
    private GreeterBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GreeterBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class GreeterFutureStub extends io.grpc.stub.AbstractFutureStub<GreeterFutureStub> {
    private GreeterFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GreeterFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_UPLOAD_FILE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GreeterImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GreeterImplBase serviceImpl, int methodId) {
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
              (io.grpc.stub.StreamObserver<org.example.route_guide.UploadStatus>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GreeterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GreeterBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.example.route_guide.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Greeter");
    }
  }

  private static final class GreeterFileDescriptorSupplier
      extends GreeterBaseDescriptorSupplier {
    GreeterFileDescriptorSupplier() {}
  }

  private static final class GreeterMethodDescriptorSupplier
      extends GreeterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GreeterMethodDescriptorSupplier(String methodName) {
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
      synchronized (GreeterGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GreeterFileDescriptorSupplier())
              .addMethod(getUploadFileMethod())
              .build();
        }
      }
    }
    return result;
  }
}
