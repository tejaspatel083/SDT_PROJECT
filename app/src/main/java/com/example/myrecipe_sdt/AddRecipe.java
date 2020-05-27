package com.example.myrecipe_sdt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddRecipe extends AppCompatActivity {

    private EditText recipename,cookname,time,cost,recipesteps;
    private Button add_button;
    private FirebaseAuth firebaseAuth;
    String recipe_name,cook_name,estimated_time,estimated_cost,recipe_steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        recipename = findViewById(R.id.Add_MyRecipeName);
        cookname = findViewById(R.id.Add_CookName);
        time = findViewById(R.id.Add_EstimatedTime);
        cost = findViewById(R.id.Add_EstimatedCost);
        recipesteps = findViewById(R.id.Add_RecipeSteps);
        add_button = findViewById(R.id.Add_button);

        firebaseAuth = FirebaseAuth.getInstance();


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (recipename.getText().toString().trim().length()==0 || cookname.getText().toString().trim().length()==0 || time.getText().toString().trim().length()==0 || cost.getText().toString().trim().length()==0 || recipesteps.getText().toString().trim().length()==0)
                {
                    Toast toast = Toast.makeText(AddRecipe.this,"Enter All Details",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
                else
                {
                    recipe_name = recipename.getText().toString().trim();
                    cook_name = cookname.getText().toString().trim();
                    estimated_time = time.getText().toString().trim();
                    estimated_cost = cost.getText().toString().trim();
                    recipe_steps = recipesteps.getText().toString().trim();

                    Toast.makeText(AddRecipe.this, "Recipe Added", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddRecipe.this,MyRecipeListPage.class));
                    sendUserData();
                }

            }
        });
    }

    private void sendUserData()
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference(firebaseAuth.getUid());
        DatabaseReference recipelistbranch = reference.child("My Recipe List");
        RecipeGetterSetter recipeGetterSetter = new RecipeGetterSetter(cook_name,estimated_cost,estimated_time,recipe_steps,recipe_name);
        recipelistbranch.setValue(recipeGetterSetter);
    }
}
