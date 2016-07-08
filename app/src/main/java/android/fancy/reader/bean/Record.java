package android.fancy.reader.bean;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by inx95 on 16-7-6.
 */

@Table("record")
public class Record {

    @Column("userName")
    @PrimaryKey(AssignType.BY_MYSELF)
    private String userName;

    @Column("bookName")
    private String bookName;

    @Column("lineNum")
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


