package com.example.challenge_banks.infra.adapter.in.controller;

import com.example.challenge_banks.domain.model.Bank;
import com.example.challenge_banks.domain.port.in.action.IActionBank;
import com.example.challenge_banks.infra.adapter.in.controller.dto.BankDTO;
import com.example.challenge_banks.infra.adapter.in.controller.mapper.BankMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

class BankControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IActionBank actionBank; // Mock de la interfaz de acción del dominio

    @Mock
    private BankMapper bankMapper; // Mock del mapper

    @InjectMocks
    private BankController bankController; // Inyecta los mocks en el controlador

    @BeforeEach
    void setUp() {
        // Initializes all mocks annotated in this class
        MockitoAnnotations.openMocks(this);
        // Sets up MockMvc to test the BankController in isolation
        mockMvc = MockMvcBuilders.standaloneSetup(bankController).build();
    }

    @Test
    @DisplayName("Should return a list of BankDTOs and HTTP 200 OK when calling getAll")
    void getAll_shouldReturnListOfBankDTOsAndHttpOk() throws Exception {
        // Given: Prepare the data and mock behavior
        // Create some sample Bank domain objects
        Bank bank1 = new Bank(1L, "Bank A", "123 Main St");
        Bank bank2 = new Bank(2L, "Bank B", "456 Oak Ave");
        List<Bank> banks = Arrays.asList(bank1, bank2);

        // Create the corresponding BankDTO objects that the mapper would produce
        BankDTO bankDTO1 = new BankDTO(1L, "Bank A", "123 Main St");
        BankDTO bankDTO2 = new BankDTO(2L, "Bank B", "456 Oak Ave");
        List<BankDTO> bankDTOs = Arrays.asList(bankDTO1, bankDTO2);

        // Define the behavior of the mocked dependencies
        // When actionBank.getAllBanks() is called, return our list of Bank domain objects
        when(actionBank.getAllBanks()).thenReturn(banks);
        // When bankMapper.map(List<Bank>) is called with our list of Bank objects, return our list of BankDTOs
        when(bankMapper.map(banks)).thenReturn(bankDTOs);

        // When: Perform the HTTP GET request to the /banks/ endpoint
        mockMvc.perform(get("/banks/")) // The @GetMapping has no specific path, so it maps to /banks/
                // Then: Assert the response
                .andExpect(status().isOk()) // Verify HTTP 200 OK status
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Verify content type is JSON
                .andExpect(jsonPath("$", hasSize(2))) // Verify the JSON array has 2 elements
                .andExpect(jsonPath("$[0].id", is(1))) // Verify the id of the first element
                .andExpect(jsonPath("$[0].name", is("Bank A"))) // Verify the name of the first element
                .andExpect(jsonPath("$[1].id", is(2))) // Verify the id of the second element
                .andExpect(jsonPath("$[1].name", is("Bank B"))); // Verify the name of the second element

        // Verify that the mocked methods were called as expected
        verify(actionBank, times(1)).getAllBanks(); // Ensure actionBank.getAllBanks() was called exactly once
        verify(bankMapper, times(1)).map(banks); // Ensure bankMapper.map() was called exactly once with the expected argument
    }

    @Test
    @DisplayName("Should return empty list and HTTP 200 OK when no banks exist")
    void getAll_shouldReturnEmptyListAndHttpOk_whenNoBanksExist() throws Exception {
        // Given: No banks exist, so actionBank.getAllBanks() returns an empty list
        List<Bank> emptyBanks = Arrays.asList();
        List<BankDTO> emptyBankDTOs = Arrays.asList();

        when(actionBank.getAllBanks()).thenReturn(emptyBanks);
        when(bankMapper.map(emptyBanks)).thenReturn(emptyBankDTOs);

        // When: Perform the HTTP GET request
        mockMvc.perform( get("/banks/"))
                // Then: Assert the response
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0))); // Verify the JSON array is empty

        // Verify that the mocked methods were called
        verify(actionBank, times(1)).getAllBanks();
        verify(bankMapper, times(1)).map(emptyBanks);
    }

    @Test
    void getAll() {
    }

    @Test
    void getBank() {
    }

    @Test
    void create() {
    }

    @Test
    void delete() {
    }

    @Test
    void callSelf() {
    }
}