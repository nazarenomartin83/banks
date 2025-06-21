package com.example.challenge_banks.domain.port.in.action.imp;

import com.example.challenge_banks.domain.port.in.action.IActionBank;
import com.example.challenge_banks.domain.model.Bank;
import com.example.challenge_banks.domain.port.out.BankPort;
import com.example.challenge_banks.domain.exception.BankNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ActionBank implements IActionBank {
    private final BankPort bankPort;

    @Override
    public Bank createBank(Bank bank) {
        return bankPort.create(bank);
    }
    @Override
    public Bank getBankById(long id) {
        return bankPort.getById(id).orElseThrow(() -> new BankNotFoundException(id));
    }
    @Override
    public List<Bank> getAllBanks() {
        return bankPort.getAll();
    }

    @Override
    public void deleteBank(long id) {
        bankPort.delete(this.getBankById(id).getId());
    }
}
