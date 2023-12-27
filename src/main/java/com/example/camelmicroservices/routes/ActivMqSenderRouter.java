package com.example.camelmicroservices.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class ActivMqSenderRouter extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    //timer
    from("timer:activemq-timer?period=10000")
        .transform().constant("My Msg for for Activemq")
        .to("activemq:my-activemq-queue");
    //queue
  }
}
