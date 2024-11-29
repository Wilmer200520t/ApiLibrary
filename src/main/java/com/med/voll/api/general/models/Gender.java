package com.med.voll.api.general.models;

public enum Gender {
    Male("M"), Female("F");
    private String gender;
    
    private Gender(String gender) {
        this.gender = gender;
    }
    
}
