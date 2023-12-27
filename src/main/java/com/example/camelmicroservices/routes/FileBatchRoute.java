package com.example.camelmicroservices.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class FileBatchRoute extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    from("file:files/input")
        .log("${body}")
        .to("activemq:my-activemq-queue");;
  }
}
