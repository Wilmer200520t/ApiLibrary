package com.med.voll.api.consultation.repository;

import com.med.voll.api.consultation.models.Consultation;
import com.med.voll.api.medico.models.Medic;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    @Query("""
        SELECT\s
            CASE
                WHEN\s
                    (SELECT COUNT(*)
                    FROM consultation c
                    WHERE c.patient.id = :id
                    AND c.consultation_date = :date) > 1 THEN true
                ELSE false END
   \s""")
    boolean findByMedicIdAndTime(Long id, @NotNull @Future LocalDateTime date);
}
