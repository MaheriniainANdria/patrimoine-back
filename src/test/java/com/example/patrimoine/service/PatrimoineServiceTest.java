package com.example.patrimoine.service;

import com.example.patrimoine.model.Patrimoine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatrimoineServiceTest {

    private PatrimoineService patrimoineService;

    @BeforeEach
    void setUp() {
        patrimoineService = new PatrimoineService();
    }

    @Test
    void testSaveOrUpdatePatrimoine() throws IOException {
        Patrimoine patrimoine = new Patrimoine("Tsota", LocalDateTime.now());
        String id = "testPatrimoine";

        Patrimoine saved = patrimoineService.saveOrUpdatePatrimoine(id, patrimoine);
        assertNotNull(saved);
        assertEquals("Tsota", saved.getPossesseur());
    }

    @Test
    void testGetPatrimoine() throws IOException {
        Patrimoine patrimoine = new Patrimoine("Tsota", LocalDateTime.now());
        String id = "testPatrimoine";

        patrimoineService.saveOrUpdatePatrimoine(id, patrimoine);
        Optional<Patrimoine> retrieved = patrimoineService.getPatrimoine(id);

        assertTrue(retrieved.isPresent());
        assertEquals("Tsota", retrieved.get().getPossesseur());
    }

    @Test
    void testGetPatrimoineNotFound() throws IOException {
        Optional<Patrimoine> retrieved = patrimoineService.getPatrimoine("nonexistentId");
        assertFalse(retrieved.isPresent());
    }
}
