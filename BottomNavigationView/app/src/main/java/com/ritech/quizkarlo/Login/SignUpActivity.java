package com.ritech.quizkarlo.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ritech.quizkarlo.DbQuery;
import com.ritech.quizkarlo.Interface.MyCompleteListener;
import com.ritech.quizkarlo.MainActivity;
import com.ritech.quizkarlo.R;

public class SignUpActivity extends AppCompatActivity {

    private EditText name,email,pass,confirmPass;
    private Button signUpB;
    private ImageView backB;
    private FirebaseAuth mAuth;
    private String emailStr , passStr , confirmPassStr , nameStr;
    private Dialog progressDialog;
    private TextView dialogText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();

        name = findViewById(R.id.username);
        email = findViewById(R.id.emailID);
        pass = findViewById(R.id.password);
        confirmPass = findViewById(R.id.confirm_pass);
        signUpB = findViewById(R.id.signupB);
        backB = findViewById(R.id.backB);


        // Progress bar
         progressDialog = new Dialog(SignUpActivity.this);
         progressDialog.setContentView(R.layout.dialog_layout);
         progressDialog.setCancelable(false);
         progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
         dialogText=progressDialog.findViewById(R.id.dialog_text);
         dialogText.setText("Registering user....");


        mAuth = FirebaseAuth.getInstance();

        //For back icon clicked
        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // when sign up button click
        signUpB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()) {
                    signupNewUser();
                }

            }
        });
    }

    private boolean validate()
    {
        nameStr= name.getText().toString().trim();
        passStr= pass.getText().toString().trim();
        emailStr= email.getText().toString().trim();
        confirmPassStr= confirmPass.getText().toString().trim();

        // Check any box empty then show error
        if (nameStr.isEmpty())
        {
            name.setError("Enter Your Name");
            return false;
        }

        if (emailStr.isEmpty())
        {
            email.setError("Enter E-Mail Id");
            return false;
        }

        if (passStr.isEmpty())
        {
            pass.setError("Enter Password");
            return false;
        }

        if (confirmPassStr.isEmpty())
        {
            confirmPass.setError("Enter Confirm Password ");
            return false;
        }

        if (passStr.compareTo(confirmPassStr)!=0)
        {
            Toast.makeText(this, "Password not matched", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }


    private void signupNewUser()
    {
        // dialog show
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(emailStr, passStr)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "Sign Up Successfull", Toast.LENGTH_SHORT).show();

                            DbQuery.createUserData(emailStr,nameStr, new MyCompleteListener(){

                                @Override
                                public void onSuccess() {

                                    DbQuery.loadData(new MyCompleteListener() {
                                        @Override
                                        public void onSuccess() {
                                            Intent intent= new Intent(SignUpActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            progressDialog.dismiss();
                                            SignUpActivity.this.finish();
                                        }

                                        @Override
                                        public void onFailure() {
                                            progressDialog.dismiss();
                                            Toast.makeText(SignUpActivity.this, "Something went wrong ! Please try Later ", Toast.LENGTH_SHORT).show();
                                        }
                                    });


                                }

                                @Override
                                public void onFailure() {
                                    progressDialog.dismiss();
                                    Toast.makeText(SignUpActivity.this, "Something went wrong ! Please try Later ", Toast.LENGTH_SHORT).show();
                                }
                            });
                            
                        } else {
                            // If sign in fails, display a message to the user.
                            progressDialog.dismiss();
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}