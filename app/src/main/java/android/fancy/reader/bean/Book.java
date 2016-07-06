package android.fancy.reader.bean;

/**
 * Created by inx95 on 16-7-6.
 */
public class Book {
    private String bookName;
    private String iconUrl;
    private String bookUrl;
    private String author;
    private String description;


    public Book(String bookName, String iconUrl, String bookUrl, String author, String description) {
        this.bookName = bookName;
        this.iconUrl = iconUrl;
        this.bookUrl = bookUrl;
        this.author = author;
        this.description = description;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
