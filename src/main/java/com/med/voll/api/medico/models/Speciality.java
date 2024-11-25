package com.med.voll.api.medico.models;

public enum Speciality {
    Ortopedia("ortopedia"),
    Cardiologia("cardiologia"),
    Ginecologia("ginecologia"),
    Pediatria("pediatria");

    private String descripcion;

    private Speciality(String descripcion) {
        this.descripcion = descripcion;
    }

}
