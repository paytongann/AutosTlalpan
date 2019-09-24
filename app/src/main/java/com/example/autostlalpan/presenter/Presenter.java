package com.example.autostlalpan.presenter;

import android.content.Context;
import android.util.Log;

import com.example.autostlalpan.model.ApiInterface;
import com.example.autostlalpan.model.CarsPojo;
import com.example.autostlalpan.view.ViewContract;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Presenter implements PresenterContract {

    private ViewContract view;
    private Context context;

    @Override
    public void onBindView(ViewContract view) {
        this.view = view;
    }

    public Presenter(Context context) {
        this.context = context;
    }


    @Override
    public void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/Shasra/SLT.json/master/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        apiInterface.getCarResults().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<ArrayList<CarsPojo>>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Response<ArrayList<CarsPojo>> resultsPojoResponse) {
                if (resultsPojoResponse.isSuccessful()) {
                    view.onDataPopulated(resultsPojoResponse.body());
                    Log.d(TAG, "onNext: " + resultsPojoResponse.body().get(0).titulo);
                } else {
                    Log.d(TAG, "bad code");
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
