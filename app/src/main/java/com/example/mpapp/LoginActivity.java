package com.example.mpapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log; // Import Log class
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mpapp.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private FirebaseAuth auth;

    // It's good practice to define a TAG for logging
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        binding.btnLogin.setOnClickListener(v -> {
            String email = binding.etEmail.getText().toString().trim();
            String password = binding.etPassword.getText().toString().trim();

            // Print email and password to Logcat
            Log.d(TAG, "Email entered: " + email);
            // Be cautious about logging passwords, even in debug builds.
            // Consider logging only if it's a debug build or using a placeholder for the password.
            Log.d(TAG, "Password entered: " + password);


            if (!email.isEmpty() && !password.isEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                                // Go to Home screen -  Make sure this is the intended navigation
                                startActivity(new Intent(this, RegisterActivity.class));
                                finish();
                            } else {
                                Toast.makeText(this, "Login Failed: " +
                                        task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                // Log the error for debugging
                                Log.e(TAG, "Login Failed: ", task.getException());
                            }
                        });
            } else {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            }
        });

        // Forgot Password button buttonResetPassword
        binding.tvSignup.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class)));

        binding.tvForgotPassword.setOnClickListener(v ->
                startActivity(new Intent(this, ForgotPasswordActivity.class)));
    }
}
