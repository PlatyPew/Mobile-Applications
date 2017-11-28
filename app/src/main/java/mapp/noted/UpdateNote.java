package mapp.noted;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import mapp.noted.db.DbDataSource;

public class UpdateNote extends AppCompatActivity {
    private int id;
    private String name;
    private String note;
    private String desc;
    private String date;
    private String perm;
    private String time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);



        //The code below tries to obtain the information which was passed from
        //the main UI screen. The data was sent to here using putExtra technique.
        //The data is collected here by using the getStringExtra() technique.

        String tempId =  getIntent().getStringExtra("ID");
        id= Integer.parseInt(tempId);

        name = getIntent().getStringExtra("NAME");
        note = getIntent().getStringExtra("NOTE");
        desc = getIntent().getStringExtra("DESCRIPTION");
        date = getIntent().getStringExtra("DATE");
        perm = getIntent().getStringExtra("PERM");


        //After collecting the data, the data is used to display inside
        //the correct controls.
        TextView editTextName= (TextView)findViewById(R.id.EditText_Name);
        editTextName.setText(name);

        TextView editTextMobileContact= (TextView)findViewById(R.id.EditText_notes);
        editTextMobileContact.setText(note);
        TextView editTextdesc= (TextView)findViewById(R.id.EditText_desc);
        editTextdesc.setText(desc);
        RadioButton rb1 = (RadioButton) findViewById(R.id.priv);
        RadioButton rb = (RadioButton) findViewById(R.id.publi);
        if(perm.equals("pr")){
            rb1.setChecked(true);
        }else{
            rb.setChecked(true);
        }

        findViewById(R.id.Button_Save).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Use findViewById to find the textbox inside the activity.
                //Then get the editTextName variable to represent/reference it.
                //Once the editTextName successfully represent that textbox, I will
                //use the getText() which belongs to the editTextName to grab the user input
                EditText editTextName= (EditText)findViewById(R.id.EditText_Name);
                String nameee = editTextName.getText().toString();

                EditText notee= (EditText)findViewById(R.id.EditText_notes);
                String noteee = notee.getText().toString();

                EditText descc= (EditText)findViewById(R.id.EditText_desc);
                String descee = descc.getText().toString();

                //EditText datee= (EditText)findViewById(R.id.EditText_Date);
                DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
                Date datee = new Date();
                String dates = "Last edited on: "+dateFormat.format(datee);
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                String user= pref.getString("name","name");
                String perm="pu";
                RadioButton rb1 = (RadioButton) findViewById(R.id.priv);
                if(rb1.isChecked()){

                    perm="pr";
                }

                DbDataSource db = new DbDataSource(v.getContext());

                db.open();
                db.updateCustomer(id,nameee,noteee,descee,dates,user,perm);
                Toast.makeText(v.getContext(), "edited one note", Toast.LENGTH_SHORT).show();


                String dateee = dateFormat.format(datee);

                db.insertLog(user,user,name,"updated",dateee,id);


                finish();
                if (perm.equals("pr")){
                    Intent intentMonth = new Intent(UpdateNote.this,Secret.class);
                    intentMonth.putExtra("TIEM",dateee);
                    startActivityForResult(intentMonth,5);
                }

            }
        });//end of setOnClickListener()
    }//End of onCreate


    //-------Need these code (onBackPressed, onOptionsItemSelected) to handle user action when
    // they click the back arrow icon in action bar
    //                 and the mobile phone's default back button .
    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        // add data to Intent
        setResult(Activity.RESULT_CANCELED, data);
        super.onBackPressed();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
    //----------------------------------------------------------------------------------------------------
}
