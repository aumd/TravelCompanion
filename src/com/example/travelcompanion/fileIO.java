package com.example.travelcompanion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class fileIO {

	private Scanner sc; // Used to read from the file //
	private Scanner input; // Handles user input //
	private PrintWriter pw; //printwrite to write to files//
	private File fileIO; // File we are currently using //
	private int numLines;   //store number of lines in the file
	private String[] lines;  // stores the lines as opposed to the 'number of them'
	private String[][] hotelInfo;  // stores all the hotel info
	private ArrayList<String> resInfo;  // stores all the res info
	
	/*
	 * initialiZes all info needed to file io
	 */
	public fileIO(Scanner input, Scanner sc) 
	{
		this.sc = sc; // Set the reader to the reader passed //
		hotelInfo = new String[12][12];
		resInfo = new ArrayList<String>();
		lines = new String[12];
		this.input = input; // Set the input to input passed //

	}

	
	/*
	 * Writes all the info to the files
	 */
	public void writeTo(PrintWriter pw, ArrayList<Hotel> hotels, ArrayList<String> resInfo, File file) 
	{
		ArrayList <Room> rooms;
		this.pw = pw;
		pw.println("Hotel type ,Room type,Number of Rooms,Occupancy-min,Occupancy-max,Rates,,,,,,");
		pw.println(",,,Adult+child,Adult+child,Mon,Tues,Wed,Thurs,Fri,Sat,Sun");
		
		for(int i=0; i<hotels.size(); i++) // got hrough the list of hotels
		{
			pw.print(hotels.get(i).getRating());  // print rating
			rooms = hotels.get(i).getRoomList(); 
			for( int j=0; j<rooms.size(); j++) // go through rooms list in the current hotel
			{
				pw.print(",");
				pw.print(rooms.get(j).getType()); // print type
				pw.print(",");
				pw.print(rooms.get(j).getNum()); // print number of rooms
				pw.print(",");
				pw.print("1+0");  // always prints this never changes
				pw.print(",");
				
				for(int k=0; k<rooms.get(j).getRates().length;k++) // go through the array of rates fot the current room and print to file
				{
					pw.print(rooms.get(j).getRates()[k]);
					pw.print(",");
				}
				pw.println("");
			}
				
		}
		pw.close();
		try 
		{
			pw = new PrintWriter(file);	 // create a new pw for the resInfo file 
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();	
		}
		for (int i = 0; i< resInfo.size(); i++)
		{
			pw.println(resInfo.get(i)); // print the info in the resInfo list
		}
		pw.close();
		
	}

	
	/*
	 * read in all the info from the hotel file
	 */
	public void readIn() 
	{
		
		int currentLine = 0;  // currentline of the file

		while (sc.hasNextLine()) // While file has something to read //
		{

			String line = sc.nextLine(); // Take the next token and store it //

			lines[currentLine] = line;   //put line into lines
			

			hotelInfo[currentLine] = lines[currentLine].split(","); //put the spline lines into the hotel info array
			
			currentLine++;

		}
		
		sc.close();
	}
	
	
	/*
	 * Same as above only with the resInfo file
	 */
	public void readInResInfo(File file){
		int currentLine = 0;
		try {
			sc = new Scanner(file);
			
			} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (sc.hasNextLine()) // While file has something to read //
		{

			String line = sc.nextLine(); // Take the next token and store it //
			resInfo.add(line);
		}
		
		sc.close();
		
	}


	public void closeScanner() {
		input.close(); // Close user input //
		sc.close(); // Close file reader //
	}

	public void closeWriter() {
		pw.close(); // Close file writer //
	}

	public Scanner getInput() {
		return input;
	}

	public void setInput(Scanner input) {
		this.input = input;
	}

	public File getFile() {
		return fileIO;
	}

	public void setFile(File file) {
		this.fileIO = file;
	}

	public String[][] gethotelInfo() {
		return hotelInfo;
	}
	
	public ArrayList<String> getResInfo() {
		return resInfo;
	}

	public void setResInfo(ArrayList<String> resInfo) {
		this.resInfo = resInfo;
	}

}

