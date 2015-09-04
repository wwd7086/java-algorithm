package wwd.algorithm;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Sum2 
{
	File in = new File("/Users/wwd/Developer/workspace for java/alogrithm/Hashint.txt");
	HashSet<Integer> numbers = new HashSet<Integer>();
	//HashSet<Integer> used= new HashSet<Integer>();
 	int count=0;
	
	Sum2()
	{
		try
		{
			Scanner scann = new Scanner(in);
			while(scann.hasNextInt())
			{
				numbers.add(scann.nextInt());
			}
			scann.close();
			//System.out.println(numbers);
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	void findNum()
	{
		for(int i=2500; i<=4000; i++)
		{
			for(int k:numbers)
			{
				if(k<=4000 && k!=i-k)
				{
					if(numbers.contains(i-k))
					{
						count++;
						break;
					}
				}
			}
		}
	}
	
	public static void main(String[] Args)
	{
		Sum2 s2=new Sum2();
		s2.findNum();
		System.out.println("the total number is:"+s2.count);
	}
	
		
	
}
