package com.example.synchronizedclock;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NtpResponse {
    private String currentDateTime;
    private long currentFileTime;
    public NtpResponse() {}
    public NtpResponse(String currentDateTime, long currentFileTime) {
        this.currentDateTime = currentDateTime;
        this.currentFileTime = currentFileTime;
    }

    public String getCurrentDateTime() {
        return currentDateTime;
    }


    public void setCurrentDateTime(String currentDateTime) {
        this.currentDateTime = currentDateTime;
    }

    public long getCurrentFileTime() {
        return currentFileTime;
    }

    public void setCurrentFileTime(long currentFileTime) {
        this.currentFileTime = currentFileTime;
    }
}
