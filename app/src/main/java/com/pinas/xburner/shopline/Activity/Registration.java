package com.pinas.xburner.shopline.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.pinas.xburner.shopline.R;

public class Registration extends AppCompatActivity implements View.OnClickListener{

    Button btnRegister, btnResetPassword;
    EditText etEmail, etPassword;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initialization();

    }

    private void initialization(){
        etEmail = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnResetPassword = (Button) findViewById(R.id.btnResetPassword);
        btnRegister.setOnClickListener(this);
        btnResetPassword.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRegister:
                final String email = etEmail.getText().toString();
                final String password = etPassword.getText().toString();

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(Registration.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                } else {
                                    if (email.contains("admin")) {
                                        startActivity(new Intent(Registration.this, MainActivity.class));
                                    } else {
                                        startActivity(new Intent(Registration.this, MainActivityCustomer.class));
                                    }
                                }
                            }
                        });
                break;
            case R.id.btnResetPassword:
                break;
        }
    }
}
