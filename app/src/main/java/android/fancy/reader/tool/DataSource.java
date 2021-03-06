package android.fancy.reader.tool;

import android.content.Context;
import android.fancy.reader.FancyReaderApplication;
import android.fancy.reader.bean.Book;
import android.fancy.reader.bean.Record;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.litesuits.orm.LiteOrm;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by inx95 on 16-7-7.
 */
public class DataSource {


    public static class LiteOrmUtil {
        private static LiteOrm liteOrm = FancyReaderApplication.getLiteOrm();

        //book
        public static List<Book> getBookListFromDB() {
            return liteOrm.query(Book.class);
        }

        public static boolean updateBookListToDB(List<Book> bookList) {
            return liteOrm.update(bookList) == bookList.size();
        }

        public static boolean insertBookListToDB(List<Book> bookList) {
            return liteOrm.insert(bookList) == bookList.size();
        }

        //record
        public static List<Record> getRecordList() {
            return liteOrm.query(Record.class);
        }

        public static boolean updateRecordList(List<Record> recordList) {
            return liteOrm.update(recordList) == recordList.size();
        }

        public static boolean insertRecordList(List<Record> recordList) {
            return liteOrm.insert(recordList) == recordList.size();
        }

        public static boolean insertRecord(Record record) {
            return liteOrm.insert(record) == 1;
        }

        public static boolean updateRecord(Record record) {
            return liteOrm.update(record) == 1;
        }
    }

    public static class VolleyUtil {
        private final Context context;
        private static VolleyUtil INSTANCE;
        private final RequestQueue mRequestQueue;

        /**
         * @param context suggest using applicationContext
         */
        public static VolleyUtil getInstance(Context context) {
            if (INSTANCE == null) {
                synchronized (VolleyUtil.class) {
                    INSTANCE = new VolleyUtil(context);
                }
            }
            return INSTANCE;
        }

        private VolleyUtil(Context context) {
            this.context = context;
            mRequestQueue = Volley.newRequestQueue(context);
        }

        public List<Book> getBookList(String url, String userName, Response.Listener<JSONObject> success, Response.ErrorListener error) {
            url = url + "?" + "userName=" + userName;
            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.GET,
                    url,
                    success,
                    error
            );
            return null;//todo replace it
        }
    }

    public static List<Book> testLiteOrmUtil() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(
                "解忧杂货店",
                "https://img1.doubanio.com/lpic/s27284878.jpg",
                "https://img1.doubanio.com/lpic/s27284878.jpg",
                "东野圭吾",
                "治愈系好书"
        ));
        bookList.add(new Book(
                "不解忧杂货店",
                "https://img1.doubanio.com/lpic/s27284878.jpg",
                "https://img1.doubanio.com/lpic/s27284878.jpg",
                "东野圭吾",
                "治愈系好书"
        ));
        LiteOrmUtil.insertBookListToDB(bookList);
        bookList.get(0).setAuthor("nimei");
        LiteOrmUtil.updateBookListToDB(bookList);
        return LiteOrmUtil.getBookListFromDB();


    }

}
