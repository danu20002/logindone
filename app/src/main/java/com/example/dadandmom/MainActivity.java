package com.example.dadandmom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText login_email, login_pass;
    Button login;
    FirebaseAuth auth;
    TextView clikher,forgotpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login_email = findViewById(R.id.email_login);
        login_pass = findViewById(R.id.password_login);
        login = findViewById(R.id.signinbtn);
        clikher = findViewById(R.id.clickhere);
        auth = FirebaseAuth.getInstance();
        forgotpassword=findViewById(R.id.forgotpassword);
        clikher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, register.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginuser();
            }
        });
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(MainActivity.this,forgot.class));
            }
        });
    }

    private void loginuser() {
        String email = login_email.getText().toString();
        String password = login_pass.getText().toString();
        if (TextUtils.isEmpty(email)) {
            login_email.setError("Email cannot be empty");
            login_pass.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            login_email.setError("password cannot be empty");
            login_pass.requestFocus();

        } else {
                    auth.signInWithEmailAndPassword(email,password ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "login successful", Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(MainActivity.this,mainscreen.class));
                            }else{
                                Toast.makeText(MainActivity.this, "error in credintials"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
    }
}