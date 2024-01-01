package com.example.camelmicroservices.routes;

import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.crypto.CryptoDataFormat;
import org.springframework.stereotype.Component;


@Component
public class ActivMqSenderRouter extends RouteBuilder {

  @Override
  public void configure() throws Exception {
//    from("activemq:split-queue")
//        .to("log:received-from-activemq-split-queue");

    from("timer:activemq-timer?period={{time-period}}")
        .transform().constant("Active ENCRYPTED msg!")
        .marshal(createEncryptor())
        .log("after encryption: ${body}")
        .to("activemq:my-activemq-json-queue");
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
