package com.app.cbfc.ui.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.cbfc.R;
import com.app.cbfc.model.ResponsePojo;
import com.app.cbfc.network.APIClient;
import com.app.cbfc.network.APIRequest;
import com.app.cbfc.ui.activity.AdminDashboard;
import com.app.cbfc.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddEmployeeFragment extends Fragment {

    View view;
    @BindView(R.id.emp_full_name)
    EditText etFullName;
    @BindView(R.id.emp_father_name)
    EditText etFatherName;
    @BindView(R.id.emp_mobile)
    EditText etMobile;
    @BindView(R.id.emp_address)
    EditText etAddress;
    @BindView(R.id.password)
    EditText etPassword;
    @BindView(R.id.emp_adhar_card_number)
    EditText etAdharCardNumber;
    @BindView(R.id.emp_pan_card_number)
    EditText etPanCardNumber;
    @BindView(R.id.submit)
    Button submit;
    ResponsePojo response;

    private Unbinder unbinder;

    public AddEmployeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_employee, container, false);
        unbinder = ButterKnife.bind(this,view);
        //ButterKnife.bind(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utility.haveNetworkConnection(getContext())) {
                    if (validate()) {
                        addEmployee();
                    }
                } else {
                    Toast.makeText(getContext(), "No Internet", Toast.LENGTH_SHORT).show();
                }


            }
        });

        return view;
    }

    private boolean validate(){
        if (TextUtils.isEmpty(etFullName.getText().toString().trim())) {
            etFullName.requestFocus();
            etFullName.setError(getString(R.string.enter_name));
            return false;
        }
        else if (TextUtils.isEmpty(etFatherName.getText().toString().trim())) {
            etFatherName.requestFocus();
            etFatherName.setError(getString(R.string.enter_father));
            return false;
        }
        else if (TextUtils.isEmpty(etMobile.getText().toString().trim())) {
            etMobile.requestFocus();
            etMobile.setError(getString(R.string.enter_mobile));
            return false;
        }
        else if (TextUtils.isEmpty(etAddress.getText().toString().trim())) {
            etAddress.requestFocus();
            etAddress.setError(getString(R.string.enter_address));
            return false;
        }
        else if (TextUtils.isEmpty(etPassword.getText().toString().trim())) {
            etPassword.requestFocus();
            etPassword.setError(getString(R.string.enter_password));
            return false;
        }
        else if (TextUtils.isEmpty(etAdharCardNumber.getText().toString().trim())) {
            etAdharCardNumber.requestFocus();
            etAdharCardNumber.setError(getString(R.string.enter_adhar_number));
            return false;
        }
        else if (TextUtils.isEmpty(etPanCardNumber.getText().toString().trim())) {
            etPanCardNumber.requestFocus();
            etPanCardNumber.setError(getString(R.string.enter_pan_number));
            return false;
        }
        else {
            return true;
        }
    }

    private void addEmployee(){
        APIRequest service = APIClient.getInstance();
        service.addEmployee(
                etFullName.getText().toString().trim(),
                etFatherName.getText().toString().trim(),
                etMobile.getText().toString().trim(),
                etAddress.getText().toString().trim(),
                etPassword.getText().toString().trim(),
                etAdharCardNumber.getText().toString().trim(),
                etPanCardNumber.getText().toString().trim())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponsePojo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Utility.showDailog(getContext());
                    }

                    @Override
                    public void onNext(ResponsePojo responsePojo) {
                        response = responsePojo;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utility.hideDailog();
                    }

                    @Override
                    public void onComplete() {
                        Utility.hideDailog();
                        // Updates UI with data
                        if(response.getStatus()==1){
                             UploadCustomberDocumnet uploadCustomberDocumnet = UploadCustomberDocumnet.getInstant();
                             Bundle bundle = new Bundle();
                             bundle.putInt("last_id",response.getLast_id());
                             uploadCustomberDocumnet.setArguments(bundle);
                            ((AdminDashboard)getActivity()).updateFragment(uploadCustomberDocumnet,"Upload Document");
                        }
                    }
                });
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
