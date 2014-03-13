package com.example.travelcompanion.db;

import java.util.ArrayList;
import java.util.List;

import com.example.travelcompanion.Reservation;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseLayer {
	String reservationName[] = { "name" };
	String reservationRoomType[] = { "roomType" };
	String reservationRoomNumber[] = { "roomNumber" };
	String reservationFee[] = { "fee" };
	String reservationDate[] = { "date" };
	DatabaseHandler dbHandler = null;
	SQLiteDatabase db = null;

	public void open() throws SQLException {
		db = dbHandler.getWritableDatabase();
	}

	public DatabaseLayer(Context context) {
		dbHandler = new DatabaseHandler(context);
	}

	public boolean saveReservation(Reservation svreservation) {
		ContentValues cvalues = new ContentValues();
		cvalues.put("name", svreservation.getName());
		cvalues.put("roomType", svreservation.getRoomType());
		cvalues.put("roomNumber", svreservation.getRoomNumber());
		cvalues.put("fee", svreservation.getFee());
		cvalues.put("date", svreservation.getDate());
		long id = db.insert("reservation", null, cvalues);
		if (id > 0) {
			return true;
		} else {
			return false;
		}
	}

	public List<String> getNames() {
		List<String> reservationList = new ArrayList<String>();
		Cursor cursor = db.query("reservation", reservationName, null, null, null,
				null, null);
		while (cursor.moveToNext()) {
			String strc = cursor.getString(0);
			reservationList.add(strc);
		}
		return reservationList;
	}

	public boolean updateReservation(Reservation edtreservation) {
		ContentValues cvalues = new ContentValues();
		String name[] = { edtreservation.getName() };
		long id = db.update("reservation", cvalues, "name=?", name);
		if (id > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void deleteReservation(String delReservation) {
		String strDelReservation[] = { delReservation };
		db.delete("reservation", "name=?", strDelReservation);
	}

	public long getFee(String gtfee) {
		long numZ;
		numZ = 0;
		String strFee[] = { gtfee };
		Cursor cursor = db.query("reservation", reservationFee, "name=?",
				strFee, null, null, null);
		while (cursor.moveToNext()) {
			numZ = cursor.getLong(0);
		}
		return numZ;
	}

	public void close() {
		db.close();
	}

}