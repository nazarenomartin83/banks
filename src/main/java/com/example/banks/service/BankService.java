package com.example.banks.service;

import com.example.banks.Exception.BankAlreadyExistsException;
import com.example.banks.Exception.BankNotFoundException;
import com.example.banks.dto.BankRecord;
import com.example.banks.mapper.BankMapper;
import com.example.banks.repository.BankRepository;
import com.example.banks.repository.entity.Bank;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BankService implements IBankService {
    private final BankRepository bankRepository;

    @Override
    public BankRecord getById(Long id) {
        Optional<Bank> bank = bankRepository.findById(id);
        return bank.map(BankMapper::EntityToDTO)
                .orElseThrow(() -> new BankNotFoundException("Bank with id " + id + " not found"));
    }
    @Override
    public List<BankRecord> getAll() {
        List<Bank> bank = bankRepository.findAll();
        return bank.stream().map(BankMapper::EntityToDTO).toList();
    }
    @Override
    public BankRecord create(BankRecord bankRecord) {
        Optional<Bank> bank = bankRepository.findByName(bankRecord.name());
        if (bank.isPresent())
            throw new BankAlreadyExistsException("Bank with name "+bankRecord.name()+" already exits.");
        return BankMapper.EntityToDTO(bankRepository.save(BankMapper.DTOToEntity(bankRecord)));
    }

}
