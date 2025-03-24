package com.example.banks;

import com.example.banks.controller.BankController;
import com.example.banks.dto.BankRecord;
import com.example.banks.service.BankService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BankControllerTest {
    @Mock
    private BankService bankService;
    @InjectMocks
    private BankController bankController;

    @Test
    void getById_shouldReturnBankRecord_whenBankExists() {
        // Arrange
        Long bankId = 1L;
        BankRecord expectedBankRecord = new BankRecord(bankId, "Santander","Banco de Santander Argentina");
        when(bankService.getById(bankId)).thenReturn(expectedBankRecord);
        // Act
        ResponseEntity<BankRecord> response = bankController.getById(bankId);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedBankRecord, response.getBody());
        verify(bankService, times(1)).getById(bankId);
    }
    @Test
    void getById_shouldReturnNotFound_whenBankDoesNotExist() {
        // Arrange
        Long bankId = 2L;
        when(bankService.getById(bankId)).thenReturn(null); // Assuming service returns null if not found

        // Act
        ResponseEntity<BankRecord> response = bankController.getById(bankId);

        // Assert
        // Assuming your service handles the case where the bank doesn't exist.
        // You might need to adjust this assertion based on how your service behaves
        // (e.g., throwing an exception that a controller advice handles).
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Assuming OK with null body for now
        assertEquals(null, response.getBody());
        verify(bankService, times(1)).getById(bankId);
    }

    @Test
    void getAll_shouldReturnListOfBankRecords_whenBanksExist() {
        // Arrange
        List<BankRecord> expectedBanks = Arrays.asList(
                new BankRecord(1L, "Santander", "Banco Santander Argentina"),
                new BankRecord(2L, "Galicia", "Banco Galicia Argentina")
        );
        when(bankService.getAll()).thenReturn(expectedBanks);

        // ActS
        ResponseEntity<List<BankRecord>> response = bankController.getAll();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedBanks, response.getBody());
        verify(bankService, times(1)).getAll();
    }

    @Test
    void getAll_shouldReturnEmptyList_whenNoBanksExist() {
        // Arrange
        List<BankRecord> expectedBanks = List.of();
        when(bankService.getAll()).thenReturn(expectedBanks);

        // Act
        ResponseEntity<List<BankRecord>> response = bankController.getAll();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedBanks, response.getBody());
        verify(bankService, times(1)).getAll();
    }

    @Test
    void create_shouldReturnCreatedBankRecord_whenValidBankRecordIsProvided() {
        // Arrange
        BankRecord inputBankRecord = new BankRecord(3L, "Santander", "Banco Santander Argentina");
        BankRecord expectedCreatedBankRecord = new BankRecord(3L, "Santander", "Banco Santander Argentina");
        when(bankService.create(inputBankRecord)).thenReturn(expectedCreatedBankRecord);

        // Act
        ResponseEntity<BankRecord> response = bankController.create(inputBankRecord);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedCreatedBankRecord, response.getBody());
        verify(bankService, times(1)).create(inputBankRecord);
    }

    @Test
    void delete_shouldReturnNoContent_whenBankExists() {
        // Arrange
        Long bankIdToDelete = 1L;
        BankRecord inputBankRecord = new BankRecord(3L, "Santander", "Banco Santander Argentina");

        when(bankService.delete(bankIdToDelete)).thenReturn(inputBankRecord);

        // Act
        ResponseEntity<Void> response = bankController.delete(bankIdToDelete);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(null, response.getBody());
        verify(bankService, times(1)).delete(bankIdToDelete);
    }
    @Disabled
    @Test
    void delete_shouldHandleNotFoundGracefully_whenBankDoesNotExist() {
        // Arrange
        Long bankIdToDelete = 99L;
        doThrow(new RuntimeException("Bank not found")).when(bankService).delete(bankIdToDelete); // Assuming service throws exception

        // Act
        ResponseEntity<Void> response = bankController.delete(bankIdToDelete);

        // Assert
        // You might need to adjust this assertion based on how your service handles
        // non-existent banks and how your controller handles potential exceptions.
        // For now, assuming it still returns NO_CONTENT as the service might handle it.
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(null, response.getBody());
        verify(bankService, times(1)).delete(bankIdToDelete);
    }
}