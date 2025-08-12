package assn02;

import java.util.Scanner;

public class LMS {
    public static void main(String[] args) {
        Library newLibrary = new Library();
        newLibrary.menu();
    }
}

// Book class
class Book {

    // attribute for book name
    String _bookName;
    // attribute for book author
    String _authorName;
    // attribute for checkedOut boolean
    boolean _checkedOut;

    // method for displaying for this particular book
    // you won't be able to use the checkedout attribute directly
    void displayInfo(int i) {
        String out;
        if (_checkedOut == true) {
            out = "Yes";
        }else {
            out = "No";
        }
        System.out.println(i + ". Title: " + _bookName + ", Author: " + _authorName + ", Checked Out: " + out);
    }

    // helper functions to update attributes -> like checkedOut
    // don't change it directly in the code -> not good coding practice
    void checkOut() {
        if (_checkedOut == false) {
            _checkedOut = true;
        }
    }

    void checkIn() {
        if (_checkedOut == true) {
            _checkedOut = false;
        }
    }
}

class Library {
    // Scanner for taking input
    Scanner scanner = new Scanner(System.in);
    // Array of books (library)
    Book[] library = new Book[5];
    // bookCount -> keep track of how many books are present in the library
    int bookCount = 0;

    void menu() {
        boolean exit = false;
        do {
            System.out.println("Library Menu:");
            System.out.println("0. Exit");
            System.out.println("1. Add a Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Check Out a Book");
            System.out.println("4. Return a Book");
            System.out.println("Enter your choice:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0: {
                    System.out.println("Goodbye!");
                    exit = true;
                    break;
                }
                case 1: {
                    // check whether library has space or not (bookCount)
                    if (bookCount >= 5) {
                        System.out.println("Library is full!");
                    } else {
                        addBook();
                    }
                    break;
                }
                case 2: {
                    System.out.println("Books in Library:");
                    for (int i = 0; i < bookCount; i++) {
                        library[i].displayInfo(i + 1);    // displayInfo() needs an instance of the Book class
                    }
                }
                break;
                case 3: {
                    System.out.println("Enter book number to check out:");
                    // input from user using scanner
                    int bookNumber = scanner.nextInt();
                    // input validation -> use bookCount
                    if (bookNumber <= bookCount) {
                        library[bookNumber - 1].checkOut();
                        System.out.println("Book checked out!");
                    } else {
                        System.out.println("Invalid book number!");
                    }
                }
                break;
                case 4: {
                    System.out.println("Enter book number to return:");
                    // input from user using scanner
                    int bookNumber = scanner.nextInt();
                    // input validation -> use bookCount
                    if (bookNumber <= bookCount) {
                        library[bookNumber - 1].checkIn();
                        System.out.println("Book returned!");
                    } else {
                        System.out.println("Invalid book number!");
                    }
                }
                break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (!exit);
    }


    void addBook() {
        Book newBook = new Book();
        // printing the 'Enter book details' messages
        System.out.println("Enter book title:");
        newBook._bookName = scanner.nextLine();

        System.out.println("Enter book author:");
        newBook._authorName = scanner.nextLine();

        newBook._checkedOut = false;

        // enter the book object into the library
        library[bookCount] = newBook;

        // update bookCount
        bookCount++;

        System.out.println("Book added!");
    }
}





