package wwd.util.graph;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import wwd.util.Importable;

public class myWeightedGraph extends HashMap<Integer, HashMap<Integer, Integer>>
							 implements Importable
{
	private static final long serialVersionUID = 1L;

	public void readFromFile(String fileName)
	{
		File in = new File(path+fileName);
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
				
				this.put(start, ends);
					
			}
			sLine.close();
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readFromSinglePairFile(String fileName)
	{
		File in = new File(path+fileName);
		try 
		{
			Scanner sLine = new Scanner(in);
			Scanner sVertex;
			int start=1;
			HashMap<Integer, Integer> ends;
			
			sVertex = new Scanner(sLine.nextLine());
			numNodes = sVertex.nextInt();
			if(sVertex.hasNextInt())
				numEdges = sVertex.nextInt();
			
			ends = new HashMap<Integer, Integer>();
			while(sLine.hasNextLine())
			{
				sVertex = new Scanner(sLine.nextLine());
				
				if(sVertex.hasNextInt())
				{
					int thisStart=sVertex.nextInt();
					int end = sVertex.nextInt();
					int length = sVertex.nextInt();
					
					if(thisStart==start)
					{
						ends.put(end, length);
					}
					else
					{
						this.put(start, ends);
						start = thisStart;
						ends = new HashMap<Integer, Integer>();
						ends.put(end, length);
					}
					
				}	
			}
			this.put(start, ends);
			sLine.close();
			sVertex.close();
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getNumNodes()
	{
		return numNodes;
	}
	
	public int getNumEdges()
	{
		return numEdges;
	}
	
	private int numNodes;
	private int numEdges;
}
