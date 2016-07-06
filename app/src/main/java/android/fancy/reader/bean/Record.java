package android.fancy.reader.bean;

/**
 * Created by inx95 on 16-7-6.
 */
public class Record {
    private String userName;
    private String bookName;
    private int lineNum;

    public Record(String userName, String bookName, int lineNum) {
        this.userName = userName;
        this.bookName = bookName;
        this.lineNum = lineNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getLineNum() {
        return lineNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }
}


