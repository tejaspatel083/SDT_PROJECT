package com.example.myrecipe_sdt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyRecipeListPage extends AppCompatActivity {

    private ListView mylistview;
    private ArrayList<MyNamelist> mynamelist;
    private MyListAdapter myListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipe_list_page);


        mylistview = findViewById(R.id.myrecipepage_list);

        mynamelist = new ArrayList<>();
        myListAdapter = new MyListAdapter(mynamelist);

        mylistview.setAdapter(myListAdapter);

        mynamelist.add(new MyNamelist("Sev Tameta"));
        mynamelist.add(new MyNamelist("Chhole Bhature"));
        mynamelist.add(new MyNamelist("Dabeli"));
        mynamelist.add(new MyNamelist("Potato Wages"));
        mynamelist.add(new MyNamelist("Vadapav"));
        mynamelist.add(new MyNamelist("Dal Dhokli"));
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

                Intent intent1 = new Intent(this,HomePage.class);
                startActivity(intent1);
                Toast.makeText(this, "Home Page", Toast.LENGTH_SHORT).show();
                break;

            case    R.id.navigation_MyProfile:

                Intent intent = new Intent(this,ProfilePage.class);
                startActivity(intent);
                Toast.makeText(this, "My Profile", Toast.LENGTH_SHORT).show();
                break;

            case R.id.navigation_List:

                Toast.makeText(this, "My Recipe List", Toast.LENGTH_SHORT).show();
                break;
        }



        return true;
    }
}
