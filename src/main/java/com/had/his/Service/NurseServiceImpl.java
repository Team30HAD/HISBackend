package com.had.his.Service;

import com.had.his.DAO.*;
import com.had.his.DTO.LoginDTO;
import com.had.his.Entity.*;
import com.had.his.Response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class NurseServiceImpl implements NurseService {

    @Autowired
    private NurseDAO nurseDAO;

    @Autowired
    private PatientDAO patientDAO;

    @Autowired
    private VitalsDAO vitalsDAO;

    @Autowired
    private SymptomsDAO symptomsDAO;

    @Autowired
    private PastHistoryDAO pastHistoryDAO;

    @Autowired
    private SymptomImagesDAO symptomImagesDAO;

    @Autowired
    private PastImagesDAO pastImagesDAO;

    @Autowired
    private TestDAO testDAO;

    @Autowired
    private TestImagesDAO testImagesDAO;


    public LoginResponse NurseLogin(LoginDTO loginDto)
    {
        Nurse nurse = nurseDAO.findByEmail(loginDto.getEmail());
        if (nurse != null && nurse.getActive().equals(true)) {
            List<NurseSchedule> schedules = nurse.getNurseSchedules();

            if (!schedules.isEmpty()) {
                LocalDate currentDate = LocalDate.now();
                LocalTime currentTime = LocalTime.now();

                for (NurseSchedule schedule : schedules) {
                    if (currentDate.getDayOfWeek() == schedule.getDay() &&
                            currentTime.isAfter(schedule.getStart_time()) &&
                            currentTime.isBefore(schedule.getEnd_time())) {

                        String password = loginDto.getPassword();

                        boolean passmatch = nurse.isPasswordMatch(password);

                        if (passmatch) {
                            String nurseId = nurse.getNurseId();
                            return new LoginResponse("Login Successful",true,nurseId);
                        } else {
                            return new LoginResponse("Password not matched", false, null);
                        }
                    }
                }
                return new LoginResponse("Nurse can't login at this time.", false, null);
            } else {
                return new LoginResponse("Nurse schedules not found.", false, null);
            }
        } else {
            return new LoginResponse("Email not found", false, null);
        }


    }

    @Transactional
    public Nurse getNurseDetailsById(String nurseId) {
        return nurseDAO.findDetailsById(nurseId);
    }

    @Transactional
    public List<Patient> getEmergencyPatients(){
        return patientDAO.findAllEmergencyPatients();
    }

    @Transactional
    public List<Patient> getAllPatients() {
        return patientDAO.findAllPatients();
    }

    @Transactional
    public Patient getPatientDetailsById(String patientId)
    {
        return patientDAO.findPatientDetailsById(patientId);
    }

    @Override
    public Vitals addVitals(String patientId,Vitals vitals) {
        Patient patient=patientDAO.findPatientDetailsById(patientId);
        vitals.setPatient(patient);
        return vitalsDAO.save(vitals);
    }

    @Override
    public Vitals editVitals(Long vitalid,Vitals vitals)
    {
        Optional<Vitals> optionalVitals=vitalsDAO.findById(vitalid);
        Vitals savevitals=optionalVitals.orElse(null);
        if(vitals.getTemperature()!=null)
            savevitals.setTemperature(vitals.getTemperature());
        if(vitals.getWeight()!=null)
            savevitals.setWeight(vitals.getWeight());
        if(vitals.getHeight()!=null)
            savevitals.setHeight(vitals.getHeight());
        if(vitals.getBp()!=null && !vitals.getBp().isEmpty())
            savevitals.setBp(vitals.getBp());
        if(vitals.getSpo2()!=null)
            savevitals.setSpo2(vitals.getSpo2());
        if(vitals.getPulse()!=null)
            savevitals.setPulse(vitals.getPulse());
        if(vitals.getPatient()!=null)
            savevitals.setPatient(vitals.getPatient());
        return vitalsDAO.save(savevitals);
    }

    @Transactional
    public Vitals viewVitals(String patientId)
    {
        return vitalsDAO.getVitalsByPatient(patientId);
    }

    @Transactional
    public void deleteVitals(Long vitalid)
    {
        Optional<Vitals> optionalVitals=vitalsDAO.findById(vitalid);
        vitalsDAO.deleteByVitalId(vitalid);

    }

    @Override
    public Symptoms addSymptoms(String patientId,Symptoms symptoms)
    {
        Patient patient=patientDAO.findPatientDetailsById(patientId);
        symptoms.setPatient(patient);
        return symptomsDAO.save(symptoms);
    }

    @Override
    public Symptoms editSymptoms(Long symptomid,Symptoms symptoms)
    {
        Symptoms savesymptoms=symptomsDAO.findBySymptomsId(symptomid);
        if(symptoms.getSymptom1()!=null  && !symptoms.getSymptom1().isEmpty())
            savesymptoms.setSymptom1(symptoms.getSymptom1());
        if(symptoms.getSymptom2()!=null && !symptoms.getSymptom2().isEmpty())
            savesymptoms.setSymptom2(symptoms.getSymptom2());
        if(symptoms.getSymptom3()!=null && !symptoms.getSymptom3().isEmpty())
            savesymptoms.setSymptom3(symptoms.getSymptom3());
        if(symptoms.getSymptom4()!=null && !symptoms.getSymptom4().isEmpty())
            savesymptoms.setSymptom4(symptoms.getSymptom4());
        if(symptoms.getSymptom5()!=null && !symptoms.getSymptom5().isEmpty())
            savesymptoms.setSymptom5(symptoms.getSymptom5());
        if(symptoms.getPatient()!=null)
            savesymptoms.setPatient(symptoms.getPatient());
        return symptomsDAO.save(savesymptoms);
    }

    @Transactional
    public Symptoms viewSymptoms(String patientId){
        Symptoms symptoms=symptomsDAO.getSymptomsByPatient(patientId);
        return symptoms;

    }

    @Transactional
    public void deleteSymptoms(Long symptomid){

        Optional<Symptoms> symptoms=symptomsDAO.findById(symptomid);
        symptomsDAO.deleteBySymptomId(symptomid);

    }

    @Override
    public PastHistory addPastHistory(String patientId,PastHistory pastHistory){
        Patient patient=patientDAO.findPatientDetailsById(patientId);
        LocalDate currentDate = LocalDate.now();
        pastHistory.setRecordedAt(currentDate);
        pastHistory.setPatient(patient);
        return pastHistoryDAO.save(pastHistory) ;
    }

    @Override
    public PastHistory editPastHistory(Long historyid,PastHistory pastHistory){
        Optional<PastHistory> optionalPastHistory=pastHistoryDAO.findById(historyid);
        PastHistory savepastHistory=optionalPastHistory.orElse(null);
        if(pastHistory.getDisease()!=null)
            savepastHistory.setDisease(pastHistory.getDisease());
        if(pastHistory.getMedicine()!=null)
            savepastHistory.setMedicine(pastHistory.getMedicine());
        if(pastHistory.getDosage()!=null)
            savepastHistory.setDosage(pastHistory.getDosage());
        if(pastHistory.getRemarks()!=null)
            savepastHistory.setRemarks(pastHistory.getRemarks());
        if(pastHistory.getRecordedAt()!=null)
            savepastHistory.setRecordedAt(pastHistory.getRecordedAt());
        if(pastHistory.getPastImages()!=null)
            savepastHistory.setPastImages(pastHistory.getPastImages());
        if(pastHistory.getPatient()!=null)
            savepastHistory.setPatient(pastHistory.getPatient());
        return pastHistoryDAO.save(savepastHistory);
    }

    @Transactional
    public List<PastHistory> viewPastHistory(String patientId)
    {
        List<PastHistory> pastHistoryList=pastHistoryDAO.getPastHistoriesByPatient(patientId);
        return pastHistoryList;
    }

    @Transactional
    public void deletePastHistory(Long historyid){
        Optional<PastHistory> optionalPastHistory=pastHistoryDAO.findById(historyid);
        PastHistory pastHistory=optionalPastHistory.orElse(null);
        pastHistoryDAO.deleteByPastHistoryId(historyid);
    }

    @Override
    public SymptomImages addSymptomImages(String patientId,SymptomImages symptomImages)
    {
        Patient patient=patientDAO.findPatientDetailsById(patientId);
        symptomImages.setPatient(patient);
        return symptomImagesDAO.save(symptomImages);
    }

    @Override
    public SymptomImages editSymptomImages(Integer id,SymptomImages symptomImages)
    {
        Optional<SymptomImages> optionalSymptomImages=symptomImagesDAO.findById(id);
        SymptomImages savesymptomImages=optionalSymptomImages.orElse(null);
        if(symptomImages.getImage()!=null)
            savesymptomImages.setImage(symptomImages.getImage());
        if(symptomImages.getDescription()!=null)
            savesymptomImages.setDescription(symptomImages.getDescription());
        if(symptomImages.getPatient()!=null)
            savesymptomImages.setPatient(symptomImages.getPatient());
        return symptomImagesDAO.save(savesymptomImages);
    }

    @Transactional
    public List<SymptomImages> viewSymptomImages(String patientId){
        List<SymptomImages> symptomImages=symptomImagesDAO.getSymptomImagesByPatient(patientId);
        return symptomImages;
    }

    @Transactional
    public void deleteSymptomImages(Integer id)
    {
        Optional<SymptomImages> optionalSymptomImages=symptomImagesDAO.findById(id);
        SymptomImages symptomImages=optionalSymptomImages.orElse(null);
        symptomImagesDAO.deleteBySymptomImageId(id);
    }

    @Override
    public PastImages addPastImages(Long historyId,PastImages pastImages){
        Optional<PastHistory> pastHistoryOptional;
        pastHistoryOptional = pastHistoryDAO.findById(historyId);
        PastHistory pastHistory = pastHistoryOptional.orElse(null);
        pastImages.setPastHistory(pastHistory);
        return pastImagesDAO.save(pastImages);
    }

    @Override
    public PastImages editPastImages(Integer imgId,PastImages pastImages)
    {
        Optional<PastImages> optionalPastImages=pastImagesDAO.findById(imgId);
        PastImages savepastImages=optionalPastImages.orElse(null);
        if(pastImages.getPastImg()!=null)
            savepastImages.setPastImg(pastImages.getPastImg());
        if(pastImages.getPastHistory()!=null)
            savepastImages.setPastHistory(pastImages.getPastHistory());
        return pastImagesDAO.save(savepastImages);
    }

    @Transactional
    public List<PastImages> viewPastImages(Integer historyId)
    {
        return pastImagesDAO.getPastImagesByPastHistory(historyId);

    }

    @Transactional
    public void deletePastImages(Integer imgId)
    {
        pastImagesDAO.deleteByPastImgId(imgId);

    }
    @Transactional
    public List<Test> viewTestName(String patientId)
    {
        List<Test> tests=testDAO.findNamesByPatientId(patientId);
        return tests;

    }


    @Transactional
    public List<Test> viewTest(String patientId)
    {
        List<Test> tests=testDAO.getTestByPatient(patientId);
        return tests;

    }

    @Override
    public Test addTestResult(Integer id,Test test)
    {
        Optional<Test> optionaltests=testDAO.findById(id);
        Test savetest=optionaltests.orElse(null);
        savetest.setResult(test.getResult());
        return testDAO.save(savetest);
    }

    @Override
    public Test editTestResult(Integer id,Test test)
    {
        Optional<Test> optionaltests=testDAO.findById(id);
        Test savetest=optionaltests.orElse(null);
        if(test.getResult()!=null)
            savetest.setResult(test.getResult());
        return testDAO.save(savetest);
    }

    @Transactional
    public void deleteTestResult(Integer id)
    {
        testDAO.deleteTestResultById(id);

    }

    @Override
    public TestImages addTestImages(Integer id,TestImages testImages){
        Optional<Test> optionalTest=testDAO.findById(id);
        Test test=optionalTest.orElse(null);
        testImages.setTests(test);
        return testImagesDAO.save(testImages);
    }

    @Override
    public TestImages editTestImages(Long testimageId,TestImages testImages){
        Optional<TestImages> optionalTest=testImagesDAO.findById(testimageId);
        TestImages savetestimages=optionalTest.orElse(null);
        if(testImages.getImage()!=null)
            savetestimages.setImage(testImages.getImage());
        return testImagesDAO.save(savetestimages);

    }

    @Transactional
    public List<TestImages> viewTestImages(Integer id)
    {
        return testImagesDAO.findTestById(id);
    }

    @Transactional
    public void deleteTestImages(Long testimageId)
    {
        testImagesDAO.deleteTestImageById(testimageId);
    }



}