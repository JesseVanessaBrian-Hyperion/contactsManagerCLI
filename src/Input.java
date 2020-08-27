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
        String input = promptUser("Would you like to select from the menu? ").toLowerCase();
        return input.equals("y") || input.equals("yes");
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
                        break;
                    case 3:
//                        search by name
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