package com.pinas.xburner.shopline.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.pinas.xburner.shopline.Functions.GlobalFunctions;
import com.pinas.xburner.shopline.R;

public class AddInquiry extends AppCompatActivity {

    AutoCompleteTextView mAutoCompletePaymentMode, mACTProduct1, mACTProduct2, mACTProduct3;
    EditText etOrderID, etFullName, etAddress, etCount1, etCount2, etCount3;
    GlobalFunctions globalFunctions;
    TextView tvAmount;

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

        globalFunctions = new GlobalFunctions();

        String[] products = getResources().getStringArray(R.array.Products);
        ArrayAdapter<String> productAdapter = new ArrayAdapter<String>(this, R.layout.custom_list_item, products);

        mACTProduct1 = (AutoCompleteTextView) findViewById(R.id.mACTProduct1);
        mACTProduct1.setAdapter(productAdapter);
        mACTProduct2 = (AutoCompleteTextView) findViewById(R.id.mACTProduct2);
        mACTProduct2.setAdapter(productAdapter);
        mACTProduct3 = (AutoCompleteTextView) findViewById(R.id.mACTProduct3);
        mACTProduct3.setAdapter(productAdapter);
        etOrderID = (EditText)findViewById(R.id.etOrderID);
        etFullName = (EditText)findViewById(R.id.etFullname);
        etAddress = (EditText)findViewById(R.id.etAddress);
        etCount1 = (EditText)findViewById(R.id.etCount1);
        etCount2 = (EditText)findViewById(R.id.etCount2);
        etCount3 = (EditText)findViewById(R.id.etCount3);
        tvAmount = (TextView) findViewById(R.id.tvAmount);
        tvAmount.setText("0.00");

        etCount1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(etCount1.getText().toString().length() != 0 && mACTProduct1.getText().toString().length() != 0){
                    String[] split = mACTProduct1.getText().toString().split(":");
                    if(split.length > 1){
                        tvAmount.setText(String.valueOf(Double.parseDouble(tvAmount.getText().toString()) + (Double.parseDouble(split[2]) * Double.parseDouble(etCount1.getText().toString()))) + "0");
                        mACTProduct2.setEnabled(true);
                        etCount2.setEnabled(true);
                    }
                }
                else{
                    tvAmount.setText("0.00");
                    mACTProduct2.setEnabled(false);
                    etCount2.setEnabled(false);
                }
            }
        });

        etOrderID.setText(globalFunctions.getOrderID());
    }
}
