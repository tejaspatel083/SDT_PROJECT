package com.example.myrecipe_sdt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccount extends AppCompatActivity {

    private EditText user_name,user_email,user_age,user_phone,user_pwd1,user_pwd2;
    private Button createbtn;
    private FirebaseAuth firebaseAuth;
    String name,email,phone,age,password,repassword;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        progressDialog =new ProgressDialog(CreateAccount.this);
        progressDialog.setMessage("Creating...");



        user_name = findViewById(R.id.CreateName);
        user_email = findViewById(R.id.CreateEmail);
        user_age = findViewById(R.id.CreateAge);
        user_phone = findViewById(R.id.CreatePhone);
        user_pwd1 = findViewById(R.id.CreatePassword);
        user_pwd2 = findViewById(R.id.CreateRePassword);
        createbtn = findViewById(R.id.CreateBtn);

        firebaseAuth = FirebaseAuth.getInstance();


        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if (user_name.getText().toString().trim().length()==0 || user_email.getText().toString().trim().length()==0 || user_age.getText().toString().trim().length()==0 || user_phone.getText().toString().trim().length()==0 || user_pwd1.getText().toString().trim().length()==0 || user_pwd2.getText().toString().trim().length()==0)
                {
                    Toast toast = Toast.makeText(CreateAccount.this,"Enter All Details",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
                else if (user_pwd1.getText().toString().trim().equals(user_pwd2.getText().toString().trim()))
                {
                    progressDialog.show();
                    name = user_name.getText().toString().trim();
                    email = user_email.getText().toString().trim();
                    age = user_age.getText().toString().trim();
                    phone = user_phone.getText().toString().trim();
                    password = user_pwd1.getText().toString().trim();
                    repassword = user_pwd2.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful())
                            {
                                progressDialog.dismiss();
                                sendEmailVerification();

                            }else
                            {
                                progressDialog.dismiss();
                                if (task.getException() instanceof FirebaseAuthUserCollisionException) {

                                    Toast toast = Toast.makeText(CreateAccount.this,"User with this email already exist.",Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                                    toast.show();
                                }
                                else
                                {
                                    Toast toast = Toast.makeText(CreateAccount.this,"Enter Strong Password\n[ Including Text and Number ]",Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                                    toast.show();
                                }
                            }
                        }
                    });
                }
                else
                {
                    Toast toast = Toast.makeText(CreateAccount.this,"Password not matched",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
            }
        });
    }


    private  void sendEmailVerification()
    {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null)
        {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful())
                    {

                        sendUserData();
                        firebaseAuth.signOut();
                        finish();

                        Toast toast = Toast.makeText(CreateAccount.this,"Registration Completed.\nVerify Email",Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();
                        startActivity(new Intent(CreateAccount.this,MainActivity.class));
                    }
                    else
                    {
                        Toast toast = Toast.makeText(CreateAccount.this,"Verification mail has not been sent",Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();
                    }
                }
            });
        }



    }

    private void sendUserData()
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference(firebaseAuth.getUid());
        UserProfile userProfile = new UserProfile(age,email,name,phone);
        reference.setValue(userProfile);
    }
}
