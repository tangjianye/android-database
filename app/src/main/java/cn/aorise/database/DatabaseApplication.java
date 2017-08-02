package cn.aorise.database;

import android.content.Context;

import cn.aorise.common.BaseApplication;
import cn.aorise.database.db.LangDbHelper;

public class DatabaseApplication extends BaseApplication {
    private static final String TAG = DatabaseApplication.class.getSimpleName();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LangDbHelper.getInstance().init(this);
    }
}
