package com.example.furnitures2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class Product_detal extends AppCompatActivity  {
TextView t1,t2,t3,t4,t5;
ImageView iv;
ImageButton imageButton;
ImageView iv1;
    String id;
Button b;
String name;
String image;
String price;
    boolean fav_btn_checker = true;
    String userId;
    FirebaseUser user;
    FirebaseAuth auth;
    DatabaseReference ref;
    private static final String TAG = "Product_detal";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detal);
        t1 = findViewById(R.id.header_tv);
        t2 = findViewById(R.id.prod_name_tv);
        t3 = findViewById(R.id.prod_price_tv);
        iv = findViewById(R.id.detali_iv);
        b= findViewById(R.id.add_cart);
        iv1 = findViewById(R.id.ib11);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userId = user.getUid();
        ref = FirebaseDatabase.getInstance().getReference("furnituresDb").child("Users").child(userId).child("Fav_products");

        imageButton = findViewById(R.id.back_product_btn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Fragment_Activity.class));
            }
        });
       name = getIntent().getExtras().getString("name_key");
       image =  getIntent().getExtras().getString("image_key");
        price =  getIntent().getExtras().getString("price_key");
        String cat_nam = getIntent().getExtras().getString("category_key");
        t1.setText(cat_nam);
        t2.setText(name);
        t3.setText(price);
        Picasso.get().load(image).into(iv);




    }



}