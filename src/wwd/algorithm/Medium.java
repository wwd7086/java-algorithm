package wwd.algorithm;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

public class Medium 
{
	File in = new File("/Users/wwd/Developer/workspace for java/alogrithm/Median.txt");
	TreeSet<Integer> maxs = new TreeSet<Integer>();
	TreeSet<Integer> mins = new TreeSet<Integer>();
	int nextInt=0;
	int sum=0;
	int curMedium=0;
	
	void doMedium()
	{
		try 
		{
			Scanner scann = new Scanner(in);
			while(scann.hasNextInt())
			{
				//insert
				nextInt=scann.nextInt();
				if(nextInt<curMedium)
				{
					mins.add(nextInt);
				}
				else
				{
					maxs.add(nextInt);
				}
				
				//reblance
				if(maxs.size()-mins.size()>1)
				{
					mins.add(maxs.first());
					maxs.pollFirst();
				}
				else if(mins.size()-maxs.size()>1)
				{
					maxs.add(mins.last());
					mins.pollLast();
				}
				
				//calculate the medium
				if(mins.size()==maxs.size())
					curMedium=mins.last();
				else if(mins.size()-maxs.size()==1)
					curMedium=mins.last();
				else if(maxs.size()-mins.size()==1)
					curMedium=maxs.first();
				else
					System.out.println("the two heaps is unbalance!!!");
				
				//accumulate 
				sum+=curMedium;
			}
			
			System.out.println(sum%10000);
			scann.close();
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] Args)
	{
		Medium med=new Medium();
		med.doMedium();
	}
}
