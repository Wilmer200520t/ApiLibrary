package com.med.voll.api.consultation.validation;

import com.med.voll.api.consultation.dto.ConsultationDto;
import com.med.voll.api.consultation.repository.ConsultationRepository;
import com.med.voll.api.general.infra.error.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationTimeConsultation implements Validator{

    @Autowired
    ConsultationRepository consultationRepository;

    public void validate(ConsultationDto consultation) {
        var existsConsultaion = consultationRepository.findByMedicIdAndTime(consultation.patient_id(), consultation.consultation_date());

        if(existsConsultaion){
            throw new ValidationException("Ya existe una consulta para este paciente en dicha hora. ");
        }

    }
}
