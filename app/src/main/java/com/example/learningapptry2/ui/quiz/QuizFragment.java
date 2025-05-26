package com.example.learningapptry2.ui.quiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.learningapptry2.R;


public class QuizFragment extends Fragment {

    public QuizFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        // Find the Start Quiz button
        Button startQuizButton = view.findViewById(R.id.startQuizBtn);
        Button historyButton = view.findViewById(R.id.historyBtn);

        startQuizButton.setOnClickListener(v -> openQuizQuestionsFragment());
        historyButton.setOnClickListener(v -> openQuizHistoryFragment());

        return view;
    }

    private void openQuizQuestionsFragment() {
        // Create the new fragment instance
        QuizQuestionsFragment quizQuestionsFragment = new QuizQuestionsFragment();

        // Get Fragment Manager
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace current fragment with QuizQuestionsFragment
        fragmentTransaction.replace(R.id.fragment_container, quizQuestionsFragment);
        fragmentTransaction.addToBackStack(null);  // Allows back navigation
        fragmentTransaction.commit();
    }

    private void openQuizHistoryFragment() { // ✅ New method to open history
        QuizHistoryFragment quizHistoryFragment = new QuizHistoryFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, quizHistoryFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}