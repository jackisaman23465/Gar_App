package com.example.aa.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView carinfo_carname;
    TextView carinfo_brand;
    TextView carinfo_model;
    TextView carinfo_fuleconsumption;
    TextView carinfo_enginedisplacement;
    TextView carinfo_price;
    List<CarInfoSpot> spotList;
    int index;
    CarInfoSQLite helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        if (helper == null) {
            helper = new CarInfoSQLite(this);
        }
    }

    private void findViews() {
        carinfo_carname = (TextView) findViewById(R.id.carinfo_carname);
        carinfo_brand = (TextView) findViewById(R.id.carinfo_brand);
        carinfo_model = (TextView) findViewById(R.id.carinfo_model);
        carinfo_fuleconsumption = (TextView) findViewById(R.id.carinfo_fuelconsumption);
        carinfo_enginedisplacement = (TextView) findViewById(R.id.carinfo_enginedisplacement);
        carinfo_price = (TextView) findViewById(R.id.carinfo_price);
    }

    @Override
    protected void onStart() {
        super.onStart();
        spotList = helper.getAllSpots();
        showCarInfoSpots(0);
    }

    private void showCarInfoSpots(int index) {
        if (spotList.size() > 0) {
            CarInfoSpot spot = spotList.get(index);
            carinfo_carname.setText(spot.getCarname());
            carinfo_brand.setText(spot.getBrand());
            carinfo_model.setText(spot.getModel());
            carinfo_fuleconsumption.setText(spot.getFuelconsumption());
            carinfo_enginedisplacement.setText(spot.getEnginedisplacement());
            carinfo_price.setText(spot.getPrice());
        } else {
            carinfo_carname.setText(null);
            carinfo_brand.setText(null);
            carinfo_model.setText(null);
            carinfo_fuleconsumption.setText(null);
            carinfo_enginedisplacement.setText(null);
            carinfo_price.setText(null);
        }
    }

    public void onNextClick(View view) {
        if (spotList.size() <= 0) {
            Toast.makeText(this, R.string.msg_NoDataFound,
                    Toast.LENGTH_SHORT).show();
            return;
        }
        index++;
        if (index >= spotList.size()) {
            index = 0;
        }
        showCarInfoSpots(index);
    }

    public void onBackClick(View view) {
        if (spotList.size() <= 0) {
            Toast.makeText(this, R.string.msg_NoDataFound,
                    Toast.LENGTH_SHORT).show();
            return;
        }
        index--;
        if (index < 0) {
            index = spotList.size() - 1;
        }
        showCarInfoSpots(index);
    }

    public void onDeleteClick(View view) {
        if (spotList.size() <= 0) {
            Toast.makeText(this, R.string.msg_NoDataFound,
                    Toast.LENGTH_SHORT).show();
            return;
        }
        CarInfoSpot spot = spotList.get(index);
        int count = helper.deleteById(spot.getId());
        spotList = helper.getAllSpots();
        showCarInfoSpots(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (helper != null) {
            helper.close();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_seller:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,SellerActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_signout:
                Intent intent1 = new Intent();
                intent1.setClass(MainActivity.this,LoginActivity.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}