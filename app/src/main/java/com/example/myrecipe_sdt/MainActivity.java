package com.example.myrecipe_sdt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button loginbtn,createbtn;
    private TextView forgotpwd;
    private EditText useremail,userpassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginbtn = (Button)findViewById(R.id.MainLoginBtn);
        createbtn = (Button)findViewById(R.id.MainCreateBtn);
        forgotpwd = (TextView)findViewById(R.id.MainForgotPassword);
        useremail = findViewById(R.id.MainEmail);
        userpassword = findViewById(R.id.MainPassword);

        firebaseAuth = FirebaseAuth.getInstance();

       FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

       if (firebaseUser != null)
       {
           finish();
           startActivity(new Intent(MainActivity.this,HomePage.class));
       }


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {

                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(20);


                if (useremail.getText().toString().trim().length() == 0)
                {
                    useremail.setError("Email Id Required");
                    //Toast.makeText(MainActivity.this,"Enter Email",Toast.LENGTH_LONG).show();
                    Toast toast = Toast.makeText(MainActivity.this,"Enter Email",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
                else if (userpassword.getText().toString().trim().length() == 0)
                {
                    useremail.setError(null);
                    userpassword.setError("Password Required");
                    //Toast.makeText(MainActivity.this,"Enter Password",Toast.LENGTH_LONG).show();
                    Toast toast = Toast.makeText(MainActivity.this,"Enter Password",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
                else
                {
                    useremail.setError(null);
                    userpassword.setError(null);
                    validate(useremail.getText().toString(),userpassword.getText().toString());

                }
            }
        });

        createbtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {

                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(20);
                Intent i = new Intent(MainActivity.this,CreateAccount.class);
                startActivity(i);

            }
        });

        forgotpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(20);
                Intent i = new Intent(MainActivity.this,ForgotPassword.class);
                startActivity(i);

            }
        });
    }


    private void validate(String userName,String userPassword)
    {

        firebaseAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {
                    checkEmailVerification();
                }
                else
                {
                    //Toast.makeText(MainActivity.this,"Enter Valid Username and Password",Toast.LENGTH_LONG).show();
                    Toast toast = Toast.makeText(MainActivity.this,"Enter Valid Email and Password",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }

            }
        });


    }

    private void checkEmailVerification()
    {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        if (emailflag)
        {
            //Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
            Toast toast = Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            startActivity(new Intent(MainActivity.this,HomePage.class));
        }
        else
        {
            //Toast.makeText(MainActivity.this,"Need to Verify Email",Toast.LENGTH_LONG).show();
            Toast toast = Toast.makeText(MainActivity.this,"Need to Verify Email",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            firebaseAuth.signOut();
        }

    }
}
