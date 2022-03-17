package com.azamat.retrofitexample.di;


import com.azamat.retrofitexample.model.CountriesApi;
import com.azamat.retrofitexample.model.CountriesService;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    private static final String BASE_URL = "https://raw.githubusercontent.com";

    @Provides
    public CountriesApi provideCountriesApi(){
        return new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(CountriesApi.class);
    }

    @Provides
    public CountriesService provideCountriesService(){
        return CountriesService.getInstance();

    }
}
