package com.hospitalcare.inventory_service.services;

import com.hospitalcare.inventory_service.entities.Medication;
import com.hospitalcare.inventory_service.repositories.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final MedicationRepository repository;

    public List<Medication> getAllMedications() {
        return repository.findAll();
    }

    public Medication getMedicationById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicamento não encontrado"));
    }

    @Transactional
    public Medication createMedication(Medication medication) {
        if (repository.existsBySku(medication.getSku())) {
            throw new RuntimeException("SKU já existe!");
        }
        if (medication.getQuantity() < 0) medication.setQuantity(0);
        return repository.save(medication);
    }

    @Transactional
    public Medication updateStock(UUID id, Integer amountToAdd) {
        Medication med = getMedicationById(id);
        int newQuantity = med.getQuantity() + amountToAdd;
        if (newQuantity < 0) throw new RuntimeException("Estoque insuficiente!");
        med.setQuantity(newQuantity);
        return repository.save(med);
    }

    public void deleteMedication(UUID id) {
        repository.deleteById(id);
    }
}