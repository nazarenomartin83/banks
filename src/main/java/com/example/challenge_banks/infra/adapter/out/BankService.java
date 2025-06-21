package com.example.challenge_banks.infra.adapter.out;

import com.example.challenge_banks.domain.model.Bank;
import com.example.challenge_banks.domain.port.out.BankPort;
import com.example.challenge_banks.infra.adapter.out.repository.BankRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BankService implements BankPort {
    private final BankRepository bankRepository;

    @Override
    public Bank create(Bank entity) {
        return bankRepository.save(entity);
    }

    @Override
    public Optional<Bank> getById(Long id) {
        return bankRepository.findById(id);
    }

    @Override
    public Optional<Bank> getByPK(String pk) {
        return Optional.empty();
    }

    @Override
    public List<Bank> getAll() {
        return bankRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        bankRepository.deleteById(id);
    }

    @Override
    public Bank update(Bank entity) {
        return null;
    }
}
