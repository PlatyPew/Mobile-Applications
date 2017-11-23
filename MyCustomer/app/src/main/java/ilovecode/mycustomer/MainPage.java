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
import android.util.Log;
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

public class MainPage extends AppCompatActivity {
    public static ArrayList<Customer> m_customerArrayList;
    RecyclerView m_recyclerView;
    public static CustomerArrayAdapter m_customerArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing list view with the custom adapter
        m_customerArrayList = new ArrayList<Customer>();
        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Toast.makeText(view.getContext(), "You have selected position " + position, Toast.LENGTH_SHORT).show();
                Customer selectedCustomerToUpdate = m_customerArrayList.get(position);
                int id = selectedCustomerToUpdate.getId();
                String name = selectedCustomerToUpdate.getName();
                String contact = selectedCustomerToUpdate.getNote();
                String desc = selectedCustomerToUpdate.getDesc();
                String date = selectedCustomerToUpdate.getDate();
                String perm=selectedCustomerToUpdate.getPerm();
                String users=selectedCustomerToUpdate.getUser();

                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                String user= pref.getString("name","name");
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                Date datee = new Date();
                String dateee = dateFormat.format(datee);
                Intent intent=null;
                switch(view.getId())  //get the id of the view clicked. (in this case button)
                {
                    case R.id.Button_view : // if its button1

                        DbDataSource db = new DbDataSource(MainPage.this);

                        db.open();
                        System.out.println(user+users+name+dateee);
                        db.insertLog(user,users,name,"viewed",dateee,id);
                        //(String from, String to, String note,String action, String time)
                        db.close();
                        intent = new Intent(MainPage.this,ViewCustomer.class);

                        intent.putExtra("ID", Integer.toString(id));
                        intent.putExtra("NAME", name);
                        intent.putExtra("NOTE", contact);
                        intent.putExtra("DESCRIPTION", desc);
                        intent.putExtra("DATE", date);
                        intent.putExtra("PERM", perm);



                        startActivityForResult(intent,5);
                        break;
                    case R.id.Button_Edit : // if its button1
                        intent = new Intent(MainPage.this,UpdateCustomer.class);

                        intent.putExtra("ID", Integer.toString(id));
                        intent.putExtra("NAME", name);
                        intent.putExtra("NOTE", contact);
                        intent.putExtra("DESCRIPTION", desc);
                        intent.putExtra("DATE", date);
                        intent.putExtra("PERM", perm);




                        startActivityForResult(intent,5);
                        break;
                    case R.id.Button_Delete:
                        db = new DbDataSource(view.getContext());
                        db.open();
                        db.deleteCustomer(id);
                        finish();
                        startActivity(getIntent());
                        break;

                }


            }
        };
        m_customerArrayAdapter = new CustomerArrayAdapter(R.layout.customer_list_item, m_customerArrayList,listener);
        m_recyclerView = (RecyclerView) findViewById(R.id.RecyclerView_CustomerList);
        m_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        m_recyclerView.setItemAnimator(new DefaultItemAnimator());
        m_recyclerView.setAdapter(m_customerArrayAdapter);

        loadData();
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
                startActivityForResult(new Intent(MainPage.this, More.class), 4);
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

    protected void loadData(){
        Customer oneCustomer;
        //Note: the m_customerArrayList is declared as class member variable
        //Clear the m_customerArrayList first before opening the database
        m_customerArrayList.clear();
        DbDataSource database = new DbDataSource(this);
        database.open();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        String users= pref.getString("name","name");
        //The following command will retrieve all data from the database
        Cursor cursor = database.selectAllMine(users);
        //The following block of code is frequently used by developers to
        //(1)loop through one record at a time and (2)quickily display in a TextView
        //to have some assurance that the database has the records.
        //I obtained these code from
        //https://stackoverflow.com/questions/10723770/whats-the-best-way-to-iterate-an-android-cursor
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex("_ID"));
            String name = cursor.getString(cursor.getColumnIndex("NAME"));
            String note = cursor.getString(cursor.getColumnIndex("NOTES"));
            String desc = cursor.getString(cursor.getColumnIndex("DESC"));
            String date = cursor.getString(cursor.getColumnIndex("DATE"));
            String user = cursor.getString(cursor.getColumnIndex("USER"));
            String perm = cursor.getString(cursor.getColumnIndex("PERM"));
            oneCustomer = new Customer(id,name,note,date,desc,user,perm);
            m_customerArrayList.add(oneCustomer);
            cursor.moveToNext();
        }
        database.close();
        //After executing the code inside loadData() method, need to close the database
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //I was not sure whether this onActivityResult will execute.
        //Therefore, I used Log.d to check.
        Log.v("MainActivity","Request code" + requestCode);
        if (requestCode == 4) {
            Log.v("MainActivity","Executed onActivityResult 4");
            loadData();//Update the recylcerview to reflect the changes
            m_customerArrayAdapter.notifyDataSetChanged();
        }
        if ((requestCode == 5)&&(resultCode == Activity.RESULT_CANCELED)) {
            Log.v("MainActivity","You visited the Edit screen and clicked Home");
            Toast.makeText(getBaseContext(), "You visited the Edit screen and clicked Home or Back", Toast.LENGTH_SHORT).show();
            loadData();//Update the recylcerview to reflect the changes
            m_customerArrayAdapter.notifyDataSetChanged();
        }
    }
}
