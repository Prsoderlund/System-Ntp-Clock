package com.example.fsdpnjfd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalTime;
import java.util.Optional;

public class NtpTimeRepository implements TimeRepository {

    ObjectMapper objectMapper
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
            System.out.println("content = " + content.toString());
            return Optional.empty();
            //parse data currentDateTime
            //convert to local timezone

        } catch (Exception e) {
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
