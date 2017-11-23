package ilovecode.mycustomer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ViewCustomer extends AppCompatActivity {
    private int id;
    private String name;
    private String note;
    private String desc;
    private String date;
    private String user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_customer);



        //The code below tries to obtain the information which was passed from
        //the main UI screen. The data was sent to here using putExtra technique.
        //The data is collected here by using the getStringExtra() technique.

        String tempId =  getIntent().getStringExtra("ID");
        id= Integer.parseInt(tempId);

        name = getIntent().getStringExtra("NAME");
        note = getIntent().getStringExtra("NOTE");
        desc = getIntent().getStringExtra("DESCRIPTION");
        date = getIntent().getStringExtra("DATE");
        user = getIntent().getStringExtra("USER");


        //After collecting the data, the data is used to display inside
        //the correct controls.
        TextView editTextName= (TextView)findViewById(R.id.EditText_Name);
        editTextName.setText("Name : "+name);

        TextView editTextMobileContact= (TextView)findViewById(R.id.EditText_note);
        editTextMobileContact.setText(note);
        TextView editTextdesc= (TextView)findViewById(R.id.EditText_Desc);
        editTextdesc.setText("Description : "+desc);
        TextView editTextdate= (TextView)findViewById(R.id.EditText_Date);
        editTextdate.setText(date);
        //SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        //String user= pref.getString("name","name");
        TextView creat= (TextView)findViewById(R.id.EditText_creator);
        creat.setText("Created by :"+user);



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
