package com.example.furnitures2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Sofa_f#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class Sofa_f extends Fragment {
    RecyclerView r;
    FirebaseDatabase fb;
    String userId;
    FirebaseUser user;
    FirebaseAuth auth;
    DatabaseReference ref,favref;
    ArrayList<Categories> ca;
    ArrayList<Categories> favList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Sofa_f.
     */
    // TODO: Rename and change types and number of parameters
    public static Sofa_f newInstance(String param1, String param2) {
        Sofa_f fragment = new Sofa_f();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Sofa_f() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v;
       v = inflater.inflate(R.layout.fragment_sofa_f, container, false);
        r = v.findViewById(R.id.sofa_rv);
        fb = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userId = user.getUid();
        ref = fb.getReference("furnituresDb").child("ALl_Categories").child("Sofas");
        favref = FirebaseDatabase.getInstance().getReference("furnituresDb").child("Users").child(userId).child("Fav_products");
        ca = new ArrayList<>();
        favList = new ArrayList<>();
        return v;
    }
 @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
     fetchFavProducts();
     getSofa();





 }

  public void fetchFavProducts()
  {
      favref.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot snapshot) {
              if(snapshot.exists()) {
                  for (DataSnapshot no : snapshot.getChildren()) {
                      String id = no.child("id").getValue(String.class);
                      String name = no.child("name").getValue(String.class);
                      String image = no.child("image").getValue(String.class);
                      String price = no.child("price").getValue(String.class);
                      Categories c =new Categories(id,name,image,price);
                      favList.add(c);

                  }
              }


          }

          @Override
          public void onCancelled(@NonNull DatabaseError error) {

          }
      });

  }
  public void getSofa()
  {
      ref.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot snapshot) {
              ca.clear();
              for (DataSnapshot no : snapshot.getChildren()) {
                  String id = no.child("id").getValue(String.class);
                  String name = no.child("name").getValue(String.class);
                  String image = no.child("image").getValue(String.class);
                  String price = no.child("price").getValue(String.class);
                  Categories sofa =new Categories(id,name,image,price);

                  if(isFav(sofa))
                  {
                      sofa.isfav=true;

                  }
                  ca.add(sofa);

              }

              CategoryRv categoryRv = new CategoryRv(getContext(), new RvLisetner() {
                  @Override
                  public void OnitemClicked(int position) {
                      Intent i = new Intent(getContext(),Product_detal.class);
                      Categories c = ca.get(position);
                      String name = c.getName();
                      String image = c.getImage();
                      String price = c.getPrice();
                      i.putExtra("name_key",name);
                      i.putExtra("image_key",image);
                      i.putExtra("price_key",price);
                      i.putExtra("category_key","Sofas");
                      startActivity(i);





                  }


              });

              categoryRv.setCat(ca);
              r.setAdapter(categoryRv);
              r.setHasFixedSize(true);
              r.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
          }

          @Override
          public void onCancelled(@NonNull DatabaseError error) {

          }
      });

  }


    private boolean isFav(Categories categories)
    { for(Categories categories1: favList)
    {
        if(categories1.id.equals(categories.id))
        {
            return  true;
        }

    }
        return false;
    }
}