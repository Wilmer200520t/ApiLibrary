package com.med.voll.api.consultation.service;

import com.med.voll.api.consultation.dto.ConsultationDto;
import com.med.voll.api.consultation.models.Consultation;
import com.med.voll.api.consultation.repository.ConsultationRepository;
import com.med.voll.api.consultation.validation.Validator;
import com.med.voll.api.general.infra.error.ValidationException;
import com.med.voll.api.medico.models.Medic;
import com.med.voll.api.medico.repository.MedicRepository;
import com.med.voll.api.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.List;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository repository;

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    private List<Validator> validators;

    public Consultation book(ConsultationDto consultationDto) {


        var patient = patientRepository.findById(consultationDto.patient_id());

        if (consultationDto.consultation_date() == null) {
            throw new ValidationException("Fecha no valida");
        }

        if (consultationDto.medic_id() != null &&  !medicRepository.existsMedicBy(consultationDto.medic_id())){
            throw new ValidationException("No existe el medico");
        }

        if (patient.isEmpty()){
            throw new ValidationException("No existe el patiente");
        }


        var medic = chooseMedic(consultationDto);
        var consultation = new Consultation(0, medic, patient.get(), consultationDto.consultation_date());

        validators.forEach(validator -> validator.validate(consultationDto));

        return repository.save(consultation);

    }

    public Medic chooseMedic(ConsultationDto consultation) {
        if (consultation.medic_id() != null){
            return medicRepository.getReferenceById(consultation.medic_id());
        }

        if (consultation.speciality() != null){
            return medicRepository.getReferenceBySpecialityAndIsFree(consultation.speciality(), consultation.consultation_date());
        }

        throw new ValidationException("Es necesario elegir una especialidad, si no se elige un medico.");
    }


}
