package com.had.his.Notification;

import com.had.his.DAO.DoctorDAO;
import com.had.his.DAO.PatientDAO;
import com.had.his.DAO.VisitDAO;
import com.had.his.Entity.Doctor;
import com.had.his.Entity.Patient;
import com.had.his.Entity.Visit;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmergencyService {

    


    @Autowired
    private VisitDAO visitDAO;

    @Autowired
    private DoctorDAO doctorDAO;

    public void sendemergencyalert(String patientId){

        String doctorId=visitDAO.findDoctorOfActivePatient(patientId);

        Doctor doctor =doctorDAO.findByDoctorId(doctorId);
        String mobile= doctor.getContact();

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                        new PhoneNumber("+91"+mobile),
                        new PhoneNumber(TWILIO_PHONE_NUMBER),
                        patientId+" "+"is serious ,Please contact with the indoor ward immediately to assist the House staff.")

                .create();
        System.out.println(message.getSid());
        System.out.println("Notification sent to doctor with contact"+ mobile);
    }
}
