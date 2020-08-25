
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
      public static void buildOutContactsList(ArrayList<String> contacts, Path p) {
        List<String> books = new ArrayList<>();
        try {
          books = Files.readAllLines(p);
        } catch (IOException e) {
          e.printStackTrace();
        }

        for (String book : books) {
          String[] info = book.split(" \\*\\*\\|\\*\\* ");
      //    contactList.putIfAbsent(info[0], info[1]);
        }
      }



    public static void main(String[] args) {


      HashSet localHashSet = new HashSet();

      ArrayList<String> contacts = new ArrayList<>();
   //   HashMap<String, Object> ContactList = new HashMap<>();
      Path p = Paths.get("./src/contacts.txt").normalize();
      // private static String[] booksArr = {"Garfield Loses His Feet **|** Jim Davis", "Wicked Problems, Righteous Solutions **|** Peter DeGrace, Leslie Hulet Stahl", "Superfudge **|** Judy Blume"};
// ArrayList<String> defaultBooks = new ArrayList<>(Arrays.asList(ContactsArr));

      buildOutContactsList(contacts, p);

    }
}
