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
import com.pinas.xburner.shopline.Adapter.CompletedAdapter;
import com.pinas.xburner.shopline.Adapter.ShippedAdapter;
import com.pinas.xburner.shopline.Model.Order;
import com.pinas.xburner.shopline.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Completed extends Fragment {
    View rootView;
    RecyclerView rvCompleted;
    DatabaseReference mDatabase;

    ArrayList<Order> completed = new ArrayList<>();
    CompletedAdapter completedAdapter;

    public Completed() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_completed, container, false);
        completed.clear();
        initialization();
        return rootView;
    }

    private void initialization() {
        try {
            rvCompleted = (RecyclerView) rootView.findViewById(R.id.rvShipped);
            completedAdapter = new CompletedAdapter(getActivity(), completed);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            mLayoutManager.setReverseLayout(true);
            mLayoutManager.setStackFromEnd(true);
            rvCompleted.setLayoutManager(mLayoutManager);
            rvCompleted.setItemAnimator(new DefaultItemAnimator());
            rvCompleted.setAdapter(completedAdapter);
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
                        if (order != null && order.getStatus().toLowerCase().contains("completed")) {
                            completed.add(order);
                            completedAdapter.notifyDataSetChanged();
                        }
                        rvCompleted.smoothScrollToPosition(completed.size() - 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Order order = dataSnapshot.getValue(Order.class);
                    if (order != null) {
                        if (!order.getStatus().equalsIgnoreCase("")) {
                            for (int i = 0; i < completed.size(); i++) {
                                if (completed.get(i).getOrderID().equalsIgnoreCase(order.getOrderID())) {
                                    completed.remove(i);
                                }
                            }
                        } else {
                            for (int i = 0; i < completed.size(); i++) {
                                if (completed.get(i).getOrderID().equalsIgnoreCase(order.getOrderID())) {
                                    completed.set(i, order);
                                }
                            }
                        }
                        completedAdapter.notifyDataSetChanged();
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
