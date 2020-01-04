/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.flooringmastery.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class UserIOConsoleImpl implements UserIO {

    // instantiate new Scanner object
    Scanner sc = new Scanner(System.in);

    // print message
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    // prompt user and return response
    @Override
    public double readDouble(String prompt) {
        String responseString;
        double responseDouble = 0;
        boolean isValid = false;

        do {
            try {
                try {
                    System.out.println(prompt);
                    responseString = sc.nextLine();
                    responseDouble = Float.parseFloat(responseString);
                    isValid = true;
                } catch (NumberFormatException e) {
                    throw new UnsupportedIOFormatException("ERROR!!! NOT A NUMBER!!!", e);
                }
            } catch (UnsupportedIOFormatException ex) {
                System.out.println(ex.getMessage());
            }

        } while (!isValid);

        return responseDouble;
    }

    // prompt user and return response between min and max, inclusive of both
    @Override
    public double readDouble(String prompt, double min, double max) {
        String responseString;
        double responseDouble = 0;
        boolean isValid = false;

        do {
            try {
                try {
                    System.out.println(prompt);
                    responseString = sc.nextLine();
                    responseDouble = Float.parseFloat(responseString);
                    if (responseDouble >= min && responseDouble <= max) {
                        isValid = true;
                    } else {
                        throw new UnsupportedIOFormatException("Must enter number within range.");
                    }
                } catch (NumberFormatException e) {
                    throw new UnsupportedIOFormatException("ERROR!!! NOT A NUMBER!!!", e);
                }
            } catch (UnsupportedIOFormatException ex) {
                System.out.println(ex.getMessage());
            }

        } while (!isValid);

        return responseDouble;
    }

    // prompt user and return response
    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        String responseString = sc.nextLine();
        float responseFloat = Float.parseFloat(responseString);
        return responseFloat;
    }

    // prompt user and return response between min and max, inclusive of both
    @Override
    public float readFloat(String prompt, float min, float max) {
        boolean isValid = false;
        float responseFloat;
        do {
            System.out.println(prompt);
            String responseString = sc.nextLine();
            responseFloat = Float.parseFloat(responseString);
            if (responseFloat >= min && responseFloat <= max) {
                isValid = true;
            }
        } while (!isValid);
        return responseFloat;
    }

    // prompt user and return response
    @Override
    public int readInt(String prompt) {
        String responseString;
        int responseInt = 0;
        boolean isValid = false;

        do {
            try {
                try {
                    System.out.println(prompt);
                    responseString = sc.nextLine();
                    responseInt = Integer.parseInt(responseString);
                    isValid = true;
                } catch (NumberFormatException e) {
                    throw new UnsupportedIOFormatException("ERROR!!! NOT A NUMBER!!!", e);
                }
            } catch (UnsupportedIOFormatException ex) {
                System.out.println(ex.getMessage());
            }

        } while (!isValid);

        return responseInt;
    }

    // prompt user and return response between min and max, inclusive of both
    @Override
    public int readInt(String prompt, int min, int max) {
        String responseString;
        int responseInt = 0;
        boolean isValid = false;

        do {
            try {
                try {
                    System.out.println(prompt);
                    responseString = sc.nextLine();
                    responseInt = Integer.parseInt(responseString);
                    if (responseInt >= min && responseInt <= max) {
                        isValid = true;
                    } else {
                        throw new UnsupportedIOFormatException("Must enter number within range.");
                    }
                } catch (NumberFormatException e) {
                    throw new UnsupportedIOFormatException("ERROR!!! NOT A NUMBER!!!", e);
                }
            } catch (UnsupportedIOFormatException ex) {
                System.out.println(ex.getMessage());
            }

        } while (!isValid);

        return responseInt;
    }

    // prompt user and return response
    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        String responseString = sc.nextLine();
        long responseLong = Long.parseLong(responseString);
        return responseLong;
    }

    // prompt user and return response between min and max, inclusive of both
    @Override
    public long readLong(String prompt, long min, long max) {
        boolean isValid = false;
        long responseLong;
        do {
            System.out.println(prompt);
            String responseString = sc.nextLine();
            responseLong = Long.parseLong(responseString);
            if (responseLong >= min && responseLong <= max) {
                isValid = true;
            }
        } while (!isValid);
        return responseLong;
    }

    // prompt user and return response
    @Override
    public String readString(String prompt) {
        String responseString = "";
        boolean isValid = false;

        do {
            try {
                System.out.println(prompt);
                responseString = sc.nextLine();
                if (!responseString.contains(":")) {
                    isValid = true;
                } else {
                    throw new UnsupportedIOFormatException("ERROR!!! YOU CANNOT ENTER A : !!!");
                }
            } catch (UnsupportedIOFormatException ex) {
                System.out.println(ex.getMessage());
            }

        } while (!isValid);

        return responseString;
    }

    // prompt user and return response - response cannot be blank ("")
    @Override
    public String readStringNoBlanks(String prompt) {
        String responseString = "";
        boolean isValid = false;

        do {
            try {
                System.out.println(prompt);
                responseString = sc.nextLine();
                if (!responseString.contains(":") && !responseString.isEmpty() && !responseString.isBlank()) {
                    isValid = true;
                } else {
                    throw new UnsupportedIOFormatException("ERROR!!! YOU CANNOT ENTER A : AND RESPONSE CANNOT BE BLANK!!!");
                }
            } catch (UnsupportedIOFormatException ex) {
                System.out.println(ex.getMessage());
            }

        } while (!isValid);

        return responseString;
    }

    // prompt user and return response - response must be LocalDate formatted as "MM/dd/yyyy"
    @Override
    public LocalDate readLocalDate(String prompt) {
        String responseString;
        LocalDate responseLocalDate = null;
        boolean isValid = false;

        do {
            try {
                try {
                    System.out.println(prompt);
                    responseString = sc.nextLine();
                    responseLocalDate = LocalDate.parse(responseString, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                    isValid = true;
                } catch (DateTimeParseException e) {
                    throw new UnsupportedIOFormatException("ERROR!!! NOT A DATE IN THE FORMAT REQUESTED!!!", e);
                }
            } catch (UnsupportedIOFormatException ex) {
                System.out.println(ex.getMessage());
            }

        } while (!isValid);

        return responseLocalDate;
    }

    // prompt user and return BigDecimal
    @Override
    public BigDecimal readBigDecimalPositive(String prompt) {
        String responseString;
        boolean isValid = false;
        BigDecimal responseBigDecimal = new BigDecimal(0);

        do {
            try {
                try {
                    System.out.println(prompt);
                    responseString = sc.nextLine();
                    responseBigDecimal = new BigDecimal(responseString);
                    if (responseBigDecimal.compareTo(new BigDecimal(0)) > 0) {
                        isValid = true;
                    } else {
                        throw new UnsupportedIOFormatException("Must enter number greater than zero.");
                    }
                } catch (NumberFormatException e) {
                    throw new UnsupportedIOFormatException("ERROR!!! NOT A NUMBER!!!", e);
                }
            } catch (UnsupportedIOFormatException ex) {
                System.out.println(ex.getMessage());
            }

        } while (!isValid);

        return responseBigDecimal;
    }

    // prompt user and return BigDecimal - if user types blank or empty string, return BigDecimal null
    @Override
    public BigDecimal readBigDecimalOrNull(String prompt) {
        String responseString;
        boolean isValid = false;
        BigDecimal responseBigDecimal = new BigDecimal(0);

        do {
            try {
                try {
                    System.out.println(prompt);
                    responseString = sc.nextLine();
                    if (!responseString.isEmpty() && !responseString.isBlank()) {
                        responseBigDecimal = new BigDecimal(responseString);
                        if (responseBigDecimal.compareTo(new BigDecimal(0)) > 0) {
                            isValid = true;
                        } else {
                            throw new UnsupportedIOFormatException("Must enter number greater than zero.");
                        }
                    } else {
                        responseBigDecimal = new BigDecimal(0);
                        isValid = true;
                    }
                } catch (NumberFormatException e) {
                    throw new UnsupportedIOFormatException("ERROR!!! NOT A NUMBER!!!", e);
                }
            } catch (UnsupportedIOFormatException ex) {
                System.out.println(ex.getMessage());
            }

        } while (!isValid);

        return responseBigDecimal;
    }

    // prompt user and return response - response must either be blank/empty or formatted as "MM/dd/yyyy"
    @Override
    public LocalDate readLocalDateOrNull(String prompt) {
        String responseString;
        LocalDate responseLocalDate = null;
        boolean isValid = false;

        do {
            try {
                try {
                    System.out.println(prompt);
                    responseString = sc.nextLine();
                    if (!responseString.isEmpty() && !responseString.isBlank()) {
                        responseLocalDate = LocalDate.parse(responseString, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                    } else {
                        responseLocalDate = null;
                    }
                    isValid = true;
                } catch (DateTimeParseException e) {
                    throw new UnsupportedIOFormatException("ERROR!!! NOT A DATE IN THE FORMAT REQUESTED!!!", e);
                }
            } catch (UnsupportedIOFormatException ex) {
                System.out.println(ex.getMessage());
            }

        } while (!isValid);

        return responseLocalDate;
    }

    // prompt user and return response - response must be either y/n
    @Override
    public String readStringYesOrNo(String prompt) {
        String responseString = "";
        boolean isValid = false;

        do {
            try {
                System.out.println(prompt);
                responseString = sc.nextLine();
                responseString = responseString.toLowerCase();
                switch (responseString) {
                    case "y":
                        responseString = "y";
                        break;
                    case "yes":
                        responseString = "y";
                        break;
                    case "n":
                        responseString = "n";
                        break;
                    case "no":
                        responseString = "n";
                        break;
                    default:
                        throw new UnsupportedIOFormatException("ERROR!!! YOU MUST EITHER ENTER Y, N, YES, NO (NOT CASE SENSITIVE)");
                }

                isValid = true;
            } catch (UnsupportedIOFormatException ex) {
                System.out.println(ex.getMessage());
            }

        } while (!isValid);

        return responseString;
    }

    // prompt user and return response - response must be in present or future
    @Override
    public LocalDate readLocalDateOnlyPresentOrFuture(String prompt) {
        String responseString;
        LocalDate responseLocalDate = null;
        boolean isValid = false;

        do {
            try {
                try {
                    System.out.println(prompt);
                    responseString = sc.nextLine();
                    responseLocalDate = LocalDate.parse(responseString, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                    LocalDate now = LocalDate.now();
                    if (responseLocalDate.compareTo(now) >= 0) {
                        isValid = true;
                    } else {
                        throw new UnsupportedIOFormatException("ERROR!!! DATE IS IN THE PAST!!!");
                    }
                } catch (DateTimeParseException e) {
                    throw new UnsupportedIOFormatException("ERROR!!! NOT A DATE IN THE FORMAT REQUESTED!!!", e);
                }
            } catch (UnsupportedIOFormatException ex) {
                System.out.println(ex.getMessage());
            }

        } while (!isValid);

        return responseLocalDate;
    }

    // prompt user and return response - response can contain :
    @Override
    public String readStringColonsAllowed(String prompt) {
        System.out.println(prompt);
        String responseString = sc.nextLine();
        return responseString;
    }

}
