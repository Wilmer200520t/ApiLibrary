package com.med.voll.api.medico.dto;

import com.med.voll.api.medico.models.Medic;

public record MedicMainData(
        String id,
        String name,
        String speciality,
        String document,
        String mail
) {
    // Constructor adicional que acepta un objeto Medic
    public MedicMainData(Medic medicData) {
        this(
                String.valueOf(medicData.getId()),
                medicData.getName(),
                medicData.getSpeciality().toString(),
                medicData.getDocument(),
                medicData.getEmail()
        );
    }
}

