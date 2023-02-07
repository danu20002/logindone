package com.example.dadandmom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot extends AppCompatActivity {
EditText email_forgot;
Button go;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        email_forgot=findViewById(R.id.email_forgot);
        go=findViewById(R.id.go_btn);
        auth=FirebaseAuth.getInstance();
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String emial_for=email_forgot.getText().toString();
                    if(!emial_for.isEmpty()){
                        fforgotpass();
                    }else{
                        Toast.makeText(forgot.this, "please enter email", Toast.LENGTH_SHORT).show();
                        email_forgot.setError("enter email");
                        email_forgot.requestFocus();
                    }
            }

            private void fforgotpass() {
                auth=FirebaseAuth.getInstance();
                String emial_for=email_forgot.getText().toString();
                auth.verifyPasswordResetCode(emial_for).addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(forgot.this, "see yor email gajani", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(forgot.this,MainActivity.class));
                            finish();
                        }else{
                            Toast.makeText(forgot.this, "error while sending Mail"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });

}



}