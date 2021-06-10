package com.example.furnitures2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.view.LayoutInflater.from;

public class MainActivity extends AppCompatActivity {
    public static String verifiy_key="verifi_key";
    TextView t, t1;
    TextInputLayout e1, e2;
    Button b1, b2, b3, b4;
    private long back;
    FirebaseAuth auth;
    FirebaseUser user;
    private ProgressBar pb1;
    final static int verifiy=1;
    final static int noTverifiy=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        t = findViewById(R.id.Signup_tv1);
        e1 = findViewById(R.id.email_et);
        e2 = findViewById(R.id.pa_et);
        b4 = findViewById(R.id.Login);
        t1 = findViewById(R.id.textView5);
        pb1 = findViewById(R.id.progressBar2);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if( !validation_email() | !validation_password() )
               {
                   return;
               }
               else
               {
                   Login();

               }

            }
        });
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUp.class));
            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),
                        MainActivity2.class));
            }
        });

        auth= FirebaseAuth.getInstance();
        user= auth.getCurrentUser();

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Application?");
        alertDialogBuilder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    public void Login() {
        String email = e1.getEditText().getText().toString();
        String password = e2.getEditText().getText().toString().trim();
        pb1.setVisibility(View.VISIBLE);
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    pb1.setVisibility(View.GONE);
                    verfiy();



                }else {Toast.makeText(MainActivity.this, task.getException()+"", Toast.LENGTH_SHORT).show();
                    pb1.setVisibility(View.GONE);
                }
            }
        });
    }

    public boolean validation_password() {
        String password = e2.getEditText().getText().toString().trim();

        if (password.isEmpty()) {
            e2.setError("Field cant be empty");
            e2.requestFocus();
            return false;

        } else if (password.length() < 8) {
            e2.setError("password must be at least 8 charachters");
            e2.requestFocus();
            return false;

        } else {
            e2.setError(null);
            return true;

        }
    }

    public boolean validation_email() {
        String email = e1.getEditText().getText().toString().trim();
        if (email.isEmpty()) {
            e1.setError("Field cant be empty");
            e1.requestFocus();
            return false;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            e1.setError("Enter Valid Email");
            e1.requestFocus();
            return false;
        } else {
            e1.setError(null);
            return true;

        }
    }

    public void verfiy() {
        user=auth.getCurrentUser();
        if(user.isEmailVerified())
        {        startActivity(new Intent(getApplicationContext(),Fragment_Activity.class));
            pb1.setVisibility(View.GONE);
        }
        else
        {
         getdialog();
            pb1.setVisibility(View.GONE);
        }

    }
    public void getdialog() {
        androidx.appcompat.app.AlertDialog alertDialog;
        alertDialog = new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this).create();
        View view = from(getApplicationContext()).inflate(R.layout.verifi2_dialog, null);
        ImageView imageView;
        AppCompatButton b;
        b=view.findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifiy2();
            }


        });
        imageView = view.findViewById(R.id.imageView855);
        alertDialog.setView(view);
        alertDialog.show();


    }
    public void verifiy2()
    {
         user =auth.getCurrentUser();
        user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MainActivity.this, "verfication mail sent", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "verfiy email not sent", Toast.LENGTH_SHORT).show();
            }
        });




    }

    }
