package handle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "usersdb";
    private static final String TABLE_Users = "userdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_LOC = "location";
    private static final String KEY_DESG = "designation";

    public DbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Users + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_LOC + " TEXT,"
                + KEY_DESG + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        onCreate(db);
    }

    // Insert new user
    public void insertUserDetails(String name, String location, String designation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME, name);
        cValues.put(KEY_LOC, location);
        cValues.put(KEY_DESG, designation);
        db.insert(TABLE_Users, null, cValues);
        db.close();
    }

    // Get all users
    public ArrayList<HashMap<String, String>> GetUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, location, designation FROM " + TABLE_Users;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> user = new HashMap<>();
                user.put("name", cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME)));
                user.put("designation", cursor.getString(cursor.getColumnIndexOrThrow(KEY_DESG)));
                user.put("location", cursor.getString(cursor.getColumnIndexOrThrow(KEY_LOC)));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }

    // Get user by ID
    public ArrayList<HashMap<String, String>> GetUserByUserId(int userid) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();

        Cursor cursor = db.query(TABLE_Users,
                new String[]{KEY_NAME, KEY_LOC, KEY_DESG},
                KEY_ID + "=?",
                new String[]{String.valueOf(userid)},
                null, null, null);

        if (cursor.moveToFirst()) {
            HashMap<String, String> user = new HashMap<>();
            user.put("name", cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME)));
            user.put("designation", cursor.getString(cursor.getColumnIndexOrThrow(KEY_DESG)));
            user.put("location", cursor.getString(cursor.getColumnIndexOrThrow(KEY_LOC)));
            userList.add(user);
        }
        cursor.close();
        db.close();
        return userList;
    }

    // Delete user by ID
    public void DeleteUser(int userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Users, KEY_ID + " = ?", new String[]{String.valueOf(userid)});
        db.close();
    }

    // Update user details
    public int UpdateUserDetails(String location, String designation, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_LOC, location);
        cVals.put(KEY_DESG, designation);
        int count = db.update(TABLE_Users, cVals, KEY_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return count;
    }
}
