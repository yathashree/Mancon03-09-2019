package com.anitha.offsitefinal.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.anitha.offsitefinal.model.User;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Anitha.db";

    // User table name
    private static final String TABLE_USER = "user";

    // User Table Columns names
    private static final String COLUMN_USER_CLOCK= "user_clock";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_CPASSWORD = "user_cpassword";
    private static final String COLUMN_USER_MOB = "user_mob";
    private static final String COLUMN_USER_BRANCH = "user_branch";
    private static final String COLUMN_USER_HUB = "user_hub";
    private static final String COLUMN_USER_PHOTO = "user_photo";





    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_CLOCK + " INTEGER PRIMARY KEY ," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT," +COLUMN_USER_CPASSWORD + " TEXT," + COLUMN_USER_MOB + " LONG," +COLUMN_USER_BRANCH+ " TEXT," +COLUMN_USER_HUB + " TEXT," +COLUMN_USER_PHOTO + " BLOB)";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);

        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_CLOCK, user.getClock());
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_CPASSWORD, user.getcPassword());
        values.put(COLUMN_USER_MOB, user.getMob());
        values.put(COLUMN_USER_BRANCH, user.getBranch());
        values.put(COLUMN_USER_HUB, user.getHub());
        values.put(COLUMN_USER_PHOTO, user.getPhoto());



        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }





    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_CLOCK,
                COLUMN_USER_NAME,
                COLUMN_USER_EMAIL,
                COLUMN_USER_PASSWORD,
                COLUMN_USER_CPASSWORD,
                COLUMN_USER_MOB,
                COLUMN_USER_BRANCH,
                COLUMN_USER_HUB,
                COLUMN_USER_PHOTO

        };

        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(TABLE_USER,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);



        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setClock(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_CLOCK))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                user.setcPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_CPASSWORD)));
                user.setMob(Long.parseLong(cursor.getString(cursor.getColumnIndex(COLUMN_USER_MOB))));
                user.setBranch(cursor.getString(cursor.getColumnIndex(COLUMN_USER_BRANCH)));
                user.setHub(cursor.getString(cursor.getColumnIndex(COLUMN_USER_HUB)));
                user.setPhoto(cursor.getBlob(cursor.getColumnIndex(COLUMN_USER_PHOTO)));



                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_USER_CLOCK, user.getClock());
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_CPASSWORD, user.getcPassword());
        values.put(COLUMN_USER_MOB, user.getMob());
        values.put(COLUMN_USER_BRANCH, user.getBranch());
        values.put(COLUMN_USER_HUB, user.getHub());
        values.put(COLUMN_USER_PHOTO, user.getPhoto());



        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_CLOCK + " = ?",
                new String[]{String.valueOf(user.getClock())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_CLOCK + " = ?",
                new String[]{String.valueOf(user.getClock())});
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param clock
     * @return true/false
     */
    public boolean checkUser(String clock) {


        String[] columns = {
                COLUMN_USER_CLOCK
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_CLOCK + " = ?";

        // selection argument
        String[] selectionArgs = {clock};


        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_CLOCK
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};


        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
}
