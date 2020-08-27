package src;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;


//The text file should contain one contact per line. 
// When the application starts, the contact list should be read from the file.
//  Before the application exits, the contacts file should be rewritten.
//  The user interface for your application should include a main menu like the following,
// where the user will need to enter a number between 1 and 5:
//


public class Contacts {
    private HashSet<Contact> addressBook;

    private List<Contact> contacts;

    //   HashMap<String, Object> ContactList = new HashMap<>();
    private Path p;

    public void buildOutContactsList() {
        List<String> fileContacts = new ArrayList<>();
        try {
            fileContacts = Files.readAllLines(p);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String contact : fileContacts) {
            String[] info = contact.split("[ |*]+");

            contacts.add(new Contact(info[0], info[1], info[2]));
            // System.out.println(Arrays.toString(info));
        }
        // for(Contact elem: contacts) {
        //  System.out.printf("name: %s phone: %s\n", elem.getName(), elem.getPhone() );
        //}
    }

//method add new contact to list
    public void addContact(List<Contact> contacts, Path p, Contact newContact){
        //APPEND
        //Files.write(p, obj, StandardOpenOption.APPEND);
//we were here last
    }

    public Contacts(){
        contacts = new ArrayList<>();
        addressBook = new HashSet<>();
        p = Paths.get("./src/contacts.txt").normalize();
        buildOutContactsList();
    }

    public void displayContacts(){
        Set<Contact> sortedSet = new TreeSet<>(contacts);
        System.out.println("Name | Phone number\n" +
                "---------------");
        for (Contact elem : sortedSet) {
            System.out.printf("%s %s | %s\n", elem.getFirstName(), elem.getLastName(), elem.getPhone());
        }
    }


    public static void main(String[] args) {
        Input userInput = new Input();
        Contacts mainContacts = new Contacts();
        mainContacts.addressBook.addAll(mainContacts.contacts);

        userInput.cli(mainContacts);
    }


}


