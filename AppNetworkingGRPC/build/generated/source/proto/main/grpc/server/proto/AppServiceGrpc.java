package server.proto;

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
    value = "by gRPC proto compiler (version 1.25.0)",
    comments = "Source: app.proto")
public final class AppServiceGrpc {

  private AppServiceGrpc() {}

  public static final String SERVICE_NAME = "server.proto.AppService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<server.proto.searchRequest,
      server.proto.searchResponse> getSearchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "search",
      requestType = server.proto.searchRequest.class,
      responseType = server.proto.searchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<server.proto.searchRequest,
      server.proto.searchResponse> getSearchMethod() {
    io.grpc.MethodDescriptor<server.proto.searchRequest, server.proto.searchResponse> getSearchMethod;
    if ((getSearchMethod = AppServiceGrpc.getSearchMethod) == null) {
      synchronized (AppServiceGrpc.class) {
        if ((getSearchMethod = AppServiceGrpc.getSearchMethod) == null) {
          AppServiceGrpc.getSearchMethod = getSearchMethod =
              io.grpc.MethodDescriptor.<server.proto.searchRequest, server.proto.searchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "search"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.proto.searchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.proto.searchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AppServiceMethodDescriptorSupplier("search"))
              .build();
        }
      }
    }
    return getSearchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<server.proto.findClientIDRequest,
      server.proto.findClientIDResponse> getFindClientIDMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findClientID",
      requestType = server.proto.findClientIDRequest.class,
      responseType = server.proto.findClientIDResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<server.proto.findClientIDRequest,
      server.proto.findClientIDResponse> getFindClientIDMethod() {
    io.grpc.MethodDescriptor<server.proto.findClientIDRequest, server.proto.findClientIDResponse> getFindClientIDMethod;
    if ((getFindClientIDMethod = AppServiceGrpc.getFindClientIDMethod) == null) {
      synchronized (AppServiceGrpc.class) {
        if ((getFindClientIDMethod = AppServiceGrpc.getFindClientIDMethod) == null) {
          AppServiceGrpc.getFindClientIDMethod = getFindClientIDMethod =
              io.grpc.MethodDescriptor.<server.proto.findClientIDRequest, server.proto.findClientIDResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "findClientID"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.proto.findClientIDRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.proto.findClientIDResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AppServiceMethodDescriptorSupplier("findClientID"))
              .build();
        }
      }
    }
    return getFindClientIDMethod;
  }

  private static volatile io.grpc.MethodDescriptor<server.proto.reserveRequest,
      server.proto.reserveResponse> getReserveMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "reserve",
      requestType = server.proto.reserveRequest.class,
      responseType = server.proto.reserveResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<server.proto.reserveRequest,
      server.proto.reserveResponse> getReserveMethod() {
    io.grpc.MethodDescriptor<server.proto.reserveRequest, server.proto.reserveResponse> getReserveMethod;
    if ((getReserveMethod = AppServiceGrpc.getReserveMethod) == null) {
      synchronized (AppServiceGrpc.class) {
        if ((getReserveMethod = AppServiceGrpc.getReserveMethod) == null) {
          AppServiceGrpc.getReserveMethod = getReserveMethod =
              io.grpc.MethodDescriptor.<server.proto.reserveRequest, server.proto.reserveResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "reserve"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.proto.reserveRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.proto.reserveResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AppServiceMethodDescriptorSupplier("reserve"))
              .build();
        }
      }
    }
    return getReserveMethod;
  }

  private static volatile io.grpc.MethodDescriptor<server.proto.showTripsRequest,
      server.proto.showTripsResponse> getShowTripsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "showTrips",
      requestType = server.proto.showTripsRequest.class,
      responseType = server.proto.showTripsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<server.proto.showTripsRequest,
      server.proto.showTripsResponse> getShowTripsMethod() {
    io.grpc.MethodDescriptor<server.proto.showTripsRequest, server.proto.showTripsResponse> getShowTripsMethod;
    if ((getShowTripsMethod = AppServiceGrpc.getShowTripsMethod) == null) {
      synchronized (AppServiceGrpc.class) {
        if ((getShowTripsMethod = AppServiceGrpc.getShowTripsMethod) == null) {
          AppServiceGrpc.getShowTripsMethod = getShowTripsMethod =
              io.grpc.MethodDescriptor.<server.proto.showTripsRequest, server.proto.showTripsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "showTrips"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.proto.showTripsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.proto.showTripsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AppServiceMethodDescriptorSupplier("showTrips"))
              .build();
        }
      }
    }
    return getShowTripsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<server.proto.getTripIDByDestinationAndDepartureRequest,
      server.proto.getTripIDByDestinationAndDepartureResponse> getGetTripIDByDestinationAndDepartureMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getTripIDByDestinationAndDeparture",
      requestType = server.proto.getTripIDByDestinationAndDepartureRequest.class,
      responseType = server.proto.getTripIDByDestinationAndDepartureResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<server.proto.getTripIDByDestinationAndDepartureRequest,
      server.proto.getTripIDByDestinationAndDepartureResponse> getGetTripIDByDestinationAndDepartureMethod() {
    io.grpc.MethodDescriptor<server.proto.getTripIDByDestinationAndDepartureRequest, server.proto.getTripIDByDestinationAndDepartureResponse> getGetTripIDByDestinationAndDepartureMethod;
    if ((getGetTripIDByDestinationAndDepartureMethod = AppServiceGrpc.getGetTripIDByDestinationAndDepartureMethod) == null) {
      synchronized (AppServiceGrpc.class) {
        if ((getGetTripIDByDestinationAndDepartureMethod = AppServiceGrpc.getGetTripIDByDestinationAndDepartureMethod) == null) {
          AppServiceGrpc.getGetTripIDByDestinationAndDepartureMethod = getGetTripIDByDestinationAndDepartureMethod =
              io.grpc.MethodDescriptor.<server.proto.getTripIDByDestinationAndDepartureRequest, server.proto.getTripIDByDestinationAndDepartureResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getTripIDByDestinationAndDeparture"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.proto.getTripIDByDestinationAndDepartureRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.proto.getTripIDByDestinationAndDepartureResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AppServiceMethodDescriptorSupplier("getTripIDByDestinationAndDeparture"))
              .build();
        }
      }
    }
    return getGetTripIDByDestinationAndDepartureMethod;
  }

  private static volatile io.grpc.MethodDescriptor<server.proto.loginRequest,
      server.proto.loginResponse> getLoginMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "login",
      requestType = server.proto.loginRequest.class,
      responseType = server.proto.loginResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<server.proto.loginRequest,
      server.proto.loginResponse> getLoginMethod() {
    io.grpc.MethodDescriptor<server.proto.loginRequest, server.proto.loginResponse> getLoginMethod;
    if ((getLoginMethod = AppServiceGrpc.getLoginMethod) == null) {
      synchronized (AppServiceGrpc.class) {
        if ((getLoginMethod = AppServiceGrpc.getLoginMethod) == null) {
          AppServiceGrpc.getLoginMethod = getLoginMethod =
              io.grpc.MethodDescriptor.<server.proto.loginRequest, server.proto.loginResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "login"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.proto.loginRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.proto.loginResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AppServiceMethodDescriptorSupplier("login"))
              .build();
        }
      }
    }
    return getLoginMethod;
  }

  private static volatile io.grpc.MethodDescriptor<server.proto.logoutRequest,
      server.proto.logoutResponse> getLogoutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "logout",
      requestType = server.proto.logoutRequest.class,
      responseType = server.proto.logoutResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<server.proto.logoutRequest,
      server.proto.logoutResponse> getLogoutMethod() {
    io.grpc.MethodDescriptor<server.proto.logoutRequest, server.proto.logoutResponse> getLogoutMethod;
    if ((getLogoutMethod = AppServiceGrpc.getLogoutMethod) == null) {
      synchronized (AppServiceGrpc.class) {
        if ((getLogoutMethod = AppServiceGrpc.getLogoutMethod) == null) {
          AppServiceGrpc.getLogoutMethod = getLogoutMethod =
              io.grpc.MethodDescriptor.<server.proto.logoutRequest, server.proto.logoutResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "logout"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.proto.logoutRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.proto.logoutResponse.getDefaultInstance()))
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
    return new AppServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AppServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new AppServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AppServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new AppServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class AppServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void search(server.proto.searchRequest request,
        io.grpc.stub.StreamObserver<server.proto.searchResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSearchMethod(), responseObserver);
    }

    /**
     */
    public void findClientID(server.proto.findClientIDRequest request,
        io.grpc.stub.StreamObserver<server.proto.findClientIDResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getFindClientIDMethod(), responseObserver);
    }

    /**
     */
    public void reserve(server.proto.reserveRequest request,
        io.grpc.stub.StreamObserver<server.proto.reserveResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getReserveMethod(), responseObserver);
    }

    /**
     */
    public void showTrips(server.proto.showTripsRequest request,
        io.grpc.stub.StreamObserver<server.proto.showTripsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getShowTripsMethod(), responseObserver);
    }

    /**
     */
    public void getTripIDByDestinationAndDeparture(server.proto.getTripIDByDestinationAndDepartureRequest request,
        io.grpc.stub.StreamObserver<server.proto.getTripIDByDestinationAndDepartureResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetTripIDByDestinationAndDepartureMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<server.proto.loginRequest> login(
        io.grpc.stub.StreamObserver<server.proto.loginResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getLoginMethod(), responseObserver);
    }

    /**
     */
    public void logout(server.proto.logoutRequest request,
        io.grpc.stub.StreamObserver<server.proto.logoutResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLogoutMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSearchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                server.proto.searchRequest,
                server.proto.searchResponse>(
                  this, METHODID_SEARCH)))
          .addMethod(
            getFindClientIDMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                server.proto.findClientIDRequest,
                server.proto.findClientIDResponse>(
                  this, METHODID_FIND_CLIENT_ID)))
          .addMethod(
            getReserveMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                server.proto.reserveRequest,
                server.proto.reserveResponse>(
                  this, METHODID_RESERVE)))
          .addMethod(
            getShowTripsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                server.proto.showTripsRequest,
                server.proto.showTripsResponse>(
                  this, METHODID_SHOW_TRIPS)))
          .addMethod(
            getGetTripIDByDestinationAndDepartureMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                server.proto.getTripIDByDestinationAndDepartureRequest,
                server.proto.getTripIDByDestinationAndDepartureResponse>(
                  this, METHODID_GET_TRIP_IDBY_DESTINATION_AND_DEPARTURE)))
          .addMethod(
            getLoginMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                server.proto.loginRequest,
                server.proto.loginResponse>(
                  this, METHODID_LOGIN)))
          .addMethod(
            getLogoutMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                server.proto.logoutRequest,
                server.proto.logoutResponse>(
                  this, METHODID_LOGOUT)))
          .build();
    }
  }

  /**
   */
  public static final class AppServiceStub extends io.grpc.stub.AbstractStub<AppServiceStub> {
    private AppServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AppServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AppServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AppServiceStub(channel, callOptions);
    }

    /**
     */
    public void search(server.proto.searchRequest request,
        io.grpc.stub.StreamObserver<server.proto.searchResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void findClientID(server.proto.findClientIDRequest request,
        io.grpc.stub.StreamObserver<server.proto.findClientIDResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFindClientIDMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void reserve(server.proto.reserveRequest request,
        io.grpc.stub.StreamObserver<server.proto.reserveResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReserveMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void showTrips(server.proto.showTripsRequest request,
        io.grpc.stub.StreamObserver<server.proto.showTripsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getShowTripsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getTripIDByDestinationAndDeparture(server.proto.getTripIDByDestinationAndDepartureRequest request,
        io.grpc.stub.StreamObserver<server.proto.getTripIDByDestinationAndDepartureResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetTripIDByDestinationAndDepartureMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<server.proto.loginRequest> login(
        io.grpc.stub.StreamObserver<server.proto.loginResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void logout(server.proto.logoutRequest request,
        io.grpc.stub.StreamObserver<server.proto.logoutResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLogoutMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AppServiceBlockingStub extends io.grpc.stub.AbstractStub<AppServiceBlockingStub> {
    private AppServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AppServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AppServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AppServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public server.proto.searchResponse search(server.proto.searchRequest request) {
      return blockingUnaryCall(
          getChannel(), getSearchMethod(), getCallOptions(), request);
    }

    /**
     */
    public server.proto.findClientIDResponse findClientID(server.proto.findClientIDRequest request) {
      return blockingUnaryCall(
          getChannel(), getFindClientIDMethod(), getCallOptions(), request);
    }

    /**
     */
    public server.proto.reserveResponse reserve(server.proto.reserveRequest request) {
      return blockingUnaryCall(
          getChannel(), getReserveMethod(), getCallOptions(), request);
    }

    /**
     */
    public server.proto.showTripsResponse showTrips(server.proto.showTripsRequest request) {
      return blockingUnaryCall(
          getChannel(), getShowTripsMethod(), getCallOptions(), request);
    }

    /**
     */
    public server.proto.getTripIDByDestinationAndDepartureResponse getTripIDByDestinationAndDeparture(server.proto.getTripIDByDestinationAndDepartureRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetTripIDByDestinationAndDepartureMethod(), getCallOptions(), request);
    }

    /**
     */
    public server.proto.logoutResponse logout(server.proto.logoutRequest request) {
      return blockingUnaryCall(
          getChannel(), getLogoutMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AppServiceFutureStub extends io.grpc.stub.AbstractStub<AppServiceFutureStub> {
    private AppServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AppServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AppServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AppServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<server.proto.searchResponse> search(
        server.proto.searchRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<server.proto.findClientIDResponse> findClientID(
        server.proto.findClientIDRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getFindClientIDMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<server.proto.reserveResponse> reserve(
        server.proto.reserveRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getReserveMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<server.proto.showTripsResponse> showTrips(
        server.proto.showTripsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getShowTripsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<server.proto.getTripIDByDestinationAndDepartureResponse> getTripIDByDestinationAndDeparture(
        server.proto.getTripIDByDestinationAndDepartureRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetTripIDByDestinationAndDepartureMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<server.proto.logoutResponse> logout(
        server.proto.logoutRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLogoutMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEARCH = 0;
  private static final int METHODID_FIND_CLIENT_ID = 1;
  private static final int METHODID_RESERVE = 2;
  private static final int METHODID_SHOW_TRIPS = 3;
  private static final int METHODID_GET_TRIP_IDBY_DESTINATION_AND_DEPARTURE = 4;
  private static final int METHODID_LOGOUT = 5;
  private static final int METHODID_LOGIN = 6;

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
          serviceImpl.search((server.proto.searchRequest) request,
              (io.grpc.stub.StreamObserver<server.proto.searchResponse>) responseObserver);
          break;
        case METHODID_FIND_CLIENT_ID:
          serviceImpl.findClientID((server.proto.findClientIDRequest) request,
              (io.grpc.stub.StreamObserver<server.proto.findClientIDResponse>) responseObserver);
          break;
        case METHODID_RESERVE:
          serviceImpl.reserve((server.proto.reserveRequest) request,
              (io.grpc.stub.StreamObserver<server.proto.reserveResponse>) responseObserver);
          break;
        case METHODID_SHOW_TRIPS:
          serviceImpl.showTrips((server.proto.showTripsRequest) request,
              (io.grpc.stub.StreamObserver<server.proto.showTripsResponse>) responseObserver);
          break;
        case METHODID_GET_TRIP_IDBY_DESTINATION_AND_DEPARTURE:
          serviceImpl.getTripIDByDestinationAndDeparture((server.proto.getTripIDByDestinationAndDepartureRequest) request,
              (io.grpc.stub.StreamObserver<server.proto.getTripIDByDestinationAndDepartureResponse>) responseObserver);
          break;
        case METHODID_LOGOUT:
          serviceImpl.logout((server.proto.logoutRequest) request,
              (io.grpc.stub.StreamObserver<server.proto.logoutResponse>) responseObserver);
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
        case METHODID_LOGIN:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.login(
              (io.grpc.stub.StreamObserver<server.proto.loginResponse>) responseObserver);
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
      return server.proto.App.getDescriptor();
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
              .addMethod(getFindClientIDMethod())
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
