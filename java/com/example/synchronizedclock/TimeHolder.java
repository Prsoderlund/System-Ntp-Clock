package com.example.synchronizedclock;

import java.time.LocalTime;

public class TimeHolder {
    private LocalTime time;
    private boolean ntpTime;

    private TimeHolder(LocalTime time, boolean ntpTime) {
        this.time = time;
        this.ntpTime = ntpTime;
    }

    public static TimeHolder ntp(LocalTime time){
        return new TimeHolder(time,true);
    }
    public static TimeHolder local(LocalTime time){
        return new TimeHolder(time,false);
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
