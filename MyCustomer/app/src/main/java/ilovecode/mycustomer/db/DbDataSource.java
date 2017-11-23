package ilovecode.mycustomer.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

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
            values.put(DbHelper.COLUMN_CREATOR, customer.getUser());
            values.put(DbHelper.COLUMN_PERM, customer.getPerm());

            m_database.insert(DbHelper.TABLE, null, values);

            m_database.setTransactionSuccessful();
        } finally {
            m_database.endTransaction();
        }
    }

    public boolean insertUser(String user, String password) {
        m_database.beginTransaction();
        try{
            ContentValues values = new ContentValues();
            values.put(DbHelper.COLUMN_CREATOR, user);
            values.put(DbHelper.COLUMN_PASSWORD, password);
            m_database.insert(DbHelper.TABLE1, null, values);

            m_database.setTransactionSuccessful();
            return true;
        }catch( Exception e){return false;} finally {
            m_database.endTransaction();
        }
    }

    public void insertLog(String from, String to, String note,String action, String time,int id) {

        if (likes(note,from)>0 && action.equals("liked")){
            m_database.execSQL("DELETE FROM " + DbHelper.TABLE2 + " WHERE " + DbHelper.COLUMN_FROM+"=\'"+from+"\' and "+DbHelper.COLUMN_TO+"=\'"+to+"\' and "+DbHelper.COLUMN_NOTE+"=\'"+note+"\' and "+DbHelper.COLUMN_ACTION+"=\'"+action+"\' ");
            //int success = m_database.delete(DbHelper.TABLE2,,new String[]{from,to,note,action});
        }else {
            m_database.beginTransaction();
            try {
                ContentValues values = new ContentValues();
                values.put(DbHelper.COLUMN_FROM, from);
                values.put(DbHelper.COLUMN_TO, to);
                values.put(DbHelper.COLUMN_NOTE, note);
                values.put(DbHelper.COLUMN_ACTION, action);
                values.put(DbHelper.COLUMN_TIME, time);
                values.put(DbHelper.COLUMN_ID, id);
                m_database.insert(DbHelper.TABLE2, null, values);

                m_database.setTransactionSuccessful();
            } catch (Exception e){
                System.out.println(e);
            }finally {
                m_database.endTransaction();
            }
        }
    }
    public Cursor login(String user, String password) {
        Cursor cursor = m_database.rawQuery("SELECT * FROM " + DbHelper.TABLE1+" where "
                + DbHelper.COLUMN_CREATOR+" IS \'" +user+"\' AND "+ DbHelper.COLUMN_PASSWORD+" IS \'" +password+"\';", null);
        return cursor;
    }

    //select
    public Cursor selectAllCustomers(){
        Cursor cursor = m_database.rawQuery("Select * from " + DbHelper.TABLE+" where "+ DbHelper.COLUMN_PERM+" IS \'pu\'", null);
        return cursor;
    }
    public Cursor selectComments(int I){
        Cursor cursor = m_database.rawQuery("Select * from " + DbHelper.TABLE3+" where "+ DbHelper.COLUMN_ID1+" IS \'"+I+"\'", null);
        return cursor;
    }
    public Cursor search(String k){
        Cursor cursor = m_database.rawQuery("SELECT * FROM " + DbHelper.TABLE+" WHERE ("+ DbHelper.COLUMN_ID+" LIKE \'%"+k+"%\' OR "+ DbHelper.COLUMN_NAME+" LIKE \'%"+k+"%\' OR "+ DbHelper.COLUMN_NOTES+" LIKE \'%"+k+"%\' OR "+ DbHelper.COLUMN_DESC+" LIKE \'%"+k+"%\' OR "+ DbHelper.COLUMN_CREATOR+" LIKE \'%"+k+"%\' OR "+ DbHelper.COLUMN_DATE+" LIKE \'%"+k+"%\' )AND "+ DbHelper.COLUMN_PERM+" IS \'pu\' ;", null);
        return cursor;
    }


    //change to make it not just returnn logs but to change logs to customer objects
    public int[] selectLikes(String user){
        Cursor cursor = m_database.rawQuery("Select * from " + DbHelper.TABLE2 +" where "+ DbHelper.COLUMN_FROM+" IS \'" +user+"\' AND "+DbHelper.COLUMN_ACTION+" IS \'liked\'", null);
        cursor.moveToFirst();
        //int [] k={};
        List<Integer> myList = new ArrayList<Integer>();

        int kk=0;
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(DbHelper.COLUMN_ID));

            myList.add(id);
            kk=kk+1;
            System.out.println(id);
            cursor.moveToNext();
        }
        int[] k = new int[myList.size()];
        for (int i = 0; i < k.length; i++) {
            k[i] = myList.get(i);
        }

        return k;
    }
    public int likes(String user){
        Cursor cursor = m_database.rawQuery("Select * from " + DbHelper.TABLE2 +" where "+ DbHelper.COLUMN_NOTE+" IS \'" +user+"\' AND "+DbHelper.COLUMN_ACTION+" IS \'liked\'", null);
        cursor.moveToFirst();
        int k=0;
        while (!cursor.isAfterLast()) {
            k=k+1;
            cursor.moveToNext();
        }
        return k;
    }
    public int likes(String note,String user){
        Cursor cursor = m_database.rawQuery("Select * from " + DbHelper.TABLE2 +" where "+ DbHelper.COLUMN_NOTE+" IS \'" +note+"\' AND "+DbHelper.COLUMN_ACTION+" IS \'liked\' AND "+DbHelper.COLUMN_FROM+" IS \'"+user+"\'", null);
        cursor.moveToFirst();
        int k=0;
        while (!cursor.isAfterLast()) {
            k=k+1;
            cursor.moveToNext();
        }
        cursor.close();
        return k;
    }
    public Cursor selectLikers(String user){
        Cursor cursor = m_database.rawQuery("Select * from " + DbHelper.TABLE2 +" where "+ DbHelper.COLUMN_TO+" IS \'" +user+"\'", null);
        return cursor;
    }
    /*public Cursor selectLikes(String user){
        Cursor cursor = m_database.rawQuery("Select * from " + DbHelper.TABLE2 +" where "+ DbHelper.COLUMN_FROM+" IS \'" +user+"\' AND " + DbHelper.COLUMN_ACTION + " IS \'liked\'", null);
        return cursor;
    }*/

    public Cursor selectAllMine(String user){
        Cursor cursor = m_database.rawQuery("Select * from " + DbHelper.TABLE +" where "+ DbHelper.COLUMN_CREATOR+" IS \'" +user+"\'", null);
        return cursor;
    }

    public Cursor selectOneCustomer(int inId){
        Cursor cursor = m_database.rawQuery("Select * from " + DbHelper.TABLE+" where "
                + DbHelper.COLUMN_ID+" = " + inId, null);
        return cursor;
    }
    public Cursor selectCustomers(int[] inId){
        String query="SELECT * FROM "+DbHelper.TABLE+" WHERE "+DbHelper.COLUMN_ID+" IN (";
        for (int i=0;i<inId.length;i++){
            query=query+" "+inId[i]+" ";
            if((i+1)<inId.length){
                query=query+",";
            }
        }
        query=query+")";
        Cursor cursor = m_database.rawQuery(query, null);
        return cursor;
    }


    //update
    public boolean updateCustomer(int inId, String inName, String notes,String desc, String date, String user,String perm){
        ContentValues values = new ContentValues();
        int success = -1;
        values.put(DbHelper.COLUMN_NAME, inName);
        values.put(DbHelper.COLUMN_NOTES, notes);
        values.put(DbHelper.COLUMN_DESC, desc);
        values.put(DbHelper.COLUMN_DATE, date);
        values.put(DbHelper.COLUMN_CREATOR, user);
        values.put(DbHelper.COLUMN_PERM, perm);
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
