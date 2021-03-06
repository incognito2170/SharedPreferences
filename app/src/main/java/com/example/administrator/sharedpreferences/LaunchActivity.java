package com.example.administrator.sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        SharedPreferences sp = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        boolean foodisSet = sp.getBoolean("food_isSet", false);
        boolean clothisSet = sp.getBoolean("cloth_isSet", false);
        boolean houseisSet = sp.getBoolean("house_isSet", false);
        boolean tuitionisSet = sp.getBoolean("tuition_isSet", false);
        boolean medicalisSet = sp.getBoolean("medical_isSet", false);
        boolean othersisSet = sp.getBoolean("others_isSet", false);
        Intent intent;


        if(foodisSet == false || clothisSet == false || houseisSet == false || tuitionisSet == false || medicalisSet == false || othersisSet == false){
            intent = new Intent(LaunchActivity.this, SetBudget.class);
            startActivity(intent);
            finish();
        }
        else if(foodisSet == true && clothisSet == true && houseisSet == true && tuitionisSet == true && medicalisSet == true && othersisSet == true){
            intent = new Intent(LaunchActivity.this, BudgetManager.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_launch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
