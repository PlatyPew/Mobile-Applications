package mapp.noted;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
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

import mapp.noted.db.DbDataSource;

public class MainPage extends AppCompatActivity {
    public static ArrayList<Note> m_noteArrayList;
    RecyclerView m_recyclerView;
    public static NoteArrayAdapter m_noteArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);

        // Initializing list view with the custom adapter
        m_noteArrayList = new ArrayList<Note>();
        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Toast.makeText(view.getContext(), "You have selected position " + position, Toast.LENGTH_SHORT).show();
                Note selectedNoteToUpdate = m_noteArrayList.get(position);
                int id = selectedNoteToUpdate.getId();
                String name = selectedNoteToUpdate.getName();
                String contact = selectedNoteToUpdate.getNote();
                String desc = selectedNoteToUpdate.getDesc();
                String date = selectedNoteToUpdate.getDate();
                String perm= selectedNoteToUpdate.getPerm();
                String users= selectedNoteToUpdate.getUser();

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
                        intent = new Intent(MainPage.this,ViewNote.class);
                        intent.putExtra("ID", Integer.toString(id));
                        intent.putExtra("NAME", name);
                        intent.putExtra("NOTE", contact);
                        intent.putExtra("DESCRIPTION", desc);
                        intent.putExtra("DATE", date);
                        intent.putExtra("PERM", perm);

                        startActivityForResult(intent,5);
                        break;
                    case R.id.Button_Edit : // if its button1
                        intent = new Intent(MainPage.this,UpdateNote.class);
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
        m_noteArrayAdapter = new NoteArrayAdapter(R.layout.note_list_item, m_noteArrayList,listener);
        m_recyclerView = (RecyclerView) findViewById(R.id.RecyclerView_CustomerList);
        m_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        m_recyclerView.setItemAnimator(new DefaultItemAnimator());
        m_recyclerView.setAdapter(m_noteArrayAdapter);

        loadData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Reference: http://www.vogella.com/tutorials/AndroidActionBar/article.html
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                startActivityForResult(new Intent(MainPage.this, AddNotes.class), 4);
                break;
            case R.id.logout:
                startActivityForResult(new Intent(MainPage.this, MainActivity.class), 4);
                break;
            case R.id.all:
                Intent intent=new Intent(MainPage.this, ViewPage.class);
                intent.putExtra("VIEW", "ALL");
                startActivityForResult(intent,5);
                break;
            case R.id.search:
                startActivityForResult(new Intent(MainPage.this, Search.class), 4);
                break;
            case R.id.recent:
                startActivityForResult(new Intent(MainPage.this, RecentPage.class), 4);
                break;
            case R.id.about:
                startActivityForResult(new Intent(MainPage.this, About.class),4);
                break;
            case R.id.likes:
                Intent intentS = new Intent(MainPage.this, ViewPage.class);
                intentS.putExtra("VIEW", "LIKE");
                startActivityForResult(intentS,5);
                break;
            case R.id.month:
                Intent intentMonth = new Intent(MainPage.this, MonthPage.class);
                intentMonth.putExtra("VIEW", "MONTH");
                startActivityForResult(intentMonth,5);
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
        Note oneNote;
        //Note: the m_noteArrayList is declared as class member variable
        //Clear the m_noteArrayList first before opening the database
        m_noteArrayList.clear();
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
            oneNote = new Note(id,name,note,date,desc,user,perm);
            m_noteArrayList.add(oneNote);
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
            m_noteArrayAdapter.notifyDataSetChanged();
        }
        if ((requestCode == 5)&&(resultCode == Activity.RESULT_CANCELED)) {
            //Log.v("MainActivity","You visited the Edit screen and clicked Home");
            //Toast.makeText(getBaseContext(), "You visited the Edit screen and clicked Home or Back", Toast.LENGTH_SHORT).show();
            loadData();//Update the recylcerview to reflect the changes
            m_noteArrayAdapter.notifyDataSetChanged();
        }
    }
}
