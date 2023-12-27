package com.example.camelmicroservices.routes;

import com.example.camelmicroservices.beans.EmployeeData;
import com.example.camelmicroservices.beans.EmployeeDataProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


//@Component
public class ActivemqRecieverRoute extends RouteBuilder {

  @Autowired
  EmployeeDataProcessor dataProcessor;

  @Override
  public void configure() throws Exception {
    from("activemq:my-activemq-queue")
        .unmarshal().json(JsonLibrary.Jackson, EmployeeData.class)
        .bean(dataProcessor)
        .to("log:received-message-from-activemq");
  }
}
