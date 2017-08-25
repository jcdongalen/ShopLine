package com.pinas.xburner.shopline.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pinas.xburner.shopline.Model.Order;
import com.pinas.xburner.shopline.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by john_dongalen on 8/24/2017.
 */

public class ForShipmentAdapter extends RecyclerView.Adapter<ForShipmentAdapter.ForShipmentViewHolder> {
    private List<Order> forshipment;
    private Context mContext;
    private DecimalFormat formatter;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseDatabase;

    public class ForShipmentViewHolder extends RecyclerView.ViewHolder {
        public TextView tvOrderID, tvPrice, tvCount, tvTotalAmount, tvProductName, tvFullName, tvMore, tvItemCategory;
        public ImageView imgLogo;
        public Button btnConfirm;

        public ForShipmentViewHolder(View view) {
            super(view);
            tvOrderID = (TextView) view.findViewById(R.id.tvOrderID);
            tvPrice = (TextView) view.findViewById(R.id.tvPrice);
            tvCount = (TextView) view.findViewById(R.id.tvCount);
            tvTotalAmount = (TextView) view.findViewById(R.id.tvTotalAmount);
            tvProductName = (TextView) view.findViewById(R.id.tvProductName);
            tvFullName = (TextView) view.findViewById(R.id.tvFullName);
            imgLogo = (ImageView) view.findViewById(R.id.imgLogo);
            btnConfirm = (Button) view.findViewById(R.id.btnConfirm);
            tvMore = (TextView) view.findViewById(R.id.tvMore);
            tvItemCategory = (TextView)view.findViewById(R.id.tvItemCategory);
        }
    }

    public ForShipmentAdapter(Context context, List<Order> forshipment) {
        this.forshipment = forshipment;
        this.mContext = context;
        this.formatter = new DecimalFormat("###,###,###.00");
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("Orders");
    }

    @Override
    public ForShipmentAdapter.ForShipmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_inquiry, parent, false);

        return new ForShipmentAdapter.ForShipmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ForShipmentAdapter.ForShipmentViewHolder holder, int position) {
        final Order order = forshipment.get(position);

        if (order.getProducts().size() > 1) {
            holder.tvMore.setVisibility(View.VISIBLE);
        } else {
            holder.tvMore.setVisibility(View.GONE);
        }

        holder.tvItemCategory.setText("Paid");
        holder.tvOrderID.setText(order.getOrderID());
        holder.tvFullName.setText(order.getFullName());
        holder.tvProductName.setText(order.getProducts().get(0).getName());
        holder.tvPrice.setText(formatter.format(Double.parseDouble(order.getProducts().get(0).getAmount())));
        holder.tvCount.setText("x" + order.getProducts().get(0).getStock());
        holder.tvTotalAmount.setText(order.getTotalAmount());
        Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
        int anim = position + 1;
        animation.setDuration(anim * 200);
        holder.itemView.startAnimation(animation);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "This will be the listener for the item click.", Toast.LENGTH_LONG).show();
            }
        });

        holder.btnConfirm.setText("Ship Item");
        holder.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(mContext, R.style.Theme_AppCompat_DayNight_Dialog_Alert)
                        .setTitle("SHOPLINE")
                        .setMessage("Are you sure?")
                        .setPositiveButton("Yup!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                order.setStatus("Shipped to: " + order.getFullName() + "\nAddress: " + order.getShippingAddress() + " - as of " + new SimpleDateFormat("HH:mm a, MMM dd, yyyy.").format(new Date()));
                                mDatabaseReference.child(order.getOrderID()).setValue(order);
                            }
                        })
                        .setNegativeButton("Wait", null)
                        .show();
                //Toast.makeText(mContext, "This will be the listener for button confirm.", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return forshipment.size();
    }
}
