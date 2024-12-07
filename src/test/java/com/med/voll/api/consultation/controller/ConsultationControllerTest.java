package com.med.voll.api.consultation.controller;

import com.med.voll.api.consultation.dto.ConsultationDto;
import com.med.voll.api.consultation.models.Consultation;
import com.med.voll.api.consultation.service.ConsultationService;
import com.med.voll.api.medico.models.Speciality;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<ConsultationDto> jsonCDto;

    @Autowired
    private JacksonTester<Consultation> jsonCon;

    @MockBean
    private ConsultationController consultationController;

    @MockBean
    private ConsultationService consultationService;

    @Test
    @DisplayName("Deberia retornar metodo http 400.")
    @WithMockUser
    void saveConsultation400() throws Exception {
        var response = mockMvc.perform(post("/consulta"))
                .andReturn()
                .getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    @DisplayName("Deberia retornar metodo http 200.")
    @WithMockUser
    void saveConsultation200() throws Exception {
        var fecha = LocalDateTime.now().plusHours(1);
        var datosDetalle = new ConsultationDto(1L, 1L, fecha, Speciality.Cardiologia);

        // Simular la respuesta del servicio
        Consultation consultation = new Consultation();
        when(consultationService.book(any(ConsultationDto.class))).thenReturn(consultation);

        // Simular la respuesta del controlador con tipo explícito
        ResponseEntity<ConsultationDto> expectedResponse = ResponseEntity.ok(datosDetalle);
        when(consultationController.saveConsultation(any(ConsultationDto.class)))
                .thenReturn(expectedResponse);


        var response = mockMvc.perform(
                    post("/consulta")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonCDto.write(
                                    new ConsultationDto(1L, 1L, fecha, Speciality.Cardiologia)
                            ).getJson())
                )
                .andReturn()
                .getResponse();

        var jsonExpected = jsonCDto.write(datosDetalle).getJson();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(jsonExpected, response.getContentAsString());

    }

    @Test
    void getAllConsultation() {
    }
}