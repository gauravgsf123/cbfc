package com.app.cbfc.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.app.cbfc.R;
import com.app.cbfc.adapter.CustomberAdapter;
import com.app.cbfc.model.CustomerPojo;
import com.app.cbfc.view_model.CustomberViewModel;

import java.util.ArrayList;
import java.util.List;

public class CustomerListActivity extends AppCompatActivity {

    private static final String TAG = "CustomerListActivity";
    RecyclerView mRecyclerView;
    private CustomberViewModel customberViewModel;
    CustomberAdapter customberAdapter;
    List<CustomerPojo.Result> list = new ArrayList<>();
    MutableLiveData<List<CustomerPojo.Result>> mutableLiveData = new MutableLiveData<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        Log.e(TAG,"CustomerListActivity");
        mRecyclerView = findViewById(R.id.customerRecyclerView);
        customberViewModel = ViewModelProviders.of(CustomerListActivity.this).get(CustomberViewModel.class);

        /*customberViewModel.getAllCustomber().observe(this, new Observer<List<CustomerPojo.Result>>() {
            @Override
            public void onChanged(List<CustomerPojo.Result> results) {
                    prepareRecyclerView(results);
                    for(int i =0;i<results.size();i++){
                        Log.e("data",results.get(i).getCus_full_name());

                    }
            }
        });*/

        customberViewModel.getAllCustomber();
        prepareRecyclerView(customberViewModel.getAllCustomber());
        /*mutableLiveData = customberViewModel.getAllCustomber();
        for(int i =0;i<mutableLiveData.getValue().size();i++){
           list.add(mutableLiveData.getValue().get(i));
        }
        prepareRecyclerView(list);*/
    }

    private void prepareRecyclerView(List<CustomerPojo.Result> results) {
        Log.e(TAG,"prepareRecyclerView"+results.size());
            customberAdapter = new CustomberAdapter(results);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(customberAdapter);
        customberAdapter.notifyDataSetChanged();
    }
}
