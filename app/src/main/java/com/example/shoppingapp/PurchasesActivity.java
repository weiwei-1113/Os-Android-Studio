package com.example.shoppingapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class PurchasesActivity extends AppCompatActivity {

    ListView lv;
    PurchasesAdapter pa;
    ShoppingDatabase db;

    ArrayList<Products> p = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchases);

        lv = findViewById(R.id.lv_purchases);
        Button button = findViewById(R.id.button);

        db = new ShoppingDatabase(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("purchases");

        p = db.getAllProductsInPurchases();
        pa = new PurchasesAdapter(p,this);
        pa.notifyDataSetChanged();
        lv.setAdapter(pa);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showClearConfirmationDialog();
            }
        });
    }
    private void showClearConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PurchasesActivity.this);
        builder.setTitle("確認購買購物車中所有商品");
        builder.setMessage("購買後將清除購物車中的所有商品");
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clearPurchases();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void clearPurchases() {
        p.clear();
        pa.notifyDataSetChanged();
        db.deleteAllProductsInPurchases(); // 清除資料庫中的商品
    }
}