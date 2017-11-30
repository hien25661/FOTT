package co.hien.fott.android;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.concurrent.atomic.AtomicBoolean;

import co.hien.fott.android.database.SQLiteHelper;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by nguyenvanhien on 11/28/17.
 */

public class FottApplication extends Application {
    public static SQLiteHelper _dbHelper;

    private static FottApplication instance;
    public static AtomicBoolean networkConnected;
    public static FottApplication getInstance(){
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        _dbHelper = new SQLiteHelper(this);
        instance = this;
    }

    public boolean isNetworkConnected() {
        if (networkConnected != null) return networkConnected.get();
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        networkConnected = new AtomicBoolean(ni != null);
        return networkConnected.get();
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public SQLiteHelper getDbHelper() {
        return _dbHelper;
    }
}
