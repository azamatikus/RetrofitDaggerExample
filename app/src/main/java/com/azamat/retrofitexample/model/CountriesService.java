package com.azamat.retrofitexample.model;


import com.azamat.retrofitexample.di.DaggerApiComponent;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountriesService {
    private static CountriesService instance;

    @Inject
    public CountriesApi api ;

    private  CountriesService() {
        DaggerApiComponent.create().inject(this);
    }

    public static CountriesService getInstance() {
        if (instance == null){
            instance = new CountriesService();
        }
        return instance;
    }



    public Single<List<CountryModel>> getCountries(){
        return  api.getCountries();
    }
}
