package src;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Contacts {
    // Declare HashMap and Path member variables
    private HashMap<String, Contact> addressBook;
    private Path p;

    // Default Constructor
    public Contacts(){
        addressBook = new HashMap<>();
        p = Paths.get("./src/contacts.txt").normalize();
        buildOutContactsList();
    }

    // Method to build the addressBook HashMap from the data in contacts.txt
    public void buildOutContactsList() {
        List<String> fileContacts = new ArrayList<>();
        try {
            fileContacts = Files.readAllLines(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < fileContacts.size(); i++) {
            String[] info = fileContacts.get(i).split("[ |*]+");
            addressBook.put(info[0] + " " + info[1], new Contact(info[0], info[1], info[2]));
        }
    }

    // Method that adds a new contact to list
    public void addContact(Contact newContact) throws IOException {
        // format string for the new contact, then declare the BufferedWriter
        String newPerson = formatText(newContact) + "\n";
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(p.toFile(), true)
        );
        // append to the contacts.txt file using BufferedWriter
        bw.write(newPerson);
        bw.close();
        System.out.println("New contact was successfully added");
        buildOutContactsList();
    }

    // Method to delete a contact from the HashMap and the contacts.txt file
    public void deleteContact(Input scan) throws IOException{
        Set<String> listOfKeys = addressBook.keySet();
        List <String> deleteList = new ArrayList<>(listOfKeys);
        for (int i = 0; i < deleteList.size(); i++) {
            System.out.printf("%d. %s\n", i+1, deleteList.get(i));
        }

        // get user's input for contact to delete and parses it to a integer
        int removeName = Integer.parseInt(scan.promptUser("Enter the number you'd like to delete: "));
        // Removes contact from HashMap using the user's input selection
        // user's entry won't be zero based, so we subtract by 1
        String key = deleteList.get(removeName-1);
        addressBook.remove(key);

        // removes from text file (rewrites the contacts.txt)
        File contactsFile = p.toFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(contactsFile));

        // traverse through map, format Contact info string, then write it to the contacts.txt file
        for (Map.Entry<String, Contact> elem : addressBook.entrySet()) {
            String contactText = formatText(elem.getValue());
            bw.write(contactText + "\n");
        }
        // close the BufferedWriter
        bw.close();

        // maybe add some kind of message here to let the user know the removal was successful
    }

    // Method used to format string to match contacts.txt format
    public String formatText(Contact delC){
        return String.format("%s*%s **|** %s",
                delC.getFirstName(), delC.getLastName(), delC.getPhone());
    }

    // Method to display the contacts information is ascending order (lastname, firstname)
    public void displayContacts(){
        Map<String, Contact> sortedMap = new TreeMap<>(addressBook);
        System.out.println("Name | Phone number\n" +
                "---------------");
        for (Map.Entry<String, Contact> elem : sortedMap.entrySet()) {
            System.out.printf("%s %s | %s\n", elem.getValue().getFirstName(), elem.getValue().getLastName(), elem.getValue().getPhone());
        }
    }

    // Method to retrieve the contact information from the user's input entry
    public Contact searchByName(Input scan) {
        Contact temp = new Contact();
        String[] name = scan.promptUser("Enter name: (i.e Vanessa Rodriguez) ").split("[ |*]+");
        String cname = String.format("%s %s", name[0], name[1]);
        if (addressBook.containsKey(cname)) {
            return addressBook.get(cname);
        }
        return temp;
    }

    // Contacts Main method
    public static void main(String[] args) {
        // declare Input and Contacts variables
        Input userInput = new Input();
        Contacts mainContacts = new Contacts();

        // call Input object Command Line Interface method
        userInput.cli(mainContacts);
    }
}


