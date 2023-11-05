package com.example.firebaseauth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button button;
    Button btnUpdate;
    Button btnList;
    TextView textView;
    FirebaseUser user;

    EditText productName;
    EditText productPrice;

    DatabaseReference databaseProducts;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth=FirebaseAuth.getInstance();
        button = findViewById(R.id.logout);
        btnList = findViewById(R.id.btnViewList);

        productName = findViewById(R.id.productname);
        productPrice = findViewById(R.id.productprice);

        databaseProducts = FirebaseDatabase.getInstance().getReference("products");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add to the firestore database
                String prodName = productName.getText().toString();
                String prodPrice = productPrice.getText().toString();

                addProducts(prodName, prodPrice);

                //Resetting the edit texts
                productName.setText("");
                productPrice.setText("");


            }
        });


        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivityClient.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void addProducts(String name, String price){
        if(!name.isEmpty()){
            String id = databaseProducts.push().getKey();
            Product product = new Product(id, name, price);


            try {
                databaseProducts.child(id).setValue(product);
            } catch (Exception e) {
                e.printStackTrace();
            }




            Toast.makeText(MainActivity.this, "Product added", Toast.LENGTH_LONG).show();
        }
    }
}
