package ilovecode.mycustomer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ilovecode.mycustomer.db.DbDataSource;

public class RecentPage extends AppCompatActivity {
    public static ArrayList<Log> m_customerArrayList;
    RecyclerView m_recyclerView;
    public static RecentArrayAdapter m_customerArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing list view with the custom adapter
        m_customerArrayList = new ArrayList<Log>();
        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Toast.makeText(view.getContext(), "You have selected position " + position, Toast.LENGTH_SHORT).show();
                Log selectedCustomerToUpdate = m_customerArrayList.get(position);
                int id = selectedCustomerToUpdate.getId();
                String notename = selectedCustomerToUpdate.getNote();
                String action = selectedCustomerToUpdate.getAction();
                //String to = selectedCustomerToUpdate.getTo();
                String from = selectedCustomerToUpdate.getFrom();

                //
                String time = selectedCustomerToUpdate.getTime();
                String total =from+" "+action+" your "+notename+".";

                Intent intent=null;



            }
        };
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        String name= pref.getString("name","name");

        m_customerArrayAdapter = new RecentArrayAdapter(R.layout.recent_list_item, m_customerArrayList,listener,name);
        m_recyclerView = (RecyclerView) findViewById(R.id.RecyclerView_CustomerList);
        m_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        m_recyclerView.setItemAnimator(new DefaultItemAnimator());
        m_recyclerView.setAdapter(m_customerArrayAdapter);
        loadData(name);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Reference: http://www.vogella.com/tutorials/AndroidActionBar/article.html
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }//End of onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_goto_add_customer was selected
            case R.id.action_goto_add_customer:
                startActivityForResult(new Intent(RecentPage.this, AddCustomer.class), 4);
                break;
            case R.id.action_logout:
                startActivityForResult(new Intent(RecentPage.this, MainActivity.class), 4);
                break;
            case R.id.action_viewall:
                startActivityForResult(new Intent(RecentPage.this, ViewPage.class), 4);
                break;
            case R.id.action_search:
                startActivityForResult(new Intent(RecentPage.this, Search.class), 4);
                break;
            case R.id.action_recent:
                startActivityForResult(new Intent(RecentPage.this, RecentPage.class), 4);
                break;
            case android.R.id.home:
                Intent data = new Intent();
                // add data to Intent
                setResult(Activity.RESULT_CANCELED, data);
                Toast.makeText(getApplicationContext(),"Back button clicked", Toast.LENGTH_SHORT).show();
                finish();
                //Don't apply break statement. It will stop the home action.
            default:
                break;
        }
        return true;
    }//End of onOptionsItemSelected(...)

    protected void loadData(String n){
        Log oneCustomer;
        //Note: the m_customerArrayList is declared as class member variable
        //Clear the m_customerArrayList first before opening the database
        m_customerArrayList.clear();
        DbDataSource database = new DbDataSource(this);
        database.open();
        //The following command will retrieve all data from the database
        Cursor cursor = database.selectLikers(n);

        //The following block of code is frequently used by developers to
        //(1)loop through one record at a time and (2) quickily display in a TextView
        //to have some assurance that the database has the records.
        //I obtained these code from
        //https://stackoverflow.com/questions/10723770/whats-the-best-way-to-iterate-an-android-cursor
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex("ID"));
            String name = cursor.getString(cursor.getColumnIndex("TOO"));
            String note = cursor.getString(cursor.getColumnIndex("FROMO"));
            String desc = cursor.getString(cursor.getColumnIndex("NOTENAME"));
            String date = cursor.getString(cursor.getColumnIndex("ACTION"));
            String user = cursor.getString(cursor.getColumnIndex("TIME"));
            oneCustomer = new Log(id,name,note,desc,date,user);
            m_customerArrayList.add(oneCustomer);
            cursor.moveToNext();
        }
        database.close();
        //After executing the code inside loadData() method, need to close the database
    }












}