package observable;


import android.fancy.reader.FancyReaderApplication;
import android.fancy.reader.bean.Book;
import android.fancy.reader.tool.DataSource;

import java.util.List;

import rx.Observable;

/**
 * Created by inx95 on 16-7-7.
 */
public class BookListFromSourceObservable {
    public static Observable<List<Book>> ofSource(final boolean isNative) {
        return Observable.create(subscriber ->{
//            List<Book> bookList = FancyReaderApplication.getDataSource().booksOfSource(isNative);
            List<Book> bookList = DataSource.testLiteOrmUtil();
            if(bookList!=null)subscriber.onNext(bookList);
            subscriber.onCompleted();
        });
    }
}
