package src;

import java.util.Scanner;

public class Input {
    //Comand Line Interface Method to grab and record user's input
    Scanner scanner;

    public Input() {
        this.scanner = new Scanner(System.in);
    }

    //method to grab user input

    public String getString() {
        return scanner.nextLine();
    }
}
//    //prompt user for input
//    public String promptUser(String prompt) {
//        System.out.print(prompt);
//    }
//
//
//}
//}