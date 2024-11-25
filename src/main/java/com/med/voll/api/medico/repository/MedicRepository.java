package com.med.voll.api.medico.repository;

import com.med.voll.api.medico.models.Medic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicRepository extends JpaRepository<Medic, Integer> {
    Page<Medic> findByActiveTrue(Pageable pageable);
}
