package com.example.myrecipe_sdt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class AddRecipe extends AppCompatActivity {

    private EditText recipename,cookname,time,cost,recipesteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
    }
}
