package com.azamat.retrofitexample.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.azamat.retrofitexample.di.DaggerApiComponent;
import com.azamat.retrofitexample.model.CountriesService;
import com.azamat.retrofitexample.model.CountryModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ListViewModel extends ViewModel {

    public MutableLiveData<List<CountryModel>> countries = new MutableLiveData<>();
    public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public CountriesService countriesService;
    private final CompositeDisposable disposable = new CompositeDisposable();

    public void refresh() {
        DaggerApiComponent.create().inject2(this);
        fetchCountries();
    }

    private void fetchCountries() {
        loading.setValue(true);
        disposable.add(
                countriesService.getCountries()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<CountryModel>>() {

                            @Override
                            public void onSuccess(List<CountryModel> countryModels) {
                                countries.setValue(countryModels);
                                countryLoadError.setValue(false);
                                loading.setValue(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                countryLoadError.setValue(true);
                                loading.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}