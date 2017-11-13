package ilovecode.mycustomer.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final String TABLE_CUSTOMERS ="CUSTOMERS";
    public static final String COLUMN_ID = "_ID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_MOBILE_CONTACT = "MOBILE_CONTACT";



    private static final String DB_NAME = "database.db";
    private static final int DB_VER = 1;
    private static final String DB_CREATE = "CREATE TABLE "+ TABLE_CUSTOMERS+
            " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" +
            ", " + COLUMN_NAME + " TEXT " +
            ", " + COLUMN_MOBILE_CONTACT + " TEXT)";


    public DbHelper(Context context){
        super(context, DB_NAME, null, DB_VER);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
