package android.fancy.reader.DataSource;

import android.content.Context;
import android.fancy.reader.bean.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inx95 on 16-7-7.
 */
public class BookDataSource {


    public BookDataSource(Context context) {
    }

    public List<Book> booksOfSource(boolean isNative) {
        //todo get bookList
        return new ArrayList<>();
    }
}
