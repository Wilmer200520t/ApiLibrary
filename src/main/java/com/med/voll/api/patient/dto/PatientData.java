package com.med.voll.api.patient.dto;

import com.med.voll.api.general.models.Gender;


public record PatientData (
        String name,
        String last_name,
        Gender gender,
        int age
){

}
