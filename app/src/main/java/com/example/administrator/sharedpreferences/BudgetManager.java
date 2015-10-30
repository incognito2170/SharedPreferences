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

        final int food = sp.getInt("food", 0);
        String foodstr = Integer.toString(food);

        final int cloth = sp.getInt("cloth", 0);
        String clothstr = Integer.toString(cloth);

        final int house = sp.getInt("house", 0);
        String housestr = Integer.toString(house);

        final int tuition = sp.getInt("tuition", 0);
        String tuitionstr = Integer.toString(tuition);

        final int medical = sp.getInt("medical", 0);
        String medicalstr = Integer.toString(medical);

        final int others = sp.getInt("others", 0);
        String othersstr = Integer.toString(others);



        final TextView tv1=(TextView) findViewById(R.id.tv1);
        final TextView tv2=(TextView) findViewById(R.id.tv2);
        final TextView tv3=(TextView) findViewById(R.id.tv3);
        final TextView tv4=(TextView) findViewById(R.id.tv4);
        final TextView tv5=(TextView) findViewById(R.id.tv5);
        final TextView tv6=(TextView) findViewById(R.id.tv6);
        final TextView tv7=(TextView) findViewById(R.id.tv7);

        final EditText et1=(EditText) findViewById(R.id.et1);
        final EditText et2=(EditText) findViewById(R.id.et2);
        final EditText et3=(EditText) findViewById(R.id.et3);
        final EditText et4=(EditText) findViewById(R.id.et4);
        final EditText et5=(EditText) findViewById(R.id.et5);
        final EditText et6=(EditText) findViewById(R.id.et6);

        final Button bt1=(Button) findViewById(R.id.bt1);
        final Button bt2=(Button) findViewById(R.id.bt2);

        tv1.setText(balanceStr);
        tv2.setText(foodstr);
        tv3.setText(clothstr);
        tv4.setText(housestr);
        tv5.setText(medicalstr);
        tv6.setText(tuitionstr);
        tv7.setText(othersstr);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int foodint,clothint,houseint,tuitionint,medicalint,othersint;

                String foodexp = et1.getText().toString();
                et1.setText("");

                String clothexp = et2.getText().toString();
                et2.setText("");

                String houseexp = et3.getText().toString();
                et3.setText("");

                String tuitionexp = et4.getText().toString();
                et4.setText("");

                String medicalexp = et5.getText().toString();
                et5.setText("");

                String othersexp = et6.getText().toString();
                et6.setText("");

                try {
                    foodint = Integer.parseInt(foodexp);
                    clothint = Integer.parseInt(clothexp);
                    houseint = Integer.parseInt(houseexp);
                    tuitionint = Integer.parseInt(tuitionexp);
                    medicalint = Integer.parseInt(medicalexp);
                    othersint = Integer.parseInt(othersexp);

                    int bal = sp.getInt("Budget", 0);
                    int totalexpense = foodint+clothint+houseint+tuitionint+medicalint+othersint;

                    int newbal = bal - totalexpense;

                    editor.putInt("Budget", newbal);
                    editor.commit();

                    tv1.setText(Integer.toString(newbal));



                    int newfood = sp.getInt("food", 0);
                    int finalfood = newfood-foodint;

                    editor.putInt("food", finalfood);
                    editor.commit();

                    tv2.setText(Integer.toString(finalfood));



                    int newcloth = sp.getInt("cloth", 0);
                    int finalcloth = newcloth-clothint;

                    editor.putInt("cloth", finalcloth);
                    editor.commit();

                    tv3.setText(Integer.toString(finalcloth));


                    int newhouse = sp.getInt("house", 0);
                    int finalhouse = newhouse-houseint;

                    editor.putInt("house", finalhouse);
                    editor.commit();

                    tv4.setText(Integer.toString(finalhouse));


                    int newtuition = sp.getInt("tuition", 0);
                    int finaltuition = newtuition-tuitionint;

                    editor.putInt("tuition", finaltuition);
                    editor.commit();

                    tv5.setText(Integer.toString(finaltuition));


                    int newmedical = sp.getInt("medical", 0);
                    int finalmedical = newmedical-medicalint;

                    editor.putInt("food", finalmedical);
                    editor.commit();

                    tv6.setText(Integer.toString(finalmedical));


                    int newothers = sp.getInt("others", 0);
                    int finalothers = newothers-othersint;

                    editor.putInt("others", finalothers);
                    editor.commit();

                    tv7.setText(Integer.toString(finalothers));


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
