package com.example.challenge_banks.infra.adapter.in.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record BankDTO(Long id,
                      @NotBlank(message = "Name cannot be blank") String name,
                      String description) {
}
