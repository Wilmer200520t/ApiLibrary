package com.med.voll.api.consultation.validation;

import com.med.voll.api.consultation.dto.ConsultationDto;
import com.med.voll.api.general.infra.error.ValidationException;
import com.med.voll.api.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidationPatientIsActive implements Validator{

    @Autowired
    private PatientRepository patientRepository;

    public void validate(ConsultationDto consultation) {
        var patient = patientRepository.findActiveById(consultation.patient_id());

        if(!patient.isActive()) {
            throw new ValidationException("Patient is not active");
        }
    }
}
