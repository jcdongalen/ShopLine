package com.pinas.xburner.shopline.Fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pinas.xburner.shopline.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Inquiry extends Fragment implements View.OnClickListener{

    FloatingActionButton fabInquiry;
    View rootView;
    ListView lvInquiry;

    public Inquiry() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_inquiry, container, false);

        initialization();

        return rootView;
    }

    public void initialization(){
        fabInquiry = (FloatingActionButton)rootView.findViewById(R.id.fabInquiry);
        fabInquiry.setOnClickListener(this);

        lvInquiry = (ListView)rootView.findViewById(R.id.lvInquiry);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fabInquiry:
                Snackbar snackbar = Snackbar.make(rootView, "Do you want to add inquiry?", Snackbar.LENGTH_LONG);
                snackbar.setAction("Yup", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Do Nothing
                    }
                });
                snackbar.show();
                break;
        }
    }
}
