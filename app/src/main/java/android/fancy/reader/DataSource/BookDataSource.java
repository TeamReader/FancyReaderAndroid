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
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(
                "解忧杂货店",
                "https://img1.doubanio.com/lpic/s27284878.jpg",
                "https://img1.doubanio.com/lpic/s27284878.jpg",
                "东野圭吾",
                        "现代人内心流失的东西，这家杂货店能帮你找回——,僻静的街道旁有一家杂货店，只要写下烦恼投进卷帘门的投信口，第二天就会在店后的牛奶箱里得到回答。\n" +
                        "\n" +
                        "因男友身患绝症，年轻女孩静子在爱情与梦想间徘徊；克郎为了音乐梦想离家漂泊，却在现实中寸步难行；少年浩介面临家庭巨变，挣扎在亲情与未来的迷茫中……\n" +
                        "\n" +
                        "他们将困惑写成信投进杂货店，随即奇妙的事情竟不断发生。\n" +
                        "\n" +
                        "生命中的一次偶然交会，将如何演绎出截然不同的人生？\n" +
                        "\n" +
                        "如今回顾写作过程，我发现自己始终在思考一个问题：站在人生的岔路口，人究竟应该怎么做？我希望读者能在掩卷时喃喃自语：我从未读过这样的小说。——东野圭吾"
                ));
        return bookList;
    }
}
