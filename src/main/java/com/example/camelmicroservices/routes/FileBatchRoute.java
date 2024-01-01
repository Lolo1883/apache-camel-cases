package com.example.camelmicroservices.routes;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class FileBatchRoute extends RouteBuilder {

  @Autowired
  private DeciderBean deciderBean;

  @Override
  public void configure() throws Exception {
    from("file:files/input")
        .routeId("File-Batch-Route")
        .transform().body(String.class)
        .choice()
            .when(method(deciderBean))
              .log("Decider bean visited")
            .when(simple("${file:ext} ends with 'xml'"))
              .log("XML file")
            .otherwise()
              .log("UNKNOWN FILE extension")
        .end()
        .log("${body}")
        .to("file:files/output");
  }


  @Component
  @Slf4j
  class DeciderBean {
      public boolean isThisConditionMet(String body){
        log.info(body);
        return true;
      }
  }
}
