package mapp.noted;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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
}

