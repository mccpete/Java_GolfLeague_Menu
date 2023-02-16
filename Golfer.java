
/**
 * Write a description of class Golfer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Golfer
{
	private String name;
	private ArrayList<Integer> scores;
    
	public Golfer()
	{
		setName("");
		scores = new ArrayList<Integer>();
	}
    
	public Golfer(String newName, int[] newScores)
	{
		setName(newName);
		scores = new ArrayList<Integer>();
   		copyScores(newScores);
	}
    

	public String getName()
	{
		return name;
	}

	public int[] getScores()
	{
		int[] newScores = toArray();
		return newScores;
	}
	
	public int getScore(int index)
	{
		if(index >= 0 && index <= scores.size())
			return scores.get(index);
		
		return -1;
	}

	public void setName(String newName)
	{
		
		if( newName.equals("") )
		{
			name = "Unknown";
			
		} else {
			name = newName;
		}
	}	

	public boolean setScore(int index, int newScore)
	{
		if(index < 0 || index > scores.size())
		{
			return true;
		}
		
		scores.set(index, Math.max(0, newScore));
		
		return newScore < 0;
	}
	
	public void setScores(int[] newScores)
	{
   		copyScores(newScores);
	}
	
	public int grandTotal()
	{
		int total = 0;
		for(int index = 0; index < scores.size(); index++)
		{
			total += scores.get(index);
		}
        
		return total;
	}

	public int lowestNight()
	{
		if( scores.size() == 0 )
			return -1;
		
		int lowestNight = 0;
        
		for(int index = 1; index < scores.size(); index++)
		{
			if(scores.get(index) < scores.get(lowestNight))
			{
				lowestNight = index;
			}
		}
      
		return lowestNight;
	}
    
	public int lowestScore()
	{
		int index = lowestNight();
		if( index == -1 )
			return -1;
		
		return scores.get(index);
	}
  
	public int highestNight()
	{
		if( scores.size() == 0 )
			return -1;
		
		int highestNight = 0;
	        
		for(int index = 1; index < scores.size(); index++)
		{
			if(scores.get(index) < scores.get(highestNight))
			{
				highestNight = index;
			}
		}
	        
		return highestNight;
	}
	
	public int highestScore()
	{
		int index = highestNight();
		if( index == -1 )
			return -1;
		
		return scores.get(index);
	}
	
	public double averageScore()
	{
		return (scores.size() <= 0) ? 0 : (double) grandTotal() / scores.size();
	}
  
	public int handicap()
	{
		return Math.max((grandTotal() - highestScore() - lowestScore()) / (scores.size() - 2) - 36, 0);
	}

	public String toString()
	{
		String record = name;
		for(int i = 0; i < scores.size(); i++)
		{
			record += " " + scores.get(i);
		}
		
		return record;
	}
	
//	public Golfer(Golfer golfer2)
//	{
//		name = golfer2.name;
//		int[] golfer2Scores = golfer2Scores.toArray();
//		scores = copyScores(golfer2.scores);
//	}
	
	public Golfer(String record)
	{
		if( scores == null )
			scores = new ArrayList<Integer>();
		else 
			scores.clear();
		readData(record);
	}
	
	public int readData(String record)
	{
		int failed = 0;   // Indicates that we have failed reading the data from the file.
		Scanner scanner = new Scanner(record);
		
		if( scanner.hasNext() )
		{
			name = scanner.next();
			
			int i = 0;
			while(scanner.hasNextInt())
			{
				int score = scanner.nextInt();
				if( score < 0 )
				{
					score = 0;
					failed = -1;
				}
				
				scores.add(score);
			}
			
		}
		
		scanner.close();
		
		return failed;		
	}

	private int[] toArray()
	{
		int[] to = new int[scores.size()];
		
		for(int i = 0; i < scores.size(); i++)
		{
			to[i] = scores.get(i);
		}
		
		return to;
	}
	
	private void copyScores(int[] from)
	{
		scores.clear();
		
		for(int i = 0; i < from.length; i++)
		{
			scores.add(Math.max(0, from[i]));
		}
		
	}
	
//	private boolean validateScores(int[] listOfScores)
//	{
//		boolean badScore = false;
//		
//		for(int i = 0; i < listOfScores.length; i++)
//		{
//			if( listOfScores[i] < 0 )
//			{
//				listOfScores[i] = 0;
//				badScore = true;
//			}
//		}
//		
//		return badScore;
//	}
	
	public void writeData(PrintWriter writer)
	{
		writer.print(name);
		for(int i = 0; i < scores.size(); i++)
		{
			writer.print(" " + scores.get(i));
		}
		writer.println();
	}
	
	public boolean equals(Golfer golfer2)
	{
		if(! name.equalsIgnoreCase(golfer2.name) )
				return false;
		
		for(int i = 0; i < scores.size(); i++)
		{
			if(scores.get(i) != golfer2.scores.get(i))
				return false;
		}
		
		return true;
	}
	
	public boolean isSameGolfer(String compareName)
	{
		return name.equalsIgnoreCase(compareName);
	}
	
	public static boolean isValidScore(int validateScore)
	{
		return (validateScore >= 0);
	}

}

// Write GetScore
// Create Constructor that readsdata
// SetName, SetScore and SetScores Method   Make sure they validate data change accordingly throughout.
// Write Copy constructor.
// Write Equals
// Write ReadData.
// Add a toString method
// If we return an address to all the scores can we change them and change the object as well.
