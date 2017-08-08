package com.pinas.xburner.shopline.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pinas.xburner.shopline.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllItems extends Fragment {

    GridLayout grdLayout;
    View view;


    public AllItems() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_all_items, container, false);
        initialization();
        return view;
    }

    private void initialization() {
        grdLayout = (GridLayout) view.findViewById(R.id.grdLayout);

        for (int i = 0; i < 9; i++) {
            View item = LayoutInflater.from(getActivity()).inflate(R.layout.layout_grid_item, null);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(240, 480);
            params.setMargins(4, 4, 4, 4);
            item.setLayoutParams(params);
            ImageView itemIMG = (ImageView) item.findViewById(R.id.itemImage);
            TextView itemTitle = (TextView) item.findViewById(R.id.itemTitle);
            TextView itemPrice = (TextView) item.findViewById(R.id.itemPrice);

            switch (i) {
                case 0:
                    itemIMG.setImageResource(R.mipmap.ic_completed);
                    itemTitle.setText("Notebook for Engineering");
                    itemPrice.setText("P 24.00");
                    break;
                case 1:
                    itemIMG.setImageResource(R.mipmap.ic_inquiry_1);
                    itemTitle.setText("Seagate 1TB HDD");
                    itemPrice.setText("P 2,295.00");
                    break;
                case 2:
                    itemIMG.setImageResource(R.mipmap.ic_inquiry);
                    itemTitle.setText("Seagate 2TB HDD");
                    itemPrice.setText("P 3,095.00");
                    break;
                case 3:
                    itemIMG.setImageResource(R.mipmap.ic_shipment);
                    itemTitle.setText("Trailer Truck for Delivery purposes");
                    itemPrice.setText("P 308,000.00");
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
            }

            grdLayout.addView(item);
        }
    }
}
