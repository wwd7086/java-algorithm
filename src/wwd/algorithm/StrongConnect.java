package wwd.algorithm;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class StrongConnect 
{
	ArrayList[] input = new  ArrayList[1000000];
	ArrayList<Integer> explored = new ArrayList<Integer>();
	ArrayList<Integer> orders= new ArrayList<Integer>();
	ArrayList<Integer> leaders = new ArrayList<Integer>();
	HashMap<Integer, Integer> rank = new HashMap<Integer, Integer>();
	int order=1;
	int leader=1;
	int totalNumber=0;
	
	File inFile = new File("/Users/wwd/Developer/workspace for java/alogrithm/SCC1.txt");
	
	void FindStrongConnect()
	{
		getGraph();
		
		System.out.println("read finish");
		
		for(int i=1; i<=totalNumber; i++)
		{
			if(!explored.contains(i))
			{
				System.out.println("order:"+i);
				rDFS(i);		
			}
		}
		
		System.out.println("reverse search finish");
		
		getReverseGraph();
		
		System.out.println("reread finish");
		
		for(int i=totalNumber; i>0; i--)
		{
			if(!explored.contains(i))
			{
				System.out.println("leader:"+i);
				leader = i;
				DFS(i);
			}
		}
		
		System.out.println("leader search finish");
		
		for(int m: leaders)
		{
			if(rank.containsKey(m))
			{
				rank.put(m, rank.get(m)+1);
			}
			else
			{
				rank.put(m, 1);
			}
		}
		
		System.out.println(rank);
		
		QuickSort qs = new QuickSort();
		//System.out.println(rank.values());
		List result = qs.qsort(rank.values());
		System.out.println(result);
		
		int size = result.size();
		for(int i =0 ; i<5 && size>0; i++)
		{
			System.out.println(result.get(size-1));
			size--;
		}
	}
	
	@SuppressWarnings("unchecked")
	void rDFS(int start)
	{
		explored.add(start);
		ArrayList<Integer> ends = input[start-1];
		
		if(ends!=null)
		{
			for(int end : ends)
			{
				if(!explored.contains(end))
					rDFS(end);
			}
		}
		orders.set(start-1, order);
		//System.out.println(orders);   //debug use
		order++;
	}
	
	@SuppressWarnings("unchecked")
	void DFS( int start)
	{
		explored.add(start);
		leaders.set(start-1, leader);
		ArrayList<Integer> ends = input[start-1];
		
		if(ends!=null)
		{
			for(int end : ends)
			{
				if(!explored.contains(end))
					DFS(end);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	void getGraph()
	{
		int key=0, value=0;
		ArrayList<Integer> values;
		
		try 
		{
			Scanner scanner = new Scanner(inFile);
			while(scanner.hasNextInt())
			{
				value = scanner.nextInt();
				if(scanner.hasNextInt())
					key=scanner.nextInt();
				
				if(input[key-1]!=null)
				{
					values=input[key-1];
					values.add(value);
				}
				else
				{
					values=new ArrayList<Integer>();
					values.add(value);
					input[key-1]=values;
				}
				
				if(value>totalNumber)
					totalNumber=value;
				
				if(key>totalNumber)
					totalNumber=key;
				

			}
			scanner.close();
			for(int i=0; i<totalNumber; i++)
			{
				orders.add(0);
				leaders.add(0);
			}
	
		} 
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	void getReverseGraph()
	{
		input=new  ArrayList[1000000];
		explored.clear();
		
		int key=0, value=0;
		ArrayList<Integer> values;
		
		try 
		{
			Scanner scanner = new Scanner(inFile);
			while(scanner.hasNextInt())
			{
				key = orders.get(scanner.nextInt()-1);
				if(scanner.hasNextInt())
					value=orders.get(scanner.nextInt()-1);
				
				if(input[key-1]!=null)
				{
					values=input[key-1];
					values.add(value);
				}
				else
				{
					values=new ArrayList<Integer>();
					values.add(value);
					input[key-1]=values;
				}
				
			}
			scanner.close();
			//System.out.println(input);  // debug use
		} 
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String Args[])
	{
		StrongConnect sc= new StrongConnect();
		sc.FindStrongConnect();
	}
}
















