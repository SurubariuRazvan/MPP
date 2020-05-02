package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class StartRmiServer {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("classpath:spring-server.xml");
        SpringApplication.run(StartRmiServer.class, args);
    }
}
