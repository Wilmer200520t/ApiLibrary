package com.med.voll.api.consultation.validation;


import com.med.voll.api.consultation.dto.ConsultationDto;

public interface Validator {

    public void validate(ConsultationDto consultation);
}
