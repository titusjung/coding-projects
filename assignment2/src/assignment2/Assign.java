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
	private boolean [][] accounted = new  boolean[10][10];
	private int numA, numB, numC, numD;
	private final int height = 10;
	private final int length = 10;
	private int alife, blife, clife, dlife1, dlife2;
	
	
	public Board(BufferedReader bf)
	{
		numA=0; alife = 5;
		numB=0; blife =4;
		numC=0; clife = 3;
		numD=0; dlife1 = 2; dlife2 = 2;
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
					accounted[i][j]= false;
				}
			}
			catch(IOException ioe)
			{
				System.out.println("IOException: " + ioe.getMessage());
			}

		}
		if(!lookaheadcheck())
		{
			System.out.println("Invalid board");
		}
	}
	public void printboard()
	{
		for(int i=0; i<10;i++)
			System.out.println(board[i]);
		
	}

	public String checkboard(int x, int y)
	{
		if(x<height && y< height) 
		{
			switch( board2[x][y])
			{
			case 'X':
				board2[x][y]= 'G';
				return "Miss!";
			case 'G':
				return "Already Guessed";
			case 'A':
				board2[x][y]= 'G';
				alife--;
				if(alife==0) return "Sunk Aircraft Carrier";
				return "Hit Aircraft Carrier";
			case'B' :
				board2[x][y]= 'G';
				blife--;
				if(blife ==0) return "Sunk Battleship";
				return "Hit Battleship";
			case 'C': 
				board2[x][y]= 'G';
				clife--;
				if(clife ==0) return "Sunk Cruiser";
				return "Hit Cruiser";
			case 'D':
				board2[x][y]= 'G';
				dlife1--;
				if(dlife1 ==0) return "Sunk Destroyer";
				return "Hit Destroyer";
			case 'E':
				board2[x][y]= 'G';
				dlife2--;
				if(dlife2 ==0) return "Sunk Destroyer";
				return "Hit Destroyer";								
			}
		}
		 return "Out of Range";
	}
	private void assignpos(int x, int y, int life, boolean vertical, char nship)
	{
		for(int i=0; i<life; i++){
			if(vertical)
			{
				board2[x+i][y] = nship;
			}
			else
			{
				board2[x][y+i] = nship;
			}
		}
	}

	private boolean lookaheadcheck()
	{
		int num =0;
		char shiplet;
		boolean lookvertical = false;
		for( int i =0; i<height; i++)
		{
			for(int j=0; j<length; j++)
			{
				if(board[i].charAt(j) != 'X' && (!accounted[i][j]))
				{
					shiplet = board[i].charAt(j);
					switch( shiplet)
					{
					case 'A':
						if(numA==0)
							{
								num =5;
								numA++;
							}
						break;
					case 'B':
						if(numB==0)
						{ 
							num =4;
							numB++;
						}
						break;
					case 'C':
						if(numC==0) 
							{
								num =3;
								numC++;
							}
						break;
					case'D':
						if(numD<2)
							{
								num = 2;
								numD++;
							}
						break;
					}
					
					if(num==0) 
						{
							return false;
						}
					
					//horizontal check
					for(int k=0; k<num; k++)
					{
						if(j+k>=length) return false;
						
						if((board[i].charAt(j+k)!=shiplet && k==1) && (!accounted[i][j+k]))
							{
							lookvertical =true;
							break;
							}
						else if((board[i].charAt(j+k)!=shiplet) && (!accounted[i][j+k]))
						{
							return false;
						}
					}

					if((j+num+1<length)&&(board[i].charAt(j+num+1) == shiplet && shiplet == 'D')&&(!accounted[i][j+num+1]))
					{
						if((j+num+2<length) && (board[i].charAt(j+num+2) == shiplet)&&(!accounted[i][j+num+2]))
							continue;
						else if((j+num+2<length) && (board[i].charAt(j+num+2) != shiplet)&&(!accounted[i][j+num+1]))
							lookvertical = true;
						else
							return false;
					}
					
					if(!lookvertical)
					{
						for(int k=0; k<num; k++)
						{
							accounted[i][j+k]=true;
						}
						assignpos(i,j,num,false,shiplet);
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
						assignpos(i,j,num,true,shiplet);
					}					
				}
				num =0;
				lookvertical = false;

				
			}
		}
		return true;
	}
	
}
/*
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
			location = new boolean[10][10];
			for(int i=0; i<10; i++)
			{
				for(int j=0; j<10;j++)
				{
					location[i][j]=false;
				}
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
	private boolean [][] location; 
	protected boolean[][] array = new boolean[10][10];
	
}

class ACship extends ship
{
	private boolean [][] location; 
	public ACship(int headx, int heady, boolean vertical)
	{
		life = 5;
		location = new boolean[10][10];
		for(int i=0; i<10; i++)
		{
			for(int j=0; j<10;j++)
			{
				location[i][j]=false;
			}
		}
		if(vertical)
		{
			for(int i=0; i<life; i++)
			{
				location[i][heady]=true;
			}
			
		}
		else
		{
			for(int i=0; i<life; i++)
			{
				location[headx][i]=true;
			}
		}

	}
}
*/



