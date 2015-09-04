package wwd.algorithm;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class ShortPath 
{
	File in = new File("/Users/wwd/Developer/workspace for java/alogrithm/dijkstraData.txt");
	HashMap<Integer, HashMap<Integer, Integer>> graph = new HashMap<Integer, HashMap<Integer, Integer>>();
	HashMap<Integer, Integer> explored = new HashMap<Integer, Integer>();
	
	ShortPath()
	{
		try 
		{
			Scanner sLine = new Scanner(in);
			Scanner sVertex;
			int start=0, end=0, length=0; 
			HashMap<Integer, Integer> ends;
			
			while(sLine.hasNextLine())
			{
				sVertex = new Scanner(sLine.nextLine());
				sVertex.useDelimiter("(,)|(\\s)");
				ends = new HashMap<Integer, Integer>();
				
				if(sVertex.hasNextInt())
					start=sVertex.nextInt();
				else
					System.out.println("wrong line format");
				
				while(sVertex.hasNextInt())
				{
					end = sVertex.nextInt();

					if(sVertex.hasNextInt())
					{
						length = sVertex.nextInt();
						ends.put(end, length);
					}
					else
						System.out.println("no legth information");
				}
				
				graph.put(start, ends);
					
			}
			sLine.close();
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void find()
	{
		//the distance
		int smallest=0;
		boolean isFirst=true;
		int aPart;
		int bPart;
		int length;
		
		//the vertex
		int closest=0;
		
		
		
		explored.put(1,0);
		//find the samllest a()+l
		while(explored.size()<graph.size())
		{
			//iterate the explored
			for(int start:explored.keySet())
			{
				aPart = explored.get(start);
				//iterate the ends
				if(!graph.get(start).isEmpty())
				{
					for(int edge:graph.get(start).keySet())
					{
						if(!explored.containsKey(edge))
						{
							bPart = graph.get(start).get(edge);
							length = aPart + bPart;
							
							if(isFirst)
							{
								smallest=length;
								closest = edge;
								isFirst = false;
								continue;
							}
							
							if(length<smallest)
							{
								smallest = length;
								closest = edge;
							}
							
						}
					}
					
					
				}
				
			}
			
			//add the smallest to the explored 
			if(smallest == 0)
			{
				//there is no more reachable vertex
				for(int i=0; i<graph.size(); i++)
				{
					if(!explored.containsKey(i))
						explored.put(i, 1000000);
				}
				System.out.println("there is some points can not be reached"+explored.size()+"   "+ graph.size());
				break;
			}
			else
			{
				explored.put(closest, smallest);
				System.out.println("new entry"+"point:"+closest+"   length:"+smallest);
				isFirst = true;
				smallest =0;
			}
			
		}
	}
	
	void getAnswer(int key)
	{
		System.out.println("key:"+key+"   "+explored.get(key));
	}
	
	
	public static void main(String[] Args)
	{
		ShortPath test = new ShortPath();
		System.out.println(test.graph);
		test.find();
		System.out.println(test.explored);
		test.getAnswer(7);
		test.getAnswer(37);
		test.getAnswer(59);
		test.getAnswer(82);
		test.getAnswer(99);
		test.getAnswer(115);
		test.getAnswer(133);
		test.getAnswer(165);
		test.getAnswer(188);
		test.getAnswer(197);
		
	}
}
