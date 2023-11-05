package com.example.firebaseauth;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;


public class MainActivityEmploye extends AppCompatActivity {

    FirebaseAuth auth;
    Button button;

    EditText txtId;
    EditText txtName;
    EditText txtPrice;

    TextView textView;
    FirebaseUser user;

    DatabaseReference databaseReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_employe);

        auth=FirebaseAuth.getInstance();
        button = findViewById(R.id.logout);


        txtId = findViewById(R.id.prodId);
        txtName = findViewById(R.id.prodName);
        txtPrice = findViewById(R.id.prodPrice);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = txtId.getText().toString();
                String name = txtName.getText().toString();
                String price = txtPrice.getText().toString();


                UpdateProduct(id, name, price);


                txtId.setText("");
                txtName.setText("");
                txtPrice.setText("");

            }
        });

    }
    private void UpdateProduct(String id, String name,String price){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products").child(id);

        Product product = new Product(id, name, price);

        try{
            databaseReference.setValue(product);
        }catch(Exception e) {
            e.printStackTrace();
    }

        Toast.makeText(MainActivityEmploye.this,  "Product Updated", Toast.LENGTH_LONG).show();


}}