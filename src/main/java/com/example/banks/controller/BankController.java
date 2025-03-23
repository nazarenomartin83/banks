package com.example.banks.controller;

import com.example.banks.dto.BankRecord;
import com.example.banks.service.BankService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banks")
@AllArgsConstructor
public class BankController {
    private final BankService bankService;
    @GetMapping("/{id}")
    public ResponseEntity<BankRecord> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bankService.getById(id));
        //return ResponseEntity.ok(new BankRecord(72L,"Santander","Banco Santander"));
    }
    @GetMapping("/")
    public ResponseEntity<List<BankRecord>> getAll() {
        return ResponseEntity.ok(bankService.getAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BankRecord create(@RequestBody BankRecord bankRecord)
    {
        return bankService.create(bankRecord);
    }

    /*@GetMapping
    public List<CompanyDTO> getAll() {
        List<Company> companies = companyService.getAll();
        return companies
                .stream()
                .map(companyDtoMapper::entityToDto)
                .toList();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyDTO create(@RequestBody CompanyDTO companyDTO)
    {
        Company company = companyDtoMapper.dtoToEntity(companyDTO);
        return companyDtoMapper.entityToDto(companyService.create(company));
    }
    @PutMapping
    public  CompanyDTO update(@RequestBody CompanyDTO companyDTO)
    {
        Company company = companyDtoMapper.dtoToEntity(companyDTO);
        return companyDtoMapper.entityToDto(companyService.update(company));
    }

    @DeleteMapping("/{id}")
    public CompanyDTO delete(@PathVariable Long id)
    {
        return companyDtoMapper.entityToDto(companyService.delete(id));
    }*/
}
