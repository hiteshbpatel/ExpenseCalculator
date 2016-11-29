package com.supriyalahade.expense_manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.supriyalahade.expense_manager.Adapter.MainAdapter;
import com.supriyalahade.expense_manager.Database.DBHelper;
import com.supriyalahade.expense_manager.Model.FirstPage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    GridView gridView;
    ArrayList<FirstPage> arrayList;
    String[] name ={"Expenses","Income","Budget","Report"};
    int[] image ={R.drawable.expense,R.drawable.income,R.drawable.budget,R.drawable.graph};

    TextView incomeValue,expenseValue,savingValue;

    DBHelper dbHelper;

    MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView)findViewById(R.id.gridView);

        arrayList = new ArrayList<>();

        for(int i=0;i<4;i++){

            arrayList.add(new FirstPage(name[i],image[i]));

        }

        mainAdapter = new MainAdapter(this,arrayList);
        gridView.setAdapter(mainAdapter);

        gridView.setOnItemClickListener(this);


        incomeValue=(TextView)findViewById(R.id.income_txtMain);
        expenseValue=(TextView)findViewById(R.id.expenses_txtMain);
        savingValue=(TextView)findViewById(R.id.savings_txtMain);

        dbHelper = new DBHelper(this);
       dbHelper.getIncomeTotal(1,incomeValue);
        dbHelper.getExpense(1,expenseValue);
        dbHelper.getSavingsTotal(1,savingValue);



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (position){

            case 0:


                Intent expenseIntent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(expenseIntent);
                dbHelper.getExpense(1,expenseValue);
                dbHelper.getSavingsTotal(1,savingValue);
                dbHelper.getIncomeTotal(1,incomeValue);


                break;


            case 1:

                Intent incomeIntent = new Intent(MainActivity.this, IncomePage.class);
                startActivity(incomeIntent);
                dbHelper.getIncomeTotal(1,incomeValue);
                dbHelper.getExpense(1,expenseValue);
                dbHelper.getSavingsTotal(1,savingValue);
                break;


            case 2:
                Intent graphIntent = new Intent(MainActivity.this,GraphActivity.class);
                startActivity(graphIntent);
                break;


            case 3:
                break;

        }

    }
}
