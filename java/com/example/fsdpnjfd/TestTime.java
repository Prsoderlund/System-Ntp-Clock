package com.example.fsdpnjfd;

import java.time.LocalTime;

public class TestTime {
    private LocalTime time;
    private boolean ntpTime;

    public TestTime(LocalTime time, boolean ntpTime) {
        this.time = time;
        this.ntpTime = ntpTime;
    }


    public boolean isNtpTime() {
        return ntpTime;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
