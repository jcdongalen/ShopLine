package com.pinas.xburner.shopline.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pinas.xburner.shopline.Model.Order;
import com.pinas.xburner.shopline.R;

import java.util.List;

/**
 * Created by john_dongalen on 8/18/2017.
 */

public class InquiryAdapter extends RecyclerView.Adapter<InquiryAdapter.InquiryViewHolder> {

    private List<Order> inquiries;

    public class InquiryViewHolder extends RecyclerView.ViewHolder {
        public TextView tvAmount, tvCount, tvTotalAmount, tvProductName, tvFullName;
        public ImageView imgLogo;

        public InquiryViewHolder(View view) {
            super(view);
            tvAmount = (TextView) view.findViewById(R.id.tvAmount);
            tvCount = (TextView) view.findViewById(R.id.tvCount);
            tvTotalAmount = (TextView) view.findViewById(R.id.tvTotalAmount);
            tvProductName = (TextView) view.findViewById(R.id.tvProductName);
            tvFullName = (TextView) view.findViewById(R.id.tvFullName);
            imgLogo = (ImageView) view.findViewById(R.id.imgLogo);
        }
    }

    public InquiryAdapter(List<Order> inquiries) {
        this.inquiries = inquiries;
    }

    @Override
    public InquiryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_inquiry, parent, false);

        return new InquiryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InquiryViewHolder holder, int position) {
        Order order = inquiries.get(position);
        holder.tvFullName.setText(order.getFullName());
        holder.tvProductName.setText(order.getOrderID() + "\n" + order.getProduct1().split(":")[1]);
        holder.tvAmount.setText(order.getProduct1().split(":")[2]);
        holder.tvCount.setText("x" + order.getProduct1Count());
        holder.imgLogo.setImageResource(R.mipmap.ic_launcher_round);
    }

    @Override
    public int getItemCount() {
        return inquiries.size();
    }
}
