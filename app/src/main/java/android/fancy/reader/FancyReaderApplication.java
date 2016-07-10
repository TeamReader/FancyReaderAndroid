package android.fancy.reader;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.litesuits.orm.LiteOrm;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.IOException;

import observable.ApiService;

import retrofit2.Converter;
import  retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by inx95 on 16-7-5.
 */
public class FancyReaderApplication extends Application {
    private static FancyReaderApplication applicationContext;
    private static LiteOrm liteOrm;
    private static ApiService mApiService;


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
        initRetrofit();
    }

    private void initRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(ApiService.BASE_URL)
                .build();
        mApiService = retrofit.create(ApiService.class);
    }

    class StringCover implements Converter<String,String>{

        @Override
        public String convert(String value) throws IOException {
            return value.trim();
        }
    }
    public static ApiService getApiService() {
        return mApiService;
    }

    public static SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(applicationContext);
    }
}
