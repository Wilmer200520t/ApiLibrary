package com.med.voll.api.patient.models;


import com.med.voll.api.general.models.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity(name = "patient")
@Table(name = "patient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @NotNull
    private boolean active = true;
    private String last_name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int age;


}
