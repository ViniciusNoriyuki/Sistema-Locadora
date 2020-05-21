package br.com.locadora.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class locadoraGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(locadoraGatewayApplication.class, args);
    }

}