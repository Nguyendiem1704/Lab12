package com.example.lab12;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;

public class InternalStorageActivity extends AppCompatActivity {

    EditText uname, pwd;
    Button saveBtn;
    FileOutputStream fstream;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);

        uname = findViewById(R.id.txtName);
        pwd = findViewById(R.id.txtPwd);
        saveBtn = findViewById(R.id.btnSave);

        saveBtn.setOnClickListener(v -> {
            String username = uname.getText().toString() + "\n";
            String password = pwd.getText().toString();

            try {
                fstream = openFileOutput("user_details_internal", Context.MODE_PRIVATE);
                fstream.write(username.getBytes());
                fstream.write(password.getBytes());
                fstream.close();

                Toast.makeText(getApplicationContext(), "Details Saved Successfully", Toast.LENGTH_SHORT).show();

                intent = new Intent(InternalStorageActivity.this, DetailsInternalStorageActivity.class);
                startActivity(intent);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
