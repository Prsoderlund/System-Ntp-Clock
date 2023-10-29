package com.example.synchronizedclock;

import java.time.LocalTime;
import java.util.Optional;

public class SystemTimeProvider implements TimeProvider {
    @Override
    public Optional<LocalTime>  getTime() {
        return Optional.of(LocalTime.now());
    }
}
