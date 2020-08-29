package src;

import java.util.Scanner;

public class Input {
    // Scanner object to grab and record user's input
    Scanner scanner;

    // Default Constructor that initializes the Scanner object
    public Input() {
        this.scanner = new Scanner(System.in);
    }

    // Method to grab user string input
    public String getString() {
        return scanner.nextLine();
    }

    // Method to prompt user and grab their input
    public String promptUser(String prompt) {
        System.out.print(prompt);
        return getString();
    }

    // Method to prompt the user and determine if the user will continue
    public boolean yesNo() {
        String input = promptUser("\nWould you like to select from the menu? ").toLowerCase();
        return input.equals("y") || input.equals("yes");
    }

    // Method to check if the contact's phone number is either 7 or 10 digits
    public boolean chkPhoneLength(String number) {
        int l = number.length();
        if (l < 7 || l > 10 || (l > 7 && l < 10)) {
            System.out.println("Invalid entry. Number must be 7 or 10 digits in length.");
            return true;
        } else {
            return false;
        }
    }

    // Method to prompt the user for the new contact info and returns a new contact object
    public Contact newUserContact() {
        String[] newContactName = promptUser("Enter new contact name (First name, Last Name): ").split("[, *]+");
        String newContactNumber;
        do {
            newContactNumber = promptUser("Enter phone number (i.e. 9999999999): ");
        } while (chkPhoneLength(newContactNumber));
        return new Contact(newContactName[0], newContactName[1], newContactNumber);
    }


    // Method to display the Menu Options for the user and will return their selection parsed to an integer
    public int menuOption() {
        System.out.print("\n1. View Contacts\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n");
        return Integer.parseInt(promptUser("Enter an option (1, 2, 3, 4 or 5): "));
    }

    // Method to execute the command line interface (cli) for the user
    public void cli(Contacts directory) {
        outside:
        do {
            try {
                int choice = menuOption();
                switch (choice) {
                    case 1:
                        // view all contacts method
                        directory.displayContacts();
                        break;
                    case 2:
                        // add contact
                        directory.addContact(newUserContact());
                        break;
                    case 3:
                        // search by name
                        Contact searchContact = directory.searchByName(this);
                        directory.displayContactHeader();
                        System.out.print(directory.formatText(searchContact, directory.getDisFormat()));;
                        break;
                    case 4:
                        // delete existing contact
                        directory.deleteContact(this);
                        break;
                    case 5:
                        // exit the cli
                        System.out.println("Later dude!");
                        break outside;
                    default:
                        System.out.println("Invalid entry");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (yesNo());
    }
}