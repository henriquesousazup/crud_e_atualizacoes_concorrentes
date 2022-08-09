package com.example.hmr;

import com.example.hmr.model.Quarto;
import com.example.hmr.model.TipoQuarto;
import com.example.hmr.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class HmrApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(HmrApplication.class, args);
    }

    @Autowired
    QuartoRepository quartoRepository;

    @Override
    public void run(String... args) throws Exception {

        Quarto quarto = new Quarto("Espaçoso", new BigDecimal(60), TipoQuarto.CASAL, false);
        quartoRepository.save(quarto);

    }
}
