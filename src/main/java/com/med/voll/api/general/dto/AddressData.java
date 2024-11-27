package com.med.voll.api.general.dto;

import jakarta.validation.constraints.NotNull;

public record AddressData(
        @NotNull String street,
        @NotNull String district,
        @NotNull String city,
        @NotNull String number,
        String complement
) {
}
