import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Assignment_2 {
  public static void main(String[] args){
    List<Book> books = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);

    System.out.print("Welcome to Alexandria Library!");
    System.out.print("Please input 1 to add a book, 2 to borrow a book, 3 to return book");
    int answerInput = scanner.nextInt();

    boolean validInput;
    do{
    switch(answerInput){
      case 1:
        validInput = true;
          System.out.println("Please enter the book author");
          String inputAuthor = scanner.next();
          
          System.out.println("Please enter the book title");
          String inputTitle = scanner.next();

        if(isBookExist(books, inputAuthor, inputTitle)){
          System.out.println("Book already exists!!!!!!!!!!");
        }else {
          System.out.println("Please enter quantity: ");
          int inputQuantity = scanner.nextInt();

          books.add(new Book(inputAuthor, inputTitle, inputQuantity));
        }
      case 2:
        validInput = true;
        
      default:
        validInput = false;

    }

    }while(!validInput);


  }

  public static boolean isBookExist(List<Book> books, String author, String title){
    for(Book book: books){
      if(book.getAuthor().equalsIgnoreCase(author) && book.getTitle().equalsIgnoreCase(title)){
        return true;
      }
    }
    return false;
  }
}

public class Book {
  private String author;
  private String title;
  private int quantity;

  public Book(String author, String title, int quantity){
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

  public void setAuthor(String author){
    this.author = author;
  }

  public void setTitle(String title){
    this.title = title;
  }

  public void setQuantity(int quantity){
    this.quantity = quantity;
  }
}
