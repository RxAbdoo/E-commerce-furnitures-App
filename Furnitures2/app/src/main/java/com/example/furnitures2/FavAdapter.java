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

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.FavHolder> {
    private ArrayList<Categories> cat = new ArrayList<>();
    Context c;

    public void setCat(ArrayList<Categories> cat) {
        this.cat = cat;
        notifyDataSetChanged();
    }

    public FavAdapter(Context c) {

        this.c = c;
    }


    @NonNull
    @Override
    public FavAdapter.FavHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        FavAdapter.FavHolder FavHolder = new FavAdapter.FavHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_item, parent, false));
        return FavHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull FavAdapter.FavHolder holder, int position) {
        Categories c = cat.get(position);
        holder.t1.setText(c.getName());
        holder.t2.setText(c.getPrice());
        Picasso.get().load(c.getImage()).into(holder.iv);

    }

    @Override
    public int getItemCount() {
        return cat.size();
    }


    public class FavHolder extends RecyclerView.ViewHolder {
        ImageView iv, ii;
        TextView t1, t2;


        public FavHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_fav);
            t1 = itemView.findViewById(R.id.tv1_fav);
            t2 = itemView.findViewById(R.id.tv2_fav);
            ii = itemView.findViewById(R.id.ii_fav);


        }
    }

}
