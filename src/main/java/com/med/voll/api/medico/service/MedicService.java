package com.med.voll.api.medico.service;

import com.med.voll.api.medico.dto.MedicData;
import com.med.voll.api.medico.models.Medic;
import com.med.voll.api.medico.repository.MedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicService {

    @Autowired
    private MedicRepository repository;


    public void saveMedic(MedicData medicData) {
        repository.save(new Medic(medicData));
    }
}
