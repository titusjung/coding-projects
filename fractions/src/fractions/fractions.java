package fractions;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class fractions {


	public static void main(String args[]) 
	{
		Scanner input = new Scanner(System.in);
		int x = input.nextInt();
		String[] stor = new String[x];
		Fract[] stor2 = new Fract[x];
		Fract total = new Fract();
		input.nextLine();
		String buf;
		for(int i=0; i<x; i++)
		{
			buf = input.nextLine();
			stor[i]=buf;
			stor2[i]= new Fract(buf);
			total.add(stor2[i]);
			
		}
		System.out.println(total.print());
		input.close();
	}

}
class Fract
{
	private final Pattern pattern = Pattern.compile("(\\d+)\\/(\\d+)");
	Matcher matcher;
	public int denominator;
	public int numerator;
	public Boolean empty;
	public Fract()
	{
		numerator = 0;
		denominator = 1;
	}
	public Fract(String in)
	{
		matcher = pattern.matcher(in);
		if(matcher.matches())
		{
			
			numerator= Integer.parseInt(matcher.group(1));
			denominator = Integer.parseInt(matcher.group(2));
			if(denominator!=0) empty = false;
		}
		else
		{
			empty = true;
		}
	}
	public Fract(int x, int y)
	{
		denominator = y;
		numerator = x;
	}
	public void add(Fract x)
	{
		int d1 = this.denominator;
		int d2 = x.denominator;
		int cf = d1*d2;
		this.denominator = cf;
		this.numerator = d2*this.numerator;
		this.numerator = x.numerator*d1 +this.numerator;
	}

	public String print()
	{
		return new String(numerator + "/" + denominator);
	}

}
