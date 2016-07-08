package android.fancy.reader;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.fancy.reader.tool.DataSource;
import android.preference.PreferenceManager;

import com.litesuits.orm.LiteOrm;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;


/**
 * Created by inx95 on 16-7-5.
 */
public class FancyReaderApplication extends Application {
    private static FancyReaderApplication applicationContext;
    private static LiteOrm liteOrm;

    public static void initDB(Context context) {
        liteOrm = LiteOrm.newCascadeInstance(context, "fancy_reader.db");
        liteOrm.setDebugged(true);
    }

    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .denyCacheImageMultipleSizesInMemory()
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(config);
    }

    public static FancyReaderApplication getInstance() {
        return applicationContext;
    }

    public static LiteOrm getLiteOrm() {
        return liteOrm;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        initImageLoader(getApplicationContext());
        initDB(getApplicationContext());
    }

    public static SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(applicationContext);
    }
}
