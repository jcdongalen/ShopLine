package com.pinas.xburner.shopline.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.pinas.xburner.shopline.Adapter.ForShipmentAdapter;
import com.pinas.xburner.shopline.Adapter.ShippedAdapter;
import com.pinas.xburner.shopline.Model.Order;
import com.pinas.xburner.shopline.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Shipping extends Fragment {
    View rootView;
    RecyclerView rvShipped;
    DatabaseReference mDatabase;

    ArrayList<Order> shipped = new ArrayList<>();
    ShippedAdapter shippedAdapter;

    public Shipping() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_shipping, container, false);
        shipped.clear();
        initialization();
        return rootView;
    }

    private void initialization() {
        try {
            rvShipped = (RecyclerView) rootView.findViewById(R.id.rvShipped);
            shippedAdapter = new ShippedAdapter(getActivity(), shipped);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            mLayoutManager.setReverseLayout(true);
            mLayoutManager.setStackFromEnd(true);
            rvShipped.setLayoutManager(mLayoutManager);
            rvShipped.setItemAnimator(new DefaultItemAnimator());
            rvShipped.setAdapter(shippedAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mDatabase = FirebaseDatabase.getInstance().getReference();

        try {
            Query InquiryList = mDatabase.child("Orders").orderByKey();
            InquiryList.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    try {
                        Order order = dataSnapshot.getValue(Order.class);
                        if (order != null && order.getStatus().toLowerCase().contains("verified by")) {
                            shipped.add(order);
                            shippedAdapter.notifyDataSetChanged();
                        }
                        rvShipped.smoothScrollToPosition(shipped.size() - 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Order order = dataSnapshot.getValue(Order.class);
                    if (order != null) {
                        if (!order.getStatus().equalsIgnoreCase("")) {
                            for (int i = 0; i < shipped.size(); i++) {
                                if (shipped.get(i).getOrderID().equalsIgnoreCase(order.getOrderID())) {
                                    shipped.remove(i);
                                }
                            }
                        } else {
                            for (int i = 0; i < shipped.size(); i++) {
                                if (shipped.get(i).getOrderID().equalsIgnoreCase(order.getOrderID())) {
                                    shipped.set(i, order);
                                }
                            }
                        }
                        shippedAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
