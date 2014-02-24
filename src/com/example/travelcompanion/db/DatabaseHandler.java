package com.example.travelcompanion.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

	private static int version = 1;
	private static String DATABASE_NAME = "dbReservation.db";
	private static final String TABLE_NAME = "create table reservation (roomName char primary key not null, rentalcost long not null);";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, version);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DatabaseHandler.class.getName(), "Delete database old data");
		db.execSQL(TABLE_NAME);
		onCreate(db);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_NAME);
	}
}