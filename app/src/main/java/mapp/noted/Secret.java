package mapp.noted;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import mapp.noted.db.DbDataSource;

public class Secret extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secret);

        String view=getIntent().getStringExtra("TIEM");
        byte[] data=null;
        try {
            data = view.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String base64 = Base64.encodeToString(data, Base64.DEFAULT);
        TextView myAwesomeTextView = (TextView)findViewById(R.id.ID);

//in your OnCreate() method
        System.out.println(base64);
        myAwesomeTextView.setText("ID : "+base64);

        findViewById(R.id.search).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Use findViewById to find the textbox inside the activity.
                //Then get the editTextName variable to represent/reference it.
                //Once the editTextName successfully represent that textbox, I will
                //use the getText() which belongs to the editTextName to grab the user input
                EditText editTextName= (EditText)findViewById(R.id.EditText_search);
                String name = editTextName.getText().toString();


                DbDataSource db = new DbDataSource(v.getContext());



                db.open();
                String view=getIntent().getStringExtra("TIEM");
                db.insertPassword(view,name);

                Toast.makeText(v.getContext(), "Saved one note", Toast.LENGTH_SHORT).show();
                db.close();

                finish();
               /* if (perm.equals("pr")){
                    Intent intentMonth = new Intent(Secret.this, Secret.class);
                    intentMonth.putExtra("ID", );
                    startActivityForResult(intentMonth,5);
                }*/
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
                startActivityForResult(new Intent(Secret.this, Secret.class), 4);
                break;
            case R.id.logout:
                startActivityForResult(new Intent(Secret.this, MainActivity.class), 4);
                break;
            case R.id.all:
                Intent intent=new Intent(Secret.this, ViewPage.class);
                intent.putExtra("VIEW", "ALL");
                startActivityForResult(intent,5);
                break;
            case R.id.search:
                startActivityForResult(new Intent(Secret.this, Search.class), 4);
                break;
            case R.id.recent:
                startActivityForResult(new Intent(Secret.this, RecentPage.class), 4);
                break;
            case R.id.about:
                startActivityForResult(new Intent(Secret.this, About.class),4);
                break;
            case R.id.likes:
                Intent intentS = new Intent(Secret.this, ViewPage.class);
                intentS.putExtra("VIEW", "LIKE");
                startActivityForResult(intentS,5);
                break;
            case R.id.month:
                Intent intentMonth = new Intent(Secret.this, MonthPage.class);
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
