package com.example.lab12;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SharedPreferencesActivity extends AppCompatActivity {

    EditText uname, pwd;
    Button loginBtn;
    SharedPreferences pref;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        uname = findViewById(R.id.txtName);
        pwd = findViewById(R.id.txtPwd);
        loginBtn = findViewById(R.id.btnLogin);

        pref = getSharedPreferences("user_details", MODE_PRIVATE);
        intent = new Intent(SharedPreferencesActivity.this, DetailsSharedPreferencesActivity.class);

        if (pref.contains("username") && pref.contains("password")) {
            startActivity(intent);
        }

        loginBtn.setOnClickListener(v -> {
            String username = uname.getText().toString();
            String password = pwd.getText().toString();

            if (username.equals("admin") && password.equals("admin")) {
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("username", username);
                editor.putString("password", password);
                editor.apply();

                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Credentials are not valid", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
