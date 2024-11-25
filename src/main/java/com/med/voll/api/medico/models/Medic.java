package com.med.voll.api.medico.models;

import com.med.voll.api.general.models.Address;
import com.med.voll.api.medico.dto.MedicData;
import com.med.voll.api.medico.dto.MedicUpdate;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name = "medico")
@Entity(name = "Medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Medic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String phone;
    @Getter
    private boolean active;
    private String document;
    @Enumerated(EnumType.STRING)
    private Speciality speciality;
    @Embedded
    private Address address;

    public Medic(MedicData medicData) {
        this.name = medicData.name();
        this.email = medicData.email();
        this.phone = medicData.phone();
        this.document = medicData.document();
        this.active = true;
        this.speciality = medicData.speciality();
        this.address = new Address(medicData.address());
    }

    public void updateData(@Valid MedicUpdate body) {
        if(body.name() != null) this.name = body.name();
        if(body.document() != null) this.document = body.document();
        if(body.address() != null) this.address = body.address();
    }

    public void desactive() {
        this.active = false;
    }

}
