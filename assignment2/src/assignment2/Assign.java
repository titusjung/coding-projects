package assignment2;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
public class Assign {

		public static void main(String [] args)
		{
			//start
			//
			Scanner input = new Scanner(System.in);
			String filename;
			while(true)
			{
				System.out.print("Enter file Name: ");
				filename = input.nextLine();
				try{
					System.out.print(filename);

					FileReader fl = new FileReader(filename);
					break;
				}
				catch(FileNotFoundException fnfe)
				{
					System.out.println("FileNotFoundException: " + fnfe.getMessage());
				}
			}
		}
}
