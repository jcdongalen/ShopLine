package com.pinas.xburner.shopline.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.pinas.xburner.shopline.Model.Order;
import com.pinas.xburner.shopline.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

/**
 * Created by john_dongalen on 8/18/2017.
 */

public class InquiryAdapter extends RecyclerView.Adapter<InquiryAdapter.InquiryViewHolder> {

    private List<Order> inquiries;
    private Context mContext;
    private int lastPosition = -1;
    private DecimalFormat formatter;

    public class InquiryViewHolder extends RecyclerView.ViewHolder {
        public TextView tvOrderID, tvPrice, tvCount, tvTotalAmount, tvProductName, tvFullName;
        public ImageView imgLogo;

        public InquiryViewHolder(View view) {
            super(view);
            tvOrderID = (TextView) view.findViewById(R.id.tvOrderID);
            tvPrice = (TextView) view.findViewById(R.id.tvPrice);
            tvCount = (TextView) view.findViewById(R.id.tvCount);
            tvTotalAmount = (TextView) view.findViewById(R.id.tvTotalAmount);
            tvProductName = (TextView) view.findViewById(R.id.tvProductName);
            tvFullName = (TextView) view.findViewById(R.id.tvFullName);
            imgLogo = (ImageView) view.findViewById(R.id.imgLogo);
        }
    }

    public InquiryAdapter(Context context, List<Order> inquiries) {
        this.inquiries = inquiries;
        this.mContext = context;
        this.formatter = new DecimalFormat("###,###,###.00");
    }

    @Override
    public InquiryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_inquiry, parent, false);

        return new InquiryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InquiryViewHolder holder, int position) {
        if (position > lastPosition) {
            Order order = inquiries.get(position);
            holder.tvOrderID.setText(order.getOrderID());
            holder.tvFullName.setText(order.getFullName());
            holder.tvProductName.setText(order.getProduct1().split(":")[1]);
            holder.tvPrice.setText(formatter.format(Double.parseDouble(order.getProduct1().split(":")[2])));
            holder.tvCount.setText("x" + order.getProduct1Count());
            holder.tvTotalAmount.setText(formatter.format(Double.parseDouble(order.getTotalAmount())));
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            holder.itemView.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return inquiries.size();
    }
}
