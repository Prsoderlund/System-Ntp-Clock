package com.example.fsdpnjfd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)


public class NtpTime {
    private String currentDateTime;

    public NtpTime() {}
    public NtpTime(String currentDateTime) {
        this.currentDateTime = currentDateTime;
    }

    public String getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(String currentDateTime) {
        this.currentDateTime = currentDateTime;
    }
}
