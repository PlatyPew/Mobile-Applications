package ilovecode.mycustomer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ilovecode.mycustomer.db.DbDataSource;

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
                        startActivityForResult(intent,5);
                        break;
                }
                finish();
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_goto_add_customer was selected
            case R.id.action_showMore:
                startActivityForResult(new Intent(Search.this, More.class), 4);
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