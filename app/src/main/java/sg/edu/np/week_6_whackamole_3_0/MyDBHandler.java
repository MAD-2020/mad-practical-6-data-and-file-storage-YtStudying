package sg.edu.np.week_6_whackamole_3_0;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.logging.Level;

public class MyDBHandler extends SQLiteOpenHelper {
    /*
        The Database has the following properties:
        1. Database name is WhackAMole.db
        2. The Columns consist of
            a. Username
            b. Password
            c. Level
            d. Score
        3. Add user method for adding user into the Database.
        4. Find user method that finds the current position of the user and his corresponding
           data information - username, password, level highest score for each level
        5. Delete user method that deletes based on the username
        6. To replace the data in the database, we would make use of find user, delete user and add user

        The database shall look like the following:

        Username | Password | Level | Score
        --------------------------------------
        User A   | XXX      | 1     |    0
        User A   | XXX      | 2     |    0
        User A   | XXX      | 3     |    0
        User A   | XXX      | 4     |    0
        User A   | XXX      | 5     |    0
        User A   | XXX      | 6     |    0
        User A   | XXX      | 7     |    0
        User A   | XXX      | 8     |    0
        User A   | XXX      | 9     |    0
        User A   | XXX      | 10    |    0
        User B   | YYY      | 1     |    0
        User B   | YYY      | 2     |    0

     */

    private static final String FILENAME = "MyDBHandler.java";
    private static final String TAG = "Whack-A-Mole3.0!";

    private static final String DATABASE_NAME = "WhackAMole.db";
    public static int DATABASE_VERSION = 1;
    private static final String TABLE = "WAM";
    private static final String ID = "ID";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";
    private static final String LEVEL = "Level";
    private static final String SCORE = "Score";

    public MyDBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    /* public MyDBHandler(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
        HINT:
            This is used to init the database.

    }
    */

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        /* HINT:
            This is triggered on DB creation.
            Log.v(TAG, "DB Created: " + CREATE_ACCOUNTS_TABLE);
         */

        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Username TEXT, Password TEXT, Level TEXT, Score TEXT)";

        db.execSQL(CREATE_PRODUCTS_TABLE);

        Log.v(TAG, "DB Created: " + TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        /* HINT:
            This is triggered if there is a new version found. ALL DATA are replaced and irreversible.
         */
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void addUser(UserData userData)
    {
            /* HINT:
                This adds the user to the database based on the information given.
                Log.v(TAG, FILENAME + ": Adding data for Database: " + values.toString());
             */
        ContentValues values = new ContentValues();
        values.put(USERNAME, userData.getMyUserName());
        values.put(PASSWORD, userData.getMyPassword());
        values.put(LEVEL, userData.getLevels().toString());
        values.put(SCORE, userData.getScores().toString());
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE, null, values);

        Log.v(TAG, FILENAME + ": Adding data for Database: " + values.toString());

        db.close();

    }

    public UserData findUser(String username)
    {
        /* HINT:
            This finds the user that is specified and returns the data information if it is found.
            If not found, it will return a null.
            Log.v(TAG, FILENAME +": Find user form database: " + query);

            The following should be used in getting the query data.
            you may modify the code to suit your design.

            if(cursor.moveToFirst()){
                do{
                    ...
                    .....
                    ...
                }while(cursor.moveToNext());
                Log.v(TAG, FILENAME + ": QueryData: " + queryData.getLevels().toString() + queryData.getScores().toString());
            }
            else{
                Log.v(TAG, FILENAME+ ": No data found!");
            }
         */
        String query = "SELECT * FROM " + TABLE + " WHERE "
                + USERNAME
                + " = \"" + username + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        UserData us = new UserData();

        int lvl, score;
        ArrayList<Integer> levelList = new ArrayList<>();
        ArrayList<Integer> scoreList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            us.setMyUserName(cursor.getString(0));
            us.setMyPassword(cursor.getString(1));
            lvl = Integer.parseInt(cursor.getString(2));
            levelList.add(lvl);
            score = Integer.parseInt(cursor.getString(3));
            scoreList.add(score);
        }

        while(cursor.moveToNext()){
            lvl = Integer.parseInt(cursor.getString(2));
            levelList.add(lvl);
            score = Integer.parseInt(cursor.getString(3));
            scoreList.add(score);
        }

        Log.v(TAG, FILENAME +": Find user form database: " + query);

        cursor.close();

        db.close();

        return us;
    }

    public boolean deleteAccount(String username) {
        /* HINT:
            This finds and delete the user data in the database.
            This is not reversible.
            Log.v(TAG, FILENAME + ": Database delete user: " + query);
         */

        boolean result = false;

        String query = "SELECT * FROM " + TABLE + " WHERE "
                + USERNAME + " = \""
                + username + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        UserData us = new UserData();

        if (cursor.moveToFirst()) {
            us.setMyUserName(cursor.getString(0));
            db.delete(TABLE, ID + " = ?",
                    new String[] { String.valueOf(us.getMyUserName()) });
            cursor.close();
            result = true;
        }

        Log.v(TAG, FILENAME + ": Database delete user: " + query);

        db.close();
        return result;
    }
}
