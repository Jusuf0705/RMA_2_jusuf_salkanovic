package com.example.eapoteka;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainMenuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private List<Product> filteredList;
    private EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        recyclerView = findViewById(R.id.recyclerView);
        searchEditText = findViewById(R.id.searchEditText);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();
        productList.add(new Product(3.5, "Aspirin", "Tablete 500mg", R.drawable.aspirin));
        productList.add(new Product(7.2, "Vitamin C", "Šumeće tablete", R.drawable.vitaminc));
        productList.add(new Product(5.0, "Paracetamol", "Protiv bolova i temperature", R.drawable.paracetamol));
        productList.add(new Product(4.5, "Ibuprofen", "Protiv upale i bolova", R.drawable.ibuprofen));
        productList.add(new Product(6.9, "Brufen", "Protiv glavobolje i zubobolje", R.drawable.brufen));
        productList.add(new Product(9.0, "Andol", "Protiv krvnih ugrušaka", R.drawable.andol));
        productList.add(new Product(5.5, "Nimulid", "Nesteroidni antiinflamatorni lijek", R.drawable.nimulid));
        productList.add(new Product(4.2, "Panklav", "Antibiotik širokog spektra", R.drawable.panklav));
        productList.add(new Product(6.8, "Hemomycin", "Antibiotik za respiratorne infekcije", R.drawable.hemomycin));
        productList.add(new Product(7.9, "Sumamed", "Antibiotik za ENT infekcije", R.drawable.sumamed));
        productList.add(new Product(10.5, "Amoksicilin", "Antibiotik za razne infekcije", R.drawable.amoksicilin));
        productList.add(new Product(4.7, "Caffetin", "Protiv migrene i bolova", R.drawable.caffetin));
        productList.add(new Product(3.9, "Panadol", "Protiv temperature i bolova", R.drawable.panadol));
        productList.add(new Product(5.3, "Voltaren", "Tablete za upale", R.drawable.voltaren));
        productList.add(new Product(6.6, "Neofen", "Brzo djelujući analgetik", R.drawable.neofen));
        productList.add(new Product(2.5, "Lekadol", "Protiv blage boli", R.drawable.lekadol));
        productList.add(new Product(7.1, "Diklofen", "Ublažava bolove u zglobovima", R.drawable.diklofen));
        productList.add(new Product(9.3, "Ranisan", "Za žgaravicu i kiseline", R.drawable.ranisan));
        productList.add(new Product(3.0, "Buscopan", "Protiv grčeva", R.drawable.buscopan));
        productList.add(new Product(6.0, "Belodin", "Protiv alergija", R.drawable.belodin));
        productList.add(new Product(4.9, "Loratadin", "Antihistaminik", R.drawable.loratadin));
        productList.add(new Product(5.8, "Cetirizin", "Ublažava simptome alergije", R.drawable.cetirizin));
        productList.add(new Product(8.0, "Flonidan", "Protiv alergijskog rinitisa", R.drawable.flonidan));
        productList.add(new Product(5.6, "Zyrtec", "Alergije i osipi", R.drawable.zyrtec));

        filteredList = new ArrayList<>(productList);
        productAdapter = new ProductAdapter(filteredList);
        recyclerView.setAdapter(productAdapter);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterProducts(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::handleNavigation);
    }

    private void filterProducts(String text) {
        filteredList.clear();
        for (Product product : productList) {
            String title = product.getTitle();
            if (title != null && title.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(product);
            }
        }
        productAdapter.notifyDataSetChanged();
    }

    private boolean handleNavigation(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            return true;
        } else if (id == R.id.nav_cart) {
            startActivity(new Intent(this, CartActivity.class));
            return true;
        } else if (id == R.id.nav_profile) {
            startActivity(new Intent(this, ProfileActivity.class));
            return true;
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
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
