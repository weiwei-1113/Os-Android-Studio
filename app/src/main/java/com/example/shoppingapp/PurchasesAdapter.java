package com.example.shoppingapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PurchasesAdapter extends BaseAdapter {

    ArrayList<Products> purchases;
    Context context;

    public PurchasesAdapter(ArrayList<Products> purchases, Context context) {
        this.purchases = purchases;
        this.context = context;
    }

    @Override
    public int getCount() {
        return purchases.size();
    }

    @Override
    public Products getItem(int i) {
        return purchases.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private void decreaseQuantity(int position) {
        Products product = getItem(position);
        int quantity = product.getQuantity();
        if (quantity > 1) {
            product.setQuantity(quantity - 1);
            notifyDataSetChanged();
            updateDatabase(product);
        }
        else if (quantity == 1) {
            removeProduct(position);
        }

    }

    private void removeProduct(int position) {
        Products product = getItem(position);
        purchases.remove(position);
        notifyDataSetChanged();
        deleteFromDatabase(product);
        Toast.makeText(context, product.getName() + " removed from cart", Toast.LENGTH_SHORT).show();
    }

    private void deleteFromDatabase(Products product) {
        ShoppingDatabase db = new ShoppingDatabase(context);
        db.deleteProductFromPurchases(product);
    }

    private void increaseQuantity(int position) {
        Products product = getItem(position);
        product.setQuantity(product.getQuantity() + 1);
        notifyDataSetChanged();
        updateDatabase(product);
    }

    private void updateDatabase(Products product) {
        ShoppingDatabase db = new ShoppingDatabase(context);
        db.updateProductQuantityInPurchases(product);
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = view;
        if(v==null){
            v = LayoutInflater.from(context).inflate(R.layout.custome_purchases_products,null,false);
        }

        ImageView img = (ImageView) v.findViewById(R.id.img_products_purchases);
        TextView tv_name = v.findViewById(R.id.tv_name_purchases);
        TextView tv_price = v.findViewById(R.id.tv_price_purchases);
        TextView tv_brand = v.findViewById(R.id.tv_brand_purchases);
        RatingBar rating = v.findViewById(R.id.rating_purchases);
        TextView tv_quantity = v.findViewById(R.id.tv_quantity);
        Button btnIncrease = v.findViewById(R.id.btn_increase);
        Button btnDecrease = v.findViewById(R.id.btn_decrease);

        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseQuantity(i);
            }
        });

        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseQuantity(i);
            }
        });

        Products p = getItem(i);
        if(p.getImage() != 0){
            img.setImageResource(p.getImage());
        }else{
            img.setImageResource(R.drawable.products);
        }
        tv_name.setText(p.getName());
        tv_price.setText(p.getPrice()+"$");
        tv_brand.setText(p.getBrand());
        rating.setRating(p.getRating());
        tv_quantity.setText(p.getQuantity()+"");

        return v;
    }
}
