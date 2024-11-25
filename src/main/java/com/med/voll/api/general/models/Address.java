package com.med.voll.api.general.models;

import com.med.voll.api.general.dto.AdressData;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String city;
    private String district;
    private int number;
    private String complement;

    public Address(AdressData address) {
        this.street = address.street();
        this.city = address.city();
        this.district = address.district();
        this.number = Integer.parseInt(address.number());
        this.complement = address.complement();
    }
}
