/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.datetimecodealong;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Daniel Bart
 */
public class App {
    
    public static void main(String[] args) {
        
        // LocalDate - local date in your locale
        LocalDate ld = LocalDate.now();
        
        System.out.println(ld);
        
        // creates LocalDate instance
        ld = LocalDate.parse("2015-01-01");
        
        System.out.println(ld);
        
        // create LocalDate object from date with different format, uses formatter and static method
        ld = LocalDate.parse("02/07/2010", DateTimeFormatter.ofPattern("MM/dd/yyyy"));        
        // normalized to ISO format
        System.out.println(ld);
        
        // convert date to string and back
        String isoDate = ld.toString();
        System.out.println(isoDate);
        ld = LocalDate.parse(isoDate);
        System.out.println(ld);
        
        // formatting and printing dates
        // print LocalDate in MM/dd/yyyy
        System.out.println("+________________________________________");
        String formatted = ld.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println(formatted);
        // convert back to ISO format
        ld = LocalDate.parse(formatted, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println("HAHAHAHAHA");
        System.out.println(ld);
        // pattern can be anything we need
        formatted = ld.format(DateTimeFormatter.ofPattern("MM=dd=yyyy+=+=+=+="));
        System.out.println(formatted);
        
        // localize date from localized machine
        // FormatStyle is enum of Constants
        formatted = ld.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.println(formatted);
        
        // take existing date and calculate dates in future and/or past
        LocalDate past = ld.minusDays(8);
        formatted = past.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.println(formatted);
        
        past = ld.minusMonths(3);
        formatted = past.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.println(formatted);
        
        // calculate time between dates
        // Period is period of time between two dates
        Period difference = ld.until(past);
        System.out.println(difference);
        System.out.println(difference.getMonths());
        // takes past and calculates time until now from LocalDate
        difference = past.until(ld);
        System.out.println(difference.getMonths());
        
        // LocalDateTime and converting between modern API and legacy API
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        // format LocalDateTime
        formatted = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        System.out.println(formatted);
        
        // convert between modern API and legacy API
        Date legacyDate = new Date();
        System.out.println(legacyDate);
        
        GregorianCalendar legacyCalendar = new GregorianCalendar();
        System.out.println(legacyCalendar);
        
        // turn legacyDate into ZonedDateTime
        // turn legacyDate into Instant in time first
        ZonedDateTime zdt = ZonedDateTime.ofInstant(legacyDate.toInstant(), ZoneId.systemDefault());
        // turn into LocalDate
        ld = zdt.toLocalDate();
        System.out.println(ld);
        
        // turn GregorianCalendar into ZonedDateTime
        // use method to convert to ZonedDateTime
        zdt = legacyCalendar.toZonedDateTime();
        ld = zdt.toLocalDate();
        System.out.println(ld);
        
    }
    
}
