import java.util.*;

// Book Class
class Book {
    private int id;
    private String name;

    public Book(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
}

// User Class
class User {
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
}

// Library Class
class Library {
    private Map<Integer, Book> books = new TreeMap<>();        
    private Map<Integer, Boolean> availability = new TreeMap<>(); 

    // Add a book
    public void addBook(Book book) {
        books.put(book.getId(), book);
        availability.put(book.getId(), true); 
    }

    // allbooks
    public void viewBooks() {
        System.out.println("\n--- Book List ---");
        for (Book book : books.values()) {
            String status = availability.get(book.getId()) ? "Available" : "Issued";
            System.out.println("ID: " + book.getId() + " | " + book.getName() + " | " + status);
        }
    }

    // Search book by name keyword
    public void searchBook(String keyword) {
        boolean found = false;
        for (Book book : books.values()) {
            if (book.getName().toLowerCase().contains(keyword.toLowerCase())) {
                String status = availability.get(book.getId()) ? "Available" : "Issued";
                System.out.println("ID: " + book.getId() + " | " + book.getName() + " | " + status);
                found = true;
            }
        }
        if (!found) System.out.println("No books found for: " + keyword);
    }

    // Issue a book
    public void issueBook(int id, User user) {
        if (!books.containsKey(id)) {
            System.out.println("Book not found!");
            return;
        }
        if (!availability.get(id)) {
            System.out.println("Book is already issued!");
        } else {
            availability.put(id, false);
            System.out.println(user.getName() + " issued: " + books.get(id).getName());
        }
    }

    // Return a book
    public void returnBook(int id, User user) {
        if (!books.containsKey(id)) {
            System.out.println("Book not found!");
            return;
        }
        if (availability.get(id)) {
            System.out.println(" book  not issued");
        } else {
            availability.put(id, true);
            System.out.println(user.getName() + " returned" + books.get(id).getName());
        }
    }
}

// Main Class
public class LibrarySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        
        library.addBook(new Book(1, "Java Programming"));
        library.addBook(new Book(2, "Data Structures"));
        library.addBook(new Book(3, "Algorithms"));

        User currentUser = new User(101, "Alice");

        int choice;
        do {
            System.out.println("\n--- Library Menu ---");
             System.out.println("1. View Books");
             System.out.println("2. Search Book");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
              System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> library.viewBooks();
                case 2 -> {
                    System.out.print("Enter keyword: ");
                    sc.nextLine();
                    String keyword = sc.nextLine();
                    library.searchBook(keyword);
                }
                case 3 -> {
                    System.out.print("Enter Book ID to issue: ");
                    int id = sc.nextInt();
                    library.issueBook(id, currentUser);
                }
                case 4 -> {
                    System.out.print("Enter Book ID to return: ");
                    int id = sc.nextInt();
                    library.returnBook(id, currentUser);
                }
                case 5 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 5);

        sc.close();
    }
}
