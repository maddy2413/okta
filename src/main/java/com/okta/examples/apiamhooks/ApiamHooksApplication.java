package com.okta.examples.apiamhooks;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiamHooksApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiamHooksApplication.class, args);
    }
    
    
    @Bean
    public ServletWebServerFactory servletContainer() {
      TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
        @Override
        protected void postProcessContext(Context context) {
          var securityConstraint = new SecurityConstraint();
          securityConstraint.setUserConstraint("CONFIDENTIAL");
          var collection = new SecurityCollection();
          collection.addPattern("/*");
          securityConstraint.addCollection(collection);
          context.addConstraint(securityConstraint);
        }
      };
      tomcat.addAdditionalTomcatConnectors(getHttpConnector());
      return tomcat;
    }

    private Connector getHttpConnector() {
      var connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
      connector.setScheme("http");
      connector.setPort(8080);
      connector.setSecure(false);
      connector.setRedirectPort(8443);
      return connector;
    }

  
}
