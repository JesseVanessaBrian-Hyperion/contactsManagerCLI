package src;

import java.util.Scanner;

public class Input {
    //Command Line Interface Method to grab and record user's input
    Scanner scanner;

    public Input() {
        this.scanner = new Scanner(System.in);
    }

    //method to grab user input

    public String getString() {
        return scanner.nextLine();
    }

    //prompt user for input
    public String promptUser(String prompt) {
        System.out.print(prompt);
        return getString();
    }

    public boolean yesNo(){
        String input = promptUser("\nWould you like to select from the menu? ").toLowerCase();
        return input.equals("y") || input.equals("yes");
    }

    public Contact newUserContact(){
        String[] newContactName = promptUser("Enter new contact name (First name, Last Name): ").split("[, ]+");
        String newContactNumber = promptUser("Enter phone number (i.e. 9999999999): ");
        return new Contact(newContactName[0], newContactName[1], newContactNumber);
    }

    public int menuOption(){
        System.out.printf("1. View Contacts\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n");
        return Integer.valueOf(promptUser("Enter an option (1, 2, 3, 4 or 5): "));
    }

    public void cli(Contacts directory){
        do {
            try {
                int choice = menuOption();
                switch (choice) {
                    case 1:
//                        view all contacts method
                        directory.displayContacts();
                        break;
                    case 2:
//                        add contact
                        directory.addContact(newUserContact());
                        break;
                    case 3:
//                        search by name
                        Contact scontact = directory.searchByName(this);
                        System.out.printf("%s %s | %s", scontact.getFirstName(), scontact.getLastName(), scontact.getPhone());
                        break;
                    case 4:
//                        Delete existing contact
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Invalid entry");
                        break;
                }
            } catch (Exception e) {e.printStackTrace();}
        } while (yesNo());
    }


}