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
    public LocalTime getTime() {
        //try to get ntp time
        Optional<LocalTime> ntpTime = ntpTimeRepo.getTime();
        return ntpTime.orElse(LocalTime.now());
    }
}
