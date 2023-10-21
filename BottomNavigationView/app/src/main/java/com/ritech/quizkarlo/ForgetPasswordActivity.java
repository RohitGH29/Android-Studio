package com.ritech.quizkarlo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.ritech.quizkarlo.Login.LoginActivity;

public class ForgetPasswordActivity extends AppCompatActivity {

    Button forgetBtn;
    EditText txtemail;
    String email;
    ImageView backBtn;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        auth= FirebaseAuth.getInstance();

        forgetBtn=findViewById(R.id.forgetB);
        txtemail=findViewById(R.id.forgetemail);
        backBtn=findViewById(R.id.forgetbackB);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        forgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });

    }

    private void validateData() {
        email = txtemail.getText().toString();
        if (email.isEmpty()){
            txtemail.setError("Required");
        }else
        {
            forgetPass();
        }
    }

    private void forgetPass() {
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(ForgetPasswordActivity.this, "Check Your Email", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForgetPasswordActivity.this, LoginActivity.class));
                            finish();
                        }else
                        {
                            Toast.makeText(ForgetPasswordActivity.this, "Error : " +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}