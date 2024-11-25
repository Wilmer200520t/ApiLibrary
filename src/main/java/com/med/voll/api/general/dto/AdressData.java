package com.med.voll.api.general.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AdressData(
        @NotNull String street,
        @NotNull String district,
        @NotNull String city,
        @NotNull String number,
        String complement
) {
}
