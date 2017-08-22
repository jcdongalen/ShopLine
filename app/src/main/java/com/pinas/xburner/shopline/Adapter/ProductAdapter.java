package com.pinas.xburner.shopline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.pinas.xburner.shopline.Model.Products;
import com.pinas.xburner.shopline.R;

import java.util.ArrayList;

/**
 * Created by john_dongalen on 8/22/2017.
 */

public class ProductAdapter extends BaseAdapter implements Filterable {

    private Context mContext;
    private ArrayList<Products> originalList;
    public ArrayList<Products> suggestions = new ArrayList<>();
    private Filter filter = new CustomFilter();

    public ProductAdapter(){}

    public ProductAdapter(Context context, ArrayList<Products> originalList){
        this.mContext = context;
        this.originalList = originalList;
    }

    @Override
    public int getCount() {
        return suggestions.size();
    }

    @Override
    public Object getItem(int position) {
        return suggestions.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        TextView tvProductName, tvCategory, tvPrice;
        ImageView imgLogo;

        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_product, viewGroup, false);

            tvProductName = (TextView) convertView.findViewById(R.id.tvProductName);
            tvCategory = (TextView) convertView.findViewById(R.id.tvCategory);
            tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);
            imgLogo = (ImageView)convertView.findViewById(R.id.imgLogo);

            Products product = suggestions.get(position);
            tvProductName.setText(product.getName());
            tvCategory.setText(product.getDescription());
            tvPrice.setText(product.getAmount());
        }

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private class CustomFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            suggestions.clear();

            if (originalList != null && charSequence != null) { // Check if the Original List and Constraint aren't null.
                for (int i = 0; i < originalList.size(); i++) {
                    if (originalList.get(i).getProductID().toLowerCase().contains(charSequence)) { // Compare item in original list if it contains constraints.
                        suggestions.add(originalList.get(i)); // If TRUE add item in Suggestions.
                    }
                }
            }
            FilterResults results = new FilterResults(); // Create new Filter Results and return this to publishResults;
            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}
