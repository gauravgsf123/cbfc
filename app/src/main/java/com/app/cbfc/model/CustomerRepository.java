package com.app.cbfc.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.app.cbfc.network.APIClient;
import com.app.cbfc.network.APIRequest;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CustomerRepository {

    private ArrayList<CustomerPojo.Result> results = new ArrayList<>();
    private MutableLiveData<List<CustomerPojo.Result>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public CustomerRepository(Application application) {
        this.application = application;
    }

    public List<CustomerPojo.Result> getMutableLiveData() {

        APIRequest service = APIClient.getInstance();

        service.getCustomerList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CustomerPojo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CustomerPojo loginPojo) {
                        results = loginPojo.getResult();
                        //mutableLiveData.setValue(results);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        // Updates UI with data
                    }
                });

        return results;
    }
}
