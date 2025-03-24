package com.example.banks.controller;

import com.example.banks.dto.BankRecord;
import com.example.banks.service.BankService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banks/")
@AllArgsConstructor
public class BankController {
    private final BankService bankService;

    @GetMapping("{id}")
    public ResponseEntity<BankRecord> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bankService.getById(id));
    }
    @GetMapping
    public ResponseEntity<List<BankRecord>> getAll() {
        return ResponseEntity.ok(bankService.getAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BankRecord> create(@RequestBody BankRecord bankRecord)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(bankService.create(bankRecord));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        bankService.delete(id);
        return ResponseEntity.noContent().build();
    }
    /*
    @PutMapping
    public  CompanyDTO update(@RequestBody CompanyDTO companyDTO)
    {
        Company company = companyDtoMapper.dtoToEntity(companyDTO);
        return companyDtoMapper.entityToDto(companyService.update(company));
    }*/
}
