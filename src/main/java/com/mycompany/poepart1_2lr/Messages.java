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
 
class messages {
//Delaring the methods in the methods class
    boolean checkMessageID;
    String  checkRecipientCell;
    String  createMessageHash;
    String  SentMessage;
    String  printMessages;
    int     returnTotalMessages;
    int     storedMessages;
    public String recipientCell;
    public String MessageID;
    public String messageHash;
    public Object sentMessages;
     //Declaring the method for message IDs
    public boolean checkMessageID(String MessageID) {
        boolean isVaild = MessageID.length() == 10;
         if(isVaild) {
             System.out.println("Please ensure that the Message ID is not more than 10 characters");
             this.MessageID = MessageID;
             return true;
         } else {
             System.out.println("Message ID must be corrected to the 10 characters for the MessageID ");
             return false;
         }
    }
    //Declaring the method for recipient cell
    public String checkRecipientCell(String RecipientCell) {
        String recipientCell = null;
         boolean isvalid = recipientCell.length() <=10 && recipientCell("+");
        boolean isValid = false;
         if (isValid) {
            System.out.println("The recipient's cellphone number is successfully added");
            this.recipientCell = recipientCell;
            return recipientCell;
         }else { 
            System.out.println("The recipient's cellphone number is not added or is not correctly formatted and must contain (+27) and not more than 10 characters");
             String invaild = null;
            return invaild; 
         }
    }
     //Declaring the method for message hash
    public String createMessageHash(String message_id, String recipientCell, String message) {
        String hashID = message_id.substring(0,1).toUpperCase();
        String hashCell = recipientCell.substring(recipientCell.length() - 2);
        String hashMessage = message.split(" ")[0].toUpperCase();
       
       String messageHash = hashID + ":" + hashCell + ":" + hashMessage;
        String messagehash = null;
       
       System.out.println("Message hash successfully created: " + messagehash);
       this.messageHash = messageHash;
       return messageHash; 
      }
        //Declaring the method for String method hash     
    public String SentMessage(String message_id, String recipientCell, String message) {
         Scanner scanner = new Scanner(System.in);
         //menu options 
         System.out.println("\nWhat would you like to do with your messages?");
         System.out.println("1.Send Message");
         System.out.println("2.Store Message");
         System.out.println("Disregard Message");
         System.out.println("Enter your choice: ");
         
         String mychoice  = scanner.nextLine();
         scanner.nextLine();
    int choice = 0;
         
         switch (choice){
             case 1:
                 System.out.println("Message successfully sent.");
                 System.out.println("Message ID: " + message_id);
                 System.out.println("To:" + recipientCell);
                 System.out.println("Message: " + message);
             {
                 String messageid = null;
                 System.out.println("Hash: " + createMessageHash(messageid, recipientCell, message));
             }
                 return "Sent";

             case 2:
                 System.out.println("Message was successfully stored");
                 return"Stored";
             case 3:
                 System.out.println("Message disregarded.");
                 return"Disregareded";
             default:
                 System.out.println("Invaild option selected");
                 return"Error";
         } 
    }
        //Storing the arrays in the class    
           '\String[] sendMessages = new get.sendMessages();\'
           '\String[] messageIDs = new get.messageIDs();\'
           '\String[] recipientcell  = new get.recipientcell();\'
           '\String[] messageHashes = new get.messagesHashes();\'
           

    //Method in the int and using a for loop for the sent and int total method 
    public String printMessages() {
        String printMessages = 0;
        boolean isValid = true;
        if(isValid) {           
            return"No messages have been sent yet";
        } else {
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\n--- All Sent Messages ----\n");
        
        for(int i = 0; i < sentMessages.length; i++) {
            sb.append("Message" + (i + 1) + ":\n");
            sb.apprend("ID: " + get.messageIDs(i));
            sb.append("TO: " + get.recipient(i));
            sb.append("Message: " + get.sentMessages(i));
            sb.append("Hash: " + get.messageHashes(i));                   
        }
        
        return sb.toString();
    }
    //Method in the int and using a for loop for the sent and int total method 
    public int returnTotalMessages(){
        int length = 0;
        int sentMessages.length;
        String total = null;
        System.out.println("Total messages sent: " + total);
        return 0;
       }

public class Messages {  
}
