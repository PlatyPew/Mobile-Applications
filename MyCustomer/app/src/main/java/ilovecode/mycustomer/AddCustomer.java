package ilovecode.mycustomer;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ilovecode.mycustomer.db.DbDataSource;

public class AddCustomer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);


        findViewById(R.id.Button_Save).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Use findViewById to find the textbox inside the activity.
                //Then get the editTextName variable to represent/reference it.
                //Once the editTextName successfully represent that textbox, I will
                //use the getText() which belongs to the editTextName to grab the user input
                EditText editTextName= (EditText)findViewById(R.id.EditText_Name);
                String name = editTextName.getText().toString();

                EditText editTextMobileContact= (EditText)findViewById(R.id.EditText_MobileContact);
                String mobileContact = editTextMobileContact.getText().toString();

                DbDataSource db = new DbDataSource(v.getContext());
                Customer newCustomer = new Customer(0,name,mobileContact);

                db.open();
                db.insertCustomer(newCustomer);
                Toast.makeText(v.getContext(), "Saved one customer", Toast.LENGTH_SHORT).show();
                db.close();

                finish();
            }
        });//end of setOnClickListener()
    }//End of onCreate

    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        // add data to Intent
        setResult(Activity.RESULT_OK, data);
        super.onBackPressed();
    }

}
