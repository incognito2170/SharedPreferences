package com.example.administrator.sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SetBudget extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_budget);

        final EditText edit1 = (EditText) findViewById(R.id.edit1);
        final EditText edit2 = (EditText) findViewById(R.id.edit2);
        final EditText edit3 = (EditText) findViewById(R.id.edit3);
        final EditText edit4 = (EditText) findViewById(R.id.edit4);
        final EditText edit5 = (EditText) findViewById(R.id.edit5);
        final EditText edit6 = (EditText) findViewById(R.id.edit6);

        final Button enterB = (Button) findViewById(R.id.enter_button);

        final SharedPreferences sp = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor  = sp.edit();

        enterB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strBudget1 = edit1.getText().toString();
                int food = Integer.parseInt(strBudget1);
                editor.putInt("food", food);
                editor.putBoolean("food_isSet", true);
                editor.commit();

                String strBudget2 = edit2.getText().toString();
                int cloth = Integer.parseInt(strBudget2);
                editor.putInt("cloth", cloth);
                editor.putBoolean("cloth_isSet", true);
                editor.commit();


                String strBudget3 = edit3.getText().toString();
                int house = Integer.parseInt(strBudget3);
                editor.putInt("house", house);
                editor.putBoolean("house_isSet", true);
                editor.commit();


                String strBudget4 = edit4.getText().toString();
                int tuition = Integer.parseInt(strBudget4);
                editor.putInt("tuition", tuition);
                editor.putBoolean("tuition_isSet", true);
                editor.commit();


                String strBudget5 = edit5.getText().toString();
                int medical = Integer.parseInt(strBudget5);
                editor.putInt("medical", medical);
                editor.putBoolean("medical_isSet", true);
                editor.commit();


                String strBudget6 = edit6.getText().toString();
                int others = Integer.parseInt(strBudget6);
                editor.putInt("others", others);
                editor.putBoolean("others_isSet", true);
                editor.commit();

                int intBudget = food+cloth+house+tuition+medical+others;
                editor.putInt("Budget", intBudget);
                editor.commit();

                Intent intent = new Intent(SetBudget.this, BudgetManager.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_budget, menu);
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
