package com.example.lab12;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

import handle.DbHandler;

public class DetailsSQLiteActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_sqlite);

        DbHandler db = new DbHandler(this);
        ArrayList<HashMap<String, String>> userList = db.GetUsers();

        ListView lv = findViewById(R.id.user_list);
        ListAdapter adapter = new SimpleAdapter(
                DetailsSQLiteActivity.this,
                userList,
                R.layout.list_row,
                new String[]{"name", "designation", "location"},
                new int[]{R.id.name, R.id.designation, R.id.location});

        lv.setAdapter(adapter);

        Button back = findViewById(R.id.btnBack);
        back.setOnClickListener(v -> {
            intent = new Intent(DetailsSQLiteActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
