/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.rockpaperscissorsrefactored;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class RockPaperScissorsRefactored {
    
    // public method to play RockPaperScissors
    public void playRockPaperScissors() {
        // instantiate RockPaperScissors object
        RockPaperScissorsRefactored rockPaperScissorsRefactoredInstance = new RockPaperScissorsRefactored();

        // declare variables and initialize if necessary
        int userPlayAgain, totalRounds, userChoice, computerChoice, currentRound = 1, totalTies = 0, totalUserWins = 0, totalComputerWins = 0;
        boolean isValidUserChoice;
        String roundWinner;

        // play the game once, then if user says yes to start over, play again, if not, the game ends
        do {

            // prompt user how many rounds and store in variable
            totalRounds = rockPaperScissorsRefactoredInstance.promptUser("How many rounds do you wish to play sir/madam (between 1 and 10)? ");

            // detemine if user entered rounds correctly
            if (!rockPaperScissorsRefactoredInstance.isNumberValid(totalRounds, 1, 10)) {
                System.out.println("Please enter between 1 and 10. Exiting program now...");
                System.exit(0);
            }

            // while there are rounds left, play the game
            while (currentRound <= totalRounds) {
                // display round number
                System.out.println("\nThis is round " + currentRound);

                // keep prompting user until they input valid choice
                do {
                    userChoice = rockPaperScissorsRefactoredInstance.promptUser("Please choose (1 for rock, 2 for paper, 3 for scissors) ");
                    isValidUserChoice = rockPaperScissorsRefactoredInstance.isNumberValid(userChoice, 1, 3);
                } while (!isValidUserChoice);

                // print user choice as string
                System.out.println("User chose " + rockPaperScissorsRefactoredInstance.determineStringOfChoice(userChoice));

                // generate random computer choice
                computerChoice = rockPaperScissorsRefactoredInstance.generateRandomChoice();

                // print computer choice as string
                System.out.println("Computer chose " + rockPaperScissorsRefactoredInstance.determineStringOfChoice(computerChoice));

                // compare user's choice to computer's choice (returns String ("computer", "user", "tie" which then can be put in if statement to print and increment variables)
                roundWinner = rockPaperScissorsRefactoredInstance.determineRoundWinner(userChoice, computerChoice);
                if (roundWinner.equals("user")) {
                    totalUserWins++;
                    System.out.println("User Wins");
                } else if (roundWinner.equals("computer")) {
                    totalComputerWins++;
                    System.out.println("Computer Wins");
                } else {
                    totalTies++;
                    System.out.println("Tie Game");
                }

                // increment round count
                currentRound++;
            }

            // print overall results when all rounds are complete
            System.out.println("\nNumber of Ties: " + totalTies);
            System.out.println("Number of User Wins: " + totalUserWins);
            System.out.println("Number of Computer Wins: " + totalComputerWins);

            // calculate and print overall results
            if (totalUserWins > totalComputerWins) {
                System.out.println("Overall Winner: User");
            } else if (totalComputerWins > totalUserWins) {
                System.out.println("Overall Winner: Computer");
            } else {
                System.out.println("Overall Winner: No one");
            }

            // reset counter variables after displaying results, so that next game starts clean
            currentRound = 1;
            totalTies = 0;
            totalUserWins = 0;
            totalComputerWins = 0;

            // prompt user if they wish to play again
            userPlayAgain = rockPaperScissorsRefactoredInstance.promptUser("Do you wish to play again sir/madam (yes/no)? ");
        } while (userPlayAgain == 1);

        // print out message if user selects "no"
        System.out.println("Thanks for playing!");
    }

    // public method to prompt user for response, returns an integer
    public int promptUser(String prompt) {
        // instantiate new scanner object
        Scanner sc = new Scanner(System.in);

        // prompt user what is passed to method and store in variable
        System.out.println(prompt);
        String userResponseString = sc.nextLine();

        // if user response is "no" or "yes", then return 0 or 1, otherwise return int they typed
        if (userResponseString.equalsIgnoreCase("no") || userResponseString.equalsIgnoreCase("yes")) {
            if (userResponseString.equalsIgnoreCase("no")) {
                return 0;
            } else {
                return 1;
            }
        } else {
            int userResponse = Integer.parseInt(userResponseString);
            return userResponse;
        }
    }

    // detemine if user entered rounds or numbers for rock, paper, scissors correctly
    public boolean isNumberValid(int number, int min, int max) {
        return (number >= min && number <= max);
    }

    // random number generator for computer choice
    public int generateRandomChoice() {
        // instantiate new random object
        Random randomizer = new Random();
        return randomizer.nextInt(3) + 1;
    }

    // determine who won round
    public String determineRoundWinner(int userSelection, int computerSelection) {
        if (userSelection == 2 && computerSelection == 1) {
            return "user";
        } else if (userSelection == 3 && computerSelection == 1) {
            return "computer";
        } else if (userSelection == 1 && computerSelection == 2) {
            return "computer";
        } else if (userSelection == 3 && computerSelection == 2) {
            return "user";
        } else if (userSelection == 1 && computerSelection == 3) {
            return "user";
        } else if (userSelection == 2 && computerSelection == 3) {
            return "computer";
        } else {
            return "tie";
        }
    }

    // convert number to "rock", "paper" or "scissors" to print out
    public String determineStringOfChoice(int choice) {
        String choiceString = "";

        switch (choice) {
            case 1:
                choiceString = "rock";
                break;
            case 2:
                choiceString = "paper";
                break;
            case 3:
                choiceString = "scissors";
                break;
            default:
                choiceString = "BEEP BEEP: error somewhere probably";
        }

        return choiceString;
    }
    
}
