package com.example.camelmicroservices.patterns;

import com.example.camelmicroservices.beans.DynamicRouterBean;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


//@Component
public class EipPatternsRouter extends RouteBuilder {


  @Autowired
  private DynamicRouterBean dynamicRouterBean;

  @Override
  public void configure() throws Exception {


    getContext().setTracing(true);
    errorHandler(deadLetterChannel("activemq:my-activemq-deadletter-queue"));

//    from("timer:multicast-timer?period=10000")
//        .multicast()
//        .to("log:logger1", "log:logger2");

//    from("file:files/input")
//        .convertBodyTo(String.class)
//        .split(body(), ",")
//        .to("activemq:split-queue");


//    from("file:files/aggregate-json")
//        .unmarshal().json(JsonLibrary.Jackson, EmployeeData.class)
//        .aggregate(simple("${body.name}"), new ArrayListAggregationStrategy())
//        .completionSize(3)
//        .to("log:aggregate-json");

    String routingSlip = "direct:endpoint1, direct:endpoint2, direct:endpoint3";




    //routing slip pattern
//    from("timer:routing-slip-timer?period=10000")
//        .transform().constant("Msg to both guys please 1 and 2")
//        .routingSlip(simple(routingSlip));

    from("timer:routing-slip-timer?period={{time-period}}")
        .transform().constant("{{message-to-all-channels}}")
        .dynamicRouter(method(dynamicRouterBean));

    from("direct:endpoint1")
        .wireTap("log:wire-tap")
        .to("{{endpoint-for-login}}");


    from("direct:endpoint2")
        .to("log:directendpoint2");

    from("direct:endpoint3")
        .to("log:directendpoint3");

  }
}
