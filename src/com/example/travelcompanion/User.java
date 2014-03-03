package com.example.travelcompanion;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class User {
	public abstract void reserve();

	public abstract void cancel();

	
	/*
	 * Ask a set of questions depending on the current user 
	 */
	public abstract void askQuestions(Scanner sc, ArrayList<Hotel>hotels, ArrayList<String> resInfo);
	
	
}
