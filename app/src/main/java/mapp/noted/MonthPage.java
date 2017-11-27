package mapp.noted;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.time.Month;

public class MonthPage extends AppCompatActivity implements View.OnClickListener {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.month);
        Button o= (Button)findViewById(R.id.jan);
        o.setOnClickListener(this);
        Button a= (Button)findViewById(R.id.feb);
        a.setOnClickListener(this);
        Button s= (Button)findViewById(R.id.mar);
        s.setOnClickListener(this);
        Button d= (Button)findViewById(R.id.apr);
        d.setOnClickListener(this);
        Button f= (Button)findViewById(R.id.may);
        f.setOnClickListener(this);
        Button g= (Button)findViewById(R.id.jun);
        g.setOnClickListener(this);
        Button h= (Button)findViewById(R.id.jul);
        h.setOnClickListener(this);
        Button j= (Button)findViewById(R.id.aug);
        j.setOnClickListener(this);
        Button k= (Button)findViewById(R.id.oct);
        k.setOnClickListener(this);
        Button l= (Button)findViewById(R.id.sep);
        l.setOnClickListener(this);
        Button q= (Button)findViewById(R.id.nov);
        q.setOnClickListener(this);
        Button w= (Button)findViewById(R.id.dec);
        w.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Reference: http://www.vogella.com/tutorials/AndroidActionBar/article.html
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public void onClick(View v) {
        //Toast.makeText(view.getContext(), "You have selected position " + position, Toast.LENGTH_SHORT).show();
        Intent intentMonth = new Intent(MonthPage.this, ViewPage.class);
        switch(v.getId())  //get the id of the view clicked. (in this case button)
        {
            case R.id.jan: // if its button1
                intentMonth.putExtra("MONTH", "JAN");
                intentMonth.putExtra("VIEW", "MONTH");
                startActivityForResult(intentMonth,5);
                break;
            case R.id.feb : // if its button1
                intentMonth.putExtra("MONTH", "FEB");
                intentMonth.putExtra("VIEW", "MONTH");
                startActivityForResult(intentMonth,5);
                break;
            case R.id.mar : // if its button1
                intentMonth.putExtra("MONTH", "MAR");
                intentMonth.putExtra("VIEW", "MONTH");
                startActivityForResult(intentMonth,5);
                break;
            case R.id.apr : // if its button1
                intentMonth.putExtra("MONTH", "APR");
                intentMonth.putExtra("VIEW", "MONTH");
                startActivityForResult(intentMonth,5);
                break;
            case R.id.may : // if its button1
                intentMonth.putExtra("MONTH", "MAY");
                intentMonth.putExtra("VIEW", "MONTH");
                startActivityForResult(intentMonth,5);

                break;
            case R.id.jun: // if its button1
                intentMonth.putExtra("MONTH", "JUN");
                intentMonth.putExtra("VIEW", "MONTH");
                startActivityForResult(intentMonth,5);
                break;
            case R.id.jul : // if its button1
                intentMonth.putExtra("MONTH", "JUL");
                intentMonth.putExtra("VIEW", "MONTH");
                startActivityForResult(intentMonth,5);

                break;
            case R.id.aug : // if its button1
                intentMonth.putExtra("MONTH", "AUG");
                intentMonth.putExtra("VIEW", "MONTH");
                startActivityForResult(intentMonth,5);
                break;
            case R.id.sep : // if its button1

                intentMonth.putExtra("MONTH", "SEP");
                intentMonth.putExtra("VIEW", "MONTH");
                startActivityForResult(intentMonth,5);
                break;
            case R.id.oct : // if its button1
                intentMonth.putExtra("MONTH", "OCT");
                intentMonth.putExtra("VIEW", "MONTH");
                startActivityForResult(intentMonth,5);
                break;
            case R.id.nov : // if its button1
                intentMonth.putExtra("MONTH", "NOV");
                intentMonth.putExtra("VIEW", "MONTH");
                startActivityForResult(intentMonth,5);
                break;
            case R.id.dec : // if its button1
                intentMonth.putExtra("MONTH", "DEC");
                intentMonth.putExtra("VIEW", "MONTH");
                startActivityForResult(intentMonth,5);
                break;
        }
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                startActivityForResult(new Intent(MonthPage.this, AddNotes.class), 4);
                break;
            case R.id.logout:
                startActivityForResult(new Intent(MonthPage.this, MainActivity.class), 4);
                break;
            case R.id.all:
                Intent intent=new Intent(MonthPage.this, ViewPage.class);
                intent.putExtra("VIEW", "ALL");
                startActivityForResult(intent,5);
                break;
            case R.id.search:
                startActivityForResult(new Intent(MonthPage.this, Search.class), 4);
                break;
            case R.id.recent:
                startActivityForResult(new Intent(MonthPage.this, RecentPage.class), 4);
                break;
            case R.id.about:
                startActivityForResult(new Intent(MonthPage.this, About.class),4);
                break;
            case R.id.likes:
                Intent intentS = new Intent(MonthPage.this, ViewPage.class);
                intentS.putExtra("VIEW", "LIKE");
                startActivityForResult(intentS,5);
                break;
            case R.id.month:
                Intent intentMonth = new Intent(MonthPage.this, MonthPage.class);
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
    }
}

