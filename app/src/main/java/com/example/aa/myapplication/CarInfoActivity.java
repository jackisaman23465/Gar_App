package com.example.aa.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CarInfoActivity extends Activity {
    TextView carinfo_carname;
    TextView carinfo_brand;
    TextView carinfo_model;
    TextView carinfo_fuleconsumption;
    TextView carinfo_enginedisplacement;
    TextView carinfo_price;
    CarInfoSQLite helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carinfo);
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

    /**private void showSpots(int index) {
        if (spotList.size() > 0) {
            Spot spot = spotList.get(index);
            Bitmap image = BitmapFactory.decodeByteArray(spot.getImage(), 0,
                    spot.getImage().length);
            ivSpot.setImageBitmap(image);
            tvId.setText(Integer.toString(spot.getId()));
            tvName.setText(spot.getName());
            tvWeb.setText(spot.getWeb());
            tvPhone.setText(spot.getPhone());
            tvAddress.setText(spot.getAddress());
            tvRowCount.setText((index + 1) + "/" + spotList.size());
        } else {
            ivSpot.setImageResource(R.drawable.ic_launcher);
            tvId.setText(null);
            tvName.setText(null);
            tvWeb.setText(null);
            tvPhone.setText(null);
            tvAddress.setText(null);
            tvRowCount.setText(" 0/0 " + getString(R.string.msg_NoDataFound));
        }
    }

    public void onNextClick(View view) {
        index++;
        if (index >= spotList.size()) {
            index = 0;
        }
        showSpots(index);
    }

    public void onBackClick(View view) {
        index--;
        if (index < 0) {
            index = spotList.size() - 1;
        }
        showSpots(index);
    }

    public void onInsertClick(View view) {
        Intent intent = new Intent(this, InsertActivity.class);
        startActivity(intent);
    }

    public void onUpdateClick(View view) {
        if (spotList.size() <= 0) {
            Toast.makeText(this, R.string.msg_NoDataFound,
                    Toast.LENGTH_SHORT).show();
            return;
        }
        int id = Integer.parseInt(tvId.getText().toString());
        Intent intent = new Intent(this, UpdateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        intent.putExtras(bundle);
        startActivity(intent);
    }**/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (helper != null) {
            helper.close();
        }
    }
}