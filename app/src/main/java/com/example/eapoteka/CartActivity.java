package com.example.eapoteka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private Button btnOrder, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recyclerViewCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Product> cartItems = CartManager.getInstance().getCartItems();
        cartAdapter = new CartAdapter(cartItems);
        recyclerView.setAdapter(cartAdapter);

        btnOrder = findViewById(R.id.btnOrder);
        btnClear = findViewById(R.id.btnClear);

        btnOrder.setOnClickListener(v -> {
            List<Product> items = CartManager.getInstance().getCartItems();
            if (!items.isEmpty()) {
                double total = 0;
                for (Product p : items) {
                    total += p.getPrice();
                }

                Toast.makeText(this, "Završena je narudžba", Toast.LENGTH_SHORT).show();
                CartManager.getInstance().clearCart();
                cartAdapter.notifyDataSetChanged();

                startActivity(new Intent(CartActivity.this, MainMenuActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Korpa je prazna", Toast.LENGTH_SHORT).show();
            }
        });

        btnClear.setOnClickListener(v -> {
            CartManager.getInstance().clearCart();
            cartAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Vaša narudžba je obrisana", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(CartActivity.this, MainMenuActivity.class));
            finish();
        });

        // Bottom navigation
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setSelectedItemId(R.id.nav_cart);
        bottomNav.setOnItemSelectedListener(this::handleNavigation);
    }

    private boolean handleNavigation(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            startActivity(new Intent(this, MainMenuActivity.class));
            finish();
            return true;
        } else if (id == R.id.nav_cart) {
            return true;
        } else if (id == R.id.nav_profile) {
            startActivity(new Intent(this, ProfileActivity.class));
            finish();
            return true;
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            finish();
            return true;
        } else if (id == R.id.nav_logout) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        return false;
    }
}