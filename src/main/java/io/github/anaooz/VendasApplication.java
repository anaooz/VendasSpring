package io.github.anaooz;

import io.github.anaooz.domain.entity.Cliente;
import io.github.anaooz.domain.entity.Pedido;
import io.github.anaooz.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VendasApplication {

//    @Value("${application.name}")
//    private String applicationName;

//    @GetMapping("/hello")
//    public String helloWorld() {
//        return applicationName;
//    }

//    @Bean
//    public CommandLineRunner commandLineRunner(@Autowired Clientes clientes) {
//        return args -> {
//            Cliente c = new Cliente(null, "Mateus", null);
//            clientes.save(c);
//        };
//    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
