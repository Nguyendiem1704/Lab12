package com.example.lab12;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsSharedPreferencesActivity extends AppCompatActivity {

    SharedPreferences prf;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_shared_preferences);

        TextView result = findViewById(R.id.resultView);
        Button btnLogOut = findViewById(R.id.btnLogOut);

        prf = getSharedPreferences("user_details", MODE_PRIVATE);
        intent = new Intent(DetailsSharedPreferencesActivity.this, SharedPreferencesActivity.class);

        result.setText("Hello, " + prf.getString("username", null));

        btnLogOut.setOnClickListener(v -> {
            SharedPreferences.Editor editor = prf.edit();
            editor.clear();
            editor.apply();
            startActivity(intent);
        });
    }
}
