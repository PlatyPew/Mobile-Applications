package ilovecode.mycustomer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


import ilovecode.mycustomer.db.DbDataSource;

/**
 * Created by Daryl on 21/11/2017.
 */

public class Search extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editTextName= (EditText)findViewById(R.id.EditText_txt);
                String nameee = editTextName.getText().toString();
                Intent intent = new Intent(Search.this,SearchPage.class);
                intent.putExtra("SEARCH", nameee);
                startActivityForResult(intent, 5);

            }
        });//end of setOnClickListener()

    }
}
