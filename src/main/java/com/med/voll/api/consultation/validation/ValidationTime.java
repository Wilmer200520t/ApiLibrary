package com.med.voll.api.consultation.validation;

import com.med.voll.api.consultation.dto.ConsultationDto;
import com.med.voll.api.general.infra.error.ValidationException;

import java.time.DayOfWeek;

public class ValidationTime implements Validator{

    public void validate(ConsultationDto consultation) {
        var date = consultation.consultation_date();

        var sunday = date.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var hourUntilOpen = date.getHour() < 7;
        var hourUntilClose = date.getHour() < 18;

        if(sunday || hourUntilOpen || hourUntilClose){
            throw new ValidationException("Fecha elegida fuera del tiempo relativo.");
        }


    }
}
