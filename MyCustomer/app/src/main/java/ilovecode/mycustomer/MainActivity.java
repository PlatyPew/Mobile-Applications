package ilovecode.mycustomer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.RecyclerView;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import ilovecode.mycustomer.db.DbDataSource;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Customer> m_customerArrayList;
    RecyclerView m_recyclerView;
    public static CustomerArrayAdapter m_customerArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button buttonOne = (Button) findViewById(R.id.Button_login);
        buttonOne.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                DbDataSource db = new DbDataSource(v.getContext());
                db.open();

                EditText editTextName = (EditText) findViewById(R.id.EditText_Name);
                String name = editTextName.getText().toString();

                EditText passwordd = (EditText) findViewById(R.id.EditText_password);
                String password = passwordd.getText().toString();
                Cursor cursor = db.login(name, password);
                 if (((cursor!= null)&&(cursor.getCount()>0))) {
                     SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                     SharedPreferences.Editor editor = pref.edit();
                     editor.putString("name", name);

                     editor.commit(); // commit changes
                     Toast.makeText(getApplicationContext(),"Logged in!", Toast.LENGTH_SHORT).show();

                     Intent intent = new Intent(MainActivity.this, MainPage.class);
                     startActivityForResult(intent, 5);
                } else {
                     Toast.makeText(getApplicationContext(),"Wrong username or password", Toast.LENGTH_SHORT).show();


                 }
                db.close();
            }

        });
        Button buttontwo = (Button) findViewById(R.id.Button_signup);
        buttontwo.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                DbDataSource db = new DbDataSource(v.getContext());
                db.open();

                EditText editTextNames = (EditText) findViewById(R.id.EditText_Name);
                String names = editTextNames.getText().toString();
                if (names.equalsIgnoreCase("You")||names.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "Invalid name try again", Toast.LENGTH_SHORT).show();
                }else {
                    EditText passwordds = (EditText) findViewById(R.id.EditText_password);
                    String passwords = passwordds.getText().toString();
                    if(db.insertUser(names, passwords)) {
                        db.close();
                        Toast.makeText(getApplicationContext(), "User " + names + " created!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Error creating user " + names + "!", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });


    }
}




