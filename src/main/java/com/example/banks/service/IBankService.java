package com.example.banks.service;

import com.example.banks.dto.BankRecord;

import java.util.List;

public interface IBankService {
    BankRecord getById(Long id);
    List<BankRecord> getAll();
    BankRecord create(BankRecord bankRecord);
}
