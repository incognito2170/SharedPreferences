package com.example.administrator.sharedpreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BudgetManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_manager);

        final TextView tv1=(TextView) findViewById(R.id.tv1);
        final TextView tv2=(TextView) findViewById(R.id.tv2);
        final TextView tv3=(TextView) findViewById(R.id.tv3);
        final TextView tv4=(TextView) findViewById(R.id.tv4);
        final TextView tv5=(TextView) findViewById(R.id.tv5);
        final TextView tv6=(TextView) findViewById(R.id.tv6);
        final TextView tv7=(TextView) findViewById(R.id.tv7);

        final EditText et11=(EditText) findViewById(R.id.et1);
        final Button bt1=(Button) findViewById(R.id.bt1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_budget_manager, menu);
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
