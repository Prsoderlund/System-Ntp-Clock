package com.example.synchronizedclock;

import java.time.LocalTime;
import java.util.Optional;

public class DefaultTimeService implements TimeService {
    //TimeRepository localTimeRepo = new SystemTimeRepository();
    TimeProvider ntpTimeRepo;
    public DefaultTimeService(TimeProvider ntpTimeRepository){
        this.ntpTimeRepo = ntpTimeRepository;
    }
    @Override
    public TimeHolder getTime() {
        //try to get ntp time
        Optional<LocalTime> ntpTime = ntpTimeRepo.getTime();
        if (ntpTime.isPresent()) {
            return TimeHolder.ntp(ntpTime.get());
        }
        else return  TimeHolder.local(LocalTime.now());
    }
}
