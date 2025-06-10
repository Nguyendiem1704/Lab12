package com.example.lab12;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import handle.DbHandler;

public class MainActivity extends AppCompatActivity {

    Button btnSharedPref, btnInternalStorage, btnExternalStorage, btnSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSharedPref = findViewById(R.id.btnSharedPref);
        btnInternalStorage = findViewById(R.id.btnInternalStorage);
        btnExternalStorage = findViewById(R.id.btnExternalStorage);
        btnSQLite = findViewById(R.id.btnSQLite);

        btnSharedPref.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SharedPreferencesActivity.class);
            startActivity(intent);
        });

        btnInternalStorage.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, InternalStorageActivity.class);
            startActivity(intent);
        });

        btnExternalStorage.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ExternalStorageActivity.class);
            startActivity(intent);
        });

        btnSQLite.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SQLiteActivity.class);
            startActivity(intent);
        });
    }
}
