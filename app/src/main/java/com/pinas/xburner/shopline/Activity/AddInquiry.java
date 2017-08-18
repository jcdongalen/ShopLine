package com.pinas.xburner.shopline.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pinas.xburner.shopline.Functions.GlobalFunctions;
import com.pinas.xburner.shopline.Model.Order;
import com.pinas.xburner.shopline.R;

public class AddInquiry extends AppCompatActivity implements View.OnClickListener{

    AutoCompleteTextView mAutoCompletePaymentMode, mACTProduct1, mACTProduct2, mACTProduct3;
    EditText etOrderID, etFullName, etAddress, etCount1, etCount2, etCount3;
    GlobalFunctions globalFunctions;
    TextView tvAmount;
    Button btnPlaceOrder;

    DatabaseReference mDatabaseReference;
    FirebaseDatabase mFirebaseDatabase;

    private String OrderID;

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

        //Firebase Initialization
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mDatabaseReference = mFirebaseDatabase.getReference("Orders");

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
        btnPlaceOrder = (Button) findViewById(R.id.btnPlaceOrder);
        btnPlaceOrder.setOnClickListener(this);

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

        etOrderID.setText(GlobalFunctions.getOrderID());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPlaceOrder:
                try{
                    OrderID = etOrderID.getText().toString();
                    Order order = new Order(OrderID, etFullName.getText().toString(), etAddress.getText().toString(), mAutoCompletePaymentMode.getText().toString(), tvAmount.getText().toString(), mACTProduct1.getText().toString(), etCount1.getText().toString());

                    mDatabaseReference.child(OrderID).setValue(order);
                    addOrderChangeListerner();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                break;
        }
    }

    private void addOrderChangeListerner(){
        mDatabaseReference.child(OrderID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Order order = dataSnapshot.getValue(Order.class);

                if(order != null){
                    Toast.makeText(AddInquiry.this, "Successfully pushed to Firebase!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AddInquiry.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
