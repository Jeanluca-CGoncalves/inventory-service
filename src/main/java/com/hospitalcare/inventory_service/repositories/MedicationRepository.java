package com.hospitalcare.inventory_service.repositories;

import com.hospitalcare.inventory_service.entities.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface MedicationRepository extends JpaRepository<Medication, UUID> {
    boolean existsBySku(String sku);
}