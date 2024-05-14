package com.had.his.Service;


import com.had.his.Entity.Consent;

public interface ConsentService {

<<<<<<< HEAD
    Consent createConsent(String pid, String email);

    Consent consentforExisting(String pid, String email);

=======
    Consent createConsent(String pid);
>>>>>>> 8e0f9a839520fed7932bb660778a56592ca8bdb2
    boolean verifyConsent(String pid, String token);

}
