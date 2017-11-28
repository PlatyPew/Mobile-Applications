package mapp.noted;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import mapp.noted.db.DbDataSource;

public class Search extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                switch(view.getId()) {
                    case R.id.search:
                        EditText editTextSearch = findViewById(R.id.EditText_search);
                        String searchString = editTextSearch.getText().toString();
                        DbDataSource db = new DbDataSource(view.getContext());
                        Toast.makeText(view.getContext(), "Searched for: " + searchString, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Search.this, ViewPage.class);
                        intent.putExtra("SEARCH", searchString);
                        intent.putExtra("VIEW", "SEARCH");
                        startActivityForResult(intent,5);
                        break;
                }
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Reference: http://www.vogella.com/tutorials/AndroidActionBar/article.html
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }//End of onCreateOptionsMenu

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

    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        // add data to Intent
        setResult(Activity.RESULT_CANCELED, data);
        super.onBackPressed();
    }
}