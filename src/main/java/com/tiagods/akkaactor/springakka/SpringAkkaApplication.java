package com.tiagods.akkaactor.springakka;

import com.tiagods.akkaactor.springakka.services.AkkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAkkaApplication implements CommandLineRunner {

    @Autowired
    AkkaService service;

    public static void main(String[] args) {
        SpringApplication.run(SpringAkkaApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        //service.envioSimples();
        service.envioComRotas();
    }
}
