package mapp.noted;

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

import mapp.noted.db.DbDataSource;

public class  ViewPage extends AppCompatActivity {
    public static ArrayList<Note> m_noteArrayList;
    RecyclerView m_recyclerView;
    public static ViewArrayAdapter m_customerArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                String perm = selectedNoteToUpdate.getPerm();
                String users = selectedNoteToUpdate.getUser();

                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                String user= pref.getString("name","name");
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                Date datee = new Date();
                String dateee = dateFormat.format(datee);
                Intent intent=null;
                DbDataSource db = new DbDataSource(ViewPage.this);
                switch(view.getId())  //get the id of the view clicked. (in this case button)
                {
                    case R.id.Button_view : // if its button1

                        intent = new Intent(ViewPage.this,ViewNote.class);

                        intent.putExtra("ID", Integer.toString(id));
                        intent.putExtra("NAME", name);
                        intent.putExtra("NOTE", contact);
                        intent.putExtra("DESCRIPTION", desc);
                        intent.putExtra("DATE", date);
                        intent.putExtra("PERM", perm);
                        intent.putExtra("USER", users);


                        db.open();
                        System.out.println(user+users+name+dateee);
                        db.insertLog(user,users,name,"viewed",dateee,id);
                        //(String from, String to, String note,String action, String time)
                        db.close();


                        startActivityForResult(intent,5);
                        break;
                    case R.id.Button_Star : // if its button1
                        db = new DbDataSource(ViewPage.this);

                        db.open();
                        System.out.println(user+users+name+dateee);
                        db.insertLog(user,users,name,"liked",dateee,id);
                        //(String from, String to, String note,String action, String time)
                        db.close();
                        startActivity(getIntent());
                        break;
                }
            }
        };
        m_customerArrayAdapter = new ViewArrayAdapter(R.layout.view_list_item, m_noteArrayList,listener);
        m_recyclerView = (RecyclerView) findViewById(R.id.RecyclerView_CustomerList);
        m_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        m_recyclerView.setItemAnimator(new DefaultItemAnimator());
        m_recyclerView.setAdapter(m_customerArrayAdapter);
        String name="";
        try{
            name = getIntent().getStringExtra("SEARCH");

        }catch(Exception e){

        }
        System.out.println(name);
        loadData(name);
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
                startActivityForResult(new Intent(this, AddNotes.class), 4);
                break;
            case R.id.logout:
                startActivityForResult(new Intent(this, MainActivity.class), 4);
                break;
            case R.id.all:
                Intent intent=new Intent(this, ViewPage.class);
                intent.putExtra("VIEW", "ALL");
                startActivityForResult(intent,5);
                break;
            case R.id.search:
                startActivityForResult(new Intent(this, Search.class), 4);
                break;
            case R.id.recent:
                startActivityForResult(new Intent(this, RecentPage.class), 4);
                break;
            case R.id.about:
                startActivityForResult(new Intent(this, About.class),4);
                break;
            case R.id.likes:
                Intent intentS = new Intent(this, ViewPage.class);
                intentS.putExtra("VIEW", "LIKE");
                startActivityForResult(intentS,5);
                break;
            case R.id.month:
                Intent intentMonth = new Intent(this, MonthPage.class);
                intentMonth.putExtra("VIEW", "MONTH");
                startActivityForResult(intentMonth,5);
                break;
            case R.id.secret:
                Intent intents = new Intent(this, ViewSecret.class);
                startActivityForResult(intents,5);
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
        Note oneNote;
        //Note: the m_noteArrayList is declared as class member variable
        //Clear the m_noteArrayList first before opening the database
        m_noteArrayList.clear();
        DbDataSource database = new DbDataSource(this);
        database.open();
        //The following command will retrieve all data from the database
        Cursor cursor = database.selectAllCustomers();
        String view=getIntent().getStringExtra("VIEW");
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        String userz= pref.getString("name","name");
        System.out.println(view);
        switch(view) { //get the id of the view clicked. (in this case button)
            case "LIKE" : // if its button1
                int[] k = database.selectLikes(userz);
                cursor = database.selectCustomers(k);
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
                    oneNote.setLikes(database.likes(String.valueOf(id)));
                    m_noteArrayList.add(oneNote);
                    cursor.moveToNext();
                }
                cursor.close();
                database.open();
                break;
            case "ALL" : // if its button1
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
                    String ids=String.valueOf(id);
                    oneNote.setLikes(database.likes(ids));
                    m_noteArrayList.add(oneNote);
                    cursor.moveToNext();
                }
                cursor.close();
                database.close();
                break;
            case "SEARCH" : // if its button1
                cursor = database.search(n);
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
                    String ids=String.valueOf(id);
                    oneNote.setLikes(database.likes(ids));
                    m_noteArrayList.add(oneNote);
                    cursor.moveToNext();
                }
                cursor.close();
                database.close();
                break;

            case "MONTH":
                String monthData =getIntent().getStringExtra("MONTH");
                cursor = database.month(monthData);

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
                    String ids=String.valueOf(id);
                    oneNote.setLikes(database.likes(ids));
                    m_noteArrayList.add(oneNote);
                    cursor.moveToNext();
                }
                cursor.close();
                database.open();
                break;
        }
    }

}
