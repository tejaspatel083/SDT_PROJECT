package com.example.myrecipe_sdt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.w3c.dom.Comment;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    ListView list_View;
    ArrayList<String> array_List = new ArrayList<>();
    ArrayAdapter<String> array_Adapter;
    RecipeGetterSetter recipe_GetterSetter = new RecipeGetterSetter();

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        list_View = findViewById(R.id.homepage_list);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        array_Adapter = new ArrayAdapter<>(this,R.layout.homepage_model,R.id.recipename_homepage,array_List);


        DatabaseReference databaseReference = firebaseDatabase.getReference("my-recipe-63268");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    for (DataSnapshot ds1: ds.getChildren())
                    {

                        recipe_GetterSetter = ds1.getValue(RecipeGetterSetter.class);
                        array_List.add(recipe_GetterSetter.getRname());

                    }


                }

                list_View.setAdapter(array_Adapter);
                array_Adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


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
                Toast.makeText(this, "Home Page", Toast.LENGTH_SHORT).show();
                break;

            case    R.id.navigation_MyProfile:

                Intent intent = new Intent(this,ProfilePage.class);
                startActivity(intent);
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
                startActivity(new Intent(HomePage.this,MainActivity.class));
                Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
                break;
        }



        return true;
    }
}
