package com.app.cbfc.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.app.cbfc.model.LoginPojo;
import com.app.cbfc.ui.activity.LoginActivity;


public class SharedPrefManager {

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "mysharedpref";
    private static final String KEY_USERID = "id";
    private static final String KEY_FIRST_NAME = "first_name";
    private static final String KEY_LAST_NAME = "last_name";
    private static final String KEY_USER_EMAIL = "email";
    private static final String KEY_MOBILE = "mobile";
    private static final String KEY_USER_TYPE = "user_type";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_COUNTRY = "country";
    private static final String KEY_STATE = "state";
    private static final String KEY_CITY = "city";
    private static final String KEY_PINCODE = "pin_code";
    private static final String KEY_PAYMENT = "payment";
    private static final String KEY_USER_IMAGE = "user_image";
    private static final String KEY_CHAPTER_ID = "chapter_id";
    private static final String KEY_IMEI = "imei";
    private static final String KEY_SUBJECT_ID = "subject_id";
    private static final String KEY_SUBJECT_NAME = "subject_name";
    private static final String KEY_GUEST = "guest";
    private static final String KEY_ADDRESS = "address";
    private static final String STATUS = "status";
    private static final String CURRENT_DATE ="date";
    private static final String TODAY_IN ="in";
    private static final String TODAY_OUT ="out";
    private static final String EVENT_ID ="event_id";
    private static final String INTERVAL_TIME ="time";
    private static final String ALARM_STATUS ="status";

    private static final String TYPE_OF_EMPLOYEE ="employee_type";
    private static final String MINIMUN_VISIT ="status";
    private static final String LATITUDE ="latitude";
    private static final String LONGITUDE ="longitude";


    private static final String KEY_DATE ="date";
    private static final String KEY_TIME ="time";






    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }


    public void setUserData(LoginPojo.Result result){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERID, result.getId());
        editor.putString(KEY_FIRST_NAME, result.getFirst_name());
        editor.putString(KEY_LAST_NAME, result.getLast_name());
        editor.putString(KEY_USER_EMAIL, result.getEmail());
        editor.putString(KEY_MOBILE, result.getMobile());
        editor.putInt(KEY_USER_TYPE, result.getUser_type());
        editor.apply();
    }

    public LoginPojo.Result getUser(){
        LoginPojo.Result result = new LoginPojo.Result();
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        result.setId(sharedPreferences.getString(KEY_USERID, null));
        result.setFirst_name(sharedPreferences.getString(KEY_FIRST_NAME, null));
        result.setLast_name(sharedPreferences.getString(KEY_LAST_NAME, null));
        result.setEmail(sharedPreferences.getString(KEY_USER_EMAIL, null));
        result.setMobile(sharedPreferences.getString(KEY_MOBILE, null));
        result.setUser_type(sharedPreferences.getInt(KEY_USER_TYPE, 0));

        return result;
    }




    public boolean guestUser(String guest){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_GUEST, guest);
        editor.apply();

        return true;
    }

    public boolean userMobile(String mobile){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_MOBILE, mobile);
        editor.apply();

        return true;
    }

    /*public boolean userImei(String imei){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_IMEI, imei);
        editor.apply();

        return true;
    }*/

    public boolean userPayment(String payment, String imei, String subject_id, String subject_name){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_PAYMENT, payment);
        editor.putString(KEY_IMEI, imei);
        editor.putString(KEY_SUBJECT_ID, subject_id);
        editor.putString(KEY_SUBJECT_NAME, subject_name);
        editor.apply();

        return true;
    }


    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USERID, null) != null){
            return true;
        }
        return false;
    }

    public boolean logout(Context context){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        return true;
    }

    public boolean setDateTime(String date, String time){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_DATE, date);
        editor.putString(KEY_TIME, time);
        editor.apply();

        return true;
    }

    public String getDate(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_DATE, null);
    }

    public String getTime(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_TIME, null);
    }

    public String getUserImage(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_IMAGE, null);
    }

    public String getUserId(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERID, null);
    }
    public String getUserName(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_FIRST_NAME, null);
    }
    public String getFullName(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_LAST_NAME, null);
    }
    public String getEmail(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_EMAIL, null);
    }
    public String getMobile(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_MOBILE, null);
    }

    public String getDateOfBirth(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_TYPE, null);
    }





}
