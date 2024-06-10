package com.eduardordguez.grpcservice;

import com.eduardordguez.proto.GreetRequest;
import com.eduardordguez.proto.GreetResponse;
import io.grpc.internal.testing.StreamRecorder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class GreeterServiceTest {

  @InjectMocks
  private GreeterService greeterService;

  @Test
  void greet() {
    GreetRequest greetRequest = getGreetRequest();
    GreetResponse greetResponse = getGreetResponse(greetRequest.getName());

    StreamRecorder<GreetResponse> responseObserver = StreamRecorder.create();

    greeterService.greet(greetRequest, responseObserver);
    List<GreetResponse> greetResponses = responseObserver.getValues();

    assertNotNull(greetResponses);
    assertEquals(greetResponse, greetResponses.get(0));
  }

  private GreetRequest getGreetRequest() {
    return GreetRequest.newBuilder()
        .setName("gRPC")
        .build();
  }

  private GreetResponse getGreetResponse(String name) {
    return GreetResponse.newBuilder()
        .setMessage("Hello " + name)
        .build();
  }

}