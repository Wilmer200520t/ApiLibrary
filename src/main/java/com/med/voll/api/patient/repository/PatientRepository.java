package com.med.voll.api.patient.repository;

import com.med.voll.api.patient.models.Patient;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("""
        select p
        from patient p
        where p.id = :id
   \s""")
    Patient findActiveById(@NotNull Long id);
}
