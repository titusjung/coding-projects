package fractions;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class fractions {
	class fract
	{
		private Pattern pattern = Pattern.compile("(\\d+)\\/(\\d+)");
		Matcher matcher;
		int denominator;
		int numerator;

		public fract(String in)
		{
			matcher = pattern.matcher(in);
			if(matcher.matches())
			{
				numerator= matcher.group(1);
			}
			
		}
	}

	public static void main(String args[]) 
	{
		Scanner input = new Scanner(System.in);
		int x = input.nextInt();
		String[] stor = new String[x];
		input.nextLine();
		for(int i=0; i<x; i++)
		{
			stor[i]=input.nextLine();
		}
		
		
		
		
		input.close();
	}
}
