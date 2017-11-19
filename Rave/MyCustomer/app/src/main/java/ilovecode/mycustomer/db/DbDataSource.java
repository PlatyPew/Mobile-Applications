package ilovecode.mycustomer.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import ilovecode.mycustomer.Customer;

public class DbDataSource {
    //Create a variable, m_database capable of referencing the SQLiteDatabase
    private SQLiteDatabase m_database;
    //Create a variable, m_dbHelper capable of helping us make the code more readable
    private DbHelper m_dbHelper;
    //Create a variable which capable of describing the running app environment
    private Context m_context;

    public DbDataSource(Context context){
        m_context = context;
        m_dbHelper = new DbHelper(m_context);
    }

    //open data base
    public void open() throws SQLException {
        m_database = m_dbHelper.getWritableDatabase();
    }
    //close
    public void close() {
        m_database.close();
    }


    //insert
    public void insertCustomer(Customer customer) {
        m_database.beginTransaction();
        try{
            ContentValues values = new ContentValues();
            values.put(DbHelper.COLUMN_NAME, customer.getName());
            values.put(DbHelper.COLUMN_NOTES, customer.getNote());
            values.put(DbHelper.COLUMN_DESC, customer.getDesc());
            values.put(DbHelper.COLUMN_DATE, customer.getDate());

            m_database.insert(DbHelper.TABLE, null, values);

            m_database.setTransactionSuccessful();
        } finally {
            m_database.endTransaction();
        }
    }

    //select
    public Cursor selectAllCustomers(){
        Cursor cursor = m_database.rawQuery("Select * from " + DbHelper.TABLE, null);
        return cursor;
    }

    public Cursor selectOneCustomer(int inId){
        Cursor cursor = m_database.rawQuery("Select * from " + DbHelper.TABLE+" where "
                + DbHelper.COLUMN_ID+" = " + inId, null);
        return cursor;
    }

    //update
    public boolean updateCustomer(int inId, String inName, String notes,String desc, String date){
        ContentValues values = new ContentValues();
        int success = -1;
        values.put(DbHelper.COLUMN_NAME, inName);
        values.put(DbHelper.COLUMN_NOTES, notes);
        values.put(DbHelper.COLUMN_DESC, desc);
        values.put(DbHelper.COLUMN_DATE, date);
        success =  m_database.update(
                DbHelper.TABLE,
                values,
                DbHelper.COLUMN_ID + " = " + inId,
                null

        );
        if(success != -1 && success != 0) {
            return true;
        } else {
            return false;
        }

    }



    //delete
    public void delete(int id) {
        //Open the database

        //Execute sql query to remove from database
        //NOTE: When removing by String in SQL, value must be enclosed with ''
        m_database.execSQL("DELETE FROM " + DbHelper.TABLE + " WHERE " + DbHelper.COLUMN_ID + "= " + id + "");

        //Close the database
    }
    public boolean deleteCustomer(int inId) {
        int success = -1;
        success = m_database.delete(
                DbHelper.TABLE,
                DbHelper.COLUMN_ID + " = " + inId,
                null
        );
        if(success != -1 && success !=0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteAllCustomers() {
        int success = -1;
        success = m_database.delete(
                DbHelper.TABLE,
                null,
                null
        );
        if(success != -1 ) {
            return true;
        } else {
            return false;
        }
    }

}
