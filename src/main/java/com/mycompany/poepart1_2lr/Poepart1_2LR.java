/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poepart1_2lr;

/**
 *
 * @author Boikanyo
 */
//imported Scanner for input
import java.util.Scanner;
//impoted the Random and Regx pattern for genrated the number for hash 
import java.util.Random;
import java.util.regex.Pattern;
//Included the fileWriter to create and write data into a file 
import java.io.FileWriter;
//Included The IOException in order to handle the file-related errors 
import java.io.IOException;
//Included the Gson and GsonBuilder to create a pretty prnting to give the output for a more readable JSON file for humans
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class Login { 
    //Continuation of part 1
    String storedUser_Name;
    String storedPassword;
    String stored_phone;
    String user_name;
    String password;
    String phone;
    
    public boolean checkusername(String user_name) {
        boolean isValid = user_name.length() == 5 && user_name.contains("_");
        if (isValid) {
            System.out.println("Username successfully captured.");
            this.user_name = user_name;
            return true;
        } else {
            System.out.println("Username incorrectly formatted.");
            System.out.println("Must contain an underscore (_) and be exactly 5 characters.");
            return false;
        }
    }
      public boolean checkpasswordcomplexity(String password) {
        boolean lengthValid   = password.length() >= 8;
        boolean hasDigit      = Pattern.compile("[0-9]").matcher(password).find();
        boolean hasUpperCase  = Pattern.compile("[A-Z]").matcher(password).find();
        // Fixed: was [^a_zA-Z0-9] — the underscore after 'a' made it a literal character set
        boolean hasSpecialChar = Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();

        boolean isValid = lengthValid && hasDigit && hasUpperCase && hasSpecialChar;
        if (isValid) {
            System.out.println("Password successfully captured.");
            this.password = password;
            return true;
        } else {
            System.out.println("Password incorrectly formatted.");
            System.out.println("Must be 8+ characters and include: uppercase, digit, special char ($,@,&,*).");
            return false;
        }
    }
    public boolean checkcellphone(String phone) {
        boolean isValid = phone.matches("^\\+\\d{11}$");
        if (isValid) {
            System.out.println("Cellphone number successfully captured.");
            this.phone = phone;
            return true;
        } else {
            System.out.println("Cellphone number incorrectly formatted.");
            System.out.println("Must start with international code, e.g. +27716901583.");
            return false;
        }
    }
    }

     public void register_user() {
        Scanner register = new Scanner(System.in);
        System.out.println("**********REGISTER***********");
        
         System.out.print("  Enter username       : ");
        String inputUser = register.nextLine();

        System.out.print("  Enter password       : ");
        String inputPassword = register.nextLine();

        System.out.print("  Enter cell number    : ");
        String inputPhone = register.nextLine();
        
        if (checkusername(inputUser) && checkpasswordcomplexity(inputPassword) && checkcellphone(inputPhone)) {
            this.storedUser_Name = inputUser;
            this.storedPassword  = inputPassword;
            this.stored_phone    = inputPhone;
            System.out.println("\n  Registration complete. You may now log in.");
        } else {
            System.out.println("\n  Registration failed. Please correct the errors above and try again.");
        }
     }

public boolean user_login() {
        Scanner login = new Scanner(System.in);

        if (storedUser_Name == null) {
            System.out.println("\n  [!] No account found. Please register first.");
            return false;
        }

        
        System.out.println("***********LOGIN**************");
        

        int attempts = 3; 

        while (attempts > 0) {
            System.out.print("  Enter username       : ");
            String inputUser = login.nextLine();

            System.out.print("  Enter password       : ");
            String inputPassword = login.nextLine();

            System.out.print("  Enter cell number    : ");
            String inputPhone = login.nextLine();

            if (inputUser.equals(storedUser_Name);
                    && inputPassword.equals(storedPassword)
                    && inputPhone.equals(stored_phone)) {
                System.out.println("\n  Login successful. Welcome back, " + storedUser_Name + "!");
                return true;
            } else {
                attempts--;
                if (attempts > 0) {
                    System.out.println("Incorrect details. Attempts remaining: " + attempts);
                } else {
                    System.out.println("Too many failed attempts. Account is now blocked.");
                }
            }
        }
        return false;
    }
//Created the message claass
class Message {

    // String text fields for all message data
    String messageID;
    String recipient;
    String messageText;
    String messageHash;

    // int  position of this message in the send order (1, 2, 3...)
    int messageNumber;

    
    // Constructor auto generates ID and Hash on creation
    public Message(String recipient, String messageText, int messageNumber) {
        Random rand = new Random();  // Random generates an unpredictable 10 digit number for the message ID
        long id = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        this.messageID     = String.valueOf(id);   

        this.recipient     = recipient;
        this.messageText   = messageText;
        this.messageNumber = messageNumber;
        this.messageHash   = generateHash(); // Hash is built automatically the moment a Message object is created
    }     
private String generateHash() {
        // substring(0,2) takes first 2 characters of the 10-digit ID
        String first2 = messageID.substring(0, 2);
        String[] words    = messageText.trim().split("\\s+"); // split("\\s+") breaks message into words by any whitespace
        String firstWord  = words[0];
        String lastWord   = words[words.length - 1];
        return (first2 + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();// toUpperCase() — converts entire hash String to capital letters
}

        }
 }
        public void displayMessage() {
           
              System.out.println("\n******MESSAGE DETAILS****");
        Object[] messageID = null;
              System.out.printf( " Message ID   : %-21s│%n", messageID);
        Object[] messageHash = null;
              System.out.printf( "Message Hash : %-21s│%n", messageHash);
        Object[] messageText = null;
              System.out.printf( "  │ Message      : %-21s│%n",
                messageText() > 21 ? messageText(0, 18) + "..." : messageText);
        }
        
        class QuickChat {
//Included the class of QuickChat to work in the instances for messagesIDs, messageHash, messages counter 
    
    public Message[] sentMessages;

    public  int sentCount = 0;

    public int messageCount = 0;
    
    public int messageNumber = 0;

        }
        }
      
     public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("***********Welcome to QuickChat*********");
        
        System.out.print("\n  How many messages would you like to send? ");
        int messageCount = Integer.parseInt(sc.nextLine().trim());

        Message[] sentMessages = new Message[messageCount];
        //included a menu option to create the QuickChat Menu Options 
        int choice;
        do {
            System.out.println("\n*********QuickChat Menu************");
            System.out.println("*****1) Send Messages*********");
            System.out.println("*****2) Show Sent Messages****");
            System.out.println("*****3) Quit******************");
            System.out.print("*******Enter option:************");
            choice = Integer.parseInt(sc.nextLine().trim());
            
            switch (choice) {
                case 1 -> sendMessages(sc);
                case 2 -> showMessages();
                case 3 -> System.out.println(" Goodbye. Thank you for using QuickChat!");
                default -> System.out.println(" Please choose 1, 2, or 3.");
            }
        } while (choice != 3);
    }//
        public void sendMessages(Scanner sc) {
        String messageCount = null;
        int messageNumber = 0;
        'integer.parseInt()'
        if(messageNumber >= messageCount) {
        } else {
            System.out.println("\n  [!] You have used all " + messageCount + " of your messages.");
            return;
        }

        System.out.println("\n  --- Compose Messages ---"); 
        
          
        for (int i = 0; i < messageCount;i++) {
            System.out.println("\n  Message " + (i + 1) + " of " + messageCount);
             String recipient;
            while (true) {
                System.out.print("  Enter recipient cell number (e.g. +27821234567): ");
                recipient = sc.nextLine().trim();
                if (recipient.matches("^\\+\\d{1,10}$")) {
                    break;
                } else {
                    System.out.println("  [ERROR] Invalid number. Must have international code, max 10 digits after +.");
                }
            }
            
             String messageText;
            while (true) {
                System.out.print("  Enter message (max 250 characters): ");
                messageText = sc.nextLine();
                if (messageText.length() > 250) {
                    System.out.println("  [ERROR] Please enter a message of less than 250 characters.");
                } else {
                    break;
                }
            }
            
             // Increment BEFORE creating Message so hash uses correct message number
            messageNumber++;
            
            
            // Create Message object — ID and Hash auto-generated inside constructor
            Message msg = new Message(recipient, messageText, messageNumber);
            
            // ---- ACTION MENU ----
            System.out.println("\n  What would you like to do with this message?");
            System.out.println("  1) Send Message");
            System.out.println("  2) Disregard Message");
            System.out.println("  3) Store Message to send later");
            System.out.print("  Enter option: ");
            int action = Integer.parseInt(sc.nextLine().trim());
            
             switch (action) {
                case 1 -> {
                    int sentCount = 0;
                    Message[] sentMessages = null;
                    sentMessages[sentCount] = msg;
                    sentCount++;
                    System.out.println("\n  Message successfully sent.");
                    msg.displayMessage();
                }


                case 2 -> {
                    System.out.print("  Press 0 to confirm delete, or any other key to cancel: ");
                    String confirm = sc.nextLine().trim();
                    if (confirm.equals("0")) {
                        messageNumber--;   // Decrement so count stays accurate
                        System.out.println("  Message deleted.");
                    } else {
                        System.out.println("  Deletion cancelled.");
                    }
                } 
                
                case 3 -> {
                    int sentCount = 0;
                    Message[] sentMessages = null;
                    sentMessages[sentCount] = msg;
                    sentCount++;
                    System.out.println("  Message successfully stored.");
                }


                default -> {
                    System.out.println("  Invalid option. Message discarded.");
                }
            }
        } 
        String sentCount = null;
        
         System.out.println("***Total messages sent/stored:**" + sentCount);
         
         }
          public class Poepart1_2LR {
public void showMessages() {System.out.println("\n  Coming Soon.");{      
          }
}         
          public static void main(String[] args) {
                 Scanner menu = new Scanner(System.in);
                 
                 // Create one instance of each class for the whole session
                Login     account   = new Login();
                QuickChat qchat = new QUICKchat();
                
                int choice;
                
                System.out.println("*********** QuickChat App***************");
                
                do {
                    
               System.out.println("**************MENU*************************");
               System.out.println("****1. Register********");
               System.out.println("****2. Login****************");
               System.out.println("****3. Exit******************");
               System.out.print("***Enter menu option:********** ");
               choice = Integer.parseInt(menu.nextLine().trim());
               
               switch (choice) {
                case 1 -> account.register_user();
                case 2 -> {
                    // user_login() returns boolean — only launch QuickChat on true
                    boolean loggedIn = account.user_login();
                    if (loggedIn) {
                        qchat.start();
                    }
                }
                 case 3 -> System.out.println("\n  Goodbye!");
                default -> System.out.println("  [!] Invalid choice. Please enter 1, 2, or 3.");
            }

        } while (choice != 3);
                
         Scanner scanner = new Scanner(System.in); {
        Static class messages = Message Message = new Message();
     
     System.out.print("Enter Message ID: ");
     String messageID = scanner.nextLine();
     boolean validID = messages.checkMessagesID(messageID);{
       if (!validID) {
           System.out.println("Invalid Message ID. Please try again.");
           return; }
           
           System.out.print("Enter Recipient Cell Number: ");
       
       
           String cellNumber = scanner.nextLine();
           String validCell = messages.checkRecipientcell(cellNumber);     
           
           if (validCell.equals("invalid")) {
           System.out.println("Invalid cell number. Please try again.");
           return;}
               
           System.out.print("Enter your message: ");
           
           String messageText = scanner.nextLine(); 

           String hash = messages.createMessagesHash(messageID, cellNumber, messageText);
           System.out.println("Message Hash: " + hash);
           
           String status = messages.SentMessages(scanner, messageID, cellNumber, messageText);
           System.out.println("Message Status: " + status);
           System.out.println(messages.printMessages());
           
           int total = messages.returnTotalMessages();
           System.out.println("Total messages sent: " + total);

             scanner.close();
    
    Gson json = new GsonBuilder(). setPrettyPrinting().create();
    try(FileWriter writer=new FileWriter("Message.json")) {
        json.toJson(messageID,writer);
           Object recipientcell = null;
        json.toJson(recipientcell,writer);
           Object messageHash = null;
        json.toJson(messageHash,writer);
        System.out.println("Json File created successfully");
    }catch (IOException e) {
        System.out.println("error"+e.getMessage());
        
       for(int i=0;<2;a++) {}
        System.out.println("PLease enter your message ID. ");
           int i = 0;
        messagesID[i] = input.nextLine[i]; 
        System.out.println("Please enter your recipient cell phone number. ");
        recipientcell[i] = input.nextLine[i];
        System.out.println("Message Hash created. ");
        messagesHash[i] = input.nextLine[i];
    } 
    }  
    }
} 
       