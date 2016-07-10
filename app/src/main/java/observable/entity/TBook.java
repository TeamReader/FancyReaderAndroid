package observable.entity;

/**
 * Created by inx95 on 16-7-10.
 */
public class TBook<T> {
    private T books;

    public T getBooks() {
        return books;
    }

    public void setBooks(T books) {
        this.books = books;
    }
}
