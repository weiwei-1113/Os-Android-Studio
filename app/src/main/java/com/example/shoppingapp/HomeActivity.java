package com.example.shoppingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bnv;
    RecyclerView rv;
    public static final String PRODUCT_KEY = "product_key";
    private static final int PERMISSION_REQ_COD = 1;
    public static final String TABLE_NAME_KEY = "table_key";
    SharedPreferences shp;
    SharedPreferences.Editor shpEditor;
    SharedPreferences shp_id;
    SharedPreferences.Editor shpEditor_id;
    public static boolean flag;
    ShoppingDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        flag = false;
        db = new ShoppingDatabase(this);
        // int image, String name, double price, String brand, int pieces, String description, double discount, float rating
        // Game Products (TB_GAME)
        Products p = new Products(R.drawable.playstation4, "playstation4", 2500, "sony", 25, "computer noc Barnes & Noble Retail and Samsung Electronics launched the new Samsung Galaxy Tab 4 Noc tablet in New York.", 10, (float) 3.5);
        db.insertProduct(p, ShoppingDatabase.TB_GAME);

        Products p1 = new Products(R.drawable.playstation5, "playstation5&4", 7600, "sony", 81, "Sony PlayStation 5 Console + 2 DualSense Wireless Controller+ fifa 21 standard edition All prices include VAT.", 25, (float) 3.5);
        db.insertProduct(p1, ShoppingDatabase.TB_GAME);

        Products p2 = new Products(R.drawable.pes2012, "pes2012", 4900, "Konami", 45, "AUTHENTIC LEAGUES - Fully licensed leagues are coming to PES 2012. Details to be revealed soon!", 0, (float) 3.5);
        db.insertProduct(p2, ShoppingDatabase.TB_GAME);

        Products p3 = new Products(R.drawable.menacing, "menacing", 5850, "acer", 32, "Acer Predator Thronos Air, Gaming Throne with Massage Pad and Gaming Chair, Up to 3 Displays, 130 Degrees Recline, LED Lighting, PC Landing Pad, Stabilizing Arm (PC and Monitors Sold Separately)", 0, (float) 3.5);
        db.insertProduct(p3, ShoppingDatabase.TB_GAME);

        Products p4 = new Products(R.drawable.steelchair3, "char gaming", 6000, "acer", 51, "different colors gaming Thermaltake U-Fit Black-Red Gaming Chair GGC-UFT-BRMWDS-01 GGC-UFT-BRMWDS-01", 0, (float) 3.5);
        db.insertProduct(p4, ShoppingDatabase.TB_GAME);

        Products p5 = new Products(R.drawable.gamecontroller5, "gamecontroller5", 800, "Powerextra", 26, "Powerextra Xbox 360 Controllers,USB Gamepad Wired Controller Improved Ergonomic Joystick Dual Vibration,Compatible with Xbox 360 Slim/Xbox 360/PC(Windows / 7/8.1/10),Black", 0, (float) 3.5);
        db.insertProduct(p5, ShoppingDatabase.TB_GAME);

// Electronics Products (TB_ELECTRICS)
        Products p6 = new Products(R.drawable.microwave, "Microwave", 270, "TOSHIBA", 92, "Microwave is an electric device used to heat various types of foods, and this type of oven is different from traditional ovens", 0, (float) 3.5);
        db.insertProduct(p6, ShoppingDatabase.TB_ELECTRICS);

        Products p7 = new Products(R.drawable.vacuumcleaner, "vacuum cleaner", 210, "TOSHIBA", 32, "Hoover Vacuum Cleaner - 2000W, Black - Red TCP2010020 Buy with installments and pay EGP 51.06 for 48 months with select banks.", 10, (float) 3.5);
        db.insertProduct(p7, ShoppingDatabase.TB_ELECTRICS);

        Products p8 = new Products(R.drawable.washer, "Washer", 690, "TOSHIBA", 81, "TOSHIBA Washing Machine Half Automatic Capacity : 12 Kg Max Spin Speed : 1400 RPM With 2 Motors Washing Machine Giant Super Size Works With All Kinds of Regular Powders Vortexes System", 0, (float) 3.5);
        db.insertProduct(p8, ShoppingDatabase.TB_ELECTRICS);

        Products p13 = new Products(R.drawable.electricmixer, "electricmixer", 45, "unknown", 52, "electricmixer in red", 0, (float) 3.5);
        db.insertProduct(p13, ShoppingDatabase.TB_ELECTRICS);

        Products p14 = new Products(R.drawable.electricracket, "electricracket", 48, "unknown", 52, "electricracket", 0, (float) 3.5);
        db.insertProduct(p14, ShoppingDatabase.TB_ELECTRICS);

        Products p15 = new Products(R.drawable.blender, "blender", 48, "unknown", 36, "blender", 0, (float) 3.5);
        db.insertProduct(p15, ShoppingDatabase.TB_ELECTRICS);

// Fashion Products (TB_FASHION)
        Products p9 = new Products(R.drawable.teshert, "t_shirt", 45, "Zara", 52, "Black t_shirt size XL.", 0, (float) 3.5);
        db.insertProduct(p9, ShoppingDatabase.TB_FASHION);

        Products p10 = new Products(R.drawable.dress, "dress", 60, "Zara", 52, "Pink dress.", 0, (float) 3.5);
        db.insertProduct(p10, ShoppingDatabase.TB_FASHION);

        Products p11 = new Products(R.drawable.girlssweatshirt, "girlssweatshirt", 60, "Zara", 52, "Girl T-shirt.", 0, (float) 3.5);
        db.insertProduct(p11, ShoppingDatabase.TB_FASHION);

        Products p12 = new Products(R.drawable.glasses1, "glasses", 30, "Zara", 52, "Sun glasses.", 0, (float) 3.5);
        db.insertProduct(p12, ShoppingDatabase.TB_FASHION);

// Sports Products (TB_SPORTS)
        Products p16 = new Products(R.drawable.sportsshoes, "shoes", 28, "nike", 36, "blender", 0, (float) 3.5);
        db.insertProduct(p16, ShoppingDatabase.TB_SPORTS);

        Products p17 = new Products(R.drawable.sportsshoes2, "shoes", 26, "Fila", 36, "blender", 0, (float) 3.5);
        db.insertProduct(p17, ShoppingDatabase.TB_SPORTS);

        Products p18 = new Products(R.drawable.sportstshirt, "t-shirt", 25, "nike", 36, "blender", 0, (float) 3.5);
        db.insertProduct(p18, ShoppingDatabase.TB_SPORTS);

        Products p19 = new Products(R.drawable.sportshoes2, "shoes", 25, "nike", 36, "blender", 0, (float) 3.5);
        db.insertProduct(p19, ShoppingDatabase.TB_SPORTS);

        Products p20 = new Products(R.drawable.titans, "titan", 25, "unknown", 36, "blender", 0, (float) 3.5);
        db.insertProduct(p20, ShoppingDatabase.TB_SPORTS);

// Books Products (TB_BOOK)
        Products p21 = new Products(R.drawable.php, "php book", 5, "unknown", 36, "blender", 0, (float) 3.5);
        db.insertProduct(p21, ShoppingDatabase.TB_BOOK);

        Products p22 = new Products(R.drawable.python, "python book", 5, "unknown", 36, "blender", 0, (float) 3.5);
        db.insertProduct(p22, ShoppingDatabase.TB_BOOK);

        Products p23 = new Products(R.drawable.kotlin, "kotlin book", 5, "unknown", 36, "blender", 0, (float) 3.5);
        db.insertProduct(p23, ShoppingDatabase.TB_BOOK);

        Products p24 = new Products(R.drawable.java, "java book", 5, "unknown", 36, "blender", 0, (float) 3.5);
        db.insertProduct(p24, ShoppingDatabase.TB_BOOK);

        Products p25 = new Products(R.drawable.javascript, "javascript book", 5, "unknown", 36, "blender", 0, (float) 3.5);
        db.insertProduct(p25, ShoppingDatabase.TB_BOOK);

        Products p26 = new Products(R.drawable.android, "android book", 5, "unknown", 36, "blender", 0, (float) 3.5);
        db.insertProduct(p26, ShoppingDatabase.TB_BOOK);

// Laptop Products (TB_LAPTOP)
        Products p27 = new Products(R.drawable.laptopacer1, "laptop", 800, "acer", 36, "blender", 0, (float) 3.5);
        db.insertProduct(p27, ShoppingDatabase.TB_LAPTOP);

        Products p28 = new Products(R.drawable.laptopapple, "laptop", 1200, "apple", 36, "blender", 0, (float) 3.5);
        db.insertProduct(p28, ShoppingDatabase.TB_LAPTOP);

        Products p29 = new Products(R.drawable.laptopdell1, "laptop", 1000, "dell", 36, "blender", 0, (float) 3.5);
        db.insertProduct(p29, ShoppingDatabase.TB_LAPTOP);

        Products p30 = new Products(R.drawable.laptophp1, "laptop", 780, "hp", 36, "blender", 0, (float) 3.5);
        db.insertProduct(p30, ShoppingDatabase.TB_LAPTOP);

        Products p31 = new Products(R.drawable.laptopmi, "laptop", 500, "xiaomi", 36, "blender", 0, (float) 3.5);
        db.insertProduct(p31, ShoppingDatabase.TB_LAPTOP);

// Mobile Products (TB_MOBILE)
        Products p32 = new Products(R.drawable.phoneapple, "iphone 16", 348, "apple", 15, "blender", 0, (float) 3.5);
        db.insertProduct(p32, ShoppingDatabase.TB_MOBILE);

        Products p33 = new Products(R.drawable.samsunga55, "a55", 128, "samsung", 20, "blender", 0, (float) 3.5);
        db.insertProduct(p33, ShoppingDatabase.TB_MOBILE);

        Products p34 = new Products(R.drawable.huaweinova12, "nova 12", 70, "huawei", 10, "blender", 0, (float) 3.5);
        db.insertProduct(p34, ShoppingDatabase.TB_MOBILE);

// Car Tools Products (TB_CAR_TOOL)
        Products p35 = new Products(R.drawable.carwheel, "CarWheel", 45, "BMW", 6000, "Made in German.", 0, (float) 1.5);
        db.insertProduct(p35, ShoppingDatabase.TB_CAR_TOOL);

        Products p36 = new Products(R.drawable.hammer, "hammer", 45, "Shell", 52, "Made in China.", 0, (float) 1.5);
        db.insertProduct(p36, ShoppingDatabase.TB_CAR_TOOL);

        Products p37 = new Products(R.drawable.vehicleoil, "Vehicle_oil", 50, "Shell", 68, "Pure vehicle oil.", 0, (float) 1.5);
        db.insertProduct(p37, ShoppingDatabase.TB_CAR_TOOL);

        Products p38 = new Products(R.drawable.steeringwheel, "Steering_wheel", 160, "Shell", 68, "Pure vehicle oil.", 0, (float) 1.5);
        db.insertProduct(p38, ShoppingDatabase.TB_CAR_TOOL);

// Home Cooker Products (TB_HOME_COOKER)
        Products p39 = new Products(R.drawable.table, "Table", 160, "IKEA", 68, "Pure wood.", 0, (float) 1.5);
        db.insertProduct(p39, ShoppingDatabase.TB_HOME_COOKER);

        Products p40 = new Products(R.drawable.bedframe, "Bed", 200, "IKEA", 200, "Pure wood.", 0, (float) 1.5);
        db.insertProduct(p40, ShoppingDatabase.TB_HOME_COOKER);

        Products p41 = new Products(R.drawable.cookerrr, "Cooker", 160, "IKEA", 68, "Good CP.", 0, (float) 1.5);
        db.insertProduct(p41, ShoppingDatabase.TB_HOME_COOKER);

        Products p42 = new Products(R.drawable.teastrainer, "teastrainer", 100, "IKEA", 100, "Good CP.", 0, (float) 1.5);
        db.insertProduct(p42, ShoppingDatabase.TB_HOME_COOKER);

// Beauty Products (TB_HOME_COOKER)
        Products p43 = new Products(R.drawable.forsha, "forsha", 100, "IKEA", 100, "Good CP.", 0, (float) 1.5);
        db.insertProduct(p43, ShoppingDatabase.TB_BEAUTY);

        Products p44 = new Products(R.drawable.makeup1, "lipstick", 100, "IKEA", 100, "Good CP.", 0, (float) 1.5);
        db.insertProduct(p44, ShoppingDatabase.TB_BEAUTY);

        Products p45 = new Products(R.drawable.makeup2, "Cosmetics set", 100, "IKEA", 100, "Good CP.", 0, (float) 1.5);
        db.insertProduct(p45, ShoppingDatabase.TB_BEAUTY);

//        Products p = new Products(15);
//        db.deleteProduct(p,ShoppingDatabase.TB_FASHION);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQ_COD);
        }



        rv = findViewById(R.id.rv_home);
        shp = getSharedPreferences("myPreferences", MODE_PRIVATE);
        shp_id = getSharedPreferences("Preferences_id", MODE_PRIVATE);

        ArrayList<Products> products1 = new ArrayList<>();
        products1 = db.getAllProducts(ShoppingDatabase.TB_PRODUCT_DISCOUNT);
        HomeAdabter adapter = new HomeAdabter(products1, new OnRecyclerViewClickListener() {
            @Override
            public void OnItemClick(int productId) {
                Intent i = new Intent(getBaseContext(),DisplayProductsActivity.class);
                i.putExtra(PRODUCT_KEY,productId);
                i.putExtra(TABLE_NAME_KEY,ShoppingDatabase.TB_PRODUCT_DISCOUNT);
                flag = true;
                startActivity(i);
            }
        });
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        rv.setLayoutManager(lm);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);

        bnv = findViewById(R.id.BottomNavigationView);
        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

//                switch (item.getItemId()){
//                    case R.id.home:
//                        Intent intent1 = new Intent(getBaseContext(),HomeActivity.class);
//                        startActivity(intent1);
//                        break;
//                    case R.id.products:
//                        Intent intent2 = new Intent(getBaseContext(),MainActivity.class);
//                        startActivity(intent2);
//                        break;
//                    case R.id.profile:
//                        Intent intent3 = new Intent(getBaseContext(),ProfileActivity.class);
//                        startActivity(intent3);
//                        break;
//                    case R.id.basket:
//                        Intent intent4 = new Intent(getBaseContext(),PurchasesActivity.class);
//                        startActivity(intent4);
//                        break;
//                }
                int itemId = item.getItemId();

                if (itemId == R.id.home) {
                    Intent intent1 = new Intent(getBaseContext(), HomeActivity.class);
                    startActivity(intent1);
                } else if (itemId == R.id.products) {
                    Intent intent2 = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent2);
                } else if (itemId == R.id.profile) {
                    Intent intent3 = new Intent(getBaseContext(), ProfileActivity.class);
                    startActivity(intent3);
                } else if (itemId == R.id.basket) {
                    Intent intent4 = new Intent(getBaseContext(), PurchasesActivity.class);
                    startActivity(intent4);
                }

                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_up_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.logout:
//                logout();
//                break;
//            case R.id.settings:
//                Intent intent = new Intent(this,SettingActivity.class);
//                startActivity(intent);
//                break;
//        }
        int itemId = item.getItemId();

        if (itemId == R.id.logout) {
            logout();
        } else if (itemId == R.id.settings) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
        } else if (itemId == R.id.customservice) {
            Intent intent = new Intent(this, Custom.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        try {
            if (shp == null){
                shp = getSharedPreferences("myPreferences", MODE_PRIVATE);
            }
            shpEditor = shp.edit();
            shpEditor.putInt("user", 0);
            shpEditor.commit();

            if (shp_id == null){
                shp_id = getSharedPreferences("Preferences_id", MODE_PRIVATE);
            }
            shpEditor_id = shp_id.edit();
            shpEditor_id.putInt("user_id",0);
            shpEditor_id.commit();

            startActivity(new Intent(HomeActivity.this,LoginActivity.class));
            finish();

        }catch (Exception ex){
            Toast.makeText(this, ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQ_COD:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //تم الحصول عليه
                }else{

                }
        }
    }

}