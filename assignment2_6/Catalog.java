import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Catalog<T> {
    private List<LibraryItem<T>> items;

    public Catalog() {
        this.items = new ArrayList<>();
    }

    public void addItem(LibraryItem<T> item) {
        items.add(item);
        System.out.println("Item added: " + item);
    }

    public void removeItem(T itemID) {
        Optional<LibraryItem<T>> itemToRemove = items.stream()
                .filter(item -> item.getItemID().equals(itemID))
                .findFirst();

        if (itemToRemove.isPresent()) {
            items.remove(itemToRemove.get());
            System.out.println("Item removed: " + itemToRemove.get());
        } else {
            System.out.println("Item with ID " + itemID + " not found.");
        }
    }

    public void viewCatalog() {
        if (items.isEmpty()) {
            System.out.println("Catalog is empty.");
        } else {
            items.forEach(System.out::println);
        }
    }
}

