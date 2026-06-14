/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.poepart1_2lr;
/**
 *
 * @author Boikanyo
 */
import java.util.Scanner;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


 class Login { 
    //Continuation of part 1
    String storedUser_Name;
    String storedPassword;
    String storedcellphone;
    String storedpassword;
    String storedphone;
    
        public boolean checkUsername(String username) {
        boolean isValid = username.length() == 5 && username.contains("_");
        if (isValid) {
            System.out.println("Username successfully captured.");
            return true;
        } else {
            System.out.println("Username incorrectly formatted.");
            System.out.println("Must contain an underscore (_) and be exactly 5 characters.");
            return false;
        }
    }
     public boolean checkPasswordComplexity(String password) {
        boolean lengthValid = password.length() >= 8;
        boolean hasDigit = Pattern.compile("[0-9]").matcher(password).find();
        boolean hasUpperCase = Pattern.compile("[A-Z]").matcher(password).find();
        boolean hasSpecialChar = Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();

        boolean isValid = lengthValid && hasDigit && hasUpperCase && hasSpecialChar;
        if (isValid) {
            System.out.println("Password successfully captured.");
            return true;
        } else {
            System.out.println("Password incorrectly formatted.");
            System.out.println("Must be 8+ characters and include: uppercase, digit, and a special character.");
            return false;
        }
    } 
     public boolean checkCellphone(String phone) {
        boolean isValid = phone.matches("^\\+\\d{11}$");
        if (isValid) {
            System.out.println("Cellphone number successfully captured.");
            return true;
        } else {
            System.out.println("Cellphone number incorrectly formatted.");
            System.out.println("Must start with international code, e.g., +27716901583.");
            return false;
        }
    }
 
    public void registerUser(Scanner sc) {
        System.out.println("\n======= REGISTER =============");
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        System.out.print("Enter cell number: ");
        String phone = sc.nextLine();

        if (checkUsername(username) && checkPasswordComplexity(password) && checkCellphone(phone)) {
            this.storedUser_Name = username;
            this.storedPassword = password;
            this.storedphone = phone;
            System.out.println("Registration Complete.");
        } else {
            System.out.println("Registration invalid. Please ensure all details are correctly formatted.");
        }
    }
    
     public boolean userLogin(Scanner sc) {
        if (storedUser_Name == null) {
            System.out.println("No user registered yet.");
            return false;
        }

        int attempts = 3;
        System.out.println("\nLOGIN");
        while (attempts > 0) {
            System.out.print("Enter username");
            String username = sc.nextLine();
            System.out.print("Enter password ");
            String password = sc.nextLine();
            System.out.print("Enter cell number:");
            String phone = sc.nextLine();

            if (username.equals(storedUser_Name) && password.equals(storedPassword) && phone.equals(storedphone)) {
                System.out.println("Login Successful. Welcome back!");
                return true;
            } else {
                attempts--;
                if (attempts > 0) {
                    System.out.println("Invalid details. Attempts left: " + attempts);
                } else {
                    System.out.println("Too many failed attempts. Account is now blocked.");
                }
            }
        }
        return false;
    }
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
     
     public void displayMessage() {
        System.out.println("\n=== MESSAGE DETAILS ====");
        System.out.printf("Message ID   : %s%n", messageID);
        System.out.printf("Recipient    : %s%n", recipient);
        System.out.printf("Message Hash : %s%n", messageHash);
        System.out.printf("Message Text : %s%n", messageText);
    }
}

public class Poepart1_2LR{
    private static ArrayList<Message> sentMessages = new ArrayList<>();
    private static int messageLimit = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login account = new Login();
        boolean loggedIn = false;

        System.out.println("Welcome to QuickChat App");
        
         // Step 1: Registration and Login Loop
        while (true) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter option: ");
            
            String input = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

               if (choice == 1) {
                account.registerUser(scanner);
            } else if (choice == 2) {
                if (account.userLogin(scanner)) {
                    loggedIn = true;
                    break;
                }
            } else if (choice == 3) {
                System.out.println("Goodbye.");
                System.exit(0);
            } else {
                System.out.println("Invalid choice.");
            }
        }
        
        // Step 2: Messaging Menu Loop
        if (loggedIn) {
            System.out.print("\nHow many messages would you like to send? ");
            try {
                messageLimit = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Defaulting to 1 message.");
                messageLimit = 1;
            }
            
              while (true) {
                System.out.println("\nQUICKCHAT MENU");
                System.out.println("1) Send Messages");
                System.out.println("2) Show Sent Messages");
                System.out.println("3) Save to JSON and Quit");
                System.out.print("Enter option: ");

                String input = scanner.nextLine();
                int choice;
                try {
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.");
                    continue;
                }
                
                 if (choice == 1) {
                    sendMessages(scanner);
                } else if (choice == 2) {
                    showSentMessages();
                } else if (choice == 3) {
                    saveMessagesToJson();
                    System.out.println("Goodbye. Thank you for using QuickChat!");
                    break;
                } else {
                    System.out.println("Please choose 1, 2, or 3.");
                }
            }
        }
        scanner.close();
    }
    
     private static void sendMessages(Scanner sc) {
        if (sentMessages.size() >= messageLimit) {
            System.out.println("\n[!] You have used all " + messageLimit + " of your messages.");
            return;
        }

        System.out.println("\n--- Compose Message ---");
        String recipient;
        while (true) {
            System.out.print("Enter recipient cell number (e.g., +27821234567): ");
            recipient = sc.nextLine().trim();
            if (recipient.matches("^\\+\\d{11}$")) {
                break;
            } else {
                System.out.println("[ERROR] Invalid number. Must start with + and have 11 digits.");
            }
        }

        String messageText;
        while (true) {
            System.out.print("Enter message (max 250 characters): ");
            messageText = sc.nextLine();
            if (messageText.length() > 250) {
                System.out.println("[ERROR] Message too long.");
            } else {
                break;
            }
        }
        
        Message msg = new Message(recipient, messageText, sentMessages.size() + 1);
        
        
        System.out.println("\nWhat would you like to do with this message?");
        System.out.println("1) Send Message");
        System.out.println("2) Discard Message");
        System.out.print("Enter option: ");
        
        String actionInput = sc.nextLine().trim();
        if (actionInput.equals("1")) {
            sentMessages.add(msg);
            System.out.println("Message successfully sent.");
            msg.displayMessage();
        } else {
            System.out.println("Message discarded.");
        }
        
        System.out.println("*** Total messages sent/stored: " + sentMessages.size() + " ***");
    }
     
    private static void showSentMessages() {
        if (sentMessages.isEmpty()) {
            System.out.println("\nNo messages sent yet.");
            return;
        }
        System.out.println("\nSENT MESSAGES");
        for (Message m : sentMessages) {
            m.displayMessage();
        }
    }
    
     private static void saveMessagesToJson() {
        if (sentMessages.isEmpty()) return;

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("Messages.json")) {
            gson.toJson(sentMessages, writer);
            System.out.println("Messages saved to Messages.json successfully.");
        } catch (IOException e) {
            System.out.println("Error saving JSON: " + e.getMessage());
        }
    }
}
