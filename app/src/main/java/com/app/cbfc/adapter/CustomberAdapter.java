package com.app.cbfc.adapter;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.cbfc.R;
import com.app.cbfc.model.CustomerPojo;

import java.util.List;

public class CustomberAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final String TAG = "CustomberAdapter";

    private List<CustomerPojo.Result> list;

    public CustomberAdapter(List<CustomerPojo.Result> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_item, parent, false));


    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends BaseViewHolder {

        TextView tvFullName,tvFatherName,tvMobile,tvAddress;
        CardView mainCard;

        public ViewHolder(View itemView) {
            super(itemView);
            Log.e(TAG,"ViewHolder");
            tvFullName = itemView.findViewById(R.id.full_name);
            tvFatherName = itemView.findViewById(R.id.father_name);
            tvMobile = itemView.findViewById(R.id.mobile);
            tvAddress = itemView.findViewById(R.id.address);
            mainCard = itemView.findViewById(R.id.main_card);
        }

        protected void clear() {

            tvFullName.setText("");
            tvFatherName.setText("");
            tvMobile.setText("");
            tvAddress.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);

            final CustomerPojo.Result result = list.get(position);



            if (result.getCus_full_name() != null) {
                tvFullName.setText(result.getCus_full_name());
            }

            if (result.getCus_father_name() != null) {
                tvFatherName.setText(result.getCus_father_name());
            }

            if (result.getCus_mobile() != null) {
                tvMobile.setText(result.getCus_mobile());
            }

            mainCard.setOnClickListener(v -> {
                Toast.makeText(itemView.getContext(), "Click", Toast.LENGTH_SHORT).show();
                /*if (mBlog.getLink() != null) {
                    try {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse(mBlog.getLink()));
                        itemView.getContext().startActivity(intent);
                    } catch (Exception e) {
                        Log.e(TAG, "onClick: Image url is not correct");
                    }
                }*/
            });
        }
    }
}
