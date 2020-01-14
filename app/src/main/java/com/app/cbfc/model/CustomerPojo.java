package com.app.cbfc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CustomerPojo {

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

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Result> getResult() {
        return result;
    }

    public void setResult(ArrayList<Result> result) {
        this.result = result;
    }

    public class Result{
        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("emp_id")
        @Expose
        private String emp_id;
        @SerializedName("cus_full_name")
        @Expose
        private String cus_full_name;
        @SerializedName("cus_father_name")
        @Expose
        private String cus_father_name;
        @SerializedName("cus_mobile")
        @Expose
        private String cus_mobile;
        @SerializedName("cus_address")
        @Expose
        private String cus_address;
        @SerializedName("cus_adhar_card_number")
        @Expose
        private String cus_adhar_card_number;
        @SerializedName("cus_pan_card_number")
        @Expose
        private String cus_pan_card_number;
        @SerializedName("cus_profile_image")
        @Expose
        private String cus_profile_image;
        @SerializedName("cus_adhar_card_image")
        @Expose
        private String cus_adhar_card_image;
        @SerializedName("cus_pan_card_image")
        @Expose
        private String cus_pan_card_image;
        @SerializedName("status")
        @Expose
        private int status;
        @SerializedName("created_date")
        @Expose
        private String created_date;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEmp_id() {
            return emp_id;
        }

        public void setEmp_id(String emp_id) {
            this.emp_id = emp_id;
        }

        public String getCus_full_name() {
            return cus_full_name;
        }

        public void setCus_full_name(String cus_full_name) {
            this.cus_full_name = cus_full_name;
        }

        public String getCus_father_name() {
            return cus_father_name;
        }

        public void setCus_father_name(String cus_father_name) {
            this.cus_father_name = cus_father_name;
        }

        public String getCus_mobile() {
            return cus_mobile;
        }

        public void setCus_mobile(String cus_mobile) {
            this.cus_mobile = cus_mobile;
        }

        public String getCus_address() {
            return cus_address;
        }

        public void setCus_address(String cus_address) {
            this.cus_address = cus_address;
        }

        public String getCus_adhar_card_number() {
            return cus_adhar_card_number;
        }

        public void setCus_adhar_card_number(String cus_adhar_card_number) {
            this.cus_adhar_card_number = cus_adhar_card_number;
        }

        public String getCus_pan_card_number() {
            return cus_pan_card_number;
        }

        public void setCus_pan_card_number(String cus_pan_card_number) {
            this.cus_pan_card_number = cus_pan_card_number;
        }

        public String getCus_profile_image() {
            return cus_profile_image;
        }

        public void setCus_profile_image(String cus_profile_image) {
            this.cus_profile_image = cus_profile_image;
        }

        public String getCus_adhar_card_image() {
            return cus_adhar_card_image;
        }

        public void setCus_adhar_card_image(String cus_adhar_card_image) {
            this.cus_adhar_card_image = cus_adhar_card_image;
        }

        public String getCus_pan_card_image() {
            return cus_pan_card_image;
        }

        public void setCus_pan_card_image(String cus_pan_card_image) {
            this.cus_pan_card_image = cus_pan_card_image;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreated_date() {
            return created_date;
        }

        public void setCreated_date(String created_date) {
            this.created_date = created_date;
        }
    }
}
