package com.pinas.xburner.shopline.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.pinas.xburner.shopline.Activity.AddInquiry;
import com.pinas.xburner.shopline.Adapter.InquiryAdapter;
import com.pinas.xburner.shopline.Functions.GlobalFunctions;
import com.pinas.xburner.shopline.Model.Order;
import com.pinas.xburner.shopline.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Inquiry extends Fragment implements View.OnClickListener {

    FloatingActionButton fabInquiry;
    View rootView;
    RecyclerView rvInquiry;
    DatabaseReference mDatabase;

    ArrayList<Order> inquiries = new ArrayList<>();
    InquiryAdapter inquiryAdapter;

    public Inquiry() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_inquiry, container, false);
        inquiries.clear();
        initialization();
        return rootView;
    }

    public void initialization() {
        fabInquiry = (FloatingActionButton) rootView.findViewById(R.id.fabInquiry);
        fabInquiry.setOnClickListener(this);

        try {
            rvInquiry = (RecyclerView) rootView.findViewById(R.id.rvInquiry);
            inquiryAdapter = new InquiryAdapter(getActivity(), inquiries);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            mLayoutManager.setReverseLayout(true);
            mLayoutManager.setStackFromEnd(true);
            rvInquiry.setLayoutManager(mLayoutManager);
            rvInquiry.setItemAnimator(new DefaultItemAnimator());
            rvInquiry.setAdapter(inquiryAdapter);
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
                        if (order != null && order.getStatus().equalsIgnoreCase("")) {
                            inquiries.add(order);
                            inquiryAdapter.notifyDataSetChanged();
                        }
                        rvInquiry.smoothScrollToPosition(inquiries.size() - 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Order order = dataSnapshot.getValue(Order.class);
                    if (order != null) {
                        if (!order.getStatus().equalsIgnoreCase("")) {
                            for (int i = 0; i < inquiries.size(); i++) {
                                if (inquiries.get(i).getOrderID().equalsIgnoreCase(order.getOrderID())) {
                                    inquiries.remove(i);
                                }
                            }
                        } else {
                            for (int i = 0; i < inquiries.size(); i++) {
                                if (inquiries.get(i).getOrderID().equalsIgnoreCase(order.getOrderID())) {
                                    inquiries.set(i, order);
                                }
                            }
                        }
                        inquiryAdapter.notifyDataSetChanged();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabInquiry:
                Snackbar snackbar = Snackbar.make(rootView, "Do you want to add inquiry?", Snackbar.LENGTH_LONG);
                snackbar.setAction("Yup", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), AddInquiry.class));
                    }
                });
                snackbar.show();
                break;
        }
    }
}
