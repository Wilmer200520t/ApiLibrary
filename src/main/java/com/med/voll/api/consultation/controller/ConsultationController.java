package com.med.voll.api.consultation.controller;

import com.med.voll.api.consultation.dto.ConsultationDto;
import com.med.voll.api.consultation.models.Consultation;
import com.med.voll.api.consultation.repository.ConsultationRepository;
import com.med.voll.api.consultation.service.ConsultationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/consulta")
public class ConsultationController {
    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private ConsultationService consultationService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> saveConsultation(@Validated @RequestBody ConsultationDto body){
        Consultation consultation = consultationService.book(body);
        ConsultationDto consultationDto = new ConsultationDto(consultation);
        return ResponseEntity.ok().body(consultationDto);
    }

    @GetMapping
    public ResponseEntity<?> getAllConsultation(){
        List<Consultation> listConsultation = consultationRepository.findAll();
        List<ConsultationDto> listConsultationDto = listConsultation.stream().map(ConsultationDto:: new).toList();
        return ResponseEntity.ok().body(listConsultationDto);
    }
}
