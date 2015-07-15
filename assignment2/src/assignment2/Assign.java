package assignment2;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
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
			try
			{
				bf.readLine();
			}
			catch(IOException ioe)
			{
				System.out.println("IOException: " + ioe.getMessage());
			}
			List<Player> plist = new ArrayList();
			String caught;
			for(int i=0; i<10;i++)
			{
				try
				{
					caught = bf.readLine();
					System.out.println(caught);
					matcher = pattern.matcher(caught);
					if(matcher.matches())
						plist.add(new Player(matcher.group(1), Integer.parseInt(matcher.group(2))));
					//else break;
				}
				catch(IOException ioe)
				{
					System.out.println("IOException: " + ioe.getMessage());
				}
			}
			for(Player p: plist)
			{
				System.out.println(p.getName()+" "+p.getScore());
			}
			Collections.sort(plist, new Player());
			for(Player p: plist)
			{
				System.out.println(p.getName()+" "+p.getScore());
			}
			//create class of players?
			//store it in a 2d array;
			//char [][] board = new char[][];
			input.close();
		}
}

class Player implements Comparator<Player>, Comparable<Player> 
{
	private String Name;
	private int Score;
	private static int numberPlayers =0;
	public Player(){}
	public Player(String n, int s)
	{
		Name = n;
		Score = s; 
		numberPlayers++;
	}
	public String getName()
	{
		return Name;
	}
	public int getScore()
	{
		return Score;
	}
	@Override
	public int compareTo(Player arg0) {
		// TODO Auto-generated method stub
		return this.Name.compareTo(arg0.getName());
	}
	public static int getNumPlayers()
	{
		return numberPlayers;
	}
	@Override
	public int compare(Player arg0, Player arg1) {
		return arg1.getScore()-arg0.getScore();
	}
}

class Board
{
	private String [][] board = new String[10][10];
	private boolean [][] check = new  boolean[10][10];
	
}




