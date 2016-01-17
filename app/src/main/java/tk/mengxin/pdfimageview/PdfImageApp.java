package tk.mengxin.pdfimageview;

import android.app.Application;
import android.content.Context;

/**
 * Created by Android Studio.
 * User: Xin Meng
 * Date: 16/01/2016
 * Time: 16:19
 * Version: V 1.0
 */

public class PdfImageApp extends Application {
    private static PdfImageApp mInstance;
    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        this.setAppContext(getApplicationContext());
    }

    public static PdfImageApp getInstance(){
        return mInstance;
    }
    public static Context getAppContext() {
        return mAppContext;
    }
    public void setAppContext(Context mAppContext) {
        this.mAppContext = mAppContext;
    }
}