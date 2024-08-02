import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Assignment_2 {
    public static void main(String[] args) {
        List<Book> books = initializeBooks();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Alexandria Library!");
        System.out.println("Please input 1 to add a book, 2 to borrow a book, 3 to return a book, 4 to exit");

        while (true) {
            System.out.print("Enter your choice: ");
            if (scanner.hasNextInt()) {
                int answerInput = scanner.nextInt();
                scanner.nextLine();

                switch (answerInput) {
                    case 1:
                        System.out.println("Please enter author name: ");
                        String inputAuthor = scanner.nextLine();

                        System.out.println("Please enter book title: ");
                        String inputTitle = scanner.nextLine();

                        System.out.println("Please enter quantity: ");
                        int inputQuantity = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline

                        Book bookExists = findBookByTitle(books, inputTitle);

                        if (bookExists != null) {
                            bookExists.setQuantity(bookExists.getQuantity() + inputQuantity);
                            System.out.println("Updated quantity for " + inputTitle);
                        } else {
                            books.add(new Book(inputAuthor, inputTitle, inputQuantity));
                            System.out.println("The book titled " + inputTitle + " by " + inputAuthor + " has been added successfully!");
                        }
                        break;

                    case 2:
                        System.out.println("Please enter book title to borrow: ");
                        String inputBorrowTitle = scanner.nextLine();
                        System.out.println("Please enter number of copies to borrow: ");
                        int inputBorrowQuantity = scanner.nextInt();
          
                        Book bookBorrowExists = findBookByTitle(books, inputBorrowTitle);
                        if(bookBorrowExists != null && bookBorrowExists.getQuantity() >= inputBorrowQuantity){
                          bookBorrowExists.setQuantity(bookBorrowExists.getQuantity() - inputBorrowQuantity);
                          System.out.println("Book titled " + inputBorrowTitle + " borrowed successfully!");
                        } else {
                          System.out.println("Not enough copies available or book not found");
                        }
                        break;
                    case 3:
                        System.out.println("Please enter book title to return: ");
                        String inputReturnTitle = scanner.nextLine();
                        System.out.println("Please enter number of copies to return: ");
                        int inputReturnQuantity = scanner.nextInt();
                    
                        Book bookReturnExists = findBookByTitle(books, inputReturnTitle);
                        if(bookReturnExists != null){
                          bookReturnExists.setQuantity(bookReturnExists.getQuantity() + inputReturnQuantity);
                          System.out.println(inputReturnQuantity + " Book(s) titled " + inputReturnTitle + " returned successfully!");
                        } else {
                          System.out.println("No book titled " + inputReturnTitle + " exist in our library!");
                          System.out.println("To add a book to the system, please navigate to the 'Add a Book' section by entering '1'.");
                        }
                        break;
                    case 4:
                        System.out.println("Exiting the program.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                        break;
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); 
            }
        }
    }

    private static List<Book> initializeBooks() {
    List<Book> initialBooks = new ArrayList<>();
    initialBooks.add(new Book("Yukio Mishima", "The Sound of Wave", 3));
    initialBooks.add(new Book("Osamu Dazai", "No Longer Human", 11));
    return initialBooks;
  }

    public static Book findBookByTitle(List<Book> books, String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}

class Book {
    private String author;
    private String title;
    private int quantity;

    public Book(String author, String title, int quantity) {
        this.author = author;
        this.title = title;
        this.quantity = quantity;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
