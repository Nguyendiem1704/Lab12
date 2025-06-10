package com.example.lab12;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import handle.DbHandler;

public class SQLiteActivity extends AppCompatActivity {

    EditText name, loc, desig;
    Button saveBtn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        name = findViewById(R.id.txtName);
        loc = findViewById(R.id.txtLocation);
        desig = findViewById(R.id.txtDesignation);
        saveBtn = findViewById(R.id.btnSave);

        saveBtn.setOnClickListener(v -> {
            String username = name.getText().toString();
            String location = loc.getText().toString();
            String designation = desig.getText().toString();

            DbHandler dbHandler = new DbHandler(SQLiteActivity.this);
            dbHandler.insertUserDetails(username, location, designation);

            intent = new Intent(SQLiteActivity.this, DetailsSQLiteActivity.class);
            startActivity(intent);

            Toast.makeText(getApplicationContext(), "Details Inserted Successfully", Toast.LENGTH_SHORT).show();
        });
    }
}
