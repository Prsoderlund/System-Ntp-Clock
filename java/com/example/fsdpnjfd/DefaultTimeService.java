package com.example.fsdpnjfd;

import java.time.LocalTime;
import java.util.Optional;

public class DefaultTimeService implements TimeService {
    //TimeRepository localTimeRepo = new SystemTimeRepository();
    TimeRepository ntpTimeRepo;
    public DefaultTimeService(TimeRepository ntpTimeRepository){
        this.ntpTimeRepo = ntpTimeRepository;
    }
    @Override
    public TestTime getTime() {
        //try to get ntp time
        Optional<LocalTime> ntpTime = ntpTimeRepo.getTime();
        if (ntpTime.isPresent()) {
            return new TestTime(ntpTime.get(), true);
        }
        else return new TestTime(LocalTime.now(), false);
    }
}
