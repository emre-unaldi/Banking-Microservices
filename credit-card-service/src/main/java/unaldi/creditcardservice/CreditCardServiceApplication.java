package unaldi.creditcardservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableCaching
public class CreditCardServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditCardServiceApplication.class, args);
    }

}
