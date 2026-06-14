/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.poepart1_2lr;

/**
 *
 * @author Boikanyo
 */

import static java.lang.reflect.Array.get;
import java.util.Arrays; 
import java.util.Scanner;
import java.util.*;
import java.util.ArrayList;
import java.io.Console;
import static java.lang.reflect.Array.get;

public class Messages {
    // Fields for a single message
    private String messageID;
    private String recipientCell;
    private String messageText;
    private String messageHash;
    private int messageNumber;

    // Static list to store multiple messages (replacing the problematic arrays)
    private static ArrayList<Messages> storedMessagesList = new ArrayList<>();

    // Default constructor
    public Messages() {}

    // Constructor for creating a new message
    public Messages(String recipientCell, String messageText, int messageNumber) {
        this.recipientCell = recipientCell;
        this.messageText = messageText;
        this.messageNumber = messageNumber;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash(this.messageID, this.recipientCell, this.messageText);
    }

    // Generate a random 10-digit message ID
    private String generateMessageID() {
        Random rand = new Random();
        long id = 1000000000L + (long) (rand.nextDouble() * 9000000000L);
        return String.valueOf(id);
    }

    // Create a message hash based on ID, recipient, and message text
    public String createMessageHash(String id, String cell, String text) {
        String hashID = id.substring(0, 2).toUpperCase();
        String hashCell = cell.length() >= 2 ? cell.substring(cell.length() - 2) : cell;
        String[] words = text.trim().split("\\s+");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        
        return hashID + ":" + hashCell + ":" + firstWord + lastWord;
    }

    // Getters
    public String getMessageID() { return messageID; }
    public String getRecipientCell() { return recipientCell; }
    public String getMessageText() { return messageText; }
    public String getMessageHash() { return messageHash; }

    // Display details of a single message
    public void displayFullDetails() {
        
        System.out.println("Message ID   : " + messageID);
        System.out.println("Recipient    : " + recipientCell);
        System.out.println("Message Hash : " + messageHash);
        System.out.println("Message Text : " + messageText);
    }

    // Static method to handle the Stored Messages Menu
    public static void storedMessageMenu(Scanner sc) {
        int choice = 0;
        do {
            System.out.println("\n===== STORED MESSAGES MENU ======");
            System.out.println("1) Display sender & recipient for all stored messages");
            System.out.println("2) Display the longest stored message");
            System.out.println("3) Search by Message ID");
            System.out.println("4) Search all messages for a recipient");
            System.out.println("5) Delete a message by hash");
            System.out.println("6) Full view report of all stored messages");
            System.out.println("7) Back to main menu");
            System.out.print("Enter Option: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1-7.");
                continue;
            }

            switch (choice) {
                case 1: displaySenderAndRecipient(); 
                break;
                case 2: displayLongestMessage(); 
                break;
                case 3:
                case 4: 
                case 5: 
                case 6: displayFullReport(); 
                case 7: System.out.println("Returning to the Main menu..."); 
                default: System.out.println("Please choose a valid option (1-7).");
            }
        } while (choice != 7);
    }

    // 1) Display recipient for all stored messages
    public static void displaySenderAndRecipient() {
        if (storedMessagesList.isEmpty()) {
            System.out.println("No stored messages available.");
            return;
        }
        System.out.println("\n--- Recipients of Stored Messages ---");
        for (Messages m : storedMessagesList) {
            System.out.println("Recipient: " + m.getRecipientCell());
        }
    }

    // 2) Display the longest stored message
    public static void displayLongestMessage() {
        if (storedMessagesList.isEmpty()) {
            System.out.println("No stored messages available.");
            return;
        }
        Messages longest = storedMessagesList.get(0);
        for (Messages m : storedMessagesList) {
            if (m.getMessageText().length() > longest.getMessageText().length()) {
                longest = m;
            }
        }
        System.out.println("\n--- Longest Stored Message ---");
        longest.displayFullDetails();
    }

    // 3) Search by Message ID
    public static void searchByMessageID(String id) {
        boolean found = false;
        for (Messages m : storedMessagesList) {
            if (m.getMessageID().equals(id)) {
                m.displayFullDetails();
                found = true;
                break;
            }
        }
        if (!found) System.out.println("No message found with ID: " + id);
    }

    // 4) Search all messages for a recipient
    public static void searchByRecipient(String recipient) {
        boolean found = false;
        for (Messages m : storedMessagesList) {
            if (m.getRecipientCell().equalsIgnoreCase(recipient)) {
                m.displayFullDetails();
                found = true;
            }
        }
        if (!found) System.out.println("No messages found for recipient: " + recipient);
    }

    // 5) Delete a message by hash
       public static void deleteByHash(String hash) {
        boolean removed = storedMessagesList.removeIf(m -> m.getMessageHash().equalsIgnoreCase(hash));
        if (removed) {
            System.out.println("Message with hash " + hash + " has been deleted.");
        } else {
            System.out.println("No message found with hash: " + hash);
        }
    }  

    // 6) Full view report of all stored messages
    public static void displayFullReport() {
        if (storedMessagesList.isEmpty()) {
            System.out.println("No stored messages available.");
            return;
        }
        System.out.println("\n===== FULL STORED MESSAGES REPORT =====");
        System.out.println("Total stored messages: " + storedMessagesList.size());
        for (Messages m : storedMessagesList) {
            m.displayFullDetails();
        }
    }

    // Method to add a message to the stored list (called from main)
    public static void addMessage(Messages msg) {
        storedMessagesList.add(msg);
    }
}
