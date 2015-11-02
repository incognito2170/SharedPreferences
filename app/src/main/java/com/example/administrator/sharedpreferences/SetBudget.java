package com.example.administrator.sharedpreferences;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
                try {
                    String strBudget1 = edit1.getText().toString();
                    int food;

                    if (TextUtils.isEmpty(strBudget1)) {
                        food = 0;
                    } else {
                        food = Integer.parseInt(strBudget1);
                    }

                    String strBudget2 = edit2.getText().toString();
                    int cloth;

                    if (TextUtils.isEmpty(strBudget2)) {
                        cloth = 0;
                    } else {
                        cloth = Integer.parseInt(strBudget2);
                    }

                    String strBudget3 = edit3.getText().toString();
                    int house;

                    if (TextUtils.isEmpty(strBudget3)) {
                        house = 0;
                    } else {
                        house = Integer.parseInt(strBudget3);
                    }

                    String strBudget4 = edit4.getText().toString();
                    int tuition;

                    if (TextUtils.isEmpty(strBudget4)) {
                        tuition = 0;
                    } else {
                        tuition = Integer.parseInt(strBudget4);
                    }

                    String strBudget5 = edit5.getText().toString();
                    int medical;

                    if (TextUtils.isEmpty(strBudget5)) {
                        medical = 0;
                    } else {
                        medical = Integer.parseInt(strBudget5);
                    }

                    String strBudget6 = edit6.getText().toString();
                    int others;

                    if (TextUtils.isEmpty(strBudget6)) {
                        others = 0;
                    } else {
                        others = Integer.parseInt(strBudget6);
                    }


                    if (food < 0 || cloth<0 || house<0 || tuition<0 || medical<0 || others<0) {
                        AlertDialog alertDialog = new AlertDialog.Builder(SetBudget.this).create();
                        alertDialog.setTitle("Alert!");
                        alertDialog.setMessage("Please enter non-negative values in all the fields.");

                        alertDialog.show();
                    } else {
                        editor.putInt("food", food);
                        editor.putInt("originalFood", food);
                        editor.putBoolean("food_isSet", true);
                        editor.commit();

                        editor.putInt("cloth", cloth);
                        editor.putInt("originalCloth", cloth);
                        editor.putBoolean("cloth_isSet", true);
                        editor.commit();

                        editor.putInt("house", house);
                        editor.putInt("originalHouse", house);
                        editor.putBoolean("house_isSet", true);
                        editor.commit();

                        editor.putInt("tuition", tuition);
                        editor.putInt("originalTuition", tuition);
                        editor.putBoolean("tuition_isSet", true);
                        editor.commit();

                        editor.putInt("medical", medical);
                        editor.putInt("originalMedical", medical);
                        editor.putBoolean("medical_isSet", true);
                        editor.commit();


                        editor.putInt("others", others);
                        editor.putInt("originalOthers", others);
                        editor.putBoolean("others_isSet", true);
                        editor.commit();


                    int intBudget = food + cloth + house + tuition + medical + others;
                    editor.putInt("Budget", intBudget);
                    editor.commit();

                    int totalBudget = food + cloth + house + tuition + medical + others;
                    editor.putInt("originalBudget", totalBudget);
                    editor.commit();

                    Intent intent = new Intent(SetBudget.this, BudgetManager.class);
                    startActivity(intent);
                    finish();
                }
                }
                catch(Exception e)
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(SetBudget.this).create();
                    alertDialog.setTitle("Alert!");
                    alertDialog.setMessage("Please enter integer values only.");

                    alertDialog.show();
                }
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
