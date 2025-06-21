package com.example.challenge_banks.infra.adapter.out.repository;

import com.example.challenge_banks.domain.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank,Long> {
}
