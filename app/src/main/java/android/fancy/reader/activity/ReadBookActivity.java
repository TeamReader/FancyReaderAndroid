package android.fancy.reader.activity;

import android.app.Activity;
import android.fancy.reader.R;
import android.fancy.reader.activity.view.ReadView;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by inx95 on 16-7-12.
 */
public class ReadBookActivity extends Activity{
    private ReadView mReadView;
    private String test="shafdjklllllllllllllllllllllllllllllllh\\n" +
            "\\\\nsfjkafsdlf\\nn\\n\\nn\\n\\nn\\n\\n\\nn\\n\\n\\nn\\n\\n\\nn\\n\\n\\n\\hhhhhhhherwrhwfhsdtfwarbhfawfhewakjfahfearhawkerahwkpyheawew";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);
        mReadView= (ReadView) findViewById(R.id.read_tv);
        mReadView.setTextSize(50);
        mReadView.setText(test);
//        ViewTreeObserver viewTreeObserver = readView.getViewTreeObserver();
//        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//                Toast.makeText(ReadBookActivity.this, readView.getLineCount(), Toast.LENGTH_LONG).show();
//                return true;
//            }
//        });
        mReadView.post(new Runnable() {
            @Override
            public void run() {
                int lineCount = mReadView.getLineCount();
                // Use lineCount here
//                Toast.makeText(ReadBookActivity.this, mReadView.getLineCount()+"", Toast.LENGTH_LONG).show();
            }
        });
        mReadView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(ReadBookActivity.this, mReadView.getCharCountForS("shafdjklllllllllllllllllllllllllllllllh\\")+"", Toast.LENGTH_LONG).show();
                Toast.makeText(ReadBookActivity.this, mReadView.getLargestLineCount()+"", Toast.LENGTH_LONG).show();


            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }
}
