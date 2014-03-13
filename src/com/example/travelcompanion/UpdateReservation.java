package com.example.travelcompanion;

import com.example.travelcompanion.db.*;
import com.example.travelcompanion.Reservation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateReservation extends Activity {
	DatabaseLayer dbLayer;
	Button btnUpdate, btnDelete, btnShow;
	long valNum = 0;
	String mbd;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_config);
		dbLayer = new DatabaseLayer(this);
		final Intent showUpdateReservation = getIntent();
		mbd = showUpdateReservation.getStringExtra("com.example.travelcompanion.name");
		TextView tv = (TextView) findViewById(R.id.name);
		btnUpdate = (Button) findViewById(R.id.btnUpdate);
		btnDelete = (Button) findViewById(R.id.btnDelete);
		btnShow = (Button) findViewById(R.id.btnShow);
		tv.setText(mbd.toLowerCase());
		btnUpdate.setOnClickListener(onUpdate);
		btnDelete.setOnClickListener(onDelete);
		btnShow.setOnClickListener(onShow);
	}

	View.OnClickListener onShow = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			dbLayer.open();
			long numFee = dbLayer.getFee(mbd);
			dbLayer.close();
			String numFee1 = null;
			alertMessage("Reservation Fee:" + numFee);

		}
	};

	View.OnClickListener onDelete = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			try {
				dbLayer.open();
				dbLayer.deleteReservation(mbd);
				dbLayer.close();
				alertMessage(mbd + " Deleted successfully");
			} catch (Exception e) {
				e.printStackTrace();
				alertMessage("Deletetion Error");
			}
		}
	};

	View.OnClickListener onUpdate = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			updateDialog();
		}
	};

	public void updateDialog() {
		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
		alertBuilder.setTitle("Update Reservation");
		alertBuilder.setMessage("messagge");
		final EditText newAmount = new EditText(this);
		alertBuilder.setCancelable(false);
		alertBuilder.setView(newAmount);

		alertBuilder.setPositiveButton("Edit",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						valNum = Long.parseLong(newAmount.getText().toString());
						try {
							dbLayer.open();
							Reservation reservation = new Reservation();
							reservation.setName(mbd);
							reservation.setFee(valNum);
							dbLayer.updateReservation(reservation);
							alertMessage("Update Successfully");

						} catch (Exception e) {
							e.printStackTrace();
							alertMessage("Update Error!");
						} finally {
							dbLayer.close();
						}

					}
				});
		alertBuilder.setNegativeButton("Cancel", null);
		alertBuilder.show();
	}

	public void alertMessage(String dialogMsg) {
		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
		alertBuilder.setTitle("Hotel Reservation");
		alertBuilder.setMessage(dialogMsg);
		alertBuilder.setCancelable(false);
		alertBuilder.setPositiveButton("Ok", null);
		alertBuilder.show();
	}
}