package com.had.his.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.time.LocalDateTime;
=======
>>>>>>> 8e0f9a839520fed7932bb660778a56592ca8bdb2
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

<<<<<<< HEAD
@Service
public class OtpService {


    // Map to store the latest OTP along with its expiration time
    private static final Map<String, OtpData> otpMap = new HashMap<>();

    // OTP expiration time in minutes
    private static final int OTP_EXPIRATION_MINUTES = 2;
=======
public class OtpService {

    

    // Map to store generated OTPs with patient mobile numbers
    private static final Map<String, String> otpMap = new HashMap<>();
>>>>>>> 8e0f9a839520fed7932bb660778a56592ca8bdb2

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
<<<<<<< HEAD
        // Remove any previous OTP for the same mobile number
        otpMap.remove(mobileNumber);

        // Generate new OTP
        String otp = generateOTP();
        Message message = Message.creator(
                        new PhoneNumber("+91" + mobileNumber),
                        new PhoneNumber(TWILIO_PHONE_NUMBER),
                        "Your OTP for verification is: " + otp)
=======
        String otp = generateOTP();
        Message message = Message.creator(
                        new PhoneNumber(mobileNumber),
                        new PhoneNumber(TWILIO_PHONE_NUMBER),
                        "Your OTP is: " + otp)
>>>>>>> 8e0f9a839520fed7932bb660778a56592ca8bdb2
                .create();

        System.out.println("OTP sent successfully to " + mobileNumber + ": " + message.getSid());

<<<<<<< HEAD
        // Store the generated OTP with its expiration time
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(OTP_EXPIRATION_MINUTES);
        otpMap.put(mobileNumber, new OtpData(otp, expirationTime));
=======
        // Store the generated OTP with the patient's mobile number
        otpMap.put(mobileNumber, otp);
>>>>>>> 8e0f9a839520fed7932bb660778a56592ca8bdb2
    }

    // Verify OTP
    public static boolean verifyOTP(String mobileNumber, String userInput) {
<<<<<<< HEAD
        OtpData otpData = otpMap.get(mobileNumber);
        if (otpData != null && otpData.isValid()) {
            if (userInput.equals(otpData.getOtp())) {
                // Check if OTP is not expired
                if (LocalDateTime.now().isBefore(otpData.getExpirationTime())) {
                    // OTP is valid and not expired
                    return true;
                } else {
                    // OTP is expired, remove it from the map
                    otpMap.remove(mobileNumber);
                }
            }
        }
        return false;
    }

    // Inner class to hold OTP data along with its expiration time
    private static class OtpData {
        private final String otp;
        private final LocalDateTime expirationTime;

        public OtpData(String otp, LocalDateTime expirationTime) {
            this.otp = otp;
            this.expirationTime = expirationTime;
        }

        public String getOtp() {
            return otp;
        }

        public LocalDateTime getExpirationTime() {
            return expirationTime;
        }

        public boolean isValid() {
            return otp != null && expirationTime != null;
        }
    }
}

=======
        String storedOTP = otpMap.get(mobileNumber);
        return userInput.equals(storedOTP);
    }
}
>>>>>>> 8e0f9a839520fed7932bb660778a56592ca8bdb2
