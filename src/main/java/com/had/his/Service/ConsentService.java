package com.had.his.Service;


import com.had.his.Entity.Consent;

public interface ConsentService {

    Consent createConsent(String pid);
    boolean verifyConsent(String pid, String token);

}
