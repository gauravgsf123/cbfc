package com.app.cbfc.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.app.cbfc.R;
import com.app.cbfc.model.LoginPojo;
import com.app.cbfc.network.APIClient;
import com.app.cbfc.network.APIRequest;
import com.app.cbfc.util.SharedPrefManager;
import com.app.cbfc.util.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    SharedPrefManager sharedPrefManager;
    //EditText userId, password;
    @BindView(R.id.et_username)
    EditText userId;
    @BindView(R.id.et_password)
    EditText password;
    @BindView(R.id.login_button)
    Button login_button;
    @BindView(R.id.rg_user_type)
    RadioGroup rgUserType;
    @BindView(R.id.rb_admin)
    RadioButton rbAdmin;
    @BindView(R.id.rb_employee)
    RadioButton rbEmployee;
    private static final int PERMISSION_REQUEST_CODE = 200;
    private int userType=0;
    ArrayList<LoginPojo.Result> results;
    LoginPojo loginPojo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init(){
        ButterKnife.bind(this);
        sharedPrefManager = SharedPrefManager.getInstance(this);
        if(sharedPrefManager.getUserId()!=null){
            if(sharedPrefManager.getUser().getUser_type()==1){
                Intent intent = new Intent(LoginActivity.this, AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }else if(sharedPrefManager.getUser().getUser_type()==2){
                Intent intent = new Intent(LoginActivity.this, EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        }
        /*userId = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        login_button = findViewById(R.id.login_button);*/
        if(rbAdmin.isChecked()){
            userType=1;
        }else if (rbEmployee.isChecked()){
            userType=2;
        }

        login_button.setOnClickListener(this);
        rgUserType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb=(RadioButton)findViewById(i);
                //Toast.makeText(LoginActivity.this, ""+rb.getText(), Toast.LENGTH_SHORT).show();
                if(rb.getText().toString().equalsIgnoreCase("Admin")){
                    userType=1;
                }else if(rb.getText().toString().equalsIgnoreCase("Employee")){
                    userType=2;
                }

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                if (Utility.haveNetworkConnection(this)) {
                    if (checkValue()) {
                        //processLogin();
                        login();
                    }
                } else {
                    Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
    private boolean checkValue() {
        if (TextUtils.isEmpty(userId.getText().toString().trim())) {
            userId.requestFocus();
            userId.setError("Please enter User id");
            return false;
        } else if (TextUtils.isEmpty(password.getText().toString().trim())) {
            password.requestFocus();
            password.setError("Please enter password");
            return false;
        } else {
            return true;
        }
    }

    public void processLogin() {
        Utility.showDailog(this);
        Call<LoginPojo> user = APIClient.getInstance().login(
                userId.getText().toString().trim(),
                password.getText().toString().trim(),
                userType+""
        );

        user.enqueue(new Callback<LoginPojo>() {
            @Override
            public void onResponse(Call<LoginPojo> call, Response<LoginPojo> response) {
                Utility.hideDailog();
                Log.e("login",response.body().getMessage());
                if (response.body().isError() == false && response.body().getStatus() == 1) {
                    LoginPojo userData = response.body();
                    sharedPrefManager.setUserData(userData.getResult().get(0));
                    if(userData.getResult().get(0).getUser_type()==1){
                        Intent intent = new Intent(LoginActivity.this, AdminDashboard.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }else if(userData.getResult().get(0).getUser_type()==2){
                        Intent intent = new Intent(LoginActivity.this, EmployeeDashboard.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }


                } else if(response.body().isError()==true && response.body().getStatus()==0) {
                    Log.wtf("tag",""+response.body().getMessage());
                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<LoginPojo> call, Throwable t) {
                Utility.hideDailog();
                Log.e("error",t.toString());
                Toast.makeText(LoginActivity.this, ""+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void login(){
        APIRequest service = APIClient.getInstance();
        service.login1(userId.getText().toString().trim(),
                password.getText().toString().trim(),
                userType+"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginPojo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Utility.showDailog(LoginActivity.this);
                    }

                    @Override
                    public void onNext(LoginPojo loginPoj) {

                        loginPojo = loginPoj;
                        results = new ArrayList<>(loginPoj.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utility.hideDailog();
                    }

                    @Override
                    public void onComplete() {
                        Utility.hideDailog();
                        // Updates UI with data
                        if (loginPojo.isError() == false && loginPojo.getStatus() == 1) {
                            sharedPrefManager.setUserData(loginPojo.getResult().get(0));
                            if(loginPojo.getResult().get(0).getUser_type()==1){
                                Intent intent = new Intent(LoginActivity.this, AdminDashboard.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }else if(loginPojo.getResult().get(0).getUser_type()==2){
                                Intent intent = new Intent(LoginActivity.this, EmployeeDashboard.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }


                        }
                    }
                });
    }

}
