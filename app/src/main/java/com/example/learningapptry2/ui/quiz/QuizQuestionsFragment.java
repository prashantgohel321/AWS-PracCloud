package com.example.learningapptry2.ui.quiz;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.learningapptry2.R;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class QuizQuestionsFragment extends Fragment {

    private List<QuizQuestion> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private TextView questionText;
    private String selectedAnswer = null;
    private RadioGroup optionsGroup;
    private Button nextButton;

    public QuizQuestionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_questions, container, false);

        // Initialize views
        questionText = view.findViewById(R.id.questionText);
        optionsGroup = view.findViewById(R.id.optionsGroup);
        nextButton = view.findViewById(R.id.nextButton);

        // Load questions from JSON
        questionList = loadQuestionsFromJson(requireContext());

        // Show first question
        if (questionList != null && !questionList.isEmpty()) {
            showQuestion(currentQuestionIndex);
        }

        nextButton.setOnClickListener(v -> {
            if (selectedAnswer == null) {
                Toast.makeText(getContext(), "Please select an answer!", Toast.LENGTH_SHORT).show();
                return; // ✅ Prevent proceeding if no answer is selected
            }

            // ✅ Check if the selected answer is correct before updating score
            QuizQuestion currentQuestion = questionList.get(currentQuestionIndex);
            if (selectedAnswer.trim().equalsIgnoreCase(currentQuestion.getCorrectAnswer().trim())) {
                score++; // ✅ Increase score only if correct answer is selected
            }

            if (currentQuestionIndex < questionList.size() - 1) {
                currentQuestionIndex++;
                showQuestion(currentQuestionIndex);
            } else {
                // After completing all questions, show the score
                Bundle bundle = new Bundle();
                bundle.putInt("final_score", score);

                QuizResultFragment resultFragment = new QuizResultFragment();
                resultFragment.setArguments(bundle);

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, resultFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

    private List<QuizQuestion> loadQuestionsFromJson(Context context) {
        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.quiz_questions);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String json = new String(buffer, "UTF-8");

            Gson gson = new Gson();
            Type listType = new TypeToken<List<QuizQuestion>>() {}.getType();
            return gson.fromJson(json, listType);

        } catch (Exception e) {
            Log.e("Quiz", "Error loading JSON", e);
            return null;
        }
    }

    private void showQuestion(int index) {
        QuizQuestion currentQuestion = questionList.get(index);
        questionText.setText(currentQuestion.getQuestion());

        questionText.setGravity(View.TEXT_ALIGNMENT_CENTER);
        questionText.setTextSize(30);
        questionText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        // Clear previous options
        optionsGroup.removeAllViews();
        selectedAnswer = null; // ✅ Reset selected answer for new question

        for (String option : currentQuestion.getOptions()) {
            RadioButton radioButton = new RadioButton(requireContext());
            radioButton.setText(option);
            radioButton.setTextSize(20);
            radioButton.setPadding(5,5,5,25);
            optionsGroup.addView(radioButton);


            // ✅ Store selected answer when user clicks
            radioButton.setOnClickListener(v -> selectedAnswer = ((RadioButton) v).getText().toString());
        }
    }


}