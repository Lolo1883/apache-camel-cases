package com.example.camelmicroservices.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
//
//@Component
public class RestApiTriggerRouter extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    restConfiguration().host("127.0.0.1").port(8080);

    from("timer:rest-api-consumer?period=10000")
        .routeId("Rest-API-Caller-Route")
        .setHeader("name", () -> "A")
        .log("${body}")
        .to("rest:get:/employee/data/{name}")
        .log("${body}");
  }
}
