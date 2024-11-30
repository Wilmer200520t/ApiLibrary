package com.med.voll.api.medico.repository;

import com.med.voll.api.consultation.models.Consultation;
import com.med.voll.api.general.dto.AddressData;
import com.med.voll.api.general.models.Gender;
import com.med.voll.api.medico.dto.MedicData;
import com.med.voll.api.medico.models.Medic;
import com.med.voll.api.medico.models.Speciality;
import com.med.voll.api.patient.dto.PatientData;
import com.med.voll.api.patient.models.Patient;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicRepositoryTest {
    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("Deberia devolver null, cuando el medico buscado existe pero no esta disponible en dicho horario.")
    void getReferenceBySpecialityAndIsNOFree() {
        //given o arrange
        var nextMonday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var medic =  registerMedic("Wilmer", "wilmer@gmail.com", "456258", Speciality.Cardiologia);
        var patient = registerPatient("Alex", "Nolan", Gender.Male, 21);
        registerBook(medic, patient, nextMonday);

        //when o act
        var medicFree = medicRepository.getReferenceBySpecialityAndIsFree(Speciality.Cardiologia, nextMonday);

        //then o assert
        assertThat(medicFree).isNull();
    }

    @Test
    @DisplayName("Deberia devolver un medico, cuando el medico buscado existe y esta disponible en dicho horario.")
    void getReferenceBySpecialityAndIsFree() {
        //given o arrange
        var nextMonday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var medic =  registerMedic("Wilmer", "wilmer@gmail.com", "456258", Speciality.Cardiologia);

        //when o act
        var medicFree = medicRepository.getReferenceBySpecialityAndIsFree(Speciality.Cardiologia, nextMonday);

        //then o assert
        assertThat(medicFree).isEqualTo(medic);
    }

    private void registerBook(Medic medic, Patient patient, LocalDateTime date) {
        entityManager.persist(new Consultation(0, medic, patient, date));
    }

    private Medic registerMedic(String name, String email, String document, Speciality speciality){
        var medic = new Medic(medicData(name, email, document, speciality));
        entityManager.persist(medic);
        return medic;
    }

    private Patient registerPatient(String name, String last_name, Gender gender, int age) {
        var patient = new Patient(patientData(name, last_name, gender, age));
        entityManager.persist(patient);
        return patient;
    }

    private MedicData medicData(String name, String email, String document, Speciality speciality) {
        return new MedicData(
                name,
                email,
                "959565664",
                document,
                speciality,
                addresData()
        );
    }

    private PatientData patientData(String name, String last_name, Gender gender, int age){
        return new PatientData(
                name,
                last_name,
                gender,
                age
        );
    }

    private AddressData addresData(){
        return new AddressData(
                "calle x",
                "distrito y",
                "ciudad 2",
                "123",
                "None"
        );
    }
}