package com.example.banks.repository;

import com.example.banks.repository.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, Long> {
    public Optional<Bank> findByName(String name);
}
