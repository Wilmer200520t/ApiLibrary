package com.med.voll.api.medico.controller;

import com.med.voll.api.medico.dto.MedicAllData;
import com.med.voll.api.medico.dto.MedicData;
import com.med.voll.api.medico.dto.MedicMainData;
import com.med.voll.api.medico.dto.MedicUpdate;
import com.med.voll.api.medico.models.Medic;
import com.med.voll.api.medico.repository.MedicRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicRepository medicRepository;
    private List<Medic> medicos;

    @GetMapping
    public Page<MedicMainData> getAll(@PageableDefault(size = 25) Pageable pageable) {
        return medicRepository.findByActiveTrue(pageable)
                .map(MedicMainData::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicAllData> getById(@PathVariable Long id) {
        Optional<Medic> medic = medicRepository.findById(id);

        return medic.map(value -> ResponseEntity.ok(new MedicAllData(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> registerMedic(@Valid @RequestBody MedicData body, UriComponentsBuilder uriBuilder) {
        Medic medic = medicRepository.save(new Medic(body));
        MedicMainData medicMainData = new MedicMainData(medic);

        URI url = uriBuilder.path("/medico/{id}").buildAndExpand(medic.getId()).toUri();

        return ResponseEntity.created(url).body(medicMainData);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateMedic(@Valid @RequestBody MedicUpdate body, @PathVariable Long id) {
        Medic medic = medicRepository.findById(id).orElse(null);

        if (medic == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Médico no encontrado.");
        }

        if (!medic.isActive()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puede actualizar el médico porque está inactivo.");
        }

        medic.updateData(body);

        return ResponseEntity.ok(new MedicMainData(medic));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteMedic(@PathVariable Long id){
        Medic medic = medicRepository.getReferenceById(id);
        medic.desactive();

        return ResponseEntity.noContent().build();
    }


}
