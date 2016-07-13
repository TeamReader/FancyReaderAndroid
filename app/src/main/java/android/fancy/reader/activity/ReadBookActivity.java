package android.fancy.reader.activity;

import android.app.Activity;
import android.fancy.reader.R;
import android.fancy.reader.activity.view.ReadView;
import android.fancy.reader.bean.ContentPerPage;
import android.fancy.reader.tool.CalculateLinesThread;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by inx95 on 16-7-12.
 */
public class ReadBookActivity extends Activity implements ReadView.OnTurnPageListener {
    private ReadView mReadView;
    private File mFile;
    private int mCurrentPage;
    private Map<Integer, ContentPerPage> pages = new HashMap<>();
    BufferedReader reader = null;
    FileInputStream fis = null;
    InputStreamReader isr = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);
        mReadView = (ReadView) findViewById(R.id.read_tv);
        mReadView.setTextSize(15);
        mFile = new File("/sdcard/test.txt");
        initReader();
        mCurrentPage = 0;
        boolean isExist = mFile.exists();
        Toast.makeText(this, String.valueOf(isExist), Toast.LENGTH_LONG).show();
        mReadView.setSlop(0f);
        mReadView.setOnTurnPageListener(this);
        mReadView.post(() -> {
            new CalculateLinesThread(pages, mFile, mReadView.getLinePerPageInTv(), mReadView.getCharCountPerLine()).start();
            setReadViewText(mCurrentPage);
        });
    }

    private void initReader() {
        try {
            fis = new FileInputStream(mFile);
            isr = new InputStreamReader(fis);
            reader = new BufferedReader(isr);
            reader.mark(Integer.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setReadViewText(int pageNum) {
        try {
            reader.reset();
            StringBuilder builder = new StringBuilder();
            while (pages.get(pageNum) == null) ;
            ContentPerPage page = pages.get(pageNum);
            int startLine = page.getStartLine();
            int continuanceLine = page.getContinuanceLines();
            //略过前面的行号
            while (startLine-- > 0) reader.readLine();
            //处理第一行和开始索引
            builder.append(reader.readLine().substring(page.getIndexInStartLine()));
            continuanceLine--;

            while (continuanceLine-- > 0) {
                builder.append("\n" + reader.readLine());
            }
            mReadView.setText(builder.toString());
        } catch (FileNotFoundException e) {
            Snackbar.make(mReadView, "书籍不存在", Snackbar.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        try {
            if (reader != null) reader.close();
            if (fis != null) fis.close();
            if (isr != null) isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    @Override
    public void turnRight() {
        setReadViewText(++mCurrentPage);
    }

    @Override
    public void turnLeft() {
        if (--mCurrentPage < 0) mCurrentPage = 0;
        setReadViewText(mCurrentPage);
    }
}
