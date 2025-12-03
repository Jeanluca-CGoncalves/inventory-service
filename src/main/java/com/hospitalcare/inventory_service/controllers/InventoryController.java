package com.hospitalcare.inventory_service.controllers;

import com.hospitalcare.inventory_service.entities.Medication;
import com.hospitalcare.inventory_service.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService service;

    @GetMapping
    public ResponseEntity<List<Medication>> getAll() {
        return ResponseEntity.ok(service.getAllMedications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medication> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getMedicationById(id));
    }

    @PostMapping
    public ResponseEntity<Medication> create(@RequestBody Medication medication) {
        return new ResponseEntity<>(service.createMedication(medication), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<Medication> updateStock(@PathVariable UUID id, @RequestBody Map<String, Integer> payload) {
        return ResponseEntity.ok(service.updateStock(id, payload.get("amount")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteMedication(id);
        return ResponseEntity.noContent().build();
    }
}