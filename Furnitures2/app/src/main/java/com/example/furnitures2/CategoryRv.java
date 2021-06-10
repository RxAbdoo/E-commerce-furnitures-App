package com.example.furnitures2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.security.acl.LastOwnerException;
import java.util.ArrayList;

public class CategoryRv extends RecyclerView.Adapter<CategoryRv.Catholder> {
    private ArrayList<Categories> cat = new ArrayList<>();
    Context c;
    RvLisetner rvLisetner;
    boolean fav_btn_checker = true;
    String userId;
    FirebaseUser user;
    FirebaseAuth auth;
    boolean flag=false;
    DatabaseReference ref;
    String id;
    public void setCat(ArrayList<Categories> cat) {
        this.cat = cat;
        notifyDataSetChanged();
    }

    public CategoryRv(Context c) {

        this.c = c;
    }

    public CategoryRv(Context c, RvLisetner rvLisetner) {
        this.c = c;
        this.rvLisetner = rvLisetner;
    }

    @NonNull
    @Override
    public Catholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userId = user.getUid();

        Catholder catholder  =  new Catholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false));
        return catholder;

    }

    @Override
    public void onBindViewHolder(@NonNull Catholder holder, int position) {
        Categories c = cat.get(position);
     holder.t1.setText(c.getName());
        holder.t2.setText(c.getPrice());
        Picasso.get().load(c.getImage()).into(holder.iv);
        if(c.isfav)
        {
            holder.checkBox.setChecked(true);


        }








    }

    @Override
    public int getItemCount() {
        return cat.size();
    }


    public class Catholder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {
        ImageView iv;
        TextView t1,t2;
       CheckBox checkBox;

        public Catholder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_cat);
            t1 = itemView.findViewById(R.id.tv1_cat);
            t2 = itemView.findViewById(R.id.tv2_cat);
            checkBox = itemView.findViewById(R.id.ii);
       checkBox.setOnCheckedChangeListener(this::onCheckedChanged);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rvLisetner.OnitemClicked(getAdapterPosition());
                }
            });





        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            ref = FirebaseDatabase.getInstance().getReference("furnituresDb").child("Users").child(userId).child("Fav_products");
            int p =getAdapterPosition();
            Categories categorieso = cat.get(p);




            if (isChecked) {
                ref.child(categorieso.id).setValue(categorieso);

            }
            else {
                ref.child(categorieso.id).setValue(null);



            }


        }
    }
}
