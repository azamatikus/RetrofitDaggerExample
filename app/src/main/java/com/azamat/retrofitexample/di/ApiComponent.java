package com.azamat.retrofitexample.di;

import com.azamat.retrofitexample.model.CountriesService;
import com.azamat.retrofitexample.viewmodel.ListViewModel;

import dagger.Component;

@Component(modules = {ApiModule.class})
public interface ApiComponent {

    void inject (CountriesService service);
    void inject2(ListViewModel model);

}
