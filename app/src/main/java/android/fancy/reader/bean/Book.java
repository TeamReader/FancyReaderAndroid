package android.fancy.reader.bean;

/**
 * Created by inx95 on 16-7-6.
 */
public class Book {
    private String bookName;
    private String icoUrl;
    private String bookUrl;
    private String author;
    private String description;


    public Book(String bookName, String icoUrl, String bookUrl, String author, String description) {
        this.bookName = bookName;
        this.icoUrl = icoUrl;
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

    public String getIcoUrl() {
        return icoUrl;
    }

    public void setIcoUrl(String icoUrl) {
        this.icoUrl = icoUrl;
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
