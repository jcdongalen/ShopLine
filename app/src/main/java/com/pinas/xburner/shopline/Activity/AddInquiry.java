package com.pinas.xburner.shopline.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.pinas.xburner.shopline.R;

public class AddInquiry extends AppCompatActivity {

    AutoCompleteTextView mAutoCompletePaymentMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inquiry);

        initialization();
    }

    private void initialization() {
        mAutoCompletePaymentMode = (AutoCompleteTextView) findViewById(R.id.mAutoCompletePaymentMode);
        String[] paymentmode = getResources().getStringArray(R.array.PaymentMode);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.custom_list_item, paymentmode);
        mAutoCompletePaymentMode.setAdapter(adapter);
    }
}
