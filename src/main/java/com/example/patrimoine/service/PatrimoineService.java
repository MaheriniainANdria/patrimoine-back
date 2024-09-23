package com.example.patrimoine.service;

import com.example.patrimoine.model.Patrimoine;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class PatrimoineService {

    private static final String STORAGE_DIR = "patrimoines/";

    public PatrimoineService() {
        Path path = Paths.get(STORAGE_DIR);
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Patrimoine saveOrUpdatePatrimoine(String id, Patrimoine patrimoine) throws IOException {
        patrimoine.setDerniereModification(LocalDateTime.now());
        Path path = Paths.get(STORAGE_DIR + id + ".json");
        Files.writeString(path, toJson(patrimoine));
        return patrimoine;
    }

    public Optional<Patrimoine> getPatrimoine(String id) throws IOException {
        Path path = Paths.get(STORAGE_DIR + id + ".json");
        if (Files.exists(path)) {
            String content = Files.readString(path);
            return Optional.of(fromJson(content));
        } else {
            return Optional.empty();
        }
    }

    private String toJson(Patrimoine patrimoine) {
        return "{ \"possesseur\": \"" + patrimoine.getPossesseur() + "\", \"derniereModification\": \"" + patrimoine.getDerniereModification().toString() + "\" }";
    }

    private Patrimoine fromJson(String json) {
        String[] parts = json.replace("{", "").replace("}", "").replace("\"", "").split(",");
        String possesseur = parts[0].split(":")[1].trim();
        LocalDateTime date;

        try {
            date = LocalDateTime.parse(parts[1].split(":")[1].trim(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (Exception e) {
            date = LocalDateTime.now();
        }

        return new Patrimoine(possesseur, date);
    }
}
