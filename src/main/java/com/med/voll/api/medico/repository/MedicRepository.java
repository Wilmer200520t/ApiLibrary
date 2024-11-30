package com.med.voll.api.medico.repository;

import com.med.voll.api.medico.models.Medic;
import com.med.voll.api.medico.models.Speciality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface MedicRepository extends JpaRepository<Medic, Long> {
    Page<Medic> findByActiveTrue(Pageable pageable);


    @Query("""
        select m from Medico m
        where \s
        m.active = true\s
        and m.speciality = :speciality
        and m.id not in (
            select c.medic.id from consultation c
            where c.consultation_date = :fecha
        )
        ORDER BY rand()
        limit 1
   \s""")
    Medic getReferenceBySpecialityAndIsFree(Speciality speciality, LocalDateTime fecha);



    @Query("""
        select \s
            m.active
        from Medico m
        where m.id = :id
   \s""")
    boolean existsMedicBy(Long id);
}
