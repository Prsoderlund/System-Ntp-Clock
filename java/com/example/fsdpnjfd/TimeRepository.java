package com.example.fsdpnjfd;

import java.time.LocalTime;
import java.util.Optional;

public interface TimeRepository {
    public Optional<LocalTime> getTime();

}
