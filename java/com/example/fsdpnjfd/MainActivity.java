package com.example.fsdpnjfd;

import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;
import static java.time.temporal.ChronoField.SECOND_OF_MINUTE;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    TimeService timeService = new DefaultTimeService(new NtpTimeRepository());
    String localTimeColor = "#d90d0d";
    String ntpTimeColor = "green";
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
        startTimer();
    }

    public void startTimer() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
                                      @Override
                                      public void run() {
                                          runOnUiThread(new Runnable() {
                                              @Override
                                              public void run() {
                                                  updateTime();
                                              }
                                          });                                      }
                                  }
                ,0, 1000);

    }

    private void updateTime() {
        TestTime testTime = timeService.getTime();
        LocalTime time = testTime.getTime();
        boolean isNtpTime = testTime.isNtpTime();
        //convert time to string
        String timeString = formatter.format(time);
        //display time
        final TextView textView = (TextView) findViewById(R.id.timeField);
        System.out.println("timeString = " + timeString);

        if (isNtpTime) {
            textView.setTextColor(Color.parseColor(ntpTimeColor));
        } else {
            textView.setTextColor(Color.parseColor(localTimeColor));
        }
        textView.setText(timeString);


    }

}
