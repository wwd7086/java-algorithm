package wwd.util;

public class Tuple
{
	public int a;
	public int b;
	
	public Tuple(int a, int b)
	{
		this.a=a;
		this.b=b;
	}
	
	public boolean equals(Object obj)
	{
		boolean result = false;
		Tuple other = (Tuple)obj;
		
		if((a==other.a && b==other.b)||
		   (a==other.b && b==other.a))	
			result = true;
		
		return result;
	}
	
	public int hashCode()
	{
		return a*b+a+b;
	}
	
	public String toString()
	{
		return a+"-"+b;
	}
}
