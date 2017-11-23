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
    public static final String TABLE2 ="LOGS";
    public static final String COLUMN_ID1 = "ID";
    public static final String COLUMN_FROM = "FROMO";
    public static final String COLUMN_TO = "TOO";
    public static final String COLUMN_NOTE = "NOTENAME";
    public static final String COLUMN_ACTION = "ACTION";
    public static final String COLUMN_TIME = "TIME";
    public static final String TABLE3 ="CHAT";
    //ID

    public static final String COLUMN_COMMENT = "COMMENT";
    //NOTE
    //DATE





    public static final String DB_NAME = "noterinoo.db";
    private static final int DB_VER = 1;
    private static final String DB_CREATE = "CREATE TABLE "+ TABLE+
            " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" +
            ", " + COLUMN_NAME + " TEXT " +
            ", " + COLUMN_NOTES + " TEXT "+
            ", " + COLUMN_DESC + " TEXT " +
            ", " + COLUMN_DATE + " TEXT " +
            ", " + COLUMN_PERM + " TEXT " +
            ", " + COLUMN_CREATOR + " TEXT )";
    private static final String DB_CREATE1 = "CREATE TABLE "+ TABLE1+
            " (" + COLUMN_CREATOR + " PRIMARY KEY" +
            ", " + COLUMN_PASSWORD + " TEXT)";
    private static final String DB_CREATE2 = "CREATE TABLE "+ TABLE2+
            " (" + COLUMN_ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT" +
            ", " + COLUMN_FROM + " TEXT " +
            ", " + COLUMN_ID + " INTEGER " +
            ", " + COLUMN_TO + " TEXT "+
            ", " + COLUMN_NOTE + " TEXT " +
            ", " + COLUMN_ACTION + " TEXT " +
            ", " + COLUMN_TIME + " TEXT )";
    private static final String DB_CREATE3 = "CREATE TABLE "+ TABLE3+
            " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" +//chat id
            ", " + COLUMN_ID1 + " INTEGER " +//NOTE ID
            ", " + COLUMN_COMMENT + " TEXT " +//COMMENT
            ", " + COLUMN_CREATOR + " TEXT " +//USER
            ", " + COLUMN_NOTE + " TEXT " +//NOTE NAME
            ", " + COLUMN_DATE + " TEXT )";//DATE
//add note primary key in db2

    public DbHelper(Context context){
        super(context, DB_NAME, null, DB_VER);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DB_CREATE);
        sqLiteDatabase.execSQL(DB_CREATE1);
        sqLiteDatabase.execSQL(DB_CREATE2);
        sqLiteDatabase.execSQL(DB_CREATE3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
