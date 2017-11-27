package mapp.noted;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class More extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more);

        Button addBtn = (Button)findViewById(R.id.add);
        Button logoutBtn = (Button)findViewById(R.id.logout);
        Button allBtn = (Button)findViewById(R.id.all);
        Button searchBtn = (Button)findViewById(R.id.search);
        Button recentBtn = (Button)findViewById(R.id.recent);
        Button aboutBtn = (Button)findViewById(R.id.about);
        Button likesBtn = (Button)findViewById(R.id.likes);
        Button monthBtn = (Button)findViewById(R.id.month);

        addBtn.setOnClickListener(this);
        logoutBtn.setOnClickListener(this);
        allBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
        recentBtn.setOnClickListener(this);
        aboutBtn.setOnClickListener(this);
        likesBtn.setOnClickListener(this);
        monthBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.add:
                startActivityForResult(new Intent(More.this, AddCustomer.class), 4);
                break;
            case R.id.logout:
                startActivityForResult(new Intent(More.this, MainActivity.class), 4);
                break;
            case R.id.all:
                Intent intent=new Intent(More.this, ViewPage.class);
                intent.putExtra("VIEW", "ALL");
                startActivityForResult(intent,5);
                break;
            case R.id.search:
                startActivityForResult(new Intent(More.this, Search.class), 4);
                break;
            case R.id.recent:
                startActivityForResult(new Intent(More.this, RecentPage.class), 4);
                break;
            case R.id.about:
                startActivityForResult(new Intent(More.this, About.class),4);
                break;
            case R.id.likes:
                Intent intentS = new Intent(More.this, ViewPage.class);
                intentS.putExtra("VIEW", "LIKE");
                startActivityForResult(intentS,5);
                break;
            case R.id.month:
                Intent intentMonth = new Intent(More.this, MonthPage.class);
                intentMonth.putExtra("VIEW", "MONTH");
                startActivityForResult(intentMonth,5);
                break;
            case android.R.id.home:
                Intent data = new Intent();
                setResult(Activity.RESULT_CANCELED, data);
                Toast.makeText(getApplicationContext(),"Back button clicked", Toast.LENGTH_SHORT).show();
                finish();
            default:
                break;
        }
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
            // action with ID action_goto_add_customer was selected
            case R.id.action_showMore:
                startActivityForResult(new Intent(More.this, More.class), 4);
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

}