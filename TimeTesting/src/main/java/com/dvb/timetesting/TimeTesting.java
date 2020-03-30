/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.timetesting;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class TimeTesting {

    public static void main(String[] args) {

        boolean keepGoing = true;

        while (keepGoing) {
            Scanner sc = new Scanner(System.in);

            // determine if maintenance page shows or not
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.US);
            String timeNow = ZonedDateTime.now(ZoneId.of("America/Chicago")).format(formatter);
            LocalTime startTime = LocalTime.parse("00:00:00", formatter);
            LocalTime endTime = LocalTime.parse("06:00:00", formatter);
            LocalTime checkTime = LocalTime.parse(timeNow, formatter);

            System.out.println("startTime: " + startTime);
            System.out.println("endTime: " + endTime);
            System.out.println("checkTime: " + checkTime);

            boolean isInBetween = false;

            if (startTime.compareTo(checkTime) <= 0 && endTime.compareTo(checkTime) >= 0) {
                isInBetween = true;
            }

            if (isInBetween) {
                System.out.println("MAINTENANCE");
            } else {
                System.out.println("NO MAINTENANCE");
            }

            System.out.println("Do you want to continue (Y/N)? ");
            String keepGoingString = sc.nextLine();

            if (keepGoingString.equalsIgnoreCase("n") || keepGoingString.equalsIgnoreCase("no")) {
                keepGoing = false;
            }
        }

    }

}
