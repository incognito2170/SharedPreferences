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
        tv7.setText("Other Purposes: " + othersStr);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int foodInt, clothInt, houseInt, tuitionInt, medicalInt, othersInt;

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

                    if (TextUtils.isEmpty(foodExp)) {
                        foodInt = 0;
                    } else {
                        foodInt = Integer.parseInt(foodExp);
                    }

                    if (TextUtils.isEmpty(clothExp)) {
                        clothInt = 0;
                    } else {
                        clothInt = Integer.parseInt(clothExp);
                    }

                    if (TextUtils.isEmpty(houseExp)) {
                        houseInt = 0;
                    } else {
                        houseInt = Integer.parseInt(houseExp);
                    }

                    if (TextUtils.isEmpty(tuitionExp)) {
                        tuitionInt = 0;
                    } else {
                        tuitionInt = Integer.parseInt(tuitionExp);
                    }

                    if (TextUtils.isEmpty(medicalExp)) {
                        medicalInt = 0;
                    } else {
                        medicalInt = Integer.parseInt(medicalExp);
                    }

                    if (TextUtils.isEmpty(othersExp)) {
                        othersInt = 0;
                    } else {
                        othersInt = Integer.parseInt(othersExp);
                    }


                    if (foodInt < 0 || clothInt < 0 || houseInt < 0 || tuitionInt < 0 || medicalInt < 0 || othersInt < 0) {

                        AlertDialog alertDialog = new AlertDialog.Builder(BudgetManager.this).create();
                        alertDialog.setTitle("Alert!");
                        alertDialog.setMessage("Please enter non-negative values.");

                        alertDialog.show();

                    } else {
                        int oriBalance = sp.getInt("originalBudget", 0);
                        int bal = sp.getInt("Budget", 0);
                        int totalExpense = foodInt + clothInt + houseInt + tuitionInt + medicalInt + othersInt;
                        int newBal = bal - totalExpense;

                        if (newBal > 0 && newBal < ((oriBalance * 25) / 100)) {

                            AlertDialog alertDialog = new AlertDialog.Builder(BudgetManager.this).create();
                            alertDialog.setTitle("Alert!");
                            alertDialog.setMessage("Your remaining Overall balance is less than 25% of your allocated Overall budget which was " + oriBalance);

                            alertDialog.show();

                        } else if (newBal == 0) {

                            AlertDialog alertDialog = new AlertDialog.Builder(BudgetManager.this).create();
                            alertDialog.setTitle("Alert!!");
                            alertDialog.setMessage("You have used 100% of your allocated Overall budget.");

                            alertDialog.show();
                        } else if (newBal < 0) {

                            AlertDialog alertDialog = new AlertDialog.Builder(BudgetManager.this).create();
                            alertDialog.setTitle("Alert!!!");
                            alertDialog.setMessage("You are running on negative Overall budget.");

                            alertDialog.show();


                        }

                        editor.putInt("Budget", newBal);
                        editor.commit();
                        tv1.setText("Remaining Balance: " + Integer.toString(newBal));


                        int oriFood = sp.getInt("originalFood", 0);
                        int newFood = sp.getInt("food", 0);
                        int lastFood = newFood - foodInt;

                        if (lastFood > 0 && lastFood < ((oriFood * 25) / 100)) {

                            AlertDialog alertDialog = new AlertDialog.Builder(BudgetManager.this).create();
                            alertDialog.setTitle("Alert!");
                            alertDialog.setMessage("Your remaining balance for Food is less than 25% of your allocated budget for Food which was " + oriFood);

                            alertDialog.show();

                        } else if (lastFood == 0) {

                            AlertDialog alertDialog = new AlertDialog.Builder(BudgetManager.this).create();
                            alertDialog.setTitle("Alert!!");
                            alertDialog.setMessage("You have used 100% of your allocated budget for Food.");

                            alertDialog.show();
                        } else if (lastFood < 0) {

                            AlertDialog alertDialog = new AlertDialog.Builder(BudgetManager.this).create();
                            alertDialog.setTitle("Alert!!!");
                            alertDialog.setMessage("You are running on a negative budget for Food.");

                            alertDialog.show();


                        }

                        editor.putInt("food", lastFood);
                        editor.commit();
                        tv2.setText("Food: " + Integer.toString(lastFood));


                        int oriCloth = sp.getInt("originalCloth", 0);
                        int newCloth = sp.getInt("cloth", 0);
                        int lastCloth = newCloth - clothInt;

                        if (lastCloth > 0 && lastCloth < ((oriCloth * 25) / 100)) {

                            AlertDialog alertDialog = new AlertDialog.Builder(BudgetManager.this).create();
                            alertDialog.setTitle("Alert!");
                            alertDialog.setMessage("Your remaining balance for Cloth is less than 25% of your allocated budget for Cloth which was " + oriCloth);

                            alertDialog.show();

                        } else if (lastCloth == 0) {

                            AlertDialog alertDialog = new AlertDialog.Builder(BudgetManager.this).create();
                            alertDialog.setTitle("Alert!!");
                            alertDialog.setMessage("You have used 100% of your allocated budget for Cloth.");

                            alertDialog.show();
                        } else if (lastCloth < 0) {

                            AlertDialog alertDialog = new AlertDialog.Builder(BudgetManager.this).create();
                            alertDialog.setTitle("Alert!!!");
                            alertDialog.setMessage("You are running on a negative budget for Cloth.");

                            alertDialog.show();


                        }

                        editor.putInt("cloth", lastCloth);
                        editor.commit();
                        tv3.setText("Cloth: " + Integer.toString(lastCloth));


                        int oriHouse = sp.getInt("originalHouse", 0);
                        int newHouse = sp.getInt("house", 0);
                        int lastHouse = newHouse - houseInt;

                        if (lastHouse > 0 && lastHouse < ((oriHouse * 25) / 100)) {

                            AlertDialog alertDialog = new AlertDialog.Builder(BudgetManager.this).create();
                            alertDialog.setTitle("Alert!");
                            alertDialog.setMessage("Your remaining balance for House Rent is less than 25% of your allocated budget for House Rent which was " + oriHouse);

                            alertDialog.show();

                        } else if (lastHouse == 0) {

                            AlertDialog alertDialog = new AlertDialog.Builder(BudgetManager.this).create();
                            alertDialog.setTitle("Alert!!");
                            alertDialog.setMessage("You have used 100% of your allocated budget for House Rent.");

                            alertDialog.show();
                        } else if (lastHouse < 0) {

                            AlertDialog alertDialog = new AlertDialog.Builder(BudgetManager.this).create();
                            alertDialog.setTitle("Alert!!!");
                            alertDialog.setMessage("You are running on a negative budget for House Rent.");

                            alertDialog.show();


                        }

                        editor.putInt("house", lastHouse);
                        editor.commit();
                        tv4.setText("House Rent: " + Integer.toString(lastHouse));


                        int oriTuition = sp.getInt("originalTuition", 0);
                        int newTuition = sp.getInt("tuition", 0);
                        int lastTuition = newTuition - tuitionInt;

                        if (lastTuition > 0 && lastTuition < ((oriTuition * 25) / 100)) {

                            AlertDialog alertDialog = new AlertDialog.Builder(BudgetManager.this).create();
                            alertDialog.setTitle("Alert!");
                            alertDialog.setMessage("Your remaining balance for Tuition Fees is less than 25% of your allocated budget for Tuition Fees which was " + oriTuition);

                            alertDialog.show();

                        } else if (lastTuition == 0) {

                            AlertDialog alertDialog = new AlertDialog.Builder(BudgetManager.this).create();
                            alertDialog.setTitle("Alert!!");
                            alertDialog.setMessage("You have used 100% of your allocated budget for Tuition Fees.");

                            alertDialog.show();
                        } else if (lastTuition < 0) {

                            AlertDialog alertDialog = new AlertDialog.Builder(BudgetManager.this).create();
                            alertDialog.setTitle("Alert!!!");
                            alertDialog.setMessage("You are running on a negative budget for Tuition Fees.");

                            alertDialog.show();


                        }

                        editor.putInt("tuition", lastTuition);
                        editor.commit();
                        tv5.setText("Tuition Fees: " + Integer.toString(lastTuition));


                        int oriMedical = sp.getInt("originalMedical", 0);
                        int newMedical = sp.getInt("medical", 0);
                        int lastMedical = newMedical - medicalInt;

                        if (lastMedical > 0 && lastMedical < ((oriMedical * 25) / 100)) {

                            AlertDialog alertDialog = new AlertDialog.Builder(BudgetManager.this).create();
                            alertDialog.setTitle("Alert!");
                            alertDialog.setMessage("Your remaining balance for Medical Bills is less than 25% of your allocated budget for Medical Bills which was " + oriMedical);

                            alertDialog.show();

                        } else if (lastMedical == 0) {

                            AlertDialog alertDialog = new AlertDialog.Builder(BudgetManager.this).create();
                            alertDialog.setTitle("Alert!!");
                            alertDialog.setMessage("You have used 100% of your allocated budget for Medical Bills.");

                            alertDialog.show();
                        } else if (lastMedical < 0) {

                            AlertDialog alertDialog = new AlertDialog.Builder(BudgetManager.this).create();
                            alertDialog.setTitle("Alert!!!");
                            alertDialog.setMessage("You are running on a negative budget for Medical Bills.");

                            alertDialog.show();


                        }

                        editor.putInt("medical", lastMedical);
                        editor.commit();
                        tv6.setText("Medical Bills: " + Integer.toString(lastMedical));


                        int oriOthers = sp.getInt("originalOthers", 0);
                        int newOthers = sp.getInt("others", 0);
                        int lastOthers = newOthers - othersInt;

                        if (lastOthers > 0 && lastOthers < ((oriOthers * 25) / 100)) {

                            AlertDialog alertDialog = new AlertDialog.Builder(BudgetManager.this).create();
                            alertDialog.setTitle("Alert!");
                            alertDialog.setMessage("Your remaining balance for Other Purposes is less than 25% of your allocated budget for Other Purposes which was " + oriOthers);

                            alertDialog.show();

                        } else if (lastOthers == 0) {

                            AlertDialog alertDialog = new AlertDialog.Builder(BudgetManager.this).create();
                            alertDialog.setTitle("Alert!!");
                            alertDialog.setMessage("You have used 100% of your allocated budget for Other Purposes.");

                            alertDialog.show();
                        } else if (lastOthers < 0) {

                            AlertDialog alertDialog = new AlertDialog.Builder(BudgetManager.this).create();
                            alertDialog.setTitle("Alert!!!");
                            alertDialog.setMessage("You are running on a negative budget for Other Purposes.");

                            alertDialog.show();


                        }

                        editor.putInt("others", lastOthers);
                        editor.commit();
                        tv7.setText("Other Purposes: " + Integer.toString(lastOthers));

                    }


                } catch (Exception e) {


                    AlertDialog alertDialog = new AlertDialog.Builder(BudgetManager.this).create();
                    alertDialog.setTitle("Alert!");
                    alertDialog.setMessage("Please enter integer values only.");

                    alertDialog.show();


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
