public class LibraryItem<T> implements Comparable<LibraryItem<T>> {
    private String title;
    private String author;
    private T itemID;

    public LibraryItem(String title, String author, T itemID) {
        this.title = title;
        this.author = author;
        this.itemID = itemID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public T getItemID() {
        return itemID;
    }

    public void setItemID(T itemID) {
        this.itemID = itemID;
    }

    @Override
    public int compareTo(LibraryItem<T> o) {
        return this.title.compareTo(o.title);
    }

    @Override
    public String toString() {
        return "LibraryItem{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", itemID=" + itemID +
                '}';
    }
}

