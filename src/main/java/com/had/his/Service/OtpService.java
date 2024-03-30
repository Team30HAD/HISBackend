package com.had.his.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class OtpService {
    

    // Map to store generated OTPs with patient mobile numbers
    private static final Map<String, String> otpMap = new HashMap<>();

    // Initialize Twilio
    static {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    // Generate a random 6-digit OTP
    public static String generateOTP() {
        Random random = new Random();
        int otp = 100_000 + random.nextInt(900_000);
        return String.valueOf(otp);
    }

    // Send OTP via SMS
    public static void sendOTP(String mobileNumber) {
        String otp = generateOTP();
        Message message = Message.creator(
                        new PhoneNumber(mobileNumber),
                        new PhoneNumber(TWILIO_PHONE_NUMBER),
                        "Your OTP is: " + otp)
                .create();

        System.out.println("OTP sent successfully to " + mobileNumber + ": " + message.getSid());

        // Store the generated OTP with the patient's mobile number
        otpMap.put(mobileNumber, otp);
    }

    // Verify OTP
    public static boolean verifyOTP(String mobileNumber, String userInput) {
        String storedOTP = otpMap.get(mobileNumber);
        return userInput.equals(storedOTP);
    }
}