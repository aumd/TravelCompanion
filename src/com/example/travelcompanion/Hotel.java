package com.example.travelcompanion;

import java.util.ArrayList;

public class Hotel 
{

	private ArrayList<Room> rooms = new ArrayList<Room>();  // list of rooms in the current hotel 
	private String rating; // rating of the hotel

	
	public void addRoom(Room room) {
		rooms.add(room);
	}

	public ArrayList<Room> getRoomList() {
		return rooms;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public void add(Hotel hotel) {
		
	}
}

