package com.example.camelmicroservices.routes;

import java.time.LocalDateTime;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class MyFirstTimerRoute extends RouteBuilder {

  @Autowired
  private GetCurrentTimeBean getCurrentTimeBean;
  @Autowired
  private SimpleLoggingComponentBean simpleLoggingComponentBean;
  @Autowired
  private SimpleLoggingProcessor simpleLoggingProcessor;

  @Override
  public void configure() {
    from("timer:first-timer")
//         .transform().constant("Constant Message To Test")
        .bean(simpleLoggingComponentBean)
        .bean(getCurrentTimeBean)
        .bean(simpleLoggingComponentBean)
        .process(simpleLoggingProcessor)
        .to("log:first-timer");
  }

  @Component
  static
  public class GetCurrentTimeBean {
    public String getCurrentTime() {
      return "Time now is: " + LocalDateTime.now();
    }
  }

  @Component
  static
  public class SimpleLoggingComponentBean {
    Logger logger = LoggerFactory.getLogger(SimpleLoggingComponentBean.class);
    public void process(String msg) {
        logger.info("SimpleLoggingBean: {}", msg);
    }
  }

  @Component
  static
  public class SimpleLoggingProcessor implements Processor{
    Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessor.class);
    @Override
    public void process(Exchange exchange) throws Exception {
      logger.info("Processor [Logger] = {}", exchange.getIn().getBody());
    }
  }
}
