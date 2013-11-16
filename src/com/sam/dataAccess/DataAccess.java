package com.sam.dataAccess;

import com.sam.model.LendItem;
import com.sam.model.LendoLog;
import com.sam.model.Person;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DataAccess extends SQLiteOpenHelper {
 
    // Logcat tag
    private static final String LOG = "DataAccess";
 
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "Lendo";
 
    // Table Names
    private static final String TABLE_PEOPLE = "people";
    private static final String TABLE_LEND_ITEM = "lend_items";
    private static final String TABLE_PEOPLE_LEND_ITEM = "people_lend_items";
    private static final String TABLE_LOG = "log";
 
    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";
    private static final String KEY_NAME = "name";
 
    // PEOPLE Table - column nmaes
    private static final String KEY_PERSON = "person";
 
    // LEND_ITEM Table - column names
    private static final String KEY_ITEM_AMOUNT = "item_amount";
 
    // PEOPLE_LEND_ITEM Table - column names
    private static final String KEY_PERSON_ID = "person_id";
    private static final String KEY_LEND_ITEM_ID = "lend_item_id";
    
    // LOG Table - column names
    private static final String KEY_EVENT = "event";
 
    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_PEOPLE = "CREATE TABLE "
            + TABLE_PEOPLE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PERSON
            + " TEXT," + KEY_NAME + " TEXT," + KEY_CREATED_AT
            + " DATETIME" + ")";
    
    private static final String CREATE_TABLE_LEND_ITEMS = "CREATE TABLE "
            + TABLE_LEND_ITEM + "(" + KEY_ID + " INTEGER PRIMARY KEY," 
    		+ KEY_NAME + " TEXT," 
    		+ KEY_ITEM_AMOUNT + " INTEGER," 
            + KEY_CREATED_AT + " DATETIME" + ")";
    
    private static final String CREATE_TABLE_PEOPLE_LEND_ITEMS = "CREATE TABLE "
            + TABLE_PEOPLE_LEND_ITEM + "(" + KEY_ID + " INTEGER PRIMARY KEY," 
    		+ KEY_PERSON_ID + " INTEGER," 
    		+ KEY_LEND_ITEM_ID + " INTEGER," 
            + KEY_CREATED_AT + " DATETIME" + ")";
    
    private static final String CREATE_TABLE_LOG = "CREATE TABLE "
            + TABLE_PEOPLE + "(" + KEY_ID + " INTEGER PRIMARY KEY," +
    		KEY_EVENT + " TEXT," + KEY_CREATED_AT
            + " DATETIME" + ")";
 
    public DataAccess(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
 
        // creating required tables
        db.execSQL(CREATE_TABLE_PEOPLE);
        db.execSQL(CREATE_TABLE_LEND_ITEMS);
        db.execSQL(CREATE_TABLE_LOG);
        db.execSQL(CREATE_TABLE_PEOPLE_LEND_ITEMS);

    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEOPLE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEND_ITEM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEOPLE_LEND_ITEM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOG);
        
        // create new tables
        onCreate(db);
    }
    
    
    
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
    
    // Person CRUD
    public long createPerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(KEY_ID, person.getId());
        values.put(KEY_NAME, person.getName());
        values.put(KEY_CREATED_AT, getDateTime());
     
        // insert row
        long person_id = db.insert(TABLE_PEOPLE, null, values);
          
        return person_id;
    }
	public Person getPerson(long person_id) {
        SQLiteDatabase db = this.getReadableDatabase();
     
        String selectQuery = "SELECT  * FROM " + TABLE_PEOPLE + " WHERE "
                + KEY_ID + " = " + person_id;
     
        Log.e(LOG, selectQuery);
     
        Cursor c = db.rawQuery(selectQuery, null);
     
        if (c != null)
            c.moveToFirst();
     
        Person td = new Person();
        td.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        td.setName(c.getString(c.getColumnIndex(KEY_NAME)));
        td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
     
        return td;
    }
    
    public int updatePerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(KEY_PERSON, person.getName());
     
        // updating row
        return db.update(TABLE_PEOPLE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(person.getId()) });
    }
    
    public void deletePerson(long person_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PEOPLE, KEY_ID + " = ?",
                new String[] { String.valueOf(person_id) });
    }
    

    // LendItem CRUD
    public long createLendItem(LendItem lendItem) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(KEY_ID, lendItem.getId());
        values.put(KEY_NAME, lendItem.getName());
        values.put(KEY_ITEM_AMOUNT, lendItem.getAmount());
        values.put(KEY_CREATED_AT, getDateTime());
     
        // insert row
        long lendItem_id = db.insert(TABLE_LEND_ITEM, null, values);
          
        return lendItem_id;
    }
	public LendItem getLendItem(long lendItem_id) {
        SQLiteDatabase db = this.getReadableDatabase();
     
        String selectQuery = "SELECT  * FROM " + TABLE_LEND_ITEM + " WHERE "
                + KEY_ID + " = " + lendItem_id;
     
        Log.e(LOG, selectQuery);
     
        Cursor c = db.rawQuery(selectQuery, null);
     
        if (c != null)
            c.moveToFirst();
     
        LendItem td = new LendItem();
        td.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        td.setName(c.getString(c.getColumnIndex(KEY_NAME)));
        td.setAmount(c.getDouble(c.getColumnIndex(KEY_ITEM_AMOUNT)));
        td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
     
        return td;
    }
    public int updateLendItem(LendItem lendItem) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, lendItem.getName());
        values.put(KEY_ITEM_AMOUNT, lendItem.getAmount());
     
        // updating row
        return db.update(TABLE_LEND_ITEM, values, KEY_ID + " = ?",
                new String[] { String.valueOf(lendItem.getId()) });
    }
    public void deleteLendItem(long lendItem_id) {
    	SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LEND_ITEM, KEY_ID + " = ?",
                new String[] { String.valueOf(lendItem_id) });
    }
    
    // Log CR
    public long createLogItem(LendoLog lendoLog) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(KEY_ID, lendoLog.getId());
        values.put(KEY_EVENT, lendoLog.getValue());
        values.put(KEY_CREATED_AT, getDateTime());
     
        // insert row
        long log_id = db.insert(TABLE_LOG, null, values);
          
        return log_id;
    }
	public LendoLog getLogItem(long log_id) {
        SQLiteDatabase db = this.getReadableDatabase();
     
        String selectQuery = "SELECT  * FROM " + TABLE_LOG + " WHERE "
                + KEY_ID + " = " + log_id;
     
        Log.e(LOG, selectQuery);
     
        Cursor c = db.rawQuery(selectQuery, null);
     
        if (c != null)
            c.moveToFirst();
     
        LendoLog td = new LendoLog();
        td.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        td.setValue(c.getString(c.getColumnIndex(KEY_EVENT)));
        td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
     
        return td;
    }
}
