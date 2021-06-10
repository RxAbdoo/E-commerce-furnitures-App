package com.example.furnitures2;

import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.view.LayoutInflater.from;

public class SignUp extends AppCompatActivity {
    TextView t,tt;
    TextInputLayout t1, t2, t3, t4;
    AppCompatButton b;
    ProgressBar pb,pb1;
    private FirebaseAuth mAuth;
    FirebaseDatabase fb;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        fb=FirebaseDatabase.getInstance();
        ref = fb.getReference("furnituresDb");
        mAuth= FirebaseAuth.getInstance();
        t = findViewById(R.id.Signin_tv);
        t1 = findViewById(R.id.FullName);
        t2 = findViewById(R.id.phone);
        t3 = findViewById(R.id.email_et1);
        t4 = findViewById(R.id.pa_et1);
        b = findViewById(R.id.SignUp);
        pb = findViewById(R.id.progressBar4);


        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validation_email() | !validation_name() | !validation_password()){
                    return;
                }
                 else
                {
                    register();





                }
            }
        });


    }

    public  void register()
    {
        String fullname = t1.getEditText().getText().toString().trim();
        String phone = t2.getEditText().getText().toString().trim();
        String email = t3.getEditText().getText().toString();
        String password = t4.getEditText().getText().toString().trim();
        pb.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    User user= new User(fullname,email,password,phone);
                   ref.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(SignUp.this, "Successful", Toast.LENGTH_SHORT).show();
                                pb.setVisibility(View.GONE);
                                verifiy();
                                getdialog();

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                            startActivity(new Intent(getApplicationContext(),MainActivity.class));


                                    }
                                },3000);



                            }
                            else
                            { Toast.makeText(SignUp.this, task.getException()+"", Toast.LENGTH_SHORT).show();
                                pb.setVisibility(View.GONE);

                            }

                        }
                    });

                }
                else
                {
                    Toast.makeText(SignUp.this, task.getException()+""+"Faild", Toast.LENGTH_SHORT).show();
                    pb.setVisibility(View.GONE);


                }

            }
        });



    }
    public void verifiy()
    {
        FirebaseUser user =mAuth.getCurrentUser();
        user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
           getdialog();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignUp.this, "verfiy email not  sent", Toast.LENGTH_SHORT).show();
            }
        });




    }
    public void getdialog() {
        androidx.appcompat.app.AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(SignUp.this).create();
        View view = from(getApplicationContext()).inflate(R.layout.dialog_vierifiy, null);
        ImageView imageView;
        imageView = view.findViewById(R.id.imageView85);
        alertDialog.setView(view);
        alertDialog.show();


    }


    public boolean validation_email() {
        String email = t3.getEditText().getText().toString().trim();
        if (email.isEmpty()) {
            t3.setError("Field cant be empty");
            t3.requestFocus();
            return false;

        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            t3.setError("Enter Valid Email");
            t3.requestFocus();
            return false;
        } else {
            t3.setError(null);
            return true;

        }
    }

    public boolean validation_name() {
        String fullname = t1.getEditText().getText().toString().trim();
        if (fullname.isEmpty()) {
            t1.setError("Field cant be empty");
            t1.requestFocus();
            return false;

        } else if(fullname.length()>20)
        {  t1.setError("fullName to long");
            t1.requestFocus();
            return false;

        }
        else
        {
            t1.setError(null);
            return true;

        }
    }

    public boolean validation_password() {
        String password = t4.getEditText().getText().toString().trim();

        if (password.isEmpty()) {
            t4.setError("Field cant be empty");
            t4.requestFocus();
            return false;

        }
        else if(password.length()<8)
        {  t4.setError("password must be at least 8 charachters");
            t4.requestFocus();
            return false;

        }
        else
        {
            t4.setError(null);
            return true;

        }
    }


    }

