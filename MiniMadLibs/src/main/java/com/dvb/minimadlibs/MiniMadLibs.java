package com.dvb.minimadlibs;

import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class MiniMadLibs {
    
    public static void main(String[] args) {
        String noun1, adjective1, noun2, adjective2, pluralNoun1, pluralNoun2, pluralNoun3, verb, pastSameVerb;
        int number;
        
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("I need a noun: ");
        noun1 = inputReader.nextLine();
        
        System.out.println("Now an adjective: ");
        adjective1 = inputReader.nextLine();
        
        System.out.println("Another noun: ");
        noun2 = inputReader.nextLine();
        
        System.out.println("And a number: ");
        String numberString = inputReader.nextLine();
        
        number = Integer.parseInt(numberString);
        
        System.out.println("Another adjective: ");
        adjective2 = inputReader.nextLine();
        
        System.out.println("A plural noun: ");
        pluralNoun1 = inputReader.nextLine();
        
        System.out.println("Another one: ");
        pluralNoun2 = inputReader.nextLine();
        
        System.out.println("One more: ");
        pluralNoun3 = inputReader.nextLine();
        
        System.out.println("A Verb (infinitive form): ");
        verb = inputReader.nextLine();
        
        System.out.println("Same verb (past participle): ");
        pastSameVerb = inputReader.nextLine();
        
        System.out.println(noun1 + ": the " + adjective1 + " frontier. These are the voyages of the starship " + noun2 + ". \n" +
        "Its " + number + "-year mission: to explore strange " + adjective2 + " " + pluralNoun1 + ", to seek out " + adjective2 + " " + pluralNoun2 + " and " + adjective2 + " " + pluralNoun3 + ", \n" +
        "to boldly " + verb + " where no one has " + pastSameVerb + " before.");
    }
    
}
