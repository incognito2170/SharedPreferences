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
import android.widget.TextView;
import android.widget.Toast;

public class BudgetManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_manager);

        final SharedPreferences sp = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor  = sp.edit();

        final int balance = sp.getInt("Budget", 0);
        String balanceStr = Integer.toString(balance);

        final int Food = sp.getInt("food", 0);
        String foodStr = Integer.toString(Food);

        final int Cloth = sp.getInt("cloth", 0);
        String clothStr = Integer.toString(Cloth);

        final int House = sp.getInt("house", 0);
        String houseStr = Integer.toString(House);

        final int Tuition = sp.getInt("tuition", 0);
        String tuitionStr = Integer.toString(Tuition);

        final int Medical = sp.getInt("medical", 0);
        String medicalStr = Integer.toString(Medical);

        final int Others = sp.getInt("others", 0);
        String othersStr = Integer.toString(Others);



        final TextView tv1=(TextView) findViewById(R.id.tv1);
        final TextView tv2=(TextView) findViewById(R.id.tv2);
        final TextView tv3=(TextView) findViewById(R.id.tv3);
        final TextView tv4=(TextView) findViewById(R.id.tv4);
        final TextView tv5=(TextView) findViewById(R.id.tv5);
        final TextView tv6=(TextView) findViewById(R.id.tv6);
        final TextView tv7=(TextView) findViewById(R.id.tv7);
        final TextView tv8=(TextView) findViewById(R.id.tv8);

        final EditText et1=(EditText) findViewById(R.id.et1);
        final EditText et2=(EditText) findViewById(R.id.et2);
        final EditText et3=(EditText) findViewById(R.id.et3);
        final EditText et4=(EditText) findViewById(R.id.et4);
        final EditText et5=(EditText) findViewById(R.id.et5);
        final EditText et6=(EditText) findViewById(R.id.et6);

        final Button bt1=(Button) findViewById(R.id.bt1);
        final Button bt2=(Button) findViewById(R.id.bt2);

        tv1.setText("Remaining Balance: "+balanceStr);
        tv2.setText("Food: "+foodStr);
        tv3.setText("Cloth: "+clothStr);
        tv4.setText("House Rent: "+houseStr);
        tv5.setText("Tuition Fees: "+tuitionStr);
        tv6.setText("Medical Bills: "+medicalStr);
        tv7.setText("Others: "+othersStr);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int foodInt,clothInt,houseInt,tuitionInt,medicalInt,othersInt;

                String foodExp = et1.getText().toString();
                et1.setText("");

                String clothExp = et2.getText().toString();
                et2.setText("");

                String houseExp = et3.getText().toString();
                et3.setText("");

                String tuitionExp = et4.getText().toString();
                et4.setText("");

                String medicalExp = et5.getText().toString();
                et5.setText("");

                String othersExp = et6.getText().toString();
                et6.setText("");

                try {
                    foodInt = Integer.parseInt(foodExp);
                    clothInt = Integer.parseInt(clothExp);
                    houseInt = Integer.parseInt(houseExp);
                    tuitionInt = Integer.parseInt(tuitionExp);
                    medicalInt = Integer.parseInt(medicalExp);
                    othersInt = Integer.parseInt(othersExp);

                    int bal = sp.getInt("Budget", 0);
                    int totalExpense = foodInt+clothInt+houseInt+tuitionInt+medicalInt+othersInt;

                    int newBal = bal - totalExpense;

                    editor.putInt("Budget", newBal);
                    editor.commit();

                    tv1.setText("Remaining Balance: "+Integer.toString(newBal));



                    int newFood = sp.getInt("food", 0);
                    int lastFood = newFood-foodInt;

                    editor.putInt("food", lastFood);
                    editor.commit();

                    tv2.setText("Food: "+Integer.toString(lastFood));



                    int newCloth = sp.getInt("cloth", 0);
                    int lastCloth = newCloth-clothInt;

                    editor.putInt("cloth", lastCloth);
                    editor.commit();

                    tv3.setText("Cloth: "+Integer.toString(lastCloth));


                    int newHouse = sp.getInt("house", 0);
                    int lastHouse = newHouse-houseInt;

                    editor.putInt("house", lastHouse);
                    editor.commit();

                    tv4.setText("House Rent: "+Integer.toString(lastHouse));


                    int newTuition = sp.getInt("tuition", 0);
                    int lastTuition = newTuition-tuitionInt;

                    editor.putInt("tuition", lastTuition);
                    editor.commit();

                    tv5.setText("Tuition Fees: "+Integer.toString(lastTuition));


                    int newMedical = sp.getInt("medical", 0);
                    int lastMedical = newMedical-medicalInt;

                    editor.putInt("medical", lastMedical);
                    editor.commit();

                    tv6.setText("Medical Bills: "+Integer.toString(lastMedical));


                    int newOthers = sp.getInt("others", 0);
                    int lastOthers = newOthers-othersInt;

                    editor.putInt("others", lastOthers);
                    editor.commit();

                    tv7.setText("Others: "+Integer.toString(lastOthers));


                } catch (Exception e) {

                    Toast.makeText(BudgetManager.this, "Please enter integer values only", Toast.LENGTH_LONG).show();


                }


            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BudgetManager.this, SetBudget.class);
                startActivity(intent);
                finish();
            }
        });
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
