import java.io.*;
import java.util.Scanner;

public class Notes {
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println("\n===== Welcome to Notes App =====");
            System.out.println("1. Write to file");
            System.out.println("2. Read from file");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            // if it is an integer
            if (!scan.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scan.nextLine(); 
                continue;
            }

            option = scan.nextInt();
            scan.nextLine(); 

            switch (option) {
                case 1 -> writeNotes(scan);
                case 2 -> readNotes();
                case 3 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid option! Try again.");
            }
        } while (option != 3);

        scan.close();
    }

    //method to write
    public static void writeNotes(Scanner scan) {
        System.out.println("\nEnter your notes (type 'END' on a new line to finish):");

        StringBuilder noteBuilder = new StringBuilder();
        while (true) {
            String line = scan.nextLine();
            if (line.equalsIgnoreCase("END")) break;
            noteBuilder.append(line).append(System.lineSeparator());
        }

        try (FileWriter file = new FileWriter(FILE_NAME, true)) { 
            file.write(noteBuilder.toString());
            System.out.println("Notes saved successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    //method to read 
    public static void readNotes() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No notes found. Please write some first.");
            return;
        }

        System.out.println("\n===== Your Notes =====");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean empty = true;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                empty = false;
            }
            if (empty) System.out.println("(No content in notes)");
        } catch (IOException e) {
            System.out.println("Error to reading file: " + e.getMessage());
        }
    }
}
