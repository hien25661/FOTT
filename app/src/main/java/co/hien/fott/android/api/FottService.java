package co.hien.fott.android.api;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.hien.fott.android.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by nguyenvanhien on 11/28/17.
 */

public class FottService {
    private static FottApi instance;

    public static FottApi getService() {
        if (instance == null) {
            instance = getRetrofit().create(FottApi.class);
        }
        return instance;
    }

    private static Retrofit getRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_FOTT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

}
