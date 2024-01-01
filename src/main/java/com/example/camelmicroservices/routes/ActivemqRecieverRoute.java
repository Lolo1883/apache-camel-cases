package com.example.camelmicroservices.routes;

import com.example.camelmicroservices.beans.EmployeeData;
import com.example.camelmicroservices.beans.EmployeeDataProcessor;
import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.crypto.CryptoDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ActivemqRecieverRoute extends RouteBuilder {

  @Autowired
  EmployeeDataProcessor dataProcessor;

  @Override
  public void configure() throws Exception {
//    from("activemq:my-activemq-deadletter-queue")
//        .unmarshal().json(JsonLibrary.Jackson, EmployeeData.class)
//        .bean(dataProcessor)
//        .to("log:received-message-from-activemq");

    from("activemq:my-activemq-json-queue")
        .routeId("receiver-router")
        .unmarshal(createEncryptor())
        .to("log:Activemq-receiver-path-log");
  }

  private CryptoDataFormat createEncryptor() throws KeyStoreException, IOException, NoSuchAlgorithmException,
      CertificateException, UnrecoverableKeyException {
    KeyStore keyStore = KeyStore.getInstance("JCEKS");
    ClassLoader classLoader = getClass().getClassLoader();
    keyStore.load(classLoader.getResourceAsStream("myDesKey.jceks"), "someKeystorePassword".toCharArray());
    Key sharedKey = keyStore.getKey("myDesKey", "someKeyPassword".toCharArray());

    return new CryptoDataFormat("DES", sharedKey);
  }
}
