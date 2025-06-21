package com.example.challenge_banks.infra.adapter.in.controller.mapper;

import com.example.challenge_banks.domain.model.Bank;
import com.example.challenge_banks.infra.adapter.in.controller.dto.BankDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-21T16:49:08-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Ubuntu)"
)
@Component
public class BankMapperImpl implements BankMapper {

    @Override
    public BankDTO bankToBankDTO(Bank bank) {
        if ( bank == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;

        id = bank.getId();
        name = bank.getName();
        description = bank.getDescription();

        BankDTO bankDTO = new BankDTO( id, name, description );

        return bankDTO;
    }

    @Override
    public Bank bankDTOToBank(BankDTO bank) {
        if ( bank == null ) {
            return null;
        }

        Bank.BankBuilder bank1 = Bank.builder();

        bank1.id( bank.id() );
        bank1.name( bank.name() );
        bank1.description( bank.description() );

        return bank1.build();
    }

    @Override
    public List<BankDTO> map(List<Bank> bank) {
        if ( bank == null ) {
            return null;
        }

        List<BankDTO> list = new ArrayList<BankDTO>( bank.size() );
        for ( Bank bank1 : bank ) {
            list.add( bankToBankDTO( bank1 ) );
        }

        return list;
    }
}
