package ilovecode.mycustomer.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final String TABLE ="CUSTOMERS";
    public static final String COLUMN_ID = "_ID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_NOTES = "NOTES";
    public static final String COLUMN_DESC = "DESC";
    public static final String COLUMN_DATE = "DATE";
    public static final String TABLE1 ="ACCOUNT";
    public static final String COLUMN_CREATOR = "USER";
    public static final String COLUMN_PASSWORD = "PASS";
    public static final String COLUMN_PERM = "PERM";



    public static final String DB_NAME = "noteds.db";
    private static final int DB_VER = 1;
    private static final String DB_CREATE = "CREATE TABLE "+ TABLE+
            " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" +
            ", " + COLUMN_NAME + " TEXT " +
            ", " + COLUMN_NOTES + " TEXT "+
            ", " + COLUMN_DESC + " TEXT " +
            ", " + COLUMN_DATE + " TEXT " +
            ", " + COLUMN_PERM + " TEXT " +
            ", " + COLUMN_CREATOR + " TEXT )";
    private static final String DB_CREATENEW = "CREATE TABLE "+ TABLE1+
            " (" + COLUMN_CREATOR + " PRIMARY KEY" +
            ", " + COLUMN_PASSWORD + " TEXT)";


    public DbHelper(Context context){
        super(context, DB_NAME, null, DB_VER);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DB_CREATE);
        sqLiteDatabase.execSQL(DB_CREATENEW);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
