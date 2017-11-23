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

import java.util.ArrayList;

import ilovecode.mycustomer.db.DbDataSource;

public class ChatPage extends AppCompatActivity {
    public static ArrayList<Chat> m_customerArrayList;
    RecyclerView m_recyclerView;
    public static ChatArrayAdapter m_customerArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing list view with the custom adapter
        m_customerArrayList = new ArrayList<Chat>();
        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Toast.makeText(view.getContext(), "You have selected position " + position, Toast.LENGTH_SHORT).show();
                Chat selectedCustomerToUpdate = m_customerArrayList.get(position);
                int id = selectedCustomerToUpdate.getId();
                String notename = selectedCustomerToUpdate.getNote();
                String comment = selectedCustomerToUpdate.getComment();
                int id1 = selectedCustomerToUpdate.getId1();//new
                String time = selectedCustomerToUpdate.getTime();
                String creator = selectedCustomerToUpdate.getCreator();

                Intent intent=null;



            }
        };
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        String name= pref.getString("name","name");

        m_customerArrayAdapter = new ChatArrayAdapter(R.layout.chat_list_item, m_customerArrayList,listener,name);
        m_recyclerView = (RecyclerView) findViewById(R.id.RecyclerView_CustomerList);
        m_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        m_recyclerView.setItemAnimator(new DefaultItemAnimator());
        m_recyclerView.setAdapter(m_customerArrayAdapter);
        String ID1 = getIntent().getStringExtra("ID");
        int ID=Integer.parseInt(ID1);
        loadData(ID);
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
            case R.id.action_showMore:
                startActivityForResult(new Intent(ChatPage.this, More.class), 4);
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

    protected void loadData(int n){
        Chat oneCustomer;
        //Note: the m_customerArrayList is declared as class member variable
        //Clear the m_customerArrayList first before opening the database
        m_customerArrayList.clear();
        DbDataSource database = new DbDataSource(this);
        database.open();
        //The following command will retrieve all data from the database
        Cursor cursor = database.selectComments(n);

        //The following block of code is frequently used by developers to
        //(1)loop through one record at a time and (2) quickily display in a TextView
        //to have some assurance that the database has the records.
        //I obtained these code from
        //https://stackoverflow.com/questions/10723770/whats-the-best-way-to-iterate-an-android-cursor
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex("_ID"));
            int id1  = cursor.getInt(cursor.getColumnIndex("ID"));
            String comment = cursor.getString(cursor.getColumnIndex("COMMENT"));
            String notes = cursor.getString(cursor.getColumnIndex("NOTENAME"));
            String creator = cursor.getString(cursor.getColumnIndex("USER"));
            String time = cursor.getString(cursor.getColumnIndex("DATE"));
            oneCustomer = new Chat(id,id1,comment,notes,creator,time);
            //int inId, String to, String from, String notes, String action, String time){//,
            m_customerArrayList.add(oneCustomer);
            cursor.moveToNext();
        }
        database.close();
        //After executing the code inside loadData() method, need to close the database
    }












}
