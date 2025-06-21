package com.example.challenge_banks.infra.adapter.out.config;

import com.example.challenge_banks.domain.model.Bank;
import com.example.challenge_banks.infra.adapter.out.repository.BankRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Bean
    CommandLineRunner initDatabase(BankRepository bankRepository) {
        return args -> {
            log.info("Preloading " + bankRepository.save(new Bank(null,"Santander","Banco internacional Santander")));
            log.info("Preloading " + bankRepository.save(new Bank(null,"Galicia","Banco internacional Galicia")));
            log.info("Preloading " + bankRepository.save(new Bank(null,"Comafi","Banco internacional Comafi")));
        };
    }
}
