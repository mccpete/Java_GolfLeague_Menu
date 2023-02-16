
/**
 * Write a description of class GolfLeague here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GolfLeague
{
// Change the number of golfers to 100
	final static int NUMBER_OF_GOLFERS = 5;
	public static void main(String[] args)   throws IOException 
	{ 
//        int scores1[] = {42, 45, 67 ,43, 32};
//        int scores2[] = {89, 72, 45, 97, 32};
//        int scores3[] = {45, 47, 43, 48};
// 
//        Golfer[] golfers = new Golfer[3];
//        golfers[0] = new Golfer("Kevin", scores1);
//        golfers[1] = new Golfer("Tanner", scores2);
//        golfers[2] = new Golfer("Andy", scores3);
       
		File file = new File("GolfLeague.txt");
        if( !file.exists() )
        {
        	System.out.println("The file \"GolfLeague.txt\" does not exist or cannot be found!  This program requires that file before it can be run.\n");
        } else {
        
        	Golfer[] golfers = new Golfer[NUMBER_OF_GOLFERS];

        	Scanner inputFile = new Scanner(file);
    		
        	int i = 0;
        	while(inputFile.hasNextLine() && i < NUMBER_OF_GOLFERS)
        	{
        		String record = inputFile.nextLine();
        		golfers[i++] = new Golfer(record);
        	}
        	
        	inputFile.close();
        	
	        for(i = 0; i < golfers.length; i++)
	        {
	        	System.out.printf("Average Score for: %s is %.2f\n", golfers[i].getName(), golfers[i].averageScore());
	        }
	        
	        Golfer highestScoreGolfer = golfers[0];
	        for(i = 1; i < golfers.length; i++)
	        {
	            if(golfers[i].highestScore() > highestScoreGolfer.highestScore())
	            {
	            	highestScoreGolfer = golfers[i];
	            }
	        }
	        
	        System.out.printf("Highest Score for all golfers is %d by %s\n", highestScoreGolfer.highestScore(), highestScoreGolfer.getName());
	        
//	        selectionSort(golfers);
	        for(i = 0; i < golfers.length; i++)
	        {
	             System.out.println("Name = " + golfers[i] );
	        }
	        
        	PrintWriter w = new PrintWriter("GolfLeague1.txt");
        	for(i = 0; i < NUMBER_OF_GOLFERS; i++)
        	{
        		golfers[i].writeData(w);
        	}
        	w.close();

        }
        
    }
    
    private static void selectionSort( Golfer[] golfers )
    {
        int startIndex, minIndex, length;
        length = golfers.length;

        for (startIndex = 0; startIndex <= length-2; startIndex++){
            //each iteration of the for loop is one pass
            minIndex = startIndex;

            //find the smallest in this pass at position minIndex
            for (int i = startIndex+1; i <= length-1; i++) {
                String name = golfers[i].getName();
                if( name.compareTo(golfers[minIndex].getName()) < 0 )
                {
                    minIndex = i;
                }
            }
            //exchange number[startIndex] and number[minIndex]
            Golfer temp = golfers[startIndex];
            golfers[startIndex] = golfers[minIndex];
            golfers[minIndex] = temp;
       }
   }

}
