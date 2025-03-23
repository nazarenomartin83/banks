package com.example.banks.mapper;

import com.example.banks.dto.BankRecord;
import com.example.banks.repository.entity.Bank;

public class BankMapper {
    public static BankRecord EntityToDTO(Bank bank)
    {
        return new BankRecord(bank.getId(),bank.getName(),bank.getDescription());
    }
    public static Bank DTOToEntity(BankRecord bankRecord)
    {
        return Bank.builder()
                .id(bankRecord.id())
                .name(bankRecord.name())
                .description(bankRecord.description()).build();
    }
}
