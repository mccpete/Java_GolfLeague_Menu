import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

// Determine how many items are in file?

public class GolfLeagueMenu {
	static Scanner keyboard;

	public static void main(String[] args) throws IOException 
	{
		ArrayList<Golfer> golfLeagueMembers = new ArrayList<Golfer>();

    	keyboard = new Scanner(System.in);
   	
    	if( readDataFromFile(golfLeagueMembers) )
    	{
    		int menuItemSelected = 0;
    		do
    		{
    			displayMenu();
    			menuItemSelected = getMenuSelection();
    			executeSelectedMenuItem(menuItemSelected, golfLeagueMembers);
    			
    		} while( menuItemSelected != 8);
   		}
    	
    	keyboard.close();
	}
	
	public static void displayMenu()
	{
		System.out.println("MENU: ");
		System.out.println("\t1. Display All Golfers.");
		System.out.println("\t2. Display a Golfer");
		System.out.println("\t3. Change a Golfers Score");
		System.out.println("\t4. Change All scores for a Golfer");
		System.out.println("\t5. Change a Golfers name");
		System.out.println("\t6. Delete a Golfer");
		System.out.println("\t7. Add NEW Golfer");
		System.out.println("\t8. Exit");
	}
	
	public static int getMenuSelection()
	{
		System.out.print("Menu Choice: ");
		return keyboard.nextInt();
	}
	
	public static void executeSelectedMenuItem(int menuItemSelected, ArrayList<Golfer> golfLeagueMembers) throws IOException 
	{
		if(menuItemSelected == 1) {
			displayAllGolfers(golfLeagueMembers);
			
		} else if (menuItemSelected == 2) {
			displayGolfer(golfLeagueMembers);
			
		} else if (menuItemSelected == 3) {
			changeGolfersScore(golfLeagueMembers);
			
		} else if (menuItemSelected == 4) {
			changeAllScoresForAGolfer(golfLeagueMembers);
			
		} else if (menuItemSelected == 5) {
			changeGolfersName(golfLeagueMembers);
			
		} else if (menuItemSelected == 6) {
			deleteGolfer(golfLeagueMembers);
			
		} else if (menuItemSelected == 7) {
			addGolfer(golfLeagueMembers);
			
		} else if (menuItemSelected == 8) {
			System.out.println("Saving Changes.....");
			saveDataToFile(golfLeagueMembers);
			System.out.println("Exiting.....");
			
		} else {
			System.out.println("** Error ** Invalid selection try again!!!!");
			
		}
	}
	
	public static boolean readDataFromFile(ArrayList<Golfer> golfLeagueMembers)   throws IOException 
	{
		File file = new File("GolfLeague.txt");
        if( !file.exists() )
        {
        	System.out.println("The file \"GolfLeague.txt\" does not exist or cannot be found!  This program requires that file before it can be run.\n");
        	return false;
        } else {
        	
        	Scanner inputFile = new Scanner(file);
    		
        	// What to do if an error is not read from file properly.
        	
        	int i = 0;
        	while(inputFile.hasNextLine())
        	{
        		String record = inputFile.nextLine();
        		golfLeagueMembers.add( new Golfer(record) );
        		Golfer duplicateName = findGolfer(golfLeagueMembers, golfLeagueMembers.get(i).getName(), true);
				if(duplicateName != null)
				{
					i++;
				} else {
					System.out.println(golfLeagueMembers.get(i).getName() + ": Golfer already exists in database.  Duplicate entrys not allowed, ignoring 2nd golfer....");
				}
        	}
        	
    		inputFile.close();
       }
        
       return true;
	}
	
	public static void saveDataToFile(ArrayList<Golfer> golfLeagueMembers) throws IOException 
	{
    	PrintWriter w = new PrintWriter("GolfLeague.txt");
    	for(int i = 0; i < golfLeagueMembers.size(); i++)
    	{
			golfLeagueMembers.get(i).writeData(w);
    	}
    	w.close();

	}
	
	public static void displayAllGolfers(ArrayList<Golfer> golfLeagueMembers)
	{
		if( numberOfGolfers(golfLeagueMembers) <= 0)
		{
			System.out.println("There are NO golfers currently in this Golf League......");
		} else {
			for(int i = 0; i < golfLeagueMembers.size(); i++)
			{
				System.out.println(golfLeagueMembers.get(i));
			}
		}
	}
	
	public static int numberOfGolfers(ArrayList<Golfer> golfLeagueMembers)
	{
		return golfLeagueMembers.size();
	}
	
	public static Golfer findGolfer(ArrayList<Golfer> golfLeagueMembers, String nameToFind, boolean suppressError)
	{
		for(int i = 0; i < golfLeagueMembers.size(); i++)
		{
			if (golfLeagueMembers.get(i).isSameGolfer(nameToFind) )
			{
				return golfLeagueMembers.get(i);
			}
		}
		
		if(!suppressError)
			System.out.println(nameToFind + " - Golfer not found.....");
		
		return null;
	}
    
	public static int findGolferByIndex(ArrayList<Golfer> golfLeagueMembers, String nameToFind, boolean suppressError)
	{
	
		for(int i = 0; i < golfLeagueMembers.size(); i++)
		{
			if (golfLeagueMembers.get(i).isSameGolfer(nameToFind) )
			{
				return i;
			}
		}
		
		if(!suppressError)
			System.out.println(nameToFind + " - Golfer not found.....");
		
		return -1;
	}

	public static String getGolfersName(String prompt)
	{
		System.out.print(prompt + ":  ");
		return keyboard.next();
	}
	
	public static int[] getGolfersScores()
	{
		int[] scores = null;
		
		System.out.print("How many scores would you like to enter for this golfer:  ");
		int numberOfScores = keyboard.nextInt();
		
		if(numberOfScores <= 0)
		{
			System.out.println("Number of scores entered must be > 0");
		} else {
			scores = new int[numberOfScores];
			
			for(int i = 0; i < numberOfScores; i++)
			{
				System.out.print("Enter score #" + (i+1) + ": ");
				scores[i] = keyboard.nextInt();

				while(!Golfer.isValidScore(scores[i]))
				{
					System.out.print("Score is out of range, please re-enter score #" + (i+1) + ":  ");
					scores[i] = keyboard.nextInt();
				}
			}
		
		}
		
		return scores;
			
	}
	
	public static void displayGolfer(ArrayList<Golfer> golfLeagueMembers)
	{
		String golfersNameToFind = getGolfersName("Enter Golfers Name to lookup");
		
		Golfer golfer = findGolfer(golfLeagueMembers, golfersNameToFind, false);
		if( golfer != null )
		{
			System.out.println(golfer.toString());
		}
	}
	
	public static void changeGolfersName(ArrayList<Golfer> golfLeagueMembers)
	{
		String golfersNameToFind = getGolfersName("Enter Golfers Name to lookup");
		
		Golfer golfer = findGolfer(golfLeagueMembers, golfersNameToFind, false);
		if( golfer != null )
		{
			String newName = getGolfersName("Enter NEW golfer name");
			
			Golfer duplicateGolferName = findGolfer(golfLeagueMembers, newName, true);   
			if( duplicateGolferName == null )
			{
				golfer.setName(newName);
			} else {
				System.out.println(golfersNameToFind + " already exists in golf league.  Must use a different name.......");				
			}
		} 
	}

	public static void changeGolfersScore(ArrayList<Golfer> golfLeagueMembers)
	{
		String golfersNameToFind = getGolfersName("Enter Golfers Name to lookup");
		Golfer golfer = findGolfer(golfLeagueMembers, golfersNameToFind, false);
		if( golfer != null )
		{
			System.out.println(golfer.toString());
			
			System.out.print("Which score would you like to change:  ");
			int weekNumber = keyboard.nextInt();
			
			System.out.print("Enter NEW score:  ");
			int newScore = keyboard.nextInt();
			
			boolean indexOutOfRange = golfer.setScore(weekNumber - 1, newScore);
			if( indexOutOfRange )
				System.out.println("The score you want to change does not exist");
		}
	}
	
	public static void changeAllScoresForAGolfer(ArrayList<Golfer> golfLeagueMembers)
	{
		String golfersNameToFind = getGolfersName("Enter Golfers Name to lookup");
		Golfer golfer = findGolfer(golfLeagueMembers, golfersNameToFind, false);
		if( golfer != null )
		{
			int[] scores = getGolfersScores();
			golfer.setScores(scores);
		}
	}

	public static void deleteGolfer(ArrayList<Golfer> golfLeagueMembers)
	{
		String golfersNameToFind = getGolfersName("Enter Golfers Name to lookup");
		int i = findGolferByIndex(golfLeagueMembers, golfersNameToFind, false);
		if( i >= 0 )
		{
			golfLeagueMembers.remove(i);
		}
	}

	public static void addGolfer(ArrayList<Golfer> golfLeagueMembers)
	{
		String newName = getGolfersName("Enter new name");
		Golfer golfer = findGolfer(golfLeagueMembers, newName, true);
		if( golfer != null )
		{
			System.out.println("New Golfer cannot be added.   It is already in Database!!!!!!!");
		} else {
			int[] scores = getGolfersScores();
			golfLeagueMembers.add(new Golfer(newName, scores));
		}
	}
		
	// How would you add a new score to each golfer.
}
