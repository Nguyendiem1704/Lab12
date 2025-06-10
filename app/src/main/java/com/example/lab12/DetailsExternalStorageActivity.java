package com.example.lab12;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DetailsExternalStorageActivity extends AppCompatActivity {

    FileInputStream fstream;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_external_storage);

        TextView result = findViewById(R.id.resultView);
        Button back = findViewById(R.id.btnBack);

        try {
            File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File myFile = new File(folder, "user_details_external");

            fstream = new FileInputStream(myFile);
            StringBuilder sbuffer = new StringBuilder();
            int i;

            while ((i = fstream.read()) != -1) {
                sbuffer.append((char) i);
            }

            fstream.close();

            String[] details = sbuffer.toString().split("\n");
            result.setText("Name: " + details[0] + "\nPassword: " + details[1]);

        } catch (IOException e) {
            e.printStackTrace();
        }

        back.setOnClickListener(v -> {
            intent = new Intent(DetailsExternalStorageActivity.this, ExternalStorageActivity.class);
            startActivity(intent);
        });
    }
}
