package android.fancy.reader.bean;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by inx95 on 16-7-6.
 */
@Table("book")

public class Book {

    @Column("bookName")
    @PrimaryKey(AssignType.BY_MYSELF)
    private String bookName;

    @Column("icoUrl")
    private String icoUrl;

    @Column("bookUrl")
    private String bookUrl;

    @Column(("author"))
    private String author;

    @Column("description")
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
