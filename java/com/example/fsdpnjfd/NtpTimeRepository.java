package com.example.fsdpnjfd;

import java.time.LocalTime;
import java.util.Optional;

public class NtpTimeRepository implements TimeRepository {
    @Override
    public Optional<LocalTime>  getTime() {
        return Optional.empty();
    }
}
