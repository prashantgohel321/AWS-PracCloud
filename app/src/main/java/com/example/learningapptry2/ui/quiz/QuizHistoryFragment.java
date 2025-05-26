package com.example.learningapptry2.ui.quiz;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.learningapptry2.DatabaseHelper;
import com.example.learningapptry2.R;

import java.util.ArrayList;
import java.util.List;

public class QuizHistoryFragment extends Fragment {

    TextView lbl_date, lbl_score;

    private LinearLayout historyLayout;
    private DatabaseHelper databaseHelper;


    public QuizHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        historyLayout = view.findViewById(R.id.historyLayout);
        databaseHelper = new DatabaseHelper(requireContext());

        // Get logged-in user ID (Replace with actual user ID if using login)
        int userId = 1;

        // Fetch and display quiz history
        List<String[]> historyData = getQuizHistory(userId);
        for (String[] record : historyData) {
            // Inflate history_card.xml dynamically
            View historyCard = LayoutInflater.from(requireContext()).inflate(R.layout.history_card, historyLayout, false);

            // Get references to the TextViews inside history_card.xml
            TextView lbl_date = historyCard.findViewById(R.id.lbl_date);
            TextView lbl_score = historyCard.findViewById(R.id.lbl_score);

            // Check if the record has exactly 2 values
            if (record.length == 2) {
                lbl_date.setText("Quiz Date: " + record[0].trim());   // ✅ Trim to remove unwanted spaces
                lbl_score.setText("Score: " + record[1].trim());
            }

            // Add the dynamically created history card to the historyLayout
            historyLayout.addView(historyCard);
        }
    }


    private List<String[]> getQuizHistory(int userId) {
        List<String[]> historyList = new ArrayList<>();
        Cursor cursor = databaseHelper.getQuizHistory(userId);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String date = cursor.getString(0);
                String score = String.valueOf(cursor.getInt(1));
                historyList.add(new String[]{date, score});
            }
            cursor.close();
        }
        return historyList;
    }
}