package com.example.travelcompanion;

import java.util.List;

import com.example.travelcompanion.db.DatabaseLayer;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ShowReservation extends ListActivity {
	DatabaseLayer dbLayer;
	List<String> list;
	ArrayAdapter<String> arAdapter;
	Intent showUpdateReservation;

	@Override
	protected void onListItemClick(ListView lv, View view, int position, long id) {
		String str = (String) getListAdapter().getItem(position);
		showUpdateReservation.putExtra("com.example.travelcompanion.name", str);
		startActivity(showUpdateReservation);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		showUpdateReservation = new Intent(this, UpdateReservation.class);
		dbLayer = new DatabaseLayer(this);
		dbLayer.open();
		list = dbLayer.getNames();
		arAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, list);
		setListAdapter(arAdapter);
		dbLayer.close();
	}
}
