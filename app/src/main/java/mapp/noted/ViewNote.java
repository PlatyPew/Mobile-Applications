package mapp.noted;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ViewNote extends AppCompatActivity {
    private int id;
    private String name;
    private String note;
    private String desc;
    private String date;
    private String user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_note);



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
        editTextName.setText(name);

        TextView editTextMobileContact= (TextView)findViewById(R.id.EditText_note);
        editTextMobileContact.setText(note);
        TextView editTextdesc= (TextView)findViewById(R.id.EditText_Desc);
        editTextdesc.setText("Description: "+desc);
        TextView editTextdate= (TextView)findViewById(R.id.EditText_Date);
        editTextdate.setText(date);
        //SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        //String user= pref.getString("name","name");
        TextView creat= (TextView)findViewById(R.id.EditText_creator);
        creat.setText("Created by: "+user);



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
    //----------------------------------------------------------------------------------------------------
}
