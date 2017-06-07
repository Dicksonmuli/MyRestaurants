package com.dicksonmully6gmail.myrestaurants.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dicksonmully6gmail.myrestaurants.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dickson on 6/5/17.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.passwordLoginButton) Button mPasswordLoginButton;
    @Bind(R.id.emailEditText) EditText mEmailEditText;
    @Bind(R.id.passwordEditText) EditText mPasswordEditText;
    @Bind(R.id.registerTextView) TextView mRegisterTextView;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        mRegisterTextView.setOnClickListener(this);

//        click listener to mPasswordLoginButton
        mPasswordLoginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
     if (view == mRegisterTextView) {
         Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
         startActivity(intent);
         finish();
     }
     if (view == mPasswordLoginButton) {
         loginWithPassword();
     }
    }
//    Firebase and authenticating the account with the user-provided credentials
    private void loginWithPassword() {
        String email = mEmailEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();
        if (email.equals("")) {
            mEmailEditText.setError("Please enter your email");
            return;
        }
        if (password.equals("")) {
            mPasswordEditText.setError("Password cannot be blank");
            return;
        }
    }
}
