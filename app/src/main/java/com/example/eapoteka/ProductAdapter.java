package com.example.eapoteka;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final List<Product> productList;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.image.setImageResource(product.getImageResId());
        holder.title.setText(product.getTitle() != null ? product.getTitle() : "N/A");
        holder.description.setText(product.getDescription() != null ? product.getDescription() : "Nema dostupnog opisa");

        if (product.getPrice() != null) {
            holder.price.setText(String.format("$%.2f", product.getPrice()));
        } else {
            holder.price.setText("Cijena nije dostupna");
        }

        holder.addButton.setOnClickListener(v -> {
            CartManager.getInstance().addToCart(product);
            Toast.makeText(holder.itemView.getContext(), "Dodano u korpu", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, description, price;
        Button addButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.productImage);
            title = itemView.findViewById(R.id.productTitle);
            description = itemView.findViewById(R.id.productDescription);
            price = itemView.findViewById(R.id.productPrice);
            addButton = itemView.findViewById(R.id.addButton);
        }
    }
}
