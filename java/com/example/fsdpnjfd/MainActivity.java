package com.example.fsdpnjfd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.time.LocalTime;

public class MainActivity extends AppCompatActivity {
    TimeService timeService = new DefaultTimeService(new NtpTimeRepository());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocalTime time = timeService.getTime();
        //convert time to string
        //display time
    }
}