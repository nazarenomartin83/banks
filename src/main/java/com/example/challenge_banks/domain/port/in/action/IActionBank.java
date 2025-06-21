package com.example.challenge_banks.domain.port.in.action;

import com.example.challenge_banks.domain.model.Bank;

import java.util.List;
import java.util.Optional;

public interface IActionBank {
    public Bank createBank(Bank bank); //throws BankAlreadyExistsException;
    public Bank getBankById(long id);
    public List<Bank> getAllBanks();
    void deleteBank(long id);
    //  BankDTO updateBank(String id, BankDTO bankDTO) throws BankNotFoundException;
}
