package com.example.furnitures2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tet#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tet extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "key_name";

    // TODO: Rename and change types of parameters
    private String mParam1;

    public Tet() {
        // Required empty public constructor
    }

    public static Tet newInstance(String param1) {
        Tet fragment = new Tet();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView t;
       View view;
    view =inflater.inflate(R.layout.fragment_tet, container, false);
    t = view.findViewById(R.id.textView100);
    t.setText(mParam1);
    return view;
    }
}