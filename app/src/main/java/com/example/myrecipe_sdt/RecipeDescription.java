package com.example.myrecipe_sdt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class RecipeDescription extends AppCompatActivity {

    private TextView recipename_txt,cookname_txt,time_txt,cost_txt,fullrecipe_txt;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_description);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        recipename_txt = findViewById(R.id.recipename_detail);
        cookname_txt = findViewById(R.id.cookname_detail);
        time_txt = findViewById(R.id.recipetime_detail);
        cost_txt = findViewById(R.id.recipecost_detail);
        fullrecipe_txt = findViewById(R.id.fullrecipe_detail);


        DatabaseReference databaseReference = (DatabaseReference) firebaseDatabase.getReference();

        DatabaseReference childreference = databaseReference.child("User Recipes").child(firebaseAuth.getUid());

        childreference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Map<String,Object> newPost=(Map<String,Object>)dataSnapshot.getValue();

                String name1 = String.valueOf(newPost.get("cname"));
                String name2 = String.valueOf(newPost.get("ecost"));
                String name3 = String.valueOf(newPost.get("etime"));
                String name4 = String.valueOf(newPost.get("rdetail"));
                String name5 = String.valueOf(newPost.get("rname"));


                recipename_txt.setText(" Recipe Name : "+name5);
                cookname_txt.setText(" Cook Name :   "+name1);
                time_txt.setText(" Estimated Time :   "+name3);
                cost_txt.setText(" Estimated Cost :   "+name2);
                fullrecipe_txt.setText(""+name4+"\n\n");




            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /*

        recipelistbranch.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                RecipeGetterSetter recipeGetterSetter = dataSnapshot.getValue(RecipeGetterSetter.class);

                recipename_txt.setText(" Recipe Name : "+recipeGetterSetter.getRname());
                cookname_txt.setText(" Cook Name :   "+recipeGetterSetter.getCname());
                time_txt.setText(" Estimated Time :   "+recipeGetterSetter.getEtime());
                cost_txt.setText(" Estimated Cost :   "+recipeGetterSetter.getEcost());
                fullrecipe_txt.setText(""+recipeGetterSetter.getRdetail()+"\n\n");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                //Toast.makeText(Main2Activity.this,databaseError.getCode(),Toast.LENGTH_LONG).show();
                Toast toast = Toast.makeText(RecipeDescription.this,databaseError.getCode(),Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
        });
*/


    }
}
