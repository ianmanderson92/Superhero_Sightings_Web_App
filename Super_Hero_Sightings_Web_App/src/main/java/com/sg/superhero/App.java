package com.sg.superhero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

@SpringBootApplication
public class App
{

    @PostConstruct
    public void init(){
        // Setting Spring Boot SetTimeZone
        TimeZone.setDefault(TimeZone.getTimeZone("America/Denver"));
        LocalDateTime now = LocalDateTime.now();
        System.out.println("LocalDateTime.now(): " + now.toString() + "\n");
        ZonedDateTime zonedNow = now.atZone( ZoneId.systemDefault() );
        System.out.println("ZonedDateTime @ default: " + zonedNow.toString() + "\n");
        //System.out.println("Time Zone Id: " + ZoneId.getAvailableZoneIds() );
        System.out.println("Current Time: " + ZonedDateTime.now(ZoneId.of("America/Denver")) + "\n");
        System.out.println("Date in UTC: " + new Date() + "\n");
        //TimeZone currentZone = new TimeZone();
        System.out.println("Spring Boot Currently using " + TimeZone.getDefault());
    }

    public static void main( String[] args )
    {
        SpringApplication.run( App.class, args );
    }//End of Main


}//End of App
