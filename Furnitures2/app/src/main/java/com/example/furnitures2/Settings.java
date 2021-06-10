package com.example.furnitures2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Settings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Settings extends Fragment {
    String userId;
    EditText e1, e2, e3;
    TextView t1, t2, t3;
    AppCompatButton b;
    FirebaseUser user;
    FirebaseAuth auth;
    DatabaseReference ref;
    ProgressBar p;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Settings() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Settings.
     */
    // TODO: Rename and change types and number of parameters
    public static Settings newInstance(String param1, String param2) {
        Settings fragment = new Settings();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ref = FirebaseDatabase.getInstance().getReference("furnituresDb").child("Users");
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userId = user.getUid();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        e1 = v.findViewById(R.id.editTextTextPersonName);
        e2 = v.findViewById(R.id.editTextTextPersonName2);
        e3 = v.findViewById(R.id.editTextTextPersonName3);
        t1 = v.findViewById(R.id.textView7);
        b = v.findViewById(R.id.Logout);
        p = v.findViewById(R.id.progressBar3);

        ref.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User u = snapshot.getValue(User.class);
                if (u != null) {
                    String fullname = u.fullname;
                    String email = u.email;
                    String phone = u.phone;
                    String password = u.password;
                    disapleFields();
                    t1.setText(fullname);
                    e1.setText(email);
                    e2.setText(phone);
                    e3.setText(password);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "wrong", Toast.LENGTH_SHORT).show();

            }
        });

        return v;


    }


    public void disapleFields() {
        e1.setEnabled(false);
        e2.setEnabled(false);
        e3.setEnabled(false);

    }

    public void enableFields() {
        e1.setEnabled(true);
        e2.setEnabled(true);
        e3.setEnabled(true);

    }

    public void removeFields() {
        e1.setText("");
        e2.setText("");
        e3.setText("");
    }
        public void logout()
        {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeFields();
                    p.setVisibility(View.VISIBLE);
                    auth.getInstance().signOut();
                    startActivity(new Intent(getContext(), MainActivity.class));


                }
            });


        }

    @Override
    public void onStart() {
        super.onStart();
        logout();
    }
}
