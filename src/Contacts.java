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
    public static void buildOutContactsList(List<Contact> contacts, Path p) {
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
    public static void addContact(List<Contact> contacts, Path p, Contact newContact){
        //APPEND
        //Files.write(p, obj, StandardOpenOption.APPEND);
//we were here last
    }



    public static void main(String[] args) {
        Input cli = new Input();


        HashSet<Contact> localHashSet = new HashSet<>();

        List<Contact> contacts = new ArrayList<>();

        //   HashMap<String, Object> ContactList = new HashMap<>();
        Path p = Paths.get("./src/contacts.txt").normalize();


        // private static String[] booksArr = {"Garfield Loses His Feet **|** Jim Davis", "Wicked Problems, Righteous Solutions **|** Peter DeGrace, Leslie Hulet Stahl", "Superfudge **|** Judy Blume"};
// ArrayList<String> defaultBooks = new ArrayList<>(Arrays.asList(ContactsArr));

        buildOutContactsList(contacts, p);
        localHashSet.addAll(contacts);

        Set<Contact> sortedSet = new TreeSet<>(contacts);
        for (Contact elem : sortedSet) {
            System.out.printf("%s, %s phone#: %s\n", elem.getLastName(), elem.getFirstName(), elem.getPhone());


        }
    }


}


