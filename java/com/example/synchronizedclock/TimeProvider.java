package com.example.synchronizedclock;

import java.time.LocalTime;
import java.util.Optional;

public interface TimeProvider {
    public Optional<LocalTime> getTime();

}
