package com.example.myrecipe_sdt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ProfilePage extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        firebaseAuth = FirebaseAuth.getInstance();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.nav_menu,menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id =   item.getItemId();

        switch (id)
        {
            case    R.id.navigation_home:

                Intent intent = new Intent(this,HomePage.class);
                startActivity(intent);
                Toast.makeText(this, "Home Page", Toast.LENGTH_SHORT).show();
                break;

            case    R.id.navigation_MyProfile:

                Toast.makeText(this, "My Profile", Toast.LENGTH_SHORT).show();
                break;

            case R.id.navigation_List:

                Intent intent1 = new Intent(this,MyRecipeListPage.class);
                startActivity(intent1);
                Toast.makeText(this, "My Recipe List", Toast.LENGTH_SHORT).show();
                break;

            case R.id.logout:

                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(ProfilePage.this,MainActivity.class));
                Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
                break;
        }



        return true;
    }
}
