package com.example.furnitures2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllAdapter extends RecyclerView.Adapter<AllAdapter.AllHolder> {
    private ArrayList<Categories> cat = new ArrayList<>();
    Context c;
    RvLisetner rvLisetner;
    boolean fav_btn_checker = true;
    String userId;
    FirebaseUser user;
    FirebaseAuth auth;
    boolean flag = false;
    DatabaseReference ref;
    String id;

    public void setCat(ArrayList<Categories> cat) {
        this.cat = cat;
        notifyDataSetChanged();
    }

    public AllAdapter(Context c) {

        this.c = c;
    }

    public AllAdapter(Context c, RvLisetner rvLisetner) {
        this.c = c;
        this.rvLisetner = rvLisetner;
    }

    @NonNull
    @Override
    public AllAdapter.AllHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userId = user.getUid();

        AllAdapter.AllHolder AllHolder = new AllAdapter.AllHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.all_item, parent, false));
        return AllHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull AllAdapter.AllHolder holder, int position) {
        Categories c = cat.get(position);
        holder.t1.setText(c.getName());
        holder.t2.setText(c.getPrice());
        Picasso.get().load(c.getImage()).into(holder.iv);
        if (c.isfav) {
            holder.checkBox.setChecked(true);


        }


    }

    @Override
    public int getItemCount() {
        return cat.size();
    }


    public class AllHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {
        ImageView iv;
        TextView t1, t2;
        CheckBox checkBox;

        public AllHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.all_iv);
            t1 = itemView.findViewById(R.id.all_tv);
            t2 = itemView.findViewById(R.id.all_tv1);
            checkBox = itemView.findViewById(R.id.all_cb);
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
            int p = getAdapterPosition();
            Categories categorieso = cat.get(p);


            if (isChecked) {
                ref.child(categorieso.id).setValue(categorieso);

            } else {
                ref.child(categorieso.id).setValue(null);


            }


        }
    }
}