package com.example.learningapptry2.ui.quiz;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.learningapptry2.DatabaseHelper;
import com.example.learningapptry2.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class QuizResultFragment extends Fragment {

    private DatabaseHelper databaseHelper;

    public QuizResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView scoreTextView = view.findViewById(R.id.scoreTextView);
        TextView messageTextView = view.findViewById(R.id.messageTextView);

        databaseHelper = new DatabaseHelper(requireContext());

        // ✅ Add null safety check
        int score = (getArguments() != null) ? getArguments().getInt("final_score", 0) : 0;

        // ✅ Display final score
        scoreTextView.setText("Your Score: " + score + "/10");

        // ✅ Set motivational message based on score
        if (score == 10) {
            messageTextView.setText("Excellent! You are an AWS expert! 🎉");
        } else if (score >= 7) {
            messageTextView.setText("Great job! Keep learning AWS. 🚀");
        } else if (score >= 5) {
            messageTextView.setText("Good effort! Try again for a better score. 👍");
        } else {
            messageTextView.setText("Keep practicing! You can do better. 💪");
        }

        insertQuizHistory(score);
    }

    private void insertQuizHistory(int score) {
        int userId = 1; // Replace this with the actual logged-in user ID
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        boolean isInserted = databaseHelper.insertQuizHistory(userId, date, score);

        if (isInserted) {
            System.out.println("✅ Quiz history inserted: User ID=" + userId + ", Date=" + date + ", Score=" + score);
        } else {
            System.out.println("❌ Failed to insert quiz history.");
        }
    }
}