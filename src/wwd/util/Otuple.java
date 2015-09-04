package wwd.util;

public class Otuple extends Tuple
{
	public Otuple(int a, int b)
	{
		super(a, b);
	}

	public boolean equals(Object obj)
	{
		boolean result = false;
		Tuple other = (Tuple)obj;
		
		if(a==other.a && b==other.b)	
			result = true;
		
		return result;
	}
}
