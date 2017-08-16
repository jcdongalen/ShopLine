package com.pinas.xburner.shopline.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.pinas.xburner.shopline.Custom.mProgressDialog;
import com.pinas.xburner.shopline.R;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    EditText etUserName, etPassword;
    TextView tvSignUp;
    ProgressBar progressLoading;

    private FirebaseAuth auth;

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
        etPassword = (EditText) findViewById(R.id.etPassword);

        etUserName.setText("admin@gmail.com");
        etPassword.setText("password");

        tvSignUp = (TextView) findViewById(R.id.tvSignUp);
        tvSignUp.setOnClickListener(this);

        //progressLoading = (ProgressBar) findViewById(R.id.progressLoading);

        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:

                final String email = etUserName.getText().toString();
                final String password = etPassword.getText().toString();

                ValidateCredential task = new ValidateCredential();
                task.execute(email, password);

//                if(email.equalsIgnoreCase("user")){
//                    startActivity(new Intent(Login.this, MainActivityCustomer.class));
//                }
//                else if(email.equalsIgnoreCase("admin")){
//                    startActivity(new Intent(Login.this, MainActivity.class));
//                }
//                else{
//                    Snackbar snackbar = Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Incorrect username or password.", Snackbar.LENGTH_LONG);
//                    snackbar.setAction("GOT IT!", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            //Do Nothing
//                        }
//                    });
//                    snackbar.show();
//                }
                break;
            case R.id.tvSignUp:
                startActivity(new Intent(Login.this, Registration.class));
                break;
        }
    }

    private class ValidateCredential extends AsyncTask<String, String, String> {

        mProgressDialog progressDialog = new mProgressDialog(Login.this);
        String email = "", password = "";
        String response = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... strings) {
            email = strings[0];
            password = strings[1];

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                response = "failed";
                            } else {
                                response = "success";
                            }
                        }
                    });

            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if(s.equalsIgnoreCase("failed")){
                Snackbar snackbar = Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Incorrect username or password.", Snackbar.LENGTH_LONG);
                snackbar.setAction("Register?", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(Login.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                        } else {
                                            if (email.equalsIgnoreCase("admin")) {
                                                startActivity(new Intent(Login.this, MainActivity.class));
                                            } else {
                                                startActivity(new Intent(Login.this, MainActivityCustomer.class));
                                            }
                                        }
                                    }
                                });
                    }
                });
                snackbar.show();
            }
            else if(s.equalsIgnoreCase("success")){
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
