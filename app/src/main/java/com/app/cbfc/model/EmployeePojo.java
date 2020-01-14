package com.app.cbfc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EmployeePojo {

    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("result")
    @Expose
    private ArrayList<Result> result;

    public class Result{
        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("emp_full_name")
        @Expose
        private String emp_full_name;
        @SerializedName("emp_father_name")
        @Expose
        private String emp_father_name;
        @SerializedName("emp_mobile")
        @Expose
        private String emp_mobile;
        @SerializedName("emp_address")
        @Expose
        private String emp_address;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("user_type")
        @Expose
        private String user_type;
        @SerializedName("emp_adhar_card_number")
        @Expose
        private String emp_adhar_card_number;
        @SerializedName("emp_pan_card_number")
        @Expose
        private String emp_pan_card_number;
        @SerializedName("emp_profile_image")
        @Expose
        private String emp_profile_image;
        @SerializedName("emp_adhar_card_image")
        @Expose
        private String emp_adhar_card_image;
        @SerializedName("emp_pan_card_image")
        @Expose
        private String emp_pan_card_image;
        @SerializedName("status")
        @Expose
        private int status;
        @SerializedName("created_date")
        @Expose
        private String created_date;

    }
}
