package com.dvb.birthstones;

import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class BirthStones {

    public static void main(String[] args) {
        int monthNumber;
        String monthName = "", birthStone = "";

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a Number: ");
        String monthNumberString = sc.nextLine();
        monthNumber = Integer.parseInt(monthNumberString);

        switch (monthNumber) {
            case 1:
                monthName = "January";
                birthStone = "Garnet";
                break;
            case 2:
                monthName = "February";
                birthStone = "Amethyst";
                break;
            case 3:
                monthName = "March";
                birthStone = "Aquamarine";
                break;
            case 4:
                monthName = "April";
                birthStone = "Diamond";
                break;
            case 5:
                monthName = "May";
                birthStone = "Emerald";
                break;
            case 6:
                monthName = "June";
                birthStone = "Pearl";
                break;
            case 7:
                monthName = "July";
                birthStone = "Ruby";
                break;
            case 8:
                monthName = "August";
                birthStone = "Peridot";
                break;
            case 9:
                monthName = "September";
                birthStone = "Sapphire";
                break;
            case 10:
                monthName = "October";
                birthStone = "Opal";
                break;
            case 11:
                monthName = "November";
                birthStone = "Topaz";
                break;
            case 12:
                monthName = "December";
                birthStone = "Turquoise";
                break;
            default:
                
        }
        
        switch(monthNumber) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                System.out.println(monthName + "'s birthstone is " + birthStone + ".");
                break;
            default:
                System.out.println("I think you must be confused, " + monthNumber + " doesn't match a month.");
        }
    }

}
