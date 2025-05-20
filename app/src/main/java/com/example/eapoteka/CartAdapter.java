package com.example.eapoteka;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<Product> cartItems;

    public CartAdapter(List<Product> cartItems) {
        this.cartItems = cartItems;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, price;
        ImageView image;
        Button removeButton;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.productTitle);
            description = view.findViewById(R.id.productDescription);
            price = view.findViewById(R.id.productPrice);
            image = view.findViewById(R.id.productImage);
            removeButton = view.findViewById(R.id.addButton);
        }
    }

    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartAdapter.ViewHolder holder, int position) {
        Product product = cartItems.get(position);

        holder.title.setText(product.getTitle());
        holder.description.setText(product.getDescription());
        holder.price.setText(String.valueOf(product.getPrice()));
        holder.image.setImageResource(product.getImageResId());

        holder.removeButton.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }
}
