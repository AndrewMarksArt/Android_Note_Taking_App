package com.AndrewsApps.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class NoteDatabase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "notesdb";
    public static final String DATABASE_TABLE = "notestable";

    // column names for database table
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";

    NoteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE tableName(id INT PRIMARY KEY, title TEXT, content TEXT, date TEXT, time TEXT);
        String query = "CREATE TABLE " + DATABASE_TABLE + "(" + KEY_ID+" INT PRIMARY KEY,"+
                KEY_TITLE + " TEXT," +
                KEY_CONTENT + " TEXT," +
                KEY_DATE + " TEXT," +
                KEY_TIME + " TEXT" + ")";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion)
            return;

        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);
    }

    public long addNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();

        c.put(KEY_TITLE, note.getTitle());
        c.put(KEY_CONTENT, note.getContent());
        c.put(KEY_DATE, note.getDate());
        c.put(KEY_TIME, note.getTime());

        long ID = db.insert(DATABASE_TABLE, null, c);
        Log.d("Inserted", "ID -> " + ID);

        return ID;
    }

}
