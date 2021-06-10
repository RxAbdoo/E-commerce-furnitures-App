package com.example.furnitures2;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.view.LayoutInflater.from;

public class SpScreen extends AppCompatActivity {
ImageView imageView;
LottieAnimationView view;
    FirebaseAuth auth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sp_screen);
        auth= FirebaseAuth.getInstance();
        user= auth.getCurrentUser();
        imageView=findViewById(R.id.imageView55);
        view=findViewById(R.id.lot);
        imageView.animate().translationY(1400).setDuration(1000).setStartDelay(5000);
        view.animate().translationY(1400).setDuration(1000).setStartDelay(5000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(user==null)
                {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }else if(user!=null && user.isEmailVerified()) {
                    startActivity(new Intent(getApplicationContext(),Fragment_Activity.class));
                }else if(user!=null && !user.isEmailVerified()){

                    startActivity(new Intent(getApplicationContext(),MainActivity.class));

                }

            }
        },6000);





    }



}

