package com.app.cbfc.util;

import android.Manifest;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.widget.TextView;
import android.widget.TimePicker;

import com.app.cbfc.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class Utility {
    private  static  SweetAlertDialog pDialog;
    private static int hour,minutes;
    final String time="";



    public static void showDailog(Context context){
        pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(R.color.colorPrimary);
        pDialog.setTitleText("Please wait...");
        pDialog.setCancelable(true);
        pDialog.show();
    }

    public static  void showErrordailog(Context context,String msg){
        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Oops...")
                .setContentText(msg)
                .show();

    }
    public static void ShowSucessDailog(Context context, String msg){
        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(msg)
                .show();
    }

    public static void hideDailog(){
        if(pDialog!=null || pDialog.isShowing()){
            pDialog.dismiss();
        }
    }

    public static boolean isNetwokAvailable(Activity activity)
    {
        ConnectivityManager connectivity = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] networks = connectivity.getAllNetworks();
            NetworkInfo networkInfo;
            for (Network mnetwork : networks) {
                networkInfo = connectivity.getNetworkInfo(mnetwork);
                if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                    return true;
                }
            }
        } else {
            if (connectivity != null) {
                NetworkInfo[] infos = connectivity.getAllNetworkInfo();
                if (infos != null) {
                    for (int i = 0; i < infos.length; i++) {
                        if (infos[i].getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
                    }
                }

            }
        }


        return false;

    }

    public static boolean haveNetworkConnection(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    public static boolean is_CAMERA_PermissionGranted(Context context) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            return (context.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED);

        } else {
            return true;
        }
    }

    public static boolean is_STORAGE_PermissionGranted(Context context) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            return (context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);

        } else {
            return true;
        }
    }

    public static void refreshGallery(Context context, String filePath) {
        // ScanFile so it will be appeared on Gallery
        MediaScannerConnection.scanFile(context,
                new String[]{filePath}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                    }
                });
    }

    public static String getCurrentDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(Calendar.getInstance().getTime());
    }
    public static String getCurrentTime(){
        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss a");
        return df.format(Calendar.getInstance().getTime());
    }

}
