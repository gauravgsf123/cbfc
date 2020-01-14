package com.app.cbfc.view_model;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import android.app.Application;
import com.app.cbfc.model.CustomerPojo;
import com.app.cbfc.model.CustomerRepository;

import java.util.List;

public class CustomberViewModel extends AndroidViewModel {

    CustomerRepository customerRepository;
    public CustomberViewModel(@NonNull Application application) {
        super(application);
        customerRepository = new CustomerRepository(application);
    }

    public List<CustomerPojo.Result> getAllCustomber() {
        return customerRepository.getMutableLiveData();
    }
}
