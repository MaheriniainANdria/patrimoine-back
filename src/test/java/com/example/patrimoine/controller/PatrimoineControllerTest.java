package com.example.patrimoine.controller;

import com.example.patrimoine.model.Patrimoine;
import com.example.patrimoine.service.PatrimoineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatrimoineControllerTest {

    private PatrimoineService patrimoineService;
    private PatrimoineController patrimoineController;

    @BeforeEach
    void setUp() {
        patrimoineService = Mockito.mock(PatrimoineService.class);
        patrimoineController = new PatrimoineController(patrimoineService);
    }

    @Test
    void testCreateOrUpdatePatrimoine() throws Exception {
        Patrimoine patrimoine = new Patrimoine("Tsota", null);
        when(patrimoineService.saveOrUpdatePatrimoine(anyString(), any(Patrimoine.class))).thenReturn(patrimoine);

        ResponseEntity<Patrimoine> response = patrimoineController.createOrUpdatePatrimoine("1", patrimoine);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Tsota", response.getBody().getPossesseur());
    }

    @Test
    void testGetPatrimoineFound() throws Exception {
        Patrimoine patrimoine = new Patrimoine("Tsota", null);
        when(patrimoineService.getPatrimoine("1")).thenReturn(Optional.of(patrimoine));

        ResponseEntity<Patrimoine> response = patrimoineController.getPatrimoine("1");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Tsota", response.getBody().getPossesseur());
    }

    @Test
    void testGetPatrimoineNotFound() throws Exception {
        when(patrimoineService.getPatrimoine("1")).thenReturn(Optional.empty());

        ResponseEntity<Patrimoine> response = patrimoineController.getPatrimoine("1");

        assertEquals(404, response.getStatusCodeValue());
    }
}
