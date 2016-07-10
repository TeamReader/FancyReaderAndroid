package observable;

import android.fancy.reader.bean.Book;

import observable.entity.PreferenceResult;
import observable.entity.TBook;
import observable.entity.TResult;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by inx95 on 16-7-10.
 */
public interface ApiService {
    String BASE_URL = "http://192.168.199.136:8080";


    @GET("logincheck")
    Observable<TResult<Boolean>> login(@Query("userName") String userName, @Query("password") String password);

    @GET("userRegist")
    Observable<TResult<Boolean>> regist(@Query("userName") String userName, @Query("password") String password);

    @GET("getPregerence")
    Observable<PreferenceResult> getPreference(@Query("userName") String userName);

    @GET("loadBookList")
    Observable<TBook<Book>> loadBookList(@Query("userName") String userName);

    @GET("deleteBook")
    Observable<TResult<Boolean>> deleteBook(@Query("bookName") String bookName, @Query("userName") String userName);

    @GET("addBook")
    Observable<TResult<Boolean>> addBook(@Query("bookName") String bookName, @Query("userName") String userName);

    @GET("recordIndex")
    Observable<TResult<Boolean>> recordIndex(@Query("bookName") String bookName, @Query("userName") String userName,@Query("lineNum")String lineNum);

    @GET("search")
    Observable<TBook<Book>> search(@Query("bookName") String bookName);


}

