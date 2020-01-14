package com.app.cbfc.ui.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.cbfc.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class UploadCustomberDocumnet extends Fragment {

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


    private int lastId=0;
    private Unbinder unbinder;


    public static UploadCustomberDocumnet getInstant() {
        // Required empty public constructor
        return new UploadCustomberDocumnet();
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
        view = inflater.inflate(R.layout.fragment_upload_customber, container, false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
