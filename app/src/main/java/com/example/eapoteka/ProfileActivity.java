package com.example.eapoteka;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    private TextView usernameTextView;
    private EditText emailEditText, passwordEditText;
    private Button btnUpdateProfile;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Inicijalizacija Firebase
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        usernameTextView = findViewById(R.id.usernameTextView);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        btnUpdateProfile = findViewById(R.id.btnUpdateProfile);

        if (currentUser != null) {
            usernameTextView.setText("Korisnik: " + (currentUser.getDisplayName() != null ? currentUser.getDisplayName() : "Nepoznat"));
            emailEditText.setText(currentUser.getEmail());
        } else {
            Toast.makeText(this, "Greška pri učitavanju korisnika", Toast.LENGTH_SHORT).show();
            finish();
        }

        btnUpdateProfile.setOnClickListener(v -> {
            String newEmail = emailEditText.getText().toString().trim();
            String newPassword = passwordEditText.getText().toString().trim();

            if (!Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()) {
                emailEditText.setError("Neispravan email format");
                emailEditText.requestFocus();
                return;
            }

            if (!newPassword.isEmpty() && newPassword.length() < 6) {
                passwordEditText.setError("Lozinka mora imati najmanje 6 karaktera");
                passwordEditText.requestFocus();
                return;
            }

            updateProfile(newEmail, newPassword);
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.nav_profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::handleNavigation);
    }

    private void updateProfile(String email, String password) {
        if (currentUser != null) {
            if (!email.equals(currentUser.getEmail())) {
                currentUser.updateEmail(email)
                        .addOnSuccessListener(aVoid -> Toast.makeText(this, "Email ažuriran", Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e -> Toast.makeText(this, "Greška: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }

            if (!password.isEmpty()) {
                currentUser.updatePassword(password)
                        .addOnSuccessListener(aVoid -> Toast.makeText(this, "Lozinka ažurirana", Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e -> Toast.makeText(this, "Greška: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }
        }
    }

    private boolean handleNavigation(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            startActivity(new Intent(this, MainMenuActivity.class));
            finish();
            return true;
        } else if (id == R.id.nav_cart) {
            startActivity(new Intent(this, CartActivity.class));
            finish();
            return true;
        } else if (id == R.id.nav_profile) {
            return true;
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            finish();
            return true;
        } else if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        return false;
    }
}
