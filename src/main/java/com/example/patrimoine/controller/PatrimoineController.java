package com.example.patrimoine.controller;

import com.example.patrimoine.model.Patrimoine;
import com.example.patrimoine.service.PatrimoineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/patrimoines")
public class PatrimoineController {

    private final PatrimoineService patrimoineService;

    @Autowired
    public PatrimoineController(PatrimoineService patrimoineService) {
        this.patrimoineService = patrimoineService;
    }

    // PUT /patrimoines/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Patrimoine> createOrUpdatePatrimoine(@PathVariable String id, @RequestBody Patrimoine patrimoine) throws IOException {
        Patrimoine savedPatrimoine = patrimoineService.saveOrUpdatePatrimoine(id, patrimoine);
        return ResponseEntity.ok(savedPatrimoine);
    }

    // GET /patrimoines/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Patrimoine> getPatrimoine(@PathVariable String id) throws IOException {
        Optional<Patrimoine> patrimoine = patrimoineService.getPatrimoine(id);
        return patrimoine.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
