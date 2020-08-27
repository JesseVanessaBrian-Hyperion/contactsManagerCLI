package src;

import java.io.*;
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
    private HashMap<String, Contact> addressBook;
//    private List<Contact> contacts;
    private Path p;

    public Contacts(){
//        contacts = new ArrayList<>();
        addressBook = new HashMap<>();
        p = Paths.get("./src/contacts.txt").normalize();
        buildOutContactsList();
    }

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

//method add new contact to list
    public void addContact(Contact newContact) throws IOException {
        //APPEND Vincent*Marbach **|** 9523456789
        String newPerson = String.format("\n%s*%s **|** %s", newContact.getFirstName(), newContact.getLastName(), newContact.getPhone());

        BufferedWriter writer = new BufferedWriter(
                new FileWriter("./src/contacts.txt", true));

        writer.write(newPerson);
        writer.close();
        System.out.println("New contact was successfully added");
        buildOutContactsList();
    }

    public void deleteContact(Input scan) throws IOException{
        Set<String> listOfKeys = addressBook.keySet();
        List <String> deleteList = new ArrayList<>(listOfKeys);
        for (int i = 0; i < deleteList.size(); i++) {
            System.out.printf("%d. %s\n", i+1, deleteList.get(i));
        }
//      Get user's input for contact delete selection and changes it to a integer
        int removeName = Integer.parseInt(scan.promptUser("Enter the number you'd like to delete: "));
//        Removes contact from HashMap using the user's input selection
       String key = deleteList.get(removeName-1);

//  Removes from text file
        File inputFile = new File("./src/contacts.txt");
        File tempFile = new File("./src/temp.txt");
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
        String removeID = formatText(addressBook.get(key));
//        System.out.println(removeID);
        String currentLine;
        while((currentLine = br.readLine()) != null){
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(removeID)) continue;
            bw.write(currentLine + System.getProperty("line.separator"));
        }
        System.out.println(currentLine);
//        if (!currentLine.isEmpty() && currentLine.contains(System.getProperty("line.separator"))){
//            currentLine.replace(System.getProperty("line.separator"),"");
//        }
        bw.close();
        br.close();
        boolean successful = tempFile.renameTo(inputFile);
        if (successful){
            System.out.println("Deletion successful!");
        }
       addressBook.remove(key);
    }
//    Method used to format string to match contacts.txt format
    public String formatText(Contact delC){
        return String.format("%s*%s **|** %s", delC.getFirstName(), delC.getLastName(), delC.getPhone());
    }

    public void displayContacts(){
        Map<String, Contact> sortedMap = new TreeMap<>(addressBook);
        System.out.println("Name | Phone number\n" +
                "---------------");
        for (Map.Entry<String, Contact> elem : sortedMap.entrySet()) {
            System.out.printf("%s %s | %s\n", elem.getValue().getFirstName(), elem.getValue().getLastName(), elem.getValue().getPhone());
        }
    }

    public Contact searchByName(Input scan) {
        Contact temp = new Contact();
        String name = scan.promptUser("Enter name: ");
        if (addressBook.containsKey(name)) {
            temp = addressBook.get(name);
        } return temp;
    }



    public static void main(String[] args) {
        Input userInput = new Input();
        Contacts mainContacts = new Contacts();


        userInput.cli(mainContacts);
    }


}


