package com.supriyalahade.expense_manager;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.supriyalahade.expense_manager.Adapter.ExpandListAdapter;
import com.supriyalahade.expense_manager.Database.DBHelper;
import com.supriyalahade.expense_manager.Model.Data_Details;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by BIDWAI on 30-07-2016.
 */
public class IncomePage extends AppCompatActivity implements ExpandableListView.OnChildClickListener {

    ExpandListAdapter expandListAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String,List<Data_Details>> listDataChild;

    String[] name_income = {"Salary","Business","Others"};

    public String date,item_name,cost;

    DatePickerDialog myDatePickerDialog;
    SimpleDateFormat DateFormatter;
    List<Data_Details> Salary,Business,Others;

    Data_Details d;
    DBHelper dbHelper;

    TextView income,saving,expenses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        DateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        expListView = (ExpandableListView)findViewById(R.id.expandableListView);
        expandListAdapter=(ExpandListAdapter) expListView.getExpandableListAdapter();
        expListView.setOnChildClickListener(this);

        Salary = new ArrayList<Data_Details>();
        Business =new ArrayList<Data_Details>();
        Others =new ArrayList<Data_Details>();

        prepareListDate();


        expandListAdapter = new ExpandListAdapter(this,listDataHeader,listDataChild);
        expListView.setAdapter(expandListAdapter);

        registerForContextMenu(expListView);


        for (int i = 0; i < name_income.length; i++) {
            dbHelper.insertIncomeCategory(name_income[i]);
        }



        income = (TextView)findViewById(R.id.income_txt);
        saving =(TextView)findViewById(R.id.savings_txt);
        expenses = (TextView)findViewById(R.id.expenses_txt);

        String inc = income.getText().toString();
        double incValue= Double.parseDouble(inc);

        String sav = expenses.getText().toString();
        double savValue = Double.parseDouble(sav);

        dbHelper.insertIncomeCalc(incValue);

        dbHelper.getIncomeTotal(1,income);
        dbHelper.getExpense(1,expenses);

        dbHelper.getSavingsTotal(1,saving);

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_menu,menu);
    }



    @Override
    public boolean onContextItemSelected(MenuItem item) {

        final ExpandableListView.ExpandableListContextMenuInfo info = (ExpandableListView.ExpandableListContextMenuInfo)item.getMenuInfo();

        if (item.getItemId() == R.id.add_id) {
            View view;
            final AlertDialog.Builder itemDialog = new AlertDialog.Builder(this);
            final AlertDialog dialog = itemDialog.create();

            LayoutInflater inflater = LayoutInflater.from(this);

            view = inflater.inflate(R.layout.item_dialog, null);

            TextView itemName = (TextView) view.findViewById(R.id.item_name_textView);
            final TextView itemCost = (TextView) view.findViewById(R.id.item_cost_textView);
            TextView itemdate = (TextView) view.findViewById(R.id.date_textView);

            final EditText editItem = (EditText) view.findViewById(R.id.item_editText);
            final EditText editCost = (EditText) view.findViewById(R.id.itemCost_editText);
            final EditText editDate = (EditText) view.findViewById(R.id.date_editText);
            editDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myDatePickerDialog.show();
                }
            });

            Calendar newCalendar = Calendar.getInstance();
            myDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Calendar newDate = Calendar.getInstance();
                    newDate.set(year,monthOfYear,dayOfMonth);
                    editDate.setText(DateFormatter.format(newDate.getTime()));

                }
            },newCalendar.get(Calendar.YEAR),newCalendar.get(Calendar.MONTH),newCalendar.get(Calendar.DAY_OF_MONTH));



            Button addButton = (Button) view.findViewById(R.id.button_add);
            Button cancelButton = (Button) view.findViewById(R.id.button_cancel);

            itemDialog.setTitle("Transactions");


            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    item_name = editItem.getText().toString();
                    date = editDate.getText().toString();
                    cost = editCost.getText().toString();





                    int grpPos = expListView.getPackedPositionGroup(info.packedPosition);

                    d = new Data_Details(date, item_name, cost);



                    dbHelper.getIncomeTotal(1,income);
                    dbHelper.getExpense(1,expenses);
                    dbHelper.getSavingsTotal(1,saving);



                    switch(grpPos){

                        case 0: listDataChild.get(listDataHeader.get(0)).add(d);
                            break;

                        case 1:listDataChild.get(listDataHeader.get(1)).add(d);
                            break;

                        case 2:listDataChild.get(listDataHeader.get(2)).add(d);
                            break;


                    }

                    double income_cost = Double.parseDouble(cost);

                    String inc = income.getText().toString();
                    double total = Double.parseDouble(inc);
                    total =total+income_cost;

                    String exp = expenses.getText().toString();
                    double expValue = Double.parseDouble(exp);

                    String sav = saving.getText().toString();
                  //  double  savingValue= Double.parseDouble(sav);

                    double savings_cost = total-expValue;

                  //  savingValue = savingValue+savings_cost;


                    dbHelper.IncomeValueUpdate(1,String.valueOf(total));
                    dbHelper.ExpValueUpdate(1,String.valueOf(expValue));
                    dbHelper.SavingsValueUpdate(1,String.valueOf(savings_cost));

                    dbHelper.getIncomeTotal(1,income);
                    dbHelper.getExpense(1,expenses);
                    dbHelper.getSavingsTotal(1,saving);


                    expandListAdapter.notifyDataSetChanged();

                    dbHelper.insertIncomeItem(grpPos,date,item_name,cost);
                    dbHelper.getIncomeItems();

                    Toast.makeText(IncomePage.this, "Item Saved", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();


                }
            });
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(IncomePage.this, "Item Cancelled", Toast.LENGTH_SHORT).show();

                    dialog.dismiss();

                }
            });
            dialog.setView(view);
            dialog.show();

            dialog.setCancelable(false);

        }


        return super.onContextItemSelected(item);
    }


    @Override
    public boolean onChildClick(ExpandableListView expandableListView, View view, final int groupPosition, final int childPosition, long l) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        final AlertDialog deleteDialog = dialogBuilder.create();
        dialogBuilder.setMessage("Delete Item?");
        dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                date=(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).getDate());
                item_name=(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).getItem());
                cost =(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).getCost());


                listDataChild.get(listDataHeader.get(groupPosition)).remove(childPosition);


                dbHelper.deleteIncomeData(groupPosition,date,item_name,cost);
                dbHelper.getIncomeItems();

                double income_cost = Double.parseDouble(cost);
                String inc_total = income.getText().toString();
                double incTotal =  Double.parseDouble(inc_total);

                double total = incTotal - income_cost;

                dbHelper.getExpense(1,expenses);
                String exp = expenses.getText().toString();
                double expValue = Double.parseDouble(exp);


                dbHelper.getSavingsTotal(1,saving);
                String sav = income.getText().toString();

                double savcalc = total-expValue;


                dbHelper.IncomeValueUpdate(1,String.valueOf(total));

                dbHelper.ExpValueUpdate(1,String.valueOf(expValue));
                dbHelper.SavingsValueUpdate(1,String.valueOf(savcalc));

                dbHelper.getIncomeTotal(1,income);
                dbHelper.getSavingsTotal(1,saving);
                dbHelper.getExpense(1,expenses);

                expandListAdapter.notifyDataSetChanged();

                deleteDialog.dismiss();

            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteDialog.dismiss();
            }
        });


        dialogBuilder.show();
        dialogBuilder.setCancelable(false);


        return false;
    }

    private void prepareListDate() {




        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<Data_Details>>();




        //Adding Header Data

        for (int i = 0; i < name_income.length; i++) {
            listDataHeader.add(name_income[i]);
        }

        dbHelper = new DBHelper(this);
        Salary = dbHelper.getIncomeChildren(0);
        Business = dbHelper.getIncomeChildren(1);
        Others = dbHelper.getIncomeChildren(2);





        listDataChild.put(listDataHeader.get(0), Salary);
        listDataChild.put(listDataHeader.get(1), Business);
        listDataChild.put(listDataHeader.get(2), Others);



    }



}










