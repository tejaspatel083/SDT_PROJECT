package com.example.myrecipe_sdt;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    private ListView listView;
    private ArrayList<RecipeNameList> arrayList;
    private RecipeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        listView = findViewById(R.id.homepage_list);

        arrayList = new ArrayList<>();
        adapter = new RecipeListAdapter(arrayList);

        arrayList.add(new RecipeNameList("Paneer Butter Masala"));
        arrayList.add(new RecipeNameList("Palak Paneer"));
        arrayList.add(new RecipeNameList("Pav Bhaji"));
        arrayList.add(new RecipeNameList("Pani Puri"));
        arrayList.add(new RecipeNameList("Sandwich"));

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(HomePage.this,RecipeDescription.class);
                startActivity(intent);
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
        }



        return true;
    }
}
