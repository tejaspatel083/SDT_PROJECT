package com.example.myrecipe_sdt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyRecipeListPage extends AppCompatActivity {

    private ImageView recipeimage;
    ListView listView;
    ArrayList<String> arList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    RecipeGetterSetter rgs = new RecipeGetterSetter();

    private Button addbutton;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipe_list_page);


        listView = findViewById(R.id.myrecipepage_list);
        addbutton = findViewById(R.id.addrecipebtn);
        recipeimage = findViewById(R.id.imgviewmylist);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        adapter = new ArrayAdapter<String>(this,R.layout.myrecipelist_model,R.id.recipename_mylist,arList);


        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MyRecipeListPage.this,AddRecipe.class);
                startActivity(intent);
            }
        });



        DatabaseReference databaseReference = firebaseDatabase.getReference();

        final DatabaseReference recipelistbranch = databaseReference.child("User Recipes").child(firebaseAuth.getUid());

        recipelistbranch.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds: dataSnapshot.getChildren())
                {

                    rgs = ds.getValue(RecipeGetterSetter.class);
                    arList.add(rgs.getRname());
                }

                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /*
        StorageReference storageReference = firebaseStorage.getReference();
        storageReference.child(firebaseAuth.getUid()).child("Images/Recipe Pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                Picasso.get().load(uri).into(recipeimage);

            }
        });


*/

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String recipetext = parent.getItemAtPosition(position).toString();

                Intent intent = new Intent(MyRecipeListPage.this,RecipeDescription.class);
                intent.putExtra("recipekey",recipetext);
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

            case R.id.logout:

                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(MyRecipeListPage.this,MainActivity.class));
                Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
                break;
        }



        return true;
    }




}
