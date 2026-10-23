/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitaladmissionsystem;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 *
 * @author vuyolwethumpongose
 */
public class HospitalAdmissionSystemTest {
    
    @Test
    public void testRegisterPatient() {
        ArrayList<Patient> patients = new ArrayList<>();
        Patient p = new Patient("P001", "Thabo", "Mokoena", 45, "Male", "Flu", PatientCategory.OUTPATIENT);
        patients.add(p);

        assertEquals(1, patients.size());
        assertEquals("P001", patients.get(0).getPatientId());
    }
    
        @Test
    public void testSearchPatient() {
        ArrayList<Patient> patients = new ArrayList<>();
        Patient p = new Patient("P002", "Lindiwe", "Dube", 30, "Female", "Checkup", PatientCategory.OUTPATIENT);
        patients.add(p);

        Patient found = null;
        for (Patient patient : patients) {
            if (patient.getPatientId().equals("P002")) {
                found = patient;
            }
        }

        assertNotNull(found);
        assertEquals("Lindiwe", found.getFirstName());
    }

    @Test
    public void testUpdatePatient() {
        Patient p = new Patient("P003", "Sipho", "Nkosi", 40, "Male", "Flu", PatientCategory.OUTPATIENT);
        p.setFirstName("SiphoUpdated");

        assertEquals("SiphoUpdated", p.getFirstName());
    }

    @Test
    public void testDeletePatient() {
        ArrayList<Patient> patients = new ArrayList<>();
        Patient p = new Patient("P004", "Zanele", "Mahlangu", 25, "Female", "Checkup", PatientCategory.OUTPATIENT);
        patients.add(p);

        patients.remove(p);

        assertEquals(0, patients.size());
    }

    @Test
    public void testAllocateBed() {
        Ward ward = new Ward();
        Inpatient inpatient = new Inpatient("P005", "Thabo", "Zungu", 50, "Male", "Diabetes", PatientCategory.INPATIENT, "A", "1");

        boolean success = ward.allocateBed(1, inpatient);

        assertTrue(success);
    }

    @Test
    public void testAllocateOccupiedBed() {
        Ward ward = new Ward();
        Inpatient inpatient1 = new Inpatient("P006", "Nomvula", "Zulu", 38, "Female", "Malaria", PatientCategory.INPATIENT, "A", "2");
        Inpatient inpatient2 = new Inpatient("P007", "Kagiso", "Sithole", 60, "Male", "Hypertension", PatientCategory.INPATIENT, "A", "2");

        ward.allocateBed(2, inpatient1);
        boolean secondAttempt = ward.allocateBed(2, inpatient2);

        assertFalse(secondAttempt);
    }

    @Test
    public void testReleaseBed() {
        Ward ward = new Ward();
        Inpatient inpatient = new Inpatient("P008", "Palesa", "Moeketsi", 33, "Female", "Asthma", PatientCategory.INPATIENT, "A", "3");

        ward.allocateBed(3, inpatient);
        boolean released = ward.releaseBed(3);

        assertTrue(released);
    }

    @Test
    public void testDuplicatePatientId() {
        ArrayList<Patient> patients = new ArrayList<>();
        Patient p1 = new Patient("P009", "Ayanda", "Cele", 29, "Male", "Cold", PatientCategory.OUTPATIENT);
        patients.add(p1);

        boolean isDuplicate = false;
        for (Patient patient : patients) {
            if (patient.getPatientId().equals("P009")) {
                isDuplicate = true;
            }
        }

        assertTrue(isDuplicate);
    }
    
}
