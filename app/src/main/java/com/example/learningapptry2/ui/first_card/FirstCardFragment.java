package com.example.learningapptry2.ui.first_card;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.learningapptry2.R;
import com.example.learningapptry2.TopicActivity;


public class FirstCardFragment extends Fragment implements View.OnClickListener{

    CardView module01, module02, module03, module04, module05, module06, module07, module08, module09, module10, module11, finalAssessment;

    public FirstCardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first_card, container, false);

        module01 = view.findViewById(R.id.module01);
        module02 = view.findViewById(R.id.module02);
        module03 = view.findViewById(R.id.module03);
        module04 = view.findViewById(R.id.module04);
        module05 = view.findViewById(R.id.module05);
        module06 = view.findViewById(R.id.module06);
        module07 = view.findViewById(R.id.module07);
        module08 = view.findViewById(R.id.module08);
        module09 = view.findViewById(R.id.module09);
        module10 = view.findViewById(R.id.module10);
        module11 = view.findViewById(R.id.module11);
        finalAssessment = view.findViewById(R.id.finalAssessment);

        module01.setOnClickListener(this);
        module02.setOnClickListener(this);
        module03.setOnClickListener(this);
        module04.setOnClickListener(this);
        module05.setOnClickListener(this);
        module06.setOnClickListener(this);
        module07.setOnClickListener(this);
        module08.setOnClickListener(this);
        module09.setOnClickListener(this);
        module10.setOnClickListener(this);
        module11.setOnClickListener(this);
        finalAssessment.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getContext(), TopicActivity.class);

        if(v.getId() == R.id.module01){
            intent.putExtra("chapterName", "Module 01");
            startActivity(intent);
        } else if (v.getId() == R.id.module02) {
            intent.putExtra("chapterName", "Module 02");
            startActivity(intent);
        }else if (v.getId() == R.id.module03) {
            intent.putExtra("chapterName", "Module 03");
            startActivity(intent);
        }else if (v.getId() == R.id.module04) {
            intent.putExtra("chapterName", "Module 04");
            startActivity(intent);
        }else if (v.getId() == R.id.module05) {
            intent.putExtra("chapterName", "Module 05");
            startActivity(intent);
        }else if (v.getId() == R.id.module06) {
            intent.putExtra("chapterName", "Module 06");
            startActivity(intent);
        }else if (v.getId() == R.id.module07) {
            intent.putExtra("chapterName", "Module 07");
            startActivity(intent);
        }else if (v.getId() == R.id.module08) {
            intent.putExtra("chapterName", "Module 08");
            startActivity(intent);
        }else if (v.getId() == R.id.module09) {
            intent.putExtra("chapterName", "Module 09");
            startActivity(intent);
        }else if (v.getId() == R.id.module10) {
            intent.putExtra("chapterName", "Module 10");
            startActivity(intent);
        }else if (v.getId() == R.id.module11) {
            intent.putExtra("chapterName", "Module 11");
            startActivity(intent);
        }else if (v.getId() == R.id.finalAssessment) {
            intent.putExtra("chapterName", "Final Assessment");
            startActivity(intent);
        }
    }
}