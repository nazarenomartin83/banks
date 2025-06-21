package com.example.challenge_banks.infra.adapter.in.controller;

import com.example.challenge_banks.domain.port.in.action.IActionBank;
import com.example.challenge_banks.infra.adapter.in.controller.dto.BankDTO;
import com.example.challenge_banks.infra.adapter.in.controller.mapper.BankMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("/banks/")
@AllArgsConstructor
public class BankController {
    private final IActionBank actionBank;
    private final BankMapper bankMapper;
    @GetMapping
    public ResponseEntity<List<BankDTO>> getAll() {
        List<BankDTO> listBankDTO = bankMapper.map(actionBank.getAllBanks());
        return ResponseEntity.status(HttpStatus.OK).body(listBankDTO);
    }
    @GetMapping("{id}")
    public ResponseEntity<BankDTO> getBank(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bankMapper.bankToBankDTO(actionBank.getBankById(id)));
/*        Optional<BankDTO> bankDTOOptional = listBanks.stream().filter(bank -> bank.id()==id).findFirst();
        return bankDTOOptional.map(ResponseEntity::ok)
                .orElseThrow(() -> new BankNotFoundException(id));*/
    }
    @PostMapping
    public ResponseEntity<BankDTO> create(@RequestBody @Validated BankDTO bankDTO) {
        /*
        if (listBanks.stream().anyMatch(bank -> bank.id().equals(bankDTO.id())))
            throw new BankAlreadyExistsException(bankDTO.id());
        this.listBanks.add(bankDTO);*/
        return new ResponseEntity<BankDTO>
                (bankMapper.bankToBankDTO(actionBank.createBank(bankMapper.bankDTOToBank(bankDTO))),
                        HttpStatus.CREATED);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        actionBank.deleteBank(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("self")
    public ResponseEntity callSelf() {
        RestClient restClient = RestClient.create("http://localhost:8080/banks/");
        return ResponseEntity.status(HttpStatus.OK).body(restClient.get()
                .uri("")
                .retrieve()
                .toEntity(List.class)
                .getBody());
    }
}
