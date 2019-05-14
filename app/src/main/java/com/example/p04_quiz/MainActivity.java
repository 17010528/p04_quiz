package com.example.p04_quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnInsert, btnRetrieve;
    EditText etBrand , etLitre;
    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRetrieve = findViewById(R.id.btnRetrieve);
        btnInsert = findViewById(R.id.btnInsert);
        tvResult = findViewById(R.id.tvResult);
        etBrand = findViewById(R.id.etBrand);
        etLitre = findViewById(R.id.etLitre);


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper db = new DBHelper(MainActivity.this);
                db.insertCar(etBrand.getText().toString(), Double.parseDouble(etLitre.getText().toString()));
                db.close();

            }
        });
        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                ArrayList<Car> data = db.getAllCar();
                db.close();
                String result ="";
                for(int i=0 ; i<data.size() ; i++) {
                    String litre = Double.toString(data.get(i).getLitre());
                    String brand = data.get(i).getBrand();
                    String id = Integer.toString(data.get(i).getId());
                   result += id + "\n" + brand + "\n" + litre + "\n";
                }
                // Insert a task
                tvResult.setText(result);


                // Link this Activity object, the row.xml layout for
                //  each row and the food String array together


            }
        });
    }
}
