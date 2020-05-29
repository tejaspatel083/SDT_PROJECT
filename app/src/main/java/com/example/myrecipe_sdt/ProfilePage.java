package com.example.myrecipe_sdt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class ProfilePage extends AppCompatActivity {

    private TextView name,phone,age,email;
    private ImageView profileImage;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();


        profileImage = findViewById(R.id.ProfileImage);
        name = findViewById(R.id.ProfileName);
        phone = findViewById(R.id.ProfilePhone);
        age = findViewById(R.id.ProfileAge);
        email = findViewById(R.id.ProfileEmail);


        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        StorageReference storageReference = firebaseStorage.getReference();
        storageReference.child(firebaseAuth.getUid()).child("Images/Profile Pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                Picasso.get().load(uri).into(profileImage);

            }
        });



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);

                name.setText(" Name : "+userProfile.getUsername());
                phone.setText(" Phone :   "+userProfile.getUserphone());
                age.setText(" Age :   "+userProfile.getUserage());
                email.setText(" Email :   "+userProfile.getUseremail());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                //Toast.makeText(Main2Activity.this,databaseError.getCode(),Toast.LENGTH_LONG).show();
                Toast toast = Toast.makeText(ProfilePage.this,databaseError.getCode(),Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
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
