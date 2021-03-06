package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Chain1 {

	public static void main(String [] args)
	{
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
		findChain c = new findChain(fl);
		System.out.println(c.printBoard());
		System.out.println("Number of chains "+ c.numofChains());
		input.close();
		
	}
}

class findChain
{
	private Boolean [][] visited;
	private int height,length;
	private char [][] map;
	private final char link = 'x';
	private int numberofChains;
	public findChain(FileReader fl)
	{
		Scanner sc = new Scanner(fl);
		height = sc.nextInt();
		length = sc.nextInt();
		visited = new Boolean[height][length];
		numberofChains = 0;
		map = new char[height][length];
		String buff;
		for(Boolean [] row:visited)
		{
			Arrays.fill(row, false);
		}
		sc.nextLine();
		for(int i=0; i<height; i++)
		{
			buff = sc.nextLine();
			for(int j=0; j<length; j++)
			{
				map[i][j] = buff.charAt(j);
			}
		}	
		parse();
		sc.close();
	}
	
	public int numofChains()
	{
		return numberofChains;
	}
	public String printBoard()
	{
		StringBuilder sb = new StringBuilder();
		
		for(char[] row:map)
		{
			sb.append(row);
			sb.append("\n");
		}
		
		return sb.toString();
	}
	private void parse()
	{
		for(int i=0; i<height; i++)
		{
			for(int j=0; j<length; j++)
			{
				if(map[i][j] == link && (!visited[i][j]))
				{
					numberofChains++;
					DFS(i,j);
				}
			}
		}
	}
	
	private void DFS(int x, int y)
	{
		visited[x][y] = true;
		//check up
		int a = x-1;
		if(a>=0 && (!visited[a][y]) && map[a][y] == link)
		{
			DFS(a,y);
		}
		//check down
		 a = x+1;
		if(a<height && (!visited[a][y]) && map[a][y] == link)
		{
			DFS(a,y);
		}
		//check right
		 a = y-1;
		if(a>=0 && (!visited[x][a]) && map[x][a] == link)
		{
			DFS(x,a);
		}
		//check left
		 a = y+1;
		if(a<length && (!visited[x][a]) && map[x][a] == link)
		{
			DFS(x,a);
		}
		return;
	}
	
	
	
	
}
