package cn.aorise.database.db;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;

import cn.aorise.common.core.db.DBManager;
import cn.aorise.database.config.Constant;
import cn.aorise.database.db.entity.LangEntity;

/**
 * @Description: 数据库操作类，由于greenDao的特殊性，不能在框架中搭建，
 * 所有数据库操作都可以参考该类实现自己的数据库操作管理类，不同的Dao实现
 * 对应的getAbstractDao方法就行。
 */
public class LangDbHelper {
    private static final String DB_NAME = Constant.DB_NAME;
    private static LangDbHelper sInstance;
    private DaoMaster.DevOpenHelper mHelper;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private static DBManager<LangEntity, Long> mLangEntityDao;

    private LangDbHelper() {
    }

    public static LangDbHelper getInstance() {
        if (sInstance == null) {
            synchronized (LangDbHelper.class) {
                if (sInstance == null) {
                    sInstance = new LangDbHelper();
                }
            }
        }
        return sInstance;
    }

    public void init(Context context) {
        init(context, DB_NAME);
    }

    public void init(Context context, String dbName) {
        mHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        mDaoMaster = new DaoMaster(mHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoMaster getDaoMaster() {
        return mDaoMaster;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    private void clear() {
        if (mDaoSession != null) {
            mDaoSession.clear();
            mDaoSession = null;
        }
    }

    public void close() {
        clear();
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }
    }

    public DBManager<LangEntity, Long> getLangEntityDao() {
        if (mLangEntityDao == null) {
            mLangEntityDao = new DBManager<LangEntity, Long>() {
                @Override
                public AbstractDao<LangEntity, Long> getAbstractDao() {
                    return mDaoSession.getLangEntityDao();
                }
            };
        }
        return mLangEntityDao;
    }
}
