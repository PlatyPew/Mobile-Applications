package mapp.noted;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import mapp.noted.db.DbDataSource;

public class AddNotes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);


        findViewById(R.id.Button_Save).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Use findViewById to find the textbox inside the activity.
                //Then get the editTextName variable to represent/reference it.
                //Once the editTextName successfully represent that textbox, I will
                //use the getText() which belongs to the editTextName to grab the user input
                EditText editTextName= (EditText)findViewById(R.id.EditText_Name);
                String name = editTextName.getText().toString();
                if (name.equals("")) {
                    name = "Untitled";
                }
                String perm ="pu";
                EditText notee= (EditText)findViewById(R.id.EditText_note);
                String note = notee.getText().toString();
                if (note.equals("")) {
                    note = "Empty like my soul";
                }

                EditText descc= (EditText)findViewById(R.id.EditText_Desc);
                String desc = descc.getText().toString();
                if (desc.equals("")) {
                    desc = "No Description";
                }

                //EditText datee= (EditText)findViewById(R.id.EditText_Date);
                DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                Date datee = new Date();
                String date = dateFormat.format(datee);

                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                String user= pref.getString("name","name");
                RadioButton rb1 = (RadioButton) findViewById(R.id.priv);
                if(rb1.isChecked()){

                    perm="pr";
                }

                DbDataSource db = new DbDataSource(v.getContext());
                Note newNote = new Note(0,name,note,date,desc,user,perm);
                db.open();
                db.insertCustomer(newNote);
                String dateee = dateFormat.format(datee);
                db.insertLog(user,user,name,"created",dateee,0);
                Toast.makeText(v.getContext(), "Saved one note", Toast.LENGTH_SHORT).show();
                db.close();

                finish();
            }
        });//end of setOnClickListener()
    }//End of onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Reference: http://www.vogella.com/tutorials/AndroidActionBar/article.html
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        // add data to Intent
        setResult(Activity.RESULT_CANCELED, data);
        super.onBackPressed();
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                startActivityForResult(new Intent(AddNotes.this, AddNotes.class), 4);
                break;
            case R.id.logout:
                startActivityForResult(new Intent(AddNotes.this, MainActivity.class), 4);
                break;
            case R.id.all:
                Intent intent=new Intent(AddNotes.this, ViewPage.class);
                intent.putExtra("VIEW", "ALL");
                startActivityForResult(intent,5);
                break;
            case R.id.search:
                startActivityForResult(new Intent(AddNotes.this, Search.class), 4);
                break;
            case R.id.recent:
                startActivityForResult(new Intent(AddNotes.this, RecentPage.class), 4);
                break;
            case R.id.about:
                startActivityForResult(new Intent(AddNotes.this, About.class),4);
                break;
            case R.id.likes:
                Intent intentS = new Intent(AddNotes.this, ViewPage.class);
                intentS.putExtra("VIEW", "LIKE");
                startActivityForResult(intentS,5);
                break;
            case R.id.month:
                Intent intentMonth = new Intent(AddNotes.this, MonthPage.class);
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
        }
        return true;
    }

}
