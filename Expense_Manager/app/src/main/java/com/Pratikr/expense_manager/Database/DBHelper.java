package com.supriyalahade.expense_manager.Database;

/**
 * Created by BIDWAI on 14-09-2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import com.supriyalahade.expense_manager.Model.Data_Details;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by BIDWAI on 01-09-2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    SQLiteDatabase sqLiteDatabase;

    //Defining Database name and Version.
    private static final String DB_NAME = "EXP_MANAGER";
    private static final int DB_VERSION =1;


    //Defining Expense Group (Headers) Table.
    private static final String TABLE_CATEGORY = "CATEGORY_LIST";
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "CATEGORY";


    //Defining Expense Child Items Table.
    private static final String TABLE_NAME = "EXPENSES";
    private static final String COLID = "ID";
    private static final String COL_CAT = "CATID";
    private static final String COL_DATE = "DATE";
    private static final String COL_ITEM = "ITEM";
    private static final String COL_COST = "COST";


    //Defining Income Group Table.
    private static final String TABLE_CATEGORY_INCOME = "CATEGORY_INCOME";
    private static final String CAT_ID = "CAT_ID";
    private static final String CAT_NAME = "CATEGORY_NAME";


    //Defining Income Child Items Table
    private static final String TABLE_INCOME = "INCOME";
    private static final String KEY_ID = "ID";
    private static final String KEY_CAT = "CATID_INCOME";
    private static final String KEY_DATE = "DATE_INCOME";
    private static final String KEY_ITEM = "ITEM_INCOME";
    private static final String KEY_COST = "COST_INCOME";


    //Defining Expense Total Table
    private static final String EXPENSE_CALC = "EXPENSE_TOTAL";
    private static final String EXP_ID = "ID";
    private static final String COL_EXPENSE = "EXPENSES";


    //Defining Income Total Table
    private static final String INCOME_CALC = "INCOME_TOTAL";
    private static final String INCOME_ID = "ID";
    private static final String COL_INCOME = "INCOME";

    //Defining Savings Total Table
    private static final String SAVINGS_CALC = "SAVINGS_TOTAL";
    private static final String SAVINGS_ID = "ID";
    private static final String COL_SAVINGS = "SAVINGS";



    //Constructor
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    //Creating all the tables.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String table_category=" CREATE TABLE " + TABLE_CATEGORY + " ( "+ COL_ID + " INTEGER PRIMARY KEY, " + COL_NAME + " TEXT ) ";
        sqLiteDatabase.execSQL(table_category);

        String create_table = " CREATE TABLE " + TABLE_NAME + " ( " + COLID + " INTEGER PRIMARY KEY, " + COL_CAT + " INTEGER, " + COL_DATE + " TEXT, "+ COL_ITEM + " TEXT, " + COL_COST + " TEXT )";
        sqLiteDatabase.execSQL(create_table);

        String category_income=" CREATE TABLE " + TABLE_CATEGORY_INCOME  + " ( "+ CAT_ID + " INTEGER PRIMARY KEY, " + CAT_NAME + " TEXT ) ";
        sqLiteDatabase.execSQL(category_income);

        String table_income = " CREATE TABLE " + TABLE_INCOME + " ( " + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_CAT + " INTEGER, " + KEY_DATE + " TEXT, " + KEY_ITEM + " TEXT, " + KEY_COST + " TEXT )";
        sqLiteDatabase.execSQL(table_income);

        String expense_calc = " CREATE TABLE " + EXPENSE_CALC + " ( " + EXP_ID + " INTEGER PRIMARY KEY, " + COL_EXPENSE +  " STRING ) ";
        sqLiteDatabase.execSQL(expense_calc);

        String income_calc = " CREATE TABLE " + INCOME_CALC + " ( " + INCOME_ID + " INTEGER PRIMARY KEY, " + COL_INCOME +  " STRING ) ";
        sqLiteDatabase.execSQL(income_calc);

        String savings_calc = " CREATE TABLE " + SAVINGS_CALC + " ( " + SAVINGS_ID + " INTEGER PRIMARY KEY, " + COL_SAVINGS +  " STRING ) ";
        sqLiteDatabase.execSQL(savings_calc);




    }

//upgrade method if the database version changes.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(" DROP TABLE IF EXITS" + TABLE_NAME);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXITS" + TABLE_CATEGORY);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXITS" + TABLE_CATEGORY_INCOME);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXITS" + TABLE_INCOME);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXITS" + EXPENSE_CALC);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXITS" + INCOME_CALC);

        onCreate(sqLiteDatabase);

    }

    //Method to insert Expense Group categories in db.
    public void insertCategory(String name){

        sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME,name);
        sqLiteDatabase.insert(TABLE_CATEGORY,null,contentValues);

        sqLiteDatabase.close();

    }

    //Method to insert Income Group categories in db.
    public void insertIncomeCategory(String name){

        sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CAT_NAME,name);
        sqLiteDatabase.insert(TABLE_CATEGORY_INCOME,null,values);


    }

    //Method to insert Expense Child items in db.
    public void insertItem(Integer id,String date,String item,String cost){

        sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_CAT,id);
        values.put(COL_DATE, date);
        values.put(COL_ITEM,item);
        values.put(COL_COST,cost);
        sqLiteDatabase.insert(TABLE_NAME,null,values);

        sqLiteDatabase.close();

    }

    //Method to insert Income Child items in db.
    public void insertIncomeItem(Integer id,String date,String item,String cost){

        sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_CAT,id);
        contentValues.put(KEY_DATE,date);
        contentValues.put(KEY_ITEM,item);
        contentValues.put(KEY_COST,cost);
        sqLiteDatabase.insert(TABLE_INCOME,null,contentValues);


        sqLiteDatabase.close();
    }

    //Method to insert Expense Total value in db.
    public void insertExpenseCalc(double expense){

        sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_EXPENSE,expense);
        sqLiteDatabase.insert(EXPENSE_CALC,null,contentValues);
        sqLiteDatabase.close();

    }

    //Method to insert Income Total value in db.
    public void insertIncomeCalc(double income){

        sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_INCOME,income);
        sqLiteDatabase.insert(INCOME_CALC,null,contentValues);
        sqLiteDatabase.close();

    }

    //Method to insert Savings Total value in db.
    public void insertSavingsTotal(double savings){

        sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_SAVINGS,savings);
        sqLiteDatabase.insert(SAVINGS_CALC,null,contentValues);
        sqLiteDatabase.close();


    }


    //Method to read expense child item from db.
    public void getItems(){

        sqLiteDatabase = this.getReadableDatabase();
        Data_Details details = new Data_Details();
        String select_all = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(select_all,null);

        if(cursor.moveToFirst()){


            do{
                int id = cursor.getInt(0);
                int cat_id = cursor.getInt(1);
                String date = cursor.getString(2);
                String item = cursor.getString(3);
                String cost= cursor.getString(4);

                details = new Data_Details(date,item,cost);

                System.out.println("EXPENSE ITEMS============>>>"+id+ "  "  +  cat_id+"  " + date +"   "+item+"   "+ cost);

            }while(cursor.moveToNext());

        }

    }


    //Method to read income child item from db.
    public void getIncomeItems(){

        sqLiteDatabase = this.getReadableDatabase();
        Data_Details details = new Data_Details();
        String select_all = "SELECT * FROM "+ TABLE_INCOME;
        Cursor cursor = sqLiteDatabase.rawQuery(select_all,null);

        if(cursor.moveToFirst()) {


            do {
                int id = cursor.getInt(0);
                int cat_id = cursor.getInt(1);
                String date = cursor.getString(2);
                String item = cursor.getString(3);
                String cost = cursor.getString(4);

                details = new Data_Details(date, item, cost);

                System.out.println("INCOME ITEMS============>>>" + id + "  " + cat_id + "  " + date + "   " + item + "   " + cost);

            } while (cursor.moveToNext());


        }

        }

   //method to get expense group from db.
    public void getGroup(){
        sqLiteDatabase = this.getReadableDatabase();
        String select_all = "SELECT * FROM "+TABLE_CATEGORY;

        Cursor cursor = sqLiteDatabase.rawQuery(select_all,null);


        if(cursor.moveToFirst()){


            do{
                int id = cursor.getInt(0);
                int cat_id = cursor.getInt(1);

                System.out.println("EXPENSE GROUP==============>>>"+id+ "  "  +  cat_id);

            }while(cursor.moveToNext());

        }

    }

    //method to get income group from db.
    public void getIncomeGroup(){
        sqLiteDatabase = this.getReadableDatabase();
        String select_all = "SELECT * FROM " + TABLE_CATEGORY_INCOME;

        Cursor cursor = sqLiteDatabase.rawQuery(select_all,null);


        if(cursor.moveToFirst()){


            do{
                int id = cursor.getInt(0);
                int cat_id = cursor.getInt(1);

                System.out.println("INCOME GROUP============>>>"+id+ "  "  +  cat_id);

            }while(cursor.moveToNext());

        }

    }

    //method to get expense total from db.
    public void getExpense(int id,TextView  expense_textView){

        sqLiteDatabase = this.getReadableDatabase();
        String [] str_args = {EXP_ID,COL_EXPENSE};
        String exp_total = null;

        Cursor cursor = sqLiteDatabase.query(EXPENSE_CALC,str_args,EXP_ID + "=?",new String[] {String.valueOf(id)},null,null,null,null);


        if(cursor.moveToFirst()){

            do{


                int exp_id = cursor.getInt(0);
                exp_total = cursor.getString(cursor.getColumnIndex(COL_EXPENSE));

                System.out.println("EXPENSE TOTAL============>>>"+id+ "  " + "  " +  exp_total);

                expense_textView.setText(exp_total);

            }while(cursor.moveToNext());

        }



            }


    //method to get income total from db.
    public void getIncomeTotal(int id,TextView income_textView){

        sqLiteDatabase = this.getReadableDatabase();
        String [] str_args = {INCOME_ID,COL_INCOME};
        String income_total = null;

        Cursor cursor = sqLiteDatabase.query(INCOME_CALC,str_args,INCOME_ID + "=?",new String[] {String.valueOf(id)},null,null,null,null);


        if(cursor.moveToFirst()){

            do{


                int income_id = cursor.getInt(0);
                income_total = cursor.getString(cursor.getColumnIndex(COL_INCOME));

                System.out.println("INCOME TOTAL============>>>"+id+ "  " + "  " +  income_total);

                income_textView.setText(income_total);
            }while(cursor.moveToNext());

        }

    }


    //method to get Savings total from db.
    public void getSavingsTotal(int id,TextView savings_textView){

        sqLiteDatabase = this.getReadableDatabase();
        String [] str_args = {SAVINGS_ID,COL_SAVINGS};
        String savings_total = null;

        Cursor cursor = sqLiteDatabase.query(SAVINGS_CALC,str_args,SAVINGS_ID + "=?",new String[] {String.valueOf(id)},null,null,null,null);


        if(cursor.moveToFirst()){

            do{


                int income_id = cursor.getInt(0);
                savings_total = cursor.getString(cursor.getColumnIndex(COL_SAVINGS));

                System.out.println("SAVINGS TOTAL============>>>"+id+ "  " + "  " +  savings_total);

                savings_textView.setText(savings_total);
            }while(cursor.moveToNext());

        }

    }



    //method to get expense children items list from db.
    public List<Data_Details> getAllChildren(int grpID){

        List<Data_Details> data_detailsList = new ArrayList<Data_Details>();


        sqLiteDatabase = this.getReadableDatabase();

        String [] str = {COL_CAT,COL_DATE,COL_ITEM,COL_COST};

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,str,COL_CAT + "=?",
                new String[] { String.valueOf(grpID) } ,null,null,null,null);




        if (cursor.moveToFirst()) {
            do {
                Data_Details dataDetails = new Data_Details();
                int id = cursor.getInt(0);
                int cat_id = cursor.getInt(1);
                dataDetails.setDate(cursor.getString(cursor.getColumnIndex(COL_DATE)));
                dataDetails.setItem(cursor.getString(cursor.getColumnIndex(COL_ITEM)));
                dataDetails.setCost(cursor.getString(cursor.getColumnIndex(COL_COST)));
                // Adding NameVO to list
                data_detailsList.add(dataDetails);
            } while (cursor.moveToNext());
        }


        return data_detailsList;
    }

    //method to get income children items list from db.
    public List<Data_Details> getIncomeChildren(int grpId){

        List<Data_Details> detailsList = new ArrayList<Data_Details>();

        sqLiteDatabase = this.getReadableDatabase();

        String [] str_args = {KEY_CAT,KEY_DATE,KEY_ITEM,KEY_COST};

        Cursor cursor = sqLiteDatabase.query(TABLE_INCOME,str_args,KEY_CAT + "=?",new String[] {String.valueOf(grpId)},null,null,null,null);


        if(cursor.moveToFirst()){

            do{

                Data_Details dataDetails = new Data_Details();
                int id = cursor.getInt(0);
                int cat_id = cursor.getInt(1);
                dataDetails.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                dataDetails.setItem(cursor.getString(cursor.getColumnIndex(KEY_ITEM)));
                dataDetails.setCost(cursor.getString(cursor.getColumnIndex(KEY_COST)));
                // Adding NameVO to list
                detailsList.add(dataDetails);


            }while(cursor.moveToNext());


        }

        return detailsList;

    }



    //method to delete expense children item from db.
    public void deleteExpenseData(int grpId,String date,String item,String cost){

        sqLiteDatabase= this.getWritableDatabase();

        sqLiteDatabase.delete(TABLE_NAME,COL_CAT + " =? AND " + COL_DATE + " =? AND " + COL_ITEM + " =? AND " + COL_COST + " =? ", new String[] { String.valueOf(grpId),String.valueOf(date),String.valueOf(item),String.valueOf(cost)});

        sqLiteDatabase.close();


    }

    //method to delete income children item from db.
    public void deleteIncomeData(int grpId,String date,String item,String cost){

        sqLiteDatabase= this.getWritableDatabase();

        sqLiteDatabase.delete(TABLE_INCOME,KEY_CAT + " =? AND " + KEY_DATE + " =? AND " + KEY_ITEM + " =? AND " + KEY_COST + " =? ", new String[] { String.valueOf(grpId),String.valueOf(date),String.valueOf(item),String.valueOf(cost)});

        sqLiteDatabase.close();


    }

    public void deleteExpenseTotal(double exp_value){

        sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete(EXPENSE_CALC ,COL_EXPENSE + "=?",new String[] {String.valueOf(exp_value)});


        sqLiteDatabase.close();



    }


    public void deleteIncomeTotal(double income_value){

        sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete(INCOME_CALC , COL_INCOME + "=?",new String[] {String.valueOf(income_value)});


        sqLiteDatabase.close();



    }

    public int ExpValueUpdate(int id,String total){

        sqLiteDatabase= this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(COL_EXPENSE,total);

        //updating row

        int res = sqLiteDatabase.update(EXPENSE_CALC,values, EXP_ID + "=?",new String[]{String.valueOf(id)});

        return res;

    }

    public int IncomeValueUpdate(int id,String total){

        sqLiteDatabase= this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(COL_INCOME,total);

        //updating row

        int res = sqLiteDatabase.update(INCOME_CALC,values, INCOME_ID + "=?",new String[]{String.valueOf(id)});

        return res;

    }

    public int SavingsValueUpdate(int id,String total){

        sqLiteDatabase= this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(COL_SAVINGS,total);

        //updating row

        int res = sqLiteDatabase.update(SAVINGS_CALC,values, SAVINGS_ID + "=?",new String[]{String.valueOf(id)});

        return res;

    }

}
