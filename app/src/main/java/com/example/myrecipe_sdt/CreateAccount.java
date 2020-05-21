package com.example.myrecipe_sdt;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccount extends AppCompatActivity {

    private EditText user_name,user_email,user_age,user_phone,user_pwd1,user_pwd2;
    private Button createbtn;
    //private FirebaseAuth firebaseAuth;
    private String name,email,phone,age,password,repassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        user_name = findViewById(R.id.CreateName);
        user_email = findViewById(R.id.CreateEmail);
        user_age = findViewById(R.id.CreateAge);
        user_phone = findViewById(R.id.CreatePhone);
        user_pwd1 = findViewById(R.id.CreatePassword);
        user_pwd2 = findViewById(R.id.CreateRePassword);
        createbtn = findViewById(R.id.CreateBtn);
        //firebaseAuth = FirebaseAuth.getInstance();


        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = user_name.getText().toString().trim();
                email = user_email.getText().toString().trim();
                age = user_age.getText().toString().trim();
                phone = user_phone.getText().toString().trim();
                password = user_pwd1.getText().toString().trim();
                repassword = user_pwd2.getText().toString().trim();


                if (name.length()==0 || email.length()==0 || age.length()==0 || phone.length()==0 || password.length()==0 || repassword.length()==0)
                {
                    Toast.makeText(CreateAccount.this, "Enter All Details", Toast.LENGTH_SHORT).show();
                }
                else if (password.equals(repassword))
                {
                    Toast.makeText(CreateAccount.this, "Account Created", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(CreateAccount.this, "Password Mismatched", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
