import com.ecommerce.Product;
import com.ecommerce.Customer;
import com.ecommerce.orders.Order;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create some products
        Product product1 = new Product(1, "Laptop", 1000.00);
        Product product2 = new Product(2, "Chair", 500.00);
        Product product3 = new Product(3, "Tissue", 100.00);

        // Store products in a list
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);

        // Create a customer
        Customer customer = new Customer(1, "John Doe");

        // Interactive menu
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Browse Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Place Order");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // Browse Products
                    System.out.println("\nAvailable products:");
                    for (Product product : products) {
                        System.out.println(product);
                    }
                    break;
                case 2:
                    // Add Product to Cart
                    System.out.print("Enter the product ID to add to cart: ");
                    int productId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    Product productToAdd = null;
                    for (Product product : products) {
                        if (product.getProductID() == productId) {
                            productToAdd = product;
                            break;
                        }
                    }
                    if (productToAdd != null) {
                        customer.addProductToCart(productToAdd);
                        System.out.println(productToAdd.getName() + " added to cart.");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 3:
                    // View Cart
                    System.out.println("\nShopping Cart:");
                    for (Product product : customer.getShoppingCart()) {
                        System.out.println(product);
                    }
                    System.out.println("Total Cost: $" + customer.calculateTotalCost());
                    break;
                case 4:
                    // Place Order
                    Order order = new Order(1, customer, customer.getShoppingCart());
                    System.out.println("\nOrder Summary:");
                    System.out.println(order.generateOrderSummary());
                    break;
                case 5:
                    // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }
}

