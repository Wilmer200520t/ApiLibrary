package com.med.voll.api.consultation.dto;

import com.med.voll.api.consultation.models.Consultation;
import com.med.voll.api.medico.models.Speciality;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultationDto(

        Long medic_id,
        @NotNull
        Long patient_id,
        @NotNull
        @Future
        LocalDateTime consultation_date,

        Speciality speciality
){
        public ConsultationDto(Consultation consultation) {
                this(
                        consultation.getMedic().getId(),
                        consultation.getPatient().getId(),
                        consultation.getConsultation_date(),
                        consultation.getMedic().getSpeciality()
                );
        }
}
