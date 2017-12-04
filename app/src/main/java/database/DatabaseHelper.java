package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import entity.History;

/**
 * Created by Emilcia on 03.12.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "refuelHistory.db";
    private static final String TABLE_NAME = "refuel_history";
    private static final String COLUMN_1 = "NAME";
    private static final String COLUMN_2 = "LATITUDE";
    private static final String COLUMN_3 = "LONGITUDE";
    private static final String COLUMN_4 = "LITERS";
    private static final String COLUMN_5 = "PRICE";
    private static final String COLUMN_6 = "DATE";
    private static final String CREATE_TABLE = "CREATE TABLE REFUEL_HISTORY (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, latitude REAL, longitude REAL, liters REAL, price REAL, date TEXT)";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS REFUEL_HISTORY";
    private static final String SELECT_ALL = "SELECT date FROM REFUEL_HISTORY";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void insert(History history) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_1, history.getName());
        contentValues.put(COLUMN_2, history.getLatitude());
        contentValues.put(COLUMN_3, history.getLongitude());
        contentValues.put(COLUMN_4, history.getLiters());
        contentValues.put(COLUMN_5, history.getPrice());
        contentValues.put(COLUMN_6, String.valueOf(history.getDate()));

        db.insert(TABLE_NAME, null, contentValues);
    }

    public List<String> getAllDates(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor data = db.rawQuery(SELECT_ALL, null);

        ArrayList<String> history = new ArrayList<>();

        while(data.moveToNext()){
            history.add(data.getString(0));
        }
        data.close();

        return history;
    }
}
