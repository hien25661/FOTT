package co.hien.fott.android.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import co.hien.fott.android.helper.EventBusUtils;


/**
 * Created by nguyenvanhien on 11/28/17.
 */

public class BaseActivity extends AppCompatActivity {
    protected EventBusUtils.Holder eventBus = EventBusUtils.getDefault();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusUtils.unregister(eventBus, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBusUtils.register(eventBus, this);
    }
}
