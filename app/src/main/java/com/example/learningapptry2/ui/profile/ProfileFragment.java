package com.example.learningapptry2.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learningapptry2.DatabaseHelper;
import com.example.learningapptry2.LoginPage;
import com.example.learningapptry2.R;

public class ProfileFragment extends Fragment {

    private TextView tvFullName;
    private Button btnLogout;
    private DatabaseHelper dbHelper;



    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize UI Components
        tvFullName = view.findViewById(R.id.tvFullName);
        btnLogout = view.findViewById(R.id.btnLogout);

        // Initialize Database
        dbHelper = new DatabaseHelper(requireContext());

        // Fetch and Set User's Name
        loadUserName();

        // Logout Button Click
        btnLogout.setOnClickListener(v -> logoutUser());

        return view;
    }

    private void loadUserName() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", ""); // Fetch username from SharedPreferences

        Cursor cursor = db.rawQuery("SELECT full_name FROM users WHERE username = ?", new String[]{username});

        if (cursor.moveToFirst()) {
            String fullName = cursor.getString(0);
            tvFullName.setText("Hey, " + fullName);
        } else {
            tvFullName.setText("User not found");
        }
        cursor.close();
        db.close();
    }

    private void logoutUser() {
        // Clear SharedPreferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        // Navigate to Login Activity
        Intent intent = new Intent(requireContext(), LoginPage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

        Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.amz_white));
    }
}