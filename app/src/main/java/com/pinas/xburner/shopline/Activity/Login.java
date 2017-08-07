package com.pinas.xburner.shopline.Activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pinas.xburner.shopline.R;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    EditText etUserName, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialization();
    }

    private void initialization() {
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        etUserName = (EditText) findViewById(R.id.etUserName);
        etUserName.setText("user");
        etPassword = (EditText) findViewById(R.id.etPassword);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnLogin:
                if(etUserName.getText().toString().equalsIgnoreCase("user")){
                    startActivity(new Intent(Login.this, MainActivityCustomer.class));
                }
                else if(etUserName.getText().toString().equalsIgnoreCase("admin")){
                    startActivity(new Intent(Login.this, MainActivity.class));
                }
                else{
                    Snackbar snackbar = Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Incorrect username or password.", Snackbar.LENGTH_LONG);
                    snackbar.setAction("GOT IT!", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Do Nothing
                        }
                    });
                    snackbar.show();
                }
                break;
        }
    }
}
