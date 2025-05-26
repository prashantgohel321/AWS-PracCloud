package com.example.learningapptry2.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.learningapptry2.CardOneModules;
import com.example.learningapptry2.HomePage;
import com.example.learningapptry2.R;
import com.example.learningapptry2.ServicesOverviewFragment;
import com.example.learningapptry2.ui.first_card.FirstCardFragment;


public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    CardView card_one, card_two;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        card_one = view.findViewById(R.id.card_one);
        card_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.my_drawer, new FirstCardFragment()).addToBackStack(null).commit();
            }
        });

        card_two = view.findViewById(R.id.card_two);
        card_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.my_drawer, new ServicesOverviewFragment()).addToBackStack(null).commit();
            }
        });
        return view;
    }
}