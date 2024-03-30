package com.had.his.Service;

import com.had.his.DAO.*;
import com.had.his.DTO.LoginDTO;
import com.had.his.Entity.*;
import com.had.his.Response.LoginResponse;
import com.had.his.Security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.time.LocalDate;


@Service
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    private DoctorDAO doctorDAO;

    @Autowired
    private PatientDAO patientDAO;

    @Autowired
    private VitalsDAO vitalsDAO;

    @Autowired
    private SymptomsDAO symptomsDAO;

    @Autowired
    private SymptomImagesDAO symptomImagesDAO;

    @Autowired
    private PastHistoryDAO pastHistoryDAO;

    @Autowired
    private PastImagesDAO pastImagesDAO;

    @Autowired
    private ProgressDAO progressDAO;

    @Autowired
    private MedicationDAO medicationDAO;

    @Autowired
    private TestDAO testDAO;

    @Autowired
    private TestImagesDAO testImagesDAO;

    @Autowired
    private BedDAO bedDAO;

    @Autowired
    private VisitDAO visitDAO;

    @Autowired
    private SpecializationDAO specializationDAO;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
   @Override
    public LoginResponse verifyDoctor(LoginDTO credentials) {
        String email = credentials.getEmail();
        String enteredPassword = credentials.getPassword();

        Doctor doc = doctorDAO.findByEmail(email);
        if (doc != null && doc.getActive()) {
            if( doc.isPasswordMatch(enteredPassword))
            {
                doc.setAvailability(true);
                doctorDAO.save(doc);
                String jwttoken= jwtTokenProvider.generateToken(doc);
                return new LoginResponse("Login Successful",true,jwttoken);
            } else {
                return new LoginResponse("Password not matched", false, null);
            }
        }
        else{
                return new LoginResponse("Invalid User.", false, null);
            }
    }

    @Transactional
    public Doctor changePassword(LoginDTO credentials){
        Doctor newDoc = doctorDAO.findByEmail(credentials.getEmail());
        if (newDoc.getActive().equals(true)){
            newDoc.setPassword(credentials.getPassword());
        }
        return doctorDAO.save(newDoc);
    }


    @Override
    public Doctor findByEmail(String email){
        return doctorDAO.findByEmail(email);
    }

    @Override
    public List<Patient> getPatients(String email){
        return patientDAO.getPatientsByDoctor(email);
    }


    @Override
    public List<Patient> getEmergencyPatients(String email){
        return patientDAO.getEmergencyPatients(email);
    }

    public Patient getPatientDetails(String pid) {
        return patientDAO.findPatientDetailsById(pid);
    }


    public Vitals getVitals(String pid){
        return vitalsDAO.getVitalsByPatient(pid);
    }

    public Symptoms getSymptoms(String pid){
        return symptomsDAO.getSymptomsByPatient(pid);
    }

    public List<SymptomImages> getSymptomImages(String pid) {
        return symptomImagesDAO.getSymptomImagesByPatient(pid);
    }

    public List<PastHistory> getPastHistory(String pid){
        return pastHistoryDAO.getPastHistoriesByPatient(pid);
    }

    public List<PastImages> getPastImages(Integer phid){
        return pastImagesDAO.getPastImagesByPastHistory(phid);
    }

    public List<Medication> getPastMedications(String pid) {
        return medicationDAO.getPastMedicationsByPatient(pid);
    }


    public List<Test> getPastTests(String pid) {
        return testDAO.getPastTestsByPatient(pid);
    }



    public Progress saveProgress(String pid,Progress progress){
        progress.setPatient(patientDAO.findPatientDetailsById(pid));
        progress.setTime(Time.valueOf(LocalTime.now()));
        progress.setDate(LocalDate.now());
        return progressDAO.save(progress);
    }

    public List<Progress> getProgressHistory(String pid){
        return progressDAO.findByPatient(pid);
    }

    public List<Medication> getMedications(String pid){
        return medicationDAO.getMedicationByPatient(pid);
    }

    public Medication getMedicationById(String pid,Integer mid){
        return medicationDAO.findMedicationByMedicineId(pid,mid);
    }

    public Medication saveMedication(String pid,Medication med){
        med.setPastMedication(false);
        med.setPrescribedOn(LocalDate.now());
        med.setServed(false);
        med.setVisit(visitDAO.getRecentVisit(pid));
        return medicationDAO.save(med);
    }

    public Medication editMedication(String pid,Integer mid,Medication med){
        Medication newMed = medicationDAO.findMedicationByMedicineId(pid,mid);
        newMed.setMedicineName(med.getMedicineName());
        newMed.setDosage(med.getDosage());
        newMed.setFrequency(med.getFrequency());
        newMed.setDuration(med.getDuration());
        newMed.setSpecialInstructions(med.getSpecialInstructions());
        return medicationDAO.save(newMed);
    }

    public void deleteMedication(String pid,Integer mid){
        System.out.println(mid);
        Medication medication=medicationDAO.findMedicationByMedicineId(pid,mid);
        medicationDAO.delete(medication);
    }

    public List<Test> getTests(String pid){
        return testDAO.findTestByPatientId(pid);
    }

    public Test getTestById(String pid,Integer tid){
        return testDAO.findTestByTestId(pid,tid);
    }

    public Test saveTest(String pid,Test test){
        test.setPastTest(false);
        test.setPrescribedOn(LocalDate.now());
        test.setVisit(visitDAO.getRecentVisit(pid));
        return testDAO.save(test);
    }

    public Test editTest(String pid,Integer tid,Test test){
        Test newTest = testDAO.findTestByTestId(pid,tid);
        newTest.setTestName(test.getTestName());
        return testDAO.save(newTest);
    }

    public void deleteTest(String pid,Integer mid){
        Test test=testDAO.findTestByTestId(pid,mid);
        testDAO.delete(test);
    }

    public List<TestImages> getTestImages(Integer tid){
        return testImagesDAO.findTestById(tid);
    }


    public Visit addDisease(String pid,String disease) {
        Visit newVisit = visitDAO.getRecentVisit(pid);
        newVisit.setDisease(disease);
        return visitDAO.save(newVisit);
    }

    public Patient recommendIP(String pid,String did){
        Patient newPatient = patientDAO.findPatientDetailsById(pid);
        newPatient.setDepartment("IP");
        Visit newVisit=visitDAO.getRecentVisit(pid);
        Doctor doctor = doctorDAO.findByDoctorId(did);
        newVisit.setDoctor(doctor);
        newVisit.setSpecialization(doctor.getSpecialization().getSpecializationName());
        visitDAO.save(newVisit);
        Bed newBed= bedDAO.getFirstFreeBed();
        newBed.setPatient(newPatient);
        newPatient.setBed(newBed);
        bedDAO.save(newBed);
        return patientDAO.save(newPatient);
    }

    public Patient dischargePatient(String pid){
        Patient newPatient = patientDAO.findPatientDetailsById(pid);
        List<Medication> medications=medicationDAO.getMedicationByPatient(pid);
        List<Test> tests=testDAO.findTestByPatientId(pid);
        for(Medication medication : medications){
            medication.setPastMedication(true);
            medicationDAO.save(medication);
        }
        for(Test test : tests){
            test.setPastTest(true);
            testDAO.save(test);
        }
        if (newPatient.getDepartment().equals("IP")) {
            Bed newBed = newPatient.getBed();
            newBed.setPatient(null);
            bedDAO.save(newBed);
        }
        Visit recentVisit = visitDAO.getRecentVisit(pid);
        recentVisit.setDischargedDate(LocalDate.now());
        visitDAO.save(recentVisit);
        return patientDAO.save(newPatient);
    }

    public Long admittedPatientCount(String email){
        return patientDAO.getAdmittedCount(email);
    }

    public Long treatedPatientCount(String email){
        return patientDAO.getTreatedCount(email);
    }

    @Override
    public List<String> getSpecializations() {
        return specializationDAO.findALLSpecializations();
    }

    public List<Doctor> getDoctorsBySpecialization(String specialization){
        return doctorDAO.getIPDoctorsBySpecialization(specialization);
    }

    public Doctor exitDoctor(String email){
        Doctor newDoc = doctorDAO.findByEmail(email);
        newDoc.setAvailability(false);
        return doctorDAO.save(newDoc);
    }

}
