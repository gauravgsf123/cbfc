package com.app.cbfc.network;

import com.app.cbfc.model.CustomerPojo;
import com.app.cbfc.model.EmployeePojo;
import com.app.cbfc.model.LoginPojo;
import com.app.cbfc.model.ResponsePojo;
import com.app.cbfc.util.Constant;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIRequest {

    @FormUrlEncoded
    @POST(Constant.LOGIN)
    Call<LoginPojo> login(@Field("user_id") String username,
                          @Field("password") String password,
                          @Field("user_type") String user_type);

    @FormUrlEncoded
    @POST(Constant.LOGIN)
    Observable<LoginPojo> login1(@Field("user_id") String username,
                                 @Field("password") String password,
                                 @Field("user_type") String user_type);

    @FormUrlEncoded
    @POST(Constant.ADD_EMPLOYEE)
    Observable<ResponsePojo> addEmployee(@Field("emp_full_name") String emp_full_name,
                                         @Field("emp_father_name") String emp_father_name,
                                         @Field("emp_mobile") String emp_mobile,
                                         @Field("emp_address") String emp_address,
                                         @Field("password") String password,
                                         @Field("emp_adhar_card_number") String emp_adhar_card_number,
                                         @Field("emp_pan_card_number") String emp_pan_card_number);

    @FormUrlEncoded
    @POST(Constant.ADD_CUSTOMER)
    Observable<ResponsePojo> addCustomer(@Field("emp_id") String emp_full_name,
                                         @Field("cus_full_name") String emp_father_name,
                                         @Field("cus_father_name") String emp_mobile,
                                         @Field("cus_mobile") String emp_address,
                                         @Field("cus_address") String password,
                                         @Field("cus_adhar_card_number") String emp_adhar_card_number,
                                         @Field("cus_pan_card_number") String emp_pan_card_number);

    @Multipart
    @POST(Constant.UPDATE_PROFILE)
    Call<ResponsePojo> updateImage(@Part MultipartBody.Part file,
                              @Part("user_id") RequestBody user_id,
                              @Part("user_type") RequestBody user_type,
                              @Part("image_name") RequestBody image_name);

    @GET(Constant.CUSTOMER_LIST)
    Observable<CustomerPojo> getCustomerList();

    @GET(Constant.CUSTOMER_LIST)
    Call<EmployeePojo> getEmployeeList();

    @Multipart
    @POST("add_file.php")
    Call<ResponsePojo> addCall(@Part MultipartBody.Part audio_file);

    @Multipart
    @POST("YOUR_URL/image_uploader.php")
    Call<ResponsePojo> uploadImages( @Part List<MultipartBody.Part> images);


    /*@Multipart
    @POST(Constant.ADD_CALL_DETAIL)
    Call<APIResponse> addCall(@Part MultipartBody.Part audio_file,
                              @Part("user_id") RequestBody user_id,
                              @Part("contact_number") RequestBody contact_number,
                              @Part("call_type") RequestBody call_type,
                              @Part("call_duration") RequestBody call_duration,
                              @Part("call_date") RequestBody call_date,
                              @Part("call_time") RequestBody call_time);

    @Multipart
    @POST("add_file.php")
    Call<APIResponse> addCall(@Part MultipartBody.Part audio_file);*/

    /*@GET("date_and_time.php")
    Call<String> getStateData();*/


    /*@FormUrlEncoded
    @POST("update_remark.php")
    Call<AttendancePojo> updateRemark(@Field("emp_id") String userId,
                                      @Field("event_id") String eventId,
                                      @Field("remark") String remark);*/

    /*@Headers( {
            "Content-Type: Application/Json;charset=UTF-8",
            "Accept: Application/Json",
            "User-Agent: Retrofit 2.3.0"
    } )
    @POST("event.php")
    Call<AttendancePojo> offlineData(@Body() String jsonObject);*/
}
