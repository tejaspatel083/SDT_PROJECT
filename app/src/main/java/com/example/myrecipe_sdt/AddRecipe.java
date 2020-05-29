package com.example.myrecipe_sdt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class AddRecipe extends AppCompatActivity {

    private EditText recipename,cookname,time,cost,recipesteps;
    private ImageView myrecipeImage;
    private Button add_button;
    private FirebaseAuth firebaseAuth;
    private FirebaseStorage firebaseStorage;
    private static int PICK_IMAGE = 123;
    private Uri recipeimagePath;
    private StorageReference storageReference;
    String recipe_name,cook_name,estimated_time,estimated_cost,recipe_steps;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null)
        {

            recipeimagePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),recipeimagePath);
                myrecipeImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        super.onActivityResult(requestCode, resultCode, data);
    }


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
        myrecipeImage = findViewById(R.id.Add_MyRecipeImage);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        storageReference = firebaseStorage.getReference();

        myrecipeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(20);

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Choose Image"),PICK_IMAGE);
            }
        });


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
        DatabaseReference recipelistbranch = reference.child("My Recipe List").push();

        StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("Images").child("Recipe Pic");
        UploadTask uploadTask = imageReference.putFile(recipeimagePath);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


                Toast toast = Toast.makeText(AddRecipe.this,"Upload Failed",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Toast toast = Toast.makeText(AddRecipe.this,"Upload Successful",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

            }
        });

        RecipeGetterSetter recipeGetterSetter = new RecipeGetterSetter(cook_name,estimated_cost,estimated_time,recipe_steps,recipe_name);
        recipelistbranch.setValue(recipeGetterSetter);
    }
}
