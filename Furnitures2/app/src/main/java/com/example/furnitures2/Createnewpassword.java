package com.example.furnitures2;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import static android.view.LayoutInflater.from;

public class Createnewpassword extends AppCompatActivity {
  EditText e;
  Button b;
  FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createnewpassword);
        e = findViewById(R.id.reset_password_et);
        b = findViewById(R.id.Reset_pass_btn);
        auth = FirebaseAuth.getInstance();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validation_email())
                {
                    return;
                }
                else
                {
                    resetPassword();
                }
            }
        });

    }
    public void resetPassword()
     {



        String email = e.getText().toString().trim();
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    getdialog();

                }
                else
                {
                    Toast.makeText(Createnewpassword.this, task.getException()+"", Toast.LENGTH_SHORT).show();

                }

            }
        });



        
    }
    public boolean validation_email() {
        String email = e.getText().toString().trim();
        if (email.isEmpty()) {
            e.setError("Field cant be empty");
            e.requestFocus();
            return false;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            e.setError("Enter Valid Email");
            e.requestFocus();
            return false;
        } else {
            e.setError(null);
            return true;

        }
    }
    public void getdialog() {
        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(Createnewpassword.this).create();
        View view = from(getApplicationContext()).inflate(R.layout.password_dialog, null);
        ImageView  i;
        i=view.findViewById(R.id.imageView85_re);
        alertDialog.setView(view);
        alertDialog.show();


    }
}