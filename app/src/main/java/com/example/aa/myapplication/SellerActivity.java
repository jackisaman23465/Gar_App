package com.example.aa.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SellerActivity extends Activity {
    private EditText carname;
    private EditText brand;
    private EditText model;
    private EditText fuelconsumption;
    private EditText enginedisplacement;
    private EditText price;
    private CarInfoSQLite helper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);
        findViews();
        if (helper == null) {
            helper = new CarInfoSQLite(this);
        }
    }

    private void findViews() {
        carname = (EditText) findViewById(R.id.carname);
        brand = (EditText) findViewById(R.id.brand);
        model = (EditText) findViewById(R.id.model);
        fuelconsumption = (EditText) findViewById(R.id.fuelconsumption);
        enginedisplacement = (EditText) findViewById(R.id.enginedisplacement);
        price = (EditText) findViewById(R.id.price);
    }

    public void onCarInfoFinishInsertClick(View view) {
        String Carname = carname.getText().toString().trim();
        String Brand = brand.getText().toString().trim();
        String Model = model.getText().toString().trim();
        String Fuelconsumption = fuelconsumption.getText().toString().trim();
        String Enginedisplacement = enginedisplacement.getText().toString().trim();
        String Price = price.getText().toString().trim();

        if(Carname.length()<=0){
            Toast.makeText(this, R.string.msg_CarNameIsInvalid,
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if(Brand.length()<=0){
            Toast.makeText(this, R.string.msg_BrandIsInvalid,
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if(Model.length()<=0){
            Toast.makeText(this, R.string.msg_ModelIsInvalid,
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if(Fuelconsumption.length()<=0){
            Toast.makeText(this, R.string.msg_FuelConsumptionIsInvalid,
                    Toast.LENGTH_SHORT).show();
            return;
        }else if(!isNumeric(Fuelconsumption)){
            Toast.makeText(this, R.string.msg_FuelConsumptionIsANumber,
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if(Enginedisplacement.length()<=0){
            Toast.makeText(this, R.string.msg_EnginedisplacementIsInvalid,
                    Toast.LENGTH_SHORT).show();
            return;
        }else if(!isNumeric(Enginedisplacement)){
            Toast.makeText(this, R.string.msg_EnginedisplacementIsANumber,
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if(Price.length()<=0){
            Toast.makeText(this, R.string.msg_PriceIsInvalid,
                    Toast.LENGTH_SHORT).show();
            return;
        }else if(!isNumeric(Price)){
            Toast.makeText(this, R.string.msg_PriceIsANumber,
                    Toast.LENGTH_SHORT).show();
            return;
        }
        CarInfoSpot carinfoSpot = new CarInfoSpot(Carname, Brand, Model, Fuelconsumption, Enginedisplacement, Price);
        long rowId = helper.insert(carinfoSpot);
        if (rowId != -1) {
            Toast.makeText(this, R.string.msg_EditSuccess,
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.msg_EditFail,
                    Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (helper != null) {
            helper.close();
        }
    }

    public boolean isNumeric(String str)
    {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() )
        {
            return false;
        }
        return true;
    }
}