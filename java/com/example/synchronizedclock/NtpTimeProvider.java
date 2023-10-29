package com.example.synchronizedclock;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

public class NtpTimeProvider implements TimeProvider {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Optional<LocalTime>  getTime() {
        //connect to ntp
        URL url = null;
        HttpURLConnection con = null;
        try {
            url = new URL("http://worldclockapi.com/api/json/cet/now");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            NtpResponse ntpResponse = objectMapper.readValue(content.toString(), NtpResponse.class);

            //content = 2023-10-28T23:07+02:00
            //LocalTime time = LocalTime.parse(ntpResponse.getCurrentDateTime(), DateTimeFormatter.ISO_DATE_TIME);
            Date date = new Date(ntpResponse.getCurrentFileTime()/10000);// LocalTime.ofNanoOfDay(ntpResponse.getCurrentFileTime())
            LocalTime time = LocalDateTime.ofInstant(new Date().toInstant(),
                    ZoneId.systemDefault()).toLocalTime();
            return Optional.of(time);
            //parse data currentDateTime
            //convert to local timezone

        } catch (Exception e) {
            System.out.println("unable to connect to ntp");
            e.printStackTrace();
            return Optional.empty();
        } finally {
            if (con != null) {
                try {
                    con.disconnect();
                } catch (Exception ignore) {
                }
            }
        }

    }

}
