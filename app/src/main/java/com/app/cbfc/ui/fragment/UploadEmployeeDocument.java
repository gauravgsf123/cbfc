package com.app.cbfc.ui.fragment;


import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.cbfc.R;
import com.app.cbfc.model.ImageResponsePojo;
import com.app.cbfc.network.APIRequest;
import com.app.cbfc.util.CheckPermission;
import com.app.cbfc.util.Constant;
import com.app.cbfc.util.Utility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class UploadEmployeeDocument extends Fragment implements View.OnClickListener {

    private static final String TAG = "UploadEmployeeDocument";
    private final int PICK_IMAGE_CAMERA = 1, PICK_IMAGE_GALLERY = 2;
    View view;
    @BindView(R.id.profile_image)
    ImageView profileImage;
    @BindView(R.id.adhar_image)
    ImageView adharImage;
    @BindView(R.id.pan_image)
    ImageView panImage;
    @BindView(R.id.upload_profile_image)
    TextView uploadProfileImage;
    @BindView(R.id.upload_adhar_image)
    TextView uploadAdharImage;
    @BindView(R.id.upload_pan_image)
    TextView uploadPanImage;
    Dialog myDialog;
    private Bitmap bitmap;
    private File destination = null;
    private InputStream inputStreamImg;
    private String imgPath = null;


    private int lastId=0;
    private Unbinder unbinder;

    public static UploadEmployeeDocument getInstant() {
        // Required empty public constructor
        return new UploadEmployeeDocument();
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            lastId = bundle.getInt("last_id", 0);
            Toast.makeText(getContext(), ""+lastId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_upload_employee_document, container, false);
        unbinder = ButterKnife.bind(this,view);
        uploadAdharImage.setOnClickListener(this);
        return view;
    }

    private void updateProfileImage(Uri uri1){
        Utility.showDailog(getContext());
        String filePath = getRealPathFromURIPath(uri1, getContext());
        File file = new File(filePath);
        Log.d(TAG, "Filename " + file.getName());
        RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
        RequestBody userid = RequestBody.create(MediaType.parse("text/plain"), id);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        APIRequest uploadImage = retrofit.create(APIRequest.class);
        Call<ImageResponsePojo> fileUpload = uploadImage.updateImage(fileToUpload, userid);
        fileUpload.enqueue(new Callback<ImageResponsePojo>() {
            @Override
            public void onResponse(Call<ImageResponsePojo> call, Response<ImageResponsePojo> response) {
                Utility.hideDailog();
                if(response.body().isError()==false){
                    new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Profile Updated!")
                            .show();
                }else {
                    Utility.showErrordailog(getContext(), response.body().getMessage());
                }

                // Toast.makeText(MainActivity.this, "Response " + response.raw().message(), Toast.LENGTH_LONG).show();
                // Toast.makeText(Member_Profiles.this, " " + response.body().getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ImageResponsePojo> call, Throwable t) {
                Utility.hideDailog();
                Log.d(TAG, "Error " + t.getMessage());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.upload_profile_image:
               new CheckPermission().checkPermission(getActivity());
                chnageprofile();
                break;
            case R.id.upload_adhar_image:
                new CheckPermission().checkPermission(getActivity());
                chnageprofile();
                break;
            case R.id.upload_pan_image:
                new CheckPermission().checkPermission(getActivity());
                chnageprofile();
                break;
        }
    }

    public void chnageprofile() {

        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.change_profile_image_layout);
        myDialog.setCancelable(true);

        LinearLayout gallery = (LinearLayout) myDialog.findViewById(R.id.gallery);
        LinearLayout camera = (LinearLayout) myDialog.findViewById(R.id.camera);
        myDialog.show();

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY);
                myDialog.dismiss();
                myDialog.cancel();

            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if(permisioncheck()){

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, PICK_IMAGE_CAMERA);
                myDialog.dismiss();
                myDialog.cancel();
                //}

            }
        });

    }

    @Override
    public void onActivityResult(final int requestCode, int resultCode, Intent data) {
        inputStreamImg = null;
        if (requestCode == PICK_IMAGE_CAMERA) {
            try {
                Uri selectedImage = data.getData();
                bitmap = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);

                Log.e("Activity", "Pick from Camera::>>> ");

                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                destination = new File(Environment.getExternalStorageDirectory() + "/" +
                        getString(R.string.app_name), "IMG_" + timeStamp + ".jpg");
                FileOutputStream fo;
                imgPath = destination.getAbsolutePath();
                Uri tempUri = getImageUri(getContext(), bitmap);
                profileImage.setImageBitmap(bitmap);
                updateProfileImage(tempUri);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == PICK_IMAGE_GALLERY) {
            try {
                Uri selectedImage = data.getData();
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
                Log.e("Activity", "Pick from Gallery::>>> ");

                profileImage.setImageBitmap(bitmap);
                updateProfileImage(selectedImage);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }


    public void checkCameraPermision(){
        new RxPermissions(getActivity())
                .request(Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE) // ask single or multiple permission once
                .subscribe(granted -> {
                    if (granted) {
                        // All requested permissions are granted
                    } else {
                    }
                });
    }
}
