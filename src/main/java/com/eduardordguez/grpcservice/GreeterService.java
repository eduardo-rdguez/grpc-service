package com.eduardordguez.grpcservice;

import com.eduardordguez.proto.GreetRequest;
import com.eduardordguez.proto.GreetResponse;
import com.eduardordguez.proto.GreeterServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GreeterService extends GreeterServiceGrpc.GreeterServiceImplBase {

  @Override
  public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {
    String message = "Hello " + request.getName();
    GreetResponse response = GreetResponse.newBuilder().setMessage(message).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

}
