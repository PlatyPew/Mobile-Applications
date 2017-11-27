package mapp.noted;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.time.Month;

public class MonthPage extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.month);
    }
    public void onClick(View view, int position) {
        //Toast.makeText(view.getContext(), "You have selected position " + position, Toast.LENGTH_SHORT).show();
        Intent intentMonth = new Intent(MonthPage.this, ViewPage.class);
        switch(view.getId())  //get the id of the view clicked. (in this case button)
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

