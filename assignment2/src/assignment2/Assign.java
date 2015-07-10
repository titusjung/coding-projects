package assignment2;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class Assign {

		public static void main(String [] args)
		{
			//start
			//
    		Pattern pattern = Pattern.compile("\\d+\\. (Player\\d) - (\\d+)");
    		Matcher matcher;
			Scanner input = new Scanner(System.in);
			String filename;
			FileReader fl;
			BufferedReader bf; 
			while(true)
			{
				System.out.print("Enter file Name: ");
				filename = input.nextLine();
				try{
					System.out.print(filename);
					fl = new FileReader(filename);
					break;
				}
				catch(FileNotFoundException fnfe)
				{
					System.out.println("FileNotFoundException: " + fnfe.getMessage());
				}
			}
			//store players
			bf = new BufferedReader(fl);
			String [] list = new String[10];
			for(int i=0; i<10;i++)
			{
				try
				{
				matcher = pattern.matcher(bf.readLine());				
				list[i]=matcher.group(1);
				}
				catch(IOException ioe)
				{
					System.out.println("IOException: " + ioe.getMessage());
				}
			}
			for(int i=0; i<10;i++)
			{
				System.out.println(list[i]);
			}
			//create class of players?
			//store it in a 2d array;
			//char [][] board = new char[][];
			input.close();
		}
}

