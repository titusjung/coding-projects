package connect4;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.*;

public class ConnectFour {

	public static void main(String [] args)
	{
		Connect4 game = new Connect4();
		
		Scanner input = new Scanner(System.in);
		String filename;
		FileReader fl;
		while(true)
		{
			System.out.print("Enter file Name: ");
			filename = input.nextLine();
			try{
				System.out.print(filename+ "\n");
				fl = new FileReader(filename);
				break;
			}
			catch(FileNotFoundException fnfe)
			{
				System.out.println("FileNotFoundException: " + fnfe.getMessage());
			}
		}
		String result = game.readGame(fl);
		System.out.print(result);
		System.out.print(game.toString());
		
		input.close();
	}
}

class Connect4
{
	private final int bsize = 6;
	private char [] [] Board = new char[bsize][bsize];
	private int turn;
	private final Pattern xpattern =  Pattern.compile(".*X{4}.*");
	private final Pattern opattern = Pattern.compile(".*O{4}.*");
	private Matcher matcher; 
	private int start;
	private String [] win = new String[4];
	public Connect4()
	{
		turn =0;
		for(char[] row:Board)
		{
			Arrays.fill(row, 'N');
		}
	}
	
	public String readGame(FileReader fl)
	{
		String result = "No Winner";
		Scanner sc = new Scanner(fl);
		while(sc.hasNext())
		{
			this.drop(sc.next().charAt(0));
			if(this.checkGame()) break;
		}				
		if(checkGame())
		{
			String p1 = "2";
			if(p1turn())
			{
				p1 = "1";
			}
			result = "Player "+ p1 + " has won at turn " + turn/2 +" (with " +this.winners() + ") \n";
		}
		sc.close();
		return result;		
	}
	
	public Boolean p1turn()
	{
		return turn%2==0;
	}
	public Boolean checkGame()
	{
		return checkHor() || checkVert();
	}
	private Boolean checkHor()
	{

		StringBuilder sb = new StringBuilder();
		int j=0;
		for(char[] row:Board)
		{

			sb.append(row);
			if(checkLine(sb.toString()))
			{
				for(int i=0; i<4; i++)
				{
					int k = bsize-j;
					win[i]= String.valueOf(Character.toChars(start+i+'A'))+k ;
				}
				return true;
			}
			sb.setLength(0);
			j++;
		}
		return false;
	}
	private Boolean checkVert()
	{
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<bsize; i++)
		{
			for(int j=0; j<bsize; j++)
			{
				sb.append(Board[j][i]);
			}
			if(checkLine(sb.toString()))
			{
				for(int k=0; k<4; k++)
				{
					int re = k -start;
					win[k] = String.valueOf(Character.toChars(i+'A'))+re; 
				}
				return true;
			}
			sb.setLength(0);
		}
		return false;
	}
	private Boolean checkLine(String line)
	{
		Pattern p1;
		if(p1turn())
		{
			p1 = xpattern;
		}
		else p1 = opattern;
		matcher= p1.matcher(line);
		if( matcher.matches())
		{
			start = matcher.start(0);
			return true;
		}
		return false;
	}
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		int i=bsize;
		sb.append(" ABCDEF\n");
		for(char[] row:Board)
		{
			sb.append(i);
			sb.append(row);
			sb.append("\n");
			i--;
		}
		return sb.toString();
	}
	public String winners()
	{
		StringBuilder result = new StringBuilder();
		for(int i=0; i<4; i++)
		{
			result.append(win[i]+" ");
		}
		return result.toString();
	}
	
	public void drop(char col)
	{
		char player;
		char x = Character.toUpperCase(col);
		Character.isLowerCase(col);
		if(turn%2==0)
		{
			player = 'X';
		}
		else
		{
			player = 'O';
		}
		drophelper(x-'A',player);
		turn++;

	}
	private void drophelper(int col, char let)
	{
		if(col>=bsize) return;
		for(int i=bsize-1; i>=0; i--)
		{
			if(Board[i][col]=='N')
			{
				Board[i][col]=let;
				return;
			}
		}
	}
	
}