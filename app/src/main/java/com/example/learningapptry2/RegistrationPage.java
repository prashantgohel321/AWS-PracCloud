package com.example.learningapptry2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class RegistrationPage extends AppCompatActivity {
    EditText fullName, email, phone, username, password, confirmPassword;
    Button signUp;
    TextView ref_signIn;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        databaseHelper = new DatabaseHelper(this);

        fullName = findViewById(R.id.full_nm);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone_no);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.cnf_password);
        signUp = findViewById(R.id.signUp);
        ref_signIn = findViewById(R.id.ref_signIn);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fullName.getText().toString();
                String mail = email.getText().toString();
                String phoneNo = phone.getText().toString();
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String confirmPass = confirmPassword.getText().toString();
                if (name.isEmpty() || mail.isEmpty() || phoneNo.isEmpty() || user.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
                    Toast.makeText(RegistrationPage.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else if (!pass.equals(confirmPass)) {
                    Toast.makeText(RegistrationPage.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isInserted = databaseHelper.registerUser(name, mail, phoneNo, user, pass);
                    if (isInserted) {
                        Toast.makeText(RegistrationPage.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegistrationPage.this, LoginPage.class));
                        finish();
                    } else {
                        Toast.makeText(RegistrationPage.this, "User already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        ref_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationPage.this, LoginPage.class));
                finish();
            }
        });


    }
}