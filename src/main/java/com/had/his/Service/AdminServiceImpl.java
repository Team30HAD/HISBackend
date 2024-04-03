package com.had.his.Service;

import com.had.his.DAO.*;
import com.had.his.DTO.LoginDTO;
import com.had.his.Entity.*;
import com.had.his.Response.LoginResponse;
import com.had.his.Role.UserRole;
import com.had.his.Security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private DoctorDAO doctorDAO;
    @Autowired
    private NurseDAO nurseDAO;
    @Autowired
    private ReceptionistDAO receptionistDAO;
    @Autowired
    private PharmacyDAO pharmacyDAO;
    @Autowired
    private PatientDAO patientDAO;
    @Autowired
    private NurseScheduleDAO nurseScheduleDAO;
    @Autowired
    private AdminDAO adminDAO;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private HospitalDAO hospitalDAO;
    @Autowired
    private SpecializationDAO specializationDAO;

    private static int adminCounter=0;

    private String generateNextAdminId() {
        adminCounter++;
        return "A" + String.format("%02d", adminCounter);
    }

    public Admin saveAdmin(Admin admin)
    {
        admin.setAdminId(generateNextAdminId());
        admin.setRole(UserRole.ADMIN);
        return adminDAO.save(admin);
    }
    public LoginResponse verifyAdmin(LoginDTO credentials) {
        String email = credentials.getEmail();
        String enteredPassword = credentials.getPassword();

        Admin admin = adminDAO.findAdminByEmail(email);
        if (admin != null) {
            if( admin.isPasswordMatch(enteredPassword))
            {
                String jwttoken= jwtTokenProvider.generateToken(admin);
                return new LoginResponse("Login Successful",true,jwttoken);
            } else {
                return new LoginResponse("Password not matched", false, null);
            }
        }
        else{
            return new LoginResponse("Invalid User.", false, null);
        }
    }

    public Doctor saveDoctor(Doctor doc,String specialization) {
        Specialization spec = specializationDAO.getSpecializationByName(specialization);
        doc.setSpecialization(spec);
        doc.setActive(true);
        doc.setAvailability(true);
        doc.setRole(UserRole.DOCTOR);
        if(doc.getPassword()==null){
            doc.setPassword("docpwd");
        }
        return doctorDAO.save(doc);
    }
    public Nurse saveNurse(Nurse nurse) {

        nurse.setActive(true);
        nurse.setRole(UserRole.NURSE);
        if (nurse.getPassword() == null) {
            nurse.setPassword("nursepwd");
        }

        for (NurseSchedule schedule : nurse.getNurseSchedules()) {
            schedule.setNurse(nurse);
        }
        // Save nurse entity along with nurse schedules (cascade)
        return nurseDAO.save(nurse);


    }
    public Receptionist saveReceptionist(Receptionist rec) {
        rec.setActive(true);
        rec.setRole(UserRole.RECEPTIONIST);
        if(rec.getPassword()==null){
            rec.setPassword("recpwd");
        }
        System.out.println(rec.getPhoto());
        return receptionistDAO.save(rec);
    }
    public Pharmacy savePharmacy(Pharmacy pharma) {
        pharma.setActive(true);
        pharma.setRole(UserRole.PHARMACY);
        if(pharma.getPassword()==null){
            pharma.setPassword("pharmapwd");
        }
        return pharmacyDAO.save(pharma);
    }
    public List<Doctor> getAllDoctors(String department) {
        return doctorDAO.getDoctorByDepartmentAndActive(department);
    }
    public List<Nurse> getAllNurses() {
        return nurseDAO.getNurseByActive();
    }
    public List<Receptionist> getAllReceptionists() {
        return receptionistDAO.getReceptionistByActive();
    }
    public List<Pharmacy> getAllPharmacies() {
        return pharmacyDAO.getPharmaciesByActive();
    }
    public Doctor getDoctor(String doctorId)
    {
        return doctorDAO.findByDoctorId(doctorId);
    }
    public Nurse getNurse(String nurseId)
    {
        return nurseDAO.findByNurseId(nurseId);
    }
    public Receptionist getReceptionist(String recepId)
    {
        return receptionistDAO.findByReceptionistId(recepId);
    }
    public Pharmacy getPharmacy(String pharmaId)
    {
        return pharmacyDAO.findPharmacyByPharmacyId(pharmaId);
    }
    public void deactivateDoctor(String doctorId) {
        Doctor newDoctor = doctorDAO.findByDoctorId(doctorId);
        newDoctor.setActive(false);
        doctorDAO.save(newDoctor);
    }

    public void deactivateNurse(String nurseId) {
        Nurse newNurse = nurseDAO.findDetailsById(nurseId);
        newNurse.setActive(false);
        nurseDAO.save(newNurse);
    }

    public void deactivatePharmacy(String pharmaId) {
        Pharmacy newPharmacy = pharmacyDAO.findPharmacyByPharmacyId(pharmaId);
        newPharmacy.setActive(false);
        pharmacyDAO.save(newPharmacy);
    }

    public void deactivateReceptionist(String recepId) {
        Receptionist newReceptionist = receptionistDAO.findReceptionistByReceptionistId(recepId);
        newReceptionist.setActive(false);
        receptionistDAO.save(newReceptionist);
    }
    public long countPatient()
    {
        return patientDAO.count();
    }

    @Override
    public long countDoctor() {
        return doctorDAO.count();
    }

    @Override
    public long countNurse() {
        return nurseDAO.count();
    }

    @Override
    public long countReceptionist()
    {
        return receptionistDAO.count();
    }

    public long countPharmacy()
    {
        return pharmacyDAO.count();
    }

    public Doctor editDoctor(String did,Doctor doctor){
        Doctor newDoctor = doctorDAO.findByDoctorId(did);
        newDoctor.setName(doctor.getName());
        if(newDoctor.getName()!=doctor.getName())
        {
            newDoctor.generateEmail();
        }
        newDoctor.setSex(doctor.getSex());
        newDoctor.setAge(doctor.getAge());
        newDoctor.setQualification(doctor.getQualification());
        newDoctor.setContact(doctor.getContact());
        newDoctor.setSpecialization(doctor.getSpecialization());
        newDoctor.setDepartment(doctor.getDepartment());
        newDoctor.setLicenseNumber(doctor.getLicenseNumber());
        newDoctor.setPhoto(doctor.getPhoto());
        newDoctor.setActive(doctor.getActive());
        return doctorDAO.save(newDoctor);
    }

    public Nurse editNurse(String nid,Nurse nurse){
        Nurse newNurse = nurseDAO.findDetailsById(nid);
        newNurse.setName(nurse.getName());
        if(newNurse.getName()!=nurse.getName())
        {
            newNurse.generateEmail();
        }
        newNurse.setSex(nurse.getSex());
        newNurse.setAge(nurse.getAge());
        newNurse.setPhoto(nurse.getPhoto());
        newNurse.setContact(nurse.getContact());
        //newNurse.setEmail(nurse.getEmail());
        List<NurseSchedule> existingSchedules=nurseScheduleDAO.getNurseScheduleById(newNurse.getNurseId());
        nurseScheduleDAO.deleteAll(existingSchedules);
        List<NurseSchedule> nurseSchedules=nurse.getNurseSchedules();
        for(NurseSchedule schedule:nurseSchedules){
            schedule.setNurse(newNurse);
        }
        newNurse.setNurseSchedules(nurseSchedules);
        return nurseDAO.save(newNurse);
    }

    public Pharmacy editPharmacy(String phid,Pharmacy pharma)
    {

        Pharmacy newPharma = pharmacyDAO.findPharmacyByPharmacyId(phid);
        newPharma.setName(pharma.getName());
        if(newPharma.getName()!=pharma.getName())
        {
            newPharma.generateEmail();
        }
        newPharma.setAddress(pharma.getAddress());
        newPharma.setLicenseNumber(pharma.getLicenseNumber());
        newPharma.setContact(pharma.getContact());
//        newPharma.setEmail(pharma.getEmail());
        return pharmacyDAO.save(newPharma);
    }

    public Receptionist editReceptionist(String rid,Receptionist receptionist)
    {
        Receptionist newRecep = receptionistDAO.findReceptionistByReceptionistId(rid);
        newRecep.setName(receptionist.getName());
        if(newRecep.getName()!=receptionist.getName())
        {
            newRecep.generateEmail();
        }
        newRecep.setSex(receptionist.getSex());
        newRecep.setPhoto(receptionist.getPhoto());
        newRecep.setContact(receptionist.getContact());
        // newRecep.setEmail(receptionist.getEmail());
        newRecep.setAge(receptionist.getAge());

        return receptionistDAO.save(newRecep);
    }

    public Hospital getHospital()
    {
        return hospitalDAO.getHospitalDetails();
    }

    @Override
    public Specialization addSpecialization(Specialization specialization) {
        return specializationDAO.save(specialization);
    }

}


