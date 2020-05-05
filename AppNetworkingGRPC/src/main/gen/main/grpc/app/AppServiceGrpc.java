package app;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.29.0)",
    comments = "Source: app.proto")
public final class AppServiceGrpc {

  private AppServiceGrpc() {}

  public static final String SERVICE_NAME = "app.AppService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<app.SearchRequest,
      app.SearchResponse> getSearchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "search",
      requestType = app.SearchRequest.class,
      responseType = app.SearchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<app.SearchRequest,
      app.SearchResponse> getSearchMethod() {
    io.grpc.MethodDescriptor<app.SearchRequest, app.SearchResponse> getSearchMethod;
    if ((getSearchMethod = AppServiceGrpc.getSearchMethod) == null) {
      synchronized (AppServiceGrpc.class) {
        if ((getSearchMethod = AppServiceGrpc.getSearchMethod) == null) {
          AppServiceGrpc.getSearchMethod = getSearchMethod =
              io.grpc.MethodDescriptor.<app.SearchRequest, app.SearchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "search"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  app.SearchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  app.SearchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AppServiceMethodDescriptorSupplier("search"))
              .build();
        }
      }
    }
    return getSearchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<app.ReserveRequest,
      app.ReserveResponse> getReserveMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "reserve",
      requestType = app.ReserveRequest.class,
      responseType = app.ReserveResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<app.ReserveRequest,
      app.ReserveResponse> getReserveMethod() {
    io.grpc.MethodDescriptor<app.ReserveRequest, app.ReserveResponse> getReserveMethod;
    if ((getReserveMethod = AppServiceGrpc.getReserveMethod) == null) {
      synchronized (AppServiceGrpc.class) {
        if ((getReserveMethod = AppServiceGrpc.getReserveMethod) == null) {
          AppServiceGrpc.getReserveMethod = getReserveMethod =
              io.grpc.MethodDescriptor.<app.ReserveRequest, app.ReserveResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "reserve"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  app.ReserveRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  app.ReserveResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AppServiceMethodDescriptorSupplier("reserve"))
              .build();
        }
      }
    }
    return getReserveMethod;
  }

  private static volatile io.grpc.MethodDescriptor<app.ShowTripsRequest,
      app.ShowTripsResponse> getShowTripsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "showTrips",
      requestType = app.ShowTripsRequest.class,
      responseType = app.ShowTripsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<app.ShowTripsRequest,
      app.ShowTripsResponse> getShowTripsMethod() {
    io.grpc.MethodDescriptor<app.ShowTripsRequest, app.ShowTripsResponse> getShowTripsMethod;
    if ((getShowTripsMethod = AppServiceGrpc.getShowTripsMethod) == null) {
      synchronized (AppServiceGrpc.class) {
        if ((getShowTripsMethod = AppServiceGrpc.getShowTripsMethod) == null) {
          AppServiceGrpc.getShowTripsMethod = getShowTripsMethod =
              io.grpc.MethodDescriptor.<app.ShowTripsRequest, app.ShowTripsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "showTrips"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  app.ShowTripsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  app.ShowTripsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AppServiceMethodDescriptorSupplier("showTrips"))
              .build();
        }
      }
    }
    return getShowTripsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<app.GetTripIDByDestinationAndDepartureRequest,
      app.GetTripIDByDestinationAndDepartureResponse> getGetTripIDByDestinationAndDepartureMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getTripIDByDestinationAndDeparture",
      requestType = app.GetTripIDByDestinationAndDepartureRequest.class,
      responseType = app.GetTripIDByDestinationAndDepartureResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<app.GetTripIDByDestinationAndDepartureRequest,
      app.GetTripIDByDestinationAndDepartureResponse> getGetTripIDByDestinationAndDepartureMethod() {
    io.grpc.MethodDescriptor<app.GetTripIDByDestinationAndDepartureRequest, app.GetTripIDByDestinationAndDepartureResponse> getGetTripIDByDestinationAndDepartureMethod;
    if ((getGetTripIDByDestinationAndDepartureMethod = AppServiceGrpc.getGetTripIDByDestinationAndDepartureMethod) == null) {
      synchronized (AppServiceGrpc.class) {
        if ((getGetTripIDByDestinationAndDepartureMethod = AppServiceGrpc.getGetTripIDByDestinationAndDepartureMethod) == null) {
          AppServiceGrpc.getGetTripIDByDestinationAndDepartureMethod = getGetTripIDByDestinationAndDepartureMethod =
              io.grpc.MethodDescriptor.<app.GetTripIDByDestinationAndDepartureRequest, app.GetTripIDByDestinationAndDepartureResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getTripIDByDestinationAndDeparture"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  app.GetTripIDByDestinationAndDepartureRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  app.GetTripIDByDestinationAndDepartureResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AppServiceMethodDescriptorSupplier("getTripIDByDestinationAndDeparture"))
              .build();
        }
      }
    }
    return getGetTripIDByDestinationAndDepartureMethod;
  }

  private static volatile io.grpc.MethodDescriptor<app.LoginRequest,
      app.LoginResponse> getLoginMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "login",
      requestType = app.LoginRequest.class,
      responseType = app.LoginResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<app.LoginRequest,
      app.LoginResponse> getLoginMethod() {
    io.grpc.MethodDescriptor<app.LoginRequest, app.LoginResponse> getLoginMethod;
    if ((getLoginMethod = AppServiceGrpc.getLoginMethod) == null) {
      synchronized (AppServiceGrpc.class) {
        if ((getLoginMethod = AppServiceGrpc.getLoginMethod) == null) {
          AppServiceGrpc.getLoginMethod = getLoginMethod =
              io.grpc.MethodDescriptor.<app.LoginRequest, app.LoginResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "login"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  app.LoginRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  app.LoginResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AppServiceMethodDescriptorSupplier("login"))
              .build();
        }
      }
    }
    return getLoginMethod;
  }

  private static volatile io.grpc.MethodDescriptor<app.LogoutRequest,
      app.LogoutResponse> getLogoutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "logout",
      requestType = app.LogoutRequest.class,
      responseType = app.LogoutResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<app.LogoutRequest,
      app.LogoutResponse> getLogoutMethod() {
    io.grpc.MethodDescriptor<app.LogoutRequest, app.LogoutResponse> getLogoutMethod;
    if ((getLogoutMethod = AppServiceGrpc.getLogoutMethod) == null) {
      synchronized (AppServiceGrpc.class) {
        if ((getLogoutMethod = AppServiceGrpc.getLogoutMethod) == null) {
          AppServiceGrpc.getLogoutMethod = getLogoutMethod =
              io.grpc.MethodDescriptor.<app.LogoutRequest, app.LogoutResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "logout"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  app.LogoutRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  app.LogoutResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AppServiceMethodDescriptorSupplier("logout"))
              .build();
        }
      }
    }
    return getLogoutMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AppServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AppServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AppServiceStub>() {
        @java.lang.Override
        public AppServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AppServiceStub(channel, callOptions);
        }
      };
    return AppServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AppServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AppServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AppServiceBlockingStub>() {
        @java.lang.Override
        public AppServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AppServiceBlockingStub(channel, callOptions);
        }
      };
    return AppServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AppServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AppServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AppServiceFutureStub>() {
        @java.lang.Override
        public AppServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AppServiceFutureStub(channel, callOptions);
        }
      };
    return AppServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class AppServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void search(app.SearchRequest request,
        io.grpc.stub.StreamObserver<app.SearchResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSearchMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<app.ReserveRequest> reserve(
        io.grpc.stub.StreamObserver<app.ReserveResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getReserveMethod(), responseObserver);
    }

    /**
     */
    public void showTrips(app.ShowTripsRequest request,
        io.grpc.stub.StreamObserver<app.ShowTripsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getShowTripsMethod(), responseObserver);
    }

    /**
     */
    public void getTripIDByDestinationAndDeparture(app.GetTripIDByDestinationAndDepartureRequest request,
        io.grpc.stub.StreamObserver<app.GetTripIDByDestinationAndDepartureResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetTripIDByDestinationAndDepartureMethod(), responseObserver);
    }

    /**
     */
    public void login(app.LoginRequest request,
        io.grpc.stub.StreamObserver<app.LoginResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLoginMethod(), responseObserver);
    }

    /**
     */
    public void logout(app.LogoutRequest request,
        io.grpc.stub.StreamObserver<app.LogoutResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLogoutMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSearchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                app.SearchRequest,
                app.SearchResponse>(
                  this, METHODID_SEARCH)))
          .addMethod(
            getReserveMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                app.ReserveRequest,
                app.ReserveResponse>(
                  this, METHODID_RESERVE)))
          .addMethod(
            getShowTripsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                app.ShowTripsRequest,
                app.ShowTripsResponse>(
                  this, METHODID_SHOW_TRIPS)))
          .addMethod(
            getGetTripIDByDestinationAndDepartureMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                app.GetTripIDByDestinationAndDepartureRequest,
                app.GetTripIDByDestinationAndDepartureResponse>(
                  this, METHODID_GET_TRIP_IDBY_DESTINATION_AND_DEPARTURE)))
          .addMethod(
            getLoginMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                app.LoginRequest,
                app.LoginResponse>(
                  this, METHODID_LOGIN)))
          .addMethod(
            getLogoutMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                app.LogoutRequest,
                app.LogoutResponse>(
                  this, METHODID_LOGOUT)))
          .build();
    }
  }

  /**
   */
  public static final class AppServiceStub extends io.grpc.stub.AbstractAsyncStub<AppServiceStub> {
    private AppServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AppServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AppServiceStub(channel, callOptions);
    }

    /**
     */
    public void search(app.SearchRequest request,
        io.grpc.stub.StreamObserver<app.SearchResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<app.ReserveRequest> reserve(
        io.grpc.stub.StreamObserver<app.ReserveResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getReserveMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void showTrips(app.ShowTripsRequest request,
        io.grpc.stub.StreamObserver<app.ShowTripsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getShowTripsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getTripIDByDestinationAndDeparture(app.GetTripIDByDestinationAndDepartureRequest request,
        io.grpc.stub.StreamObserver<app.GetTripIDByDestinationAndDepartureResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetTripIDByDestinationAndDepartureMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void login(app.LoginRequest request,
        io.grpc.stub.StreamObserver<app.LoginResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void logout(app.LogoutRequest request,
        io.grpc.stub.StreamObserver<app.LogoutResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLogoutMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AppServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<AppServiceBlockingStub> {
    private AppServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AppServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AppServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public app.SearchResponse search(app.SearchRequest request) {
      return blockingUnaryCall(
          getChannel(), getSearchMethod(), getCallOptions(), request);
    }

    /**
     */
    public app.ShowTripsResponse showTrips(app.ShowTripsRequest request) {
      return blockingUnaryCall(
          getChannel(), getShowTripsMethod(), getCallOptions(), request);
    }

    /**
     */
    public app.GetTripIDByDestinationAndDepartureResponse getTripIDByDestinationAndDeparture(app.GetTripIDByDestinationAndDepartureRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetTripIDByDestinationAndDepartureMethod(), getCallOptions(), request);
    }

    /**
     */
    public app.LoginResponse login(app.LoginRequest request) {
      return blockingUnaryCall(
          getChannel(), getLoginMethod(), getCallOptions(), request);
    }

    /**
     */
    public app.LogoutResponse logout(app.LogoutRequest request) {
      return blockingUnaryCall(
          getChannel(), getLogoutMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AppServiceFutureStub extends io.grpc.stub.AbstractFutureStub<AppServiceFutureStub> {
    private AppServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AppServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AppServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<app.SearchResponse> search(
        app.SearchRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<app.ShowTripsResponse> showTrips(
        app.ShowTripsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getShowTripsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<app.GetTripIDByDestinationAndDepartureResponse> getTripIDByDestinationAndDeparture(
        app.GetTripIDByDestinationAndDepartureRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetTripIDByDestinationAndDepartureMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<app.LoginResponse> login(
        app.LoginRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<app.LogoutResponse> logout(
        app.LogoutRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLogoutMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEARCH = 0;
  private static final int METHODID_SHOW_TRIPS = 1;
  private static final int METHODID_GET_TRIP_IDBY_DESTINATION_AND_DEPARTURE = 2;
  private static final int METHODID_LOGIN = 3;
  private static final int METHODID_LOGOUT = 4;
  private static final int METHODID_RESERVE = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AppServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AppServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEARCH:
          serviceImpl.search((app.SearchRequest) request,
              (io.grpc.stub.StreamObserver<app.SearchResponse>) responseObserver);
          break;
        case METHODID_SHOW_TRIPS:
          serviceImpl.showTrips((app.ShowTripsRequest) request,
              (io.grpc.stub.StreamObserver<app.ShowTripsResponse>) responseObserver);
          break;
        case METHODID_GET_TRIP_IDBY_DESTINATION_AND_DEPARTURE:
          serviceImpl.getTripIDByDestinationAndDeparture((app.GetTripIDByDestinationAndDepartureRequest) request,
              (io.grpc.stub.StreamObserver<app.GetTripIDByDestinationAndDepartureResponse>) responseObserver);
          break;
        case METHODID_LOGIN:
          serviceImpl.login((app.LoginRequest) request,
              (io.grpc.stub.StreamObserver<app.LoginResponse>) responseObserver);
          break;
        case METHODID_LOGOUT:
          serviceImpl.logout((app.LogoutRequest) request,
              (io.grpc.stub.StreamObserver<app.LogoutResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_RESERVE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.reserve(
              (io.grpc.stub.StreamObserver<app.ReserveResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class AppServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AppServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return app.App.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AppService");
    }
  }

  private static final class AppServiceFileDescriptorSupplier
      extends AppServiceBaseDescriptorSupplier {
    AppServiceFileDescriptorSupplier() {}
  }

  private static final class AppServiceMethodDescriptorSupplier
      extends AppServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AppServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (AppServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AppServiceFileDescriptorSupplier())
              .addMethod(getSearchMethod())
              .addMethod(getReserveMethod())
              .addMethod(getShowTripsMethod())
              .addMethod(getGetTripIDByDestinationAndDepartureMethod())
              .addMethod(getLoginMethod())
              .addMethod(getLogoutMethod())
              .build();
        }
      }
    }
    return result;
  }
}
