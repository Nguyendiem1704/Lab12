package com.example.lab12;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExternalStorageActivity extends AppCompatActivity {

    EditText uname, pwd;
    Button saveBtn;
    FileOutputStream fstream;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);

        uname = findViewById(R.id.txtName);
        pwd = findViewById(R.id.txtPwd);
        saveBtn = findViewById(R.id.btnSave);

        saveBtn.setOnClickListener(v -> {
            String username = uname.getText().toString() + "\n";
            String password = pwd.getText().toString();

            try {
                ActivityCompat.requestPermissions(ExternalStorageActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 23);

                File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                File myFile = new File(folder, "user_details_external");

                fstream = new FileOutputStream(myFile);
                fstream.write(username.getBytes());
                fstream.write(password.getBytes());
                fstream.close();

                Toast.makeText(getApplicationContext(),
                        "Details Saved in " + myFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                intent = new Intent(ExternalStorageActivity.this, DetailsExternalStorageActivity.class);
                startActivity(intent);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
