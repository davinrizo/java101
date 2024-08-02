import java.util.Scanner;

public class Main {
  public static void main(String[] args){
    Catalog<Integer> catalog = new Catalog<>();
    Scanner scanner = new Scanner(System.in);
    boolean running = true;

    while (running) {
      System.out.println("Library Catalog System:");
      System.out.println("1. Add Item");
      System.out.println("2. Remove Item");
      System.out.println("3. View Catalog");
      System.out.println("4. Exit");
      System.out.println("Choose an option: ");
      int choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1:
          System.out.print("Enter item title: ");
          String title = scanner.nextLine();
          System.out.print("Enter item author: ");
          String author = scanner.nextLine();
          System.out.print("Enter item ID: ");
          int itemID = scanner.nextInt();
          scanner.nextLine();
          LibraryItem<Integer> newItem = new LibraryItem<>(title, author, itemID);
          catalog.addItem(newItem);
          break;

        case 2:
          System.out.print("Enter item ID to remove: ");
          int removeID = scanner.nextInt();
          scanner.nextLine();
          catalog.removeItem(removeID);
          break;

        case 3:
          catalog.viewCatalog();
          break;
        case 4:
          running = false;
        System.out.println("Closing the program...");
        default:
          System.out.println("Invalid option. Please try again");
          break;
      }
    }
    scanner.close();
  }
}
