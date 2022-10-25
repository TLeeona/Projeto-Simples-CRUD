package br.com.simplescrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages = "br.com.simplescrud")
public class SimplesCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimplesCrudApplication.class, args);
    }

}
