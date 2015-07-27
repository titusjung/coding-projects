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
			List<Player> plist = new ArrayList<Player>();
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
			Board b = new Board(bf);
			b.printboard();
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
	private String [] board = new String[10];
	private char [][] board2 = new char[10][10];
	private boolean [][] check = new  boolean[10][10];
	private boolean [][] accounted = new  boolean[10][10];
	private ship ship1;
	private ship ship2;
	private int numA, numB, numC, numD;
	private final int height = 10;
	private final int length = 10;
	
	
	public Board(BufferedReader bf)
	{
		numA=0;
		numB=0;
		numC=0;
		numD=0;
		String buff;
		for(int i=0; i<height;i++)
		{
			try
			{
				buff = bf.readLine();
				board[i]=buff;
				for(int j=0; j<length; j++)
				{
					board2[i][j]=buff.charAt(j);
				}
			}
			catch(IOException ioe)
			{
				System.out.println("IOException: " + ioe.getMessage());
			}

		}
	}
	public void printboard()
	{
		for(int i=0; i<10;i++)
			System.out.println(board[i]);
		
	}
	private void processboard()
	{
		//assigns ships
		for(int i=0; i<height; i++)
		{
			for(int j=0; j<length;j++)
			{
				if(board[i].charAt(j) !='X')
				{
					
				}
					
			}
		}
	}
	
	public void checkboard(int x, int y)
	{
		
	}
	private boolean lookaheadcheck()
	{
		int num =0;
		boolean invalid = false;
		char shiplet;
		boolean lookvertical = false;
		for( int i =0; i<10; i++)
		{
			for(int j=0; j<10; j++)
			{
				if(board[i].charAt(j) != 'X' && (!accounted[i][j]))
				{
					shiplet = board[i].charAt(j);
					switch( shiplet)
					{
					case 'a':
						if(numA==0)
							{
								num =5;
								numA++;
							}
					case 'b':
						if(numB==0)
						{ 
							num =4;
							numB++;
						}
					case 'c':
						if(numC==0) 
							{
								num =3;
								numC++;
							}
					case'd':
						if(numD<2)
							{
								num = 2;
								numD++;
							}
					}
					
					if(num==0) 
						{
							return false;
						}
					
					//horizontal check
					for(int k=0; k<num; k++)
					{
						if(j+k>=length) return false;
						
						if(board[i].charAt(j+k)!=shiplet && k==1)
							lookvertical =true;
						else
						{
							return false;
						}
					}

					if(board[i].charAt(j+num+1) == shiplet && shiplet == 'D')
					{
						lookvertical = true;
					}
					
					if(!lookvertical)
					{
						for(int k=0; k<num; k++)
						{
							accounted[i][j+k]=true;
						}
					}
					
					else
					{
						for(int k=0; k<num; k++)
						{
							if(i+k>=height) return false;
							
							if(board[i+k].charAt(j)!=shiplet) return false;
						}
						for(int k=0; k<num; k++)
						{
							accounted[i+k][j]= true;
						}
					}
					
				}
				num =0;
				
				
			}
		}
		return true;
	}
	
}
class Position
{
	public int x;
	public int y;
	public boolean hit;
	public Position(int a, int b)
	{
		x=a;
		y=b;
		hit = false;
	}
	public boolean hitstatus()
	{
		return hit;
	}
	public void hitchange()
	{
		hit = true;
	}
}
abstract class ship
{
	public ship(){}
	public ship(int headx, int heady, int tailx, int taily)
	{		
	}
	abstract public void removelife(int x, int y);
	protected void shiphelper(int headx, int heady, int tailx, int taily)
	{
		if((headx != tailx && heady !=taily) || (heady==taily &&headx==tailx ))
		{
			return;
		}
		else if(headx == tailx)
		{
			int length = Math.abs(heady-taily);
			if(length>life) return;
			for(int i=0; i<length; i++)
			{
				location[i] = new Position(headx, heady+i);
			}
		}
		else
		{
			int length = Math.abs(headx-tailx);
			if(length>life) return;
			for(int i=0; i<length; i++)
			{
				location[i] = new Position(headx+i, heady);
			}
		}
	}
	
	protected int life;
	protected Position[] location;
	protected boolean[][] array = new boolean[10][10];
	
}

class ACship extends ship
{
	public ACship(int headx, int heady, int tailx, int taily)
	{
		life = 5;
		location = new Position[5];
		array = new boolean[5][5];
		shiphelper(headx,heady,tailx,taily);
	}
}



