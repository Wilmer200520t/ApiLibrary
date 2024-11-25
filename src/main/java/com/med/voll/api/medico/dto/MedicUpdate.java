package com.med.voll.api.medico.dto;

import com.med.voll.api.general.models.Address;

public record MedicUpdate(
        String name,
        String document,
        Address address
) {
}
