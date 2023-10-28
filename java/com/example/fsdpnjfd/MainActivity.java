package com.example.fsdpnjfd;

import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;
import static java.time.temporal.ChronoField.SECOND_OF_MINUTE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    TimeService timeService = new DefaultTimeService(new NtpTimeRepository());
    DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendValue(HOUR_OF_DAY, 2)
            .appendLiteral(':')
            .appendValue(MINUTE_OF_HOUR, 2)
            .optionalStart()
            .appendLiteral(':')
            .appendValue(SECOND_OF_MINUTE, 2)
            .optionalStart()
            .toFormatter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /*  LocalTime time = timeService.getTime();
        //convert time to string
        String timeString = formatter.format(time);
        //display time
        System.out.println("timeString = " + timeString);
        final TextView textView = (TextView) findViewById(R.id.timeField);
        textView.setText(timeString);
        */
        updateTime();
    }

    public void updateTime() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
                                      @Override
                                      public void run() {
                                          LocalTime time = timeService.getTime();
                                          //convert time to string
                                          String timeString = formatter.format(time);
                                          //display time
                                          System.out.println("timeString = " + timeString);
                                          final TextView textView = (TextView) findViewById(R.id.timeField);
                                          textView.setText(timeString);
                                      }
                                  },
                0, 1000);

    }

}
