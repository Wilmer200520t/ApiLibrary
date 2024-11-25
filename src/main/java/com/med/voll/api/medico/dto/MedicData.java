package com.med.voll.api.medico.dto;

import com.med.voll.api.general.dto.AdressData;
import com.med.voll.api.medico.models.Speciality;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record MedicData(
        @NotNull String name,
        @NotNull String email,
        @NotNull String phone,
        @NotNull String document,
        @NotNull Speciality speciality,
        @Valid
        @NotNull AdressData address
) {
}
