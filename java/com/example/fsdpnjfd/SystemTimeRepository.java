package com.example.fsdpnjfd;

import java.time.LocalTime;
import java.util.Optional;

public class SystemTimeRepository implements TimeRepository {
    @Override
    public Optional<LocalTime>  getTime() {
        return Optional.of(LocalTime.now());
    }
}
