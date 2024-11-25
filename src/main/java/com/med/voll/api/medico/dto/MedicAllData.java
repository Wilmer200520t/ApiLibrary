package com.med.voll.api.medico.dto;

import com.med.voll.api.general.models.Address;
import com.med.voll.api.medico.models.Medic;

public record MedicAllData(
        String id,
        String name,
        String email,
        String phone,
        String document,
        String speciality,
        Address address
) {
    // Constructor adicional que acepta un objeto Medic
    public MedicAllData(Medic medicData) {
        this(
                String.valueOf(medicData.getId()),
                medicData.getName(),
                medicData.getEmail(),
                medicData.getPhone(),
                medicData.getDocument(),
                medicData.getSpeciality().toString(),
                medicData.getAddress()
        );
    }
}
