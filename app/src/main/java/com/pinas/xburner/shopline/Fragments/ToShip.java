package com.pinas.xburner.shopline.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pinas.xburner.shopline.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToShip extends Fragment {


    public ToShip() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_to_ship, container, false);
    }

}
