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

public class register extends AppCompatActivity {
EditText reg_email,reg_pass;
Button signup;
TextView clickhere;
FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        reg_email=findViewById(R.id.email_reg);
        reg_pass=findViewById(R.id.password_reg);
        signup=findViewById(R.id.sign_up_btn);
        clickhere=findViewById(R.id.clickherelogin);
 auth=FirebaseAuth.getInstance();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createuser();
            }
        });
        clickhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(register.this,MainActivity.class));
            }
        });
    }

    private void createuser() {
        String email=reg_email.getText().toString();
        String password=reg_pass.getText().toString();
        if(TextUtils.isEmpty(email)){
            reg_email.setError("Email cannot be empty");
            reg_email.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            reg_pass.setError("password cannot be empty");
            reg_pass.requestFocus();
        }else {
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(register.this, "User register succesfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(register.this,MainActivity.class));
                    }else{
                        Toast.makeText(register.this, "error occurred"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}