package com.example.patrimoine.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatrimoineTest {

    @Test
    void testPatrimoineCreation() {
        LocalDateTime now = LocalDateTime.now();
        Patrimoine patrimoine = new Patrimoine("Tsota", now);

        assertEquals("Tsota", patrimoine.getPossesseur());
        assertEquals(now.getSecond(), patrimoine.getDerniereModification().getSecond());
    }

    @Test
    void testSetPossesseur() {
        Patrimoine patrimoine = new Patrimoine("Tsota", LocalDateTime.now());
        patrimoine.setPossesseur("NouveauPossesseur");

        assertEquals("NouveauPossesseur", patrimoine.getPossesseur());
    }

    @Test
    void testSetDerniereModification() {
        Patrimoine patrimoine = new Patrimoine("Tsota", LocalDateTime.now());
        LocalDateTime newDate = LocalDateTime.now().plusDays(1);
        patrimoine.setDerniereModification(newDate);

        assertEquals(newDate.getSecond(), patrimoine.getDerniereModification().getSecond());
    }
}
