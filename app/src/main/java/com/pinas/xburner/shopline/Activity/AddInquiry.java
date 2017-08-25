package com.pinas.xburner.shopline.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pinas.xburner.shopline.Adapter.ProductAdapter;
import com.pinas.xburner.shopline.Functions.GlobalFunctions;
import com.pinas.xburner.shopline.Model.Order;
import com.pinas.xburner.shopline.Model.Products;
import com.pinas.xburner.shopline.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AddInquiry extends AppCompatActivity implements View.OnClickListener {

    AutoCompleteTextView mAutoCompletePaymentMode, mACTProduct1, mACTProduct2, mACTProduct3;
    EditText etOrderID, etFullName, etAddress, etCount1, etCount2, etCount3;
    GlobalFunctions globalFunctions;
    TextView tvAmount;
    Button btnPlaceOrder;
    ProductAdapter productAdapter;
    ImageButton imgRemove;

    DatabaseReference mDatabaseReference;
    FirebaseDatabase mFirebaseDatabase;
    ArrayList<Products> supplies = new ArrayList<>();
    ArrayList<Products> cart = new ArrayList<>();
    private DecimalFormat formatter;

    private String OrderID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inquiry);

        initializeProducts();
        initialization();
    }

    private void initialization() {
        this.formatter = new DecimalFormat("###,###,###.00");

        mAutoCompletePaymentMode = (AutoCompleteTextView) findViewById(R.id.mAutoCompletePaymentMode);
        String[] paymentmode = getResources().getStringArray(R.array.PaymentMode);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.custom_list_item, paymentmode);
        mAutoCompletePaymentMode.setAdapter(adapter);

        imgRemove = (ImageButton) findViewById(R.id.imgRemove);
        imgRemove.setOnClickListener(this);

        //Firebase Initialization
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("Orders");
        globalFunctions = new GlobalFunctions();
        productAdapter = new ProductAdapter(AddInquiry.this, supplies);

        mACTProduct1 = (AutoCompleteTextView) findViewById(R.id.mACTProduct1);
        mACTProduct1.setAdapter(productAdapter);
        mACTProduct1.setThreshold(1);
        mACTProduct1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                mACTProduct1.setText(productAdapter.suggestions.get(position).getName());
                cart.add(productAdapter.suggestions.get(position));
                etCount1.setEnabled(true);
                mACTProduct1.setEnabled(false);
            }
        });

        etCount1 = (EditText) findViewById(R.id.etCount1);
        etCount1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (cart.get(0) != null && mACTProduct1.getText().toString().length() > 5) {
                    cart.get(0).setStock(etCount1.getText().toString());
                    Double amount = Double.parseDouble(cart.get(0).getAmount()) * Double.parseDouble(cart.get(0).getStock());
                    cart.get(0).setTotalamount(String.valueOf(amount));
                    tvAmount.setText(formatter.format(amount));
                }
                else{
                    etCount1.setText("");
                }
            }
        });

        etOrderID = (EditText) findViewById(R.id.etOrderID);
        etFullName = (EditText) findViewById(R.id.etFullname);
        etAddress = (EditText) findViewById(R.id.etAddress);
        tvAmount = (TextView) findViewById(R.id.tvAmount);
        tvAmount.setText("0.00");
        btnPlaceOrder = (Button) findViewById(R.id.btnPlaceOrder);
        btnPlaceOrder.setOnClickListener(this);

        etOrderID.setText(GlobalFunctions.getOrderID());
    }

    private void initializeProducts() {
        Products ACC_ACCProduct1 = new Products("ACC-Product1", "Fish Eye Lens", "Accessories for Camera phones.", "Accessories", "199.00");
        supplies.add(ACC_ACCProduct1);
        Products ACC_ACCProduct2 = new Products("ACC-Product2", "Generic Tripod for Smartphones", "Accessories for Smart Phones.", "Accessories", "199.00");
        supplies.add(ACC_ACCProduct2);
        Products SP_HuaweiP10 = new Products("SP-HuaweiP10", "Huawei P10 (64GB)", "Smart Phone.", "Smart Phone", "28999.00");
        supplies.add(SP_HuaweiP10);
        Products SP_HuaweiP10Plus = new Products("SP-HuaweiP10Plus", "Huawei P10 Plus (64GB)", "Smart Phone.", "Smart Phone", "32999.00");
        supplies.add(SP_HuaweiP10Plus);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPlaceOrder:
                try {
                    if(cart.size() > 0){
                        OrderID = etOrderID.getText().toString();
                        Order order = new Order(OrderID, etFullName.getText().toString(), etAddress.getText().toString(), mAutoCompletePaymentMode.getText().toString(), tvAmount.getText().toString(), cart);

                        mDatabaseReference.child(OrderID).setValue(order);
                        addOrderChangeListerner();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.imgRemove:
                cart.clear();
                etCount1.setEnabled(false);
                mACTProduct1.setText("");
                mACTProduct1.setEnabled(true);
                etCount1.setText("");
                tvAmount.setText("0.00");
                break;
        }
    }

    private void addOrderChangeListerner() {
        mDatabaseReference.child(OrderID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Order order = dataSnapshot.getValue(Order.class);

                if (order != null) {
                    Toast.makeText(AddInquiry.this, "Successfully pushed to Firebase!", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AddInquiry.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
