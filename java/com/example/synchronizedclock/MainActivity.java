package com.example.synchronizedclock;

import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;
import static java.time.temporal.ChronoField.SECOND_OF_MINUTE;
import androidx.core.content.ContextCompat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    TimeService timeService = new DefaultTimeService(new NtpTimeProvider());
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
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
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
        TimeHolder timeHolder = timeService.getTime();
        LocalTime time = timeHolder.getTime();
        boolean isNtpTime = timeHolder.isNtpTime();
        //convert time to string
        String timeString = formatter.format(time);
        //display time
        final TextView textView = (TextView) findViewById(R.id.timeField);
        System.out.println("timeString = " + timeString);


        int ntpColorResource = R.color.ntpColor;
        int localColorResource = R.color.localColor;

        int ntpColor = ContextCompat.getColor(this, ntpColorResource);
        int localColor = ContextCompat.getColor(this, localColorResource);

        if (isNtpTime) {
            System.out.println("ntpColor = " + ntpColor);
            textView.setTextColor(ntpColor);
        } else {
            System.out.println("localColor = " + localColor);
            textView.setTextColor(localColor);
        }
        textView.setText(timeString);
    }

}

