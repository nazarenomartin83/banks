package com.example.challenge_banks.infra.adapter.in.controller.mapper;

import com.example.challenge_banks.domain.model.Bank;
import com.example.challenge_banks.infra.adapter.in.controller.dto.BankDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BankMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    BankDTO bankToBankDTO(Bank bank);
    Bank bankDTOToBank(BankDTO bank);
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    List<BankDTO> map(List<Bank> bank);
}
