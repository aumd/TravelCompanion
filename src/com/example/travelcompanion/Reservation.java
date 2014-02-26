package com.example.travelcompanion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class Reservation {

	private static final Object[][] Hotel = null;
	private ArrayList<Hotel> hotel;// Info for all the hotels //
	private ArrayList<String> resInfo;  // Info for the current reservation//
	private User currentUser; // Current user using the system//
	private Scanner sc; // Used for taking file input //
	private Scanner input; // Used for taking user input //
	private PrintWriter pw; // Used for writing to file //
	private int resNum, rooms; // Res num and number of rooms //
	private double totalCost; // Total cost of the reservation//
	private double deposit; // Deposit (if any) //
	private String name; // Name of the customer //
	private com.example.travelcompanion.fileIO fileIO; // fileIO variable //
	private String[][] hotelInfo;
	private Date date; // Current date //
	private File hotelsInfo; // Hotel file //
	private String filepath; // Filepath for the hotel file //
	private File resInfoFile; // Reservation file//
	private String resInfoFilePath;   // filepath for reservation file
	private File file;
	
	
	
	/*
	 * Initialising all the resources needed
	 * for the system,such as the files and 
	 * filapaths needed, including the containers
	 * that will hold the data while the system is
	 * running e.g the arraylists
	 */
	public Reservation() {
		hotel = new ArrayList<Hotel>();      // Initialise the hotels arrayList//
		resInfo = new ArrayList<String>();    // Initialise the resInfo arrayList//
		filepath = "hotelsInfo.csv"; // Initialise the filepath //
		resInfoFilePath = "resInfo.csv";//Initialise the res filepath//
		resInfoFile = new File(resInfoFilePath);  //Initialise the res file//
		setFile(new File(filepath)); // Set the file to be used //
		input = new Scanner(System.in); // Initialise user input //
		
		
		try
		{
			sc = new Scanner(file); // Try and create a new Scanner //
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace(); // If unsuccessful, print error //
		}
		
		fileIO = new fileIO(input, sc); // Create a new fileIO //
	}
	
	
	
	/*
	 * Initialises the interface for the user,depending 
	 * on what you choose, you will be presented with 
	 * one of three sets of questions
	 */
	public void login() 
	{
		System.out.println("Please choose the type of user (1) for Customer, (2) for DeskAdmin, (3) for Supervisor.");

		int choice = input.nextInt(); // Stores the choice of the above question//
		
		try {
			switch (choice) // Like an 'if' statement, just takes the choice variable and depending on what you chose it will go to the case number// 
			{
			case 1:         // if you chose '1' then you will go down this path 
				currentUser = new CurrentUser();
				break;      // it will break out of the switch and go to the askQuestions call below


			}
		} catch (Exception e) {
			System.err.println("O_o oooops Error");
		}

		/*
		 * Ask a specific set of questions for the current user
		 */
		
		currentUser.askQuestions(input, hotel, resInfo);
		
	}

	
	/*
	 * Just sets up all the information needed for the system 
	 * to run properly, reads in files assigns info to the 
	 * containers(arrayLists) 
	 */
	public void startInterface() throws Exception {
		fileIO.readIn();
		fileIO.readInResInfo(resInfoFile);
		hotelInfo = fileIO.gethotelInfo();
		resInfo = fileIO.getResInfo();
		assignInfo();                //call assign info method in this class//
		login();                     //call login method in this class// 
		
	}
	
	/*
	 * write to the file and close the system 
	 */
	public void closeInterface()
	{
		
		try 
		{
			pw = new PrintWriter(file); //try to create a new printwriter with the hotelinfo file	
		}
		catch (Exception e)
		{
			System.err.println("File not found");
			System.exit(0);
		}
		
	
		fileIO.writeTo(pw, hotel, resInfo, resInfoFile); // call the fileIO method 'writeTo'
	}
	
	
	/*
	 * Takes all the info from the file and puts it into 
	 * the arraylists so we can access the info and process
	 * it to suit the users needs
	 */
	public void assignInfo(){
		
		String occupancy = null; // holds occupancy
		String[] occArr = new String[2];   // holds the "1,0" comlumn in the file
		for(int i=2; i<hotelInfo.length; i++){ //moving down the 2D array of hotels,start at row two because all othe info is useless
			
			if(!((ArrayList<com.example.travelcompanion.Hotel>) Hotel[i][0]).isEmpty()) //if first column has something in it
			{
				Hotel hotel = new Hotel();         // create a new hotel//
				hotel.setRating(hotelInfo[i][0]);  // set rating to the first column//
				hotel.add(hotel);                 // add the hotel to the arraylist
				
				for(int j=i;  ;j++) // go around in a loop infinitely, the middle space is empty so there is no condition, we have to 'break' it
				{
					if(j>=hotelInfo.length) //if j has gone past the size of the array
						break;              // break out of the loop 
					
					else if(j>i && !hotelInfo[j][0].isEmpty())  //otherwise if j is greater than i and first column is not empty
						break;                                   // break out of the loop
					
					Room room = new Room(hotelInfo[j][1]);          //create a new room 
					room.setNum(Integer.parseInt(hotelInfo[j][2]));  // set number of rooms for the created room to the third column 
					
					occupancy = hotelInfo[j][4];         //set occupancy to the fifth comlumn
					occArr = occupancy.split("\\+");     //assign occArr the 'split' of occupancy variable from the line before this one 
					
					room.setMaxAdults(Integer.parseInt(occArr[0]));       //set the max adults to the first item in occArr array
					room.setMaxChildren(Integer.parseInt(occArr[1]));     //set the max children to the second item in occArr array
					

					for(int k =5 ; k < hotelInfo[ hotelInfo.length-1 ].length ; k++ ) //start at five because we want to start at the sixth column in the csv
					{
						room.setDayRate(k-5, Double.parseDouble(hotelInfo[j][k])); //set the day rate to k -5 because the array in room starts at 0
					}
					hotel.addRoom(room); // add the room to hotel;
				}
			}
		}
	
		//System.out.println(hotels.get(2).getRoomList().get(2).getNum());
	}


	public int getResNum() {
		return resNum;
	}

	public void setResNum(int resNum) {
		this.resNum = resNum;
	}

	public int getRooms() {
		return rooms;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public File getFile() {
		return hotelsInfo;
	}

	public void setFile(File file) {
		this.file = file;
	}

}

