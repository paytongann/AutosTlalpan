package com.example.autostlalpan.model;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("db.json")
    Observable<Response<ArrayList<CarsPojo>>> getCarResults();
}
