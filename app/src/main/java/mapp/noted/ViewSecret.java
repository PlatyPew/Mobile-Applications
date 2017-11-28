package mapp.noted;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import mapp.noted.db.DbDataSource;

public class ViewSecret extends AppCompatActivity {
    public static ArrayList<Note> m_noteArrayList;
    RecyclerView m_recyclerView;
    public static NoteArrayAdapter m_noteArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_secret);

        Button buttonOne = (Button) findViewById(R.id.Button_login);
        buttonOne.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                DbDataSource db = new DbDataSource(v.getContext());
                db.open();

                EditText editTextName = (EditText) findViewById(R.id.EditText_Name);
                String name = editTextName.getText().toString();

                EditText passwordd = (EditText) findViewById(R.id.EditText_password);
                String password = passwordd.getText().toString();

                String text="";
                try {
                    byte[] data = Base64.decode(name, Base64.DEFAULT);

                    text = new String(data, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Cursor cursor = db.check(text, password);
                System.out.println(text+password);
                cursor.moveToFirst();
                 if (((cursor!= null)&&(cursor.getCount()>0))) {

                     cursor=db.selectOneNote(text);
                     cursor.moveToFirst();
                     String namez="";
                     String note ="";
                     String desc ="";
                     String date="";
                     String user="";
                     String perm ="";
                     int id=0;
                     while (!cursor.isAfterLast()) {
                         id = cursor.getInt(cursor.getColumnIndex("_ID"));
                          namez = cursor.getString(cursor.getColumnIndex("NAME"));
                          note = cursor.getString(cursor.getColumnIndex("NOTES"));
                          desc = cursor.getString(cursor.getColumnIndex("DESC"));
                          date = cursor.getString(cursor.getColumnIndex("DATE"));
                          user = cursor.getString(cursor.getColumnIndex("USER"));
                          perm = cursor.getString(cursor.getColumnIndex("PERM"));
                         //oneNote = new Note(id,name,note,date,desc,user,perm);
                         //String ids=String.valueOf(id);
                         //oneNote.setLikes(database.likes(ids));
                         //m_noteArrayList.add(oneNote);
                         cursor.moveToNext();
                     }
                     SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                     String users= pref.getString("name","name");
                     db.insertLog(users,user,namez,"viewed",date,id);
                     
                     //(String from, String to, String note,String action, String time)
                     db.close();
                     Intent intent = new Intent(ViewSecret.this,ViewNote.class);
                     intent.putExtra("ID", Integer.toString(id));
                     intent.putExtra("NAME", namez);
                     intent.putExtra("NOTE", note);
                     intent.putExtra("DESCRIPTION", desc);
                     intent.putExtra("DATE", date);
                     intent.putExtra("PERM", perm);
                     startActivityForResult(intent,5);
                } else {
                     Toast.makeText(getApplicationContext(),"Wrong note name or password!", Toast.LENGTH_SHORT).show();
                 }
                db.close();
            }

        });



    }
}




