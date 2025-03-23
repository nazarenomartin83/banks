package com.example.banks.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BankRecord(
        Long id,
        @Schema(description = "The name of Bank", example = "Santander")
        @NotBlank(message = "Name is required")
        @Size(max = 50, message = "The name cannot exceed 50 characters")
        String name,
        String description
) {}
