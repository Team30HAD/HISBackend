package com.had.his.Response;


public class LoginResponse {

    String message;
    Boolean status;
    String nurseId;

    public LoginResponse(String message, Boolean status, String nurseId) {
        this.message = message;
        this.status = status;
        this.nurseId = nurseId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", nurseId='" + nurseId + '\'' +
                '}';
    }
}
