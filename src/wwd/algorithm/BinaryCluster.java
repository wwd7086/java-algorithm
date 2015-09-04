package wwd.algorithm;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import wwd.util.*;
public class BinaryCluster
{
	ArrayList<Integer> nodes;
	UnionFind nodeSet;
	
	public BinaryCluster(ArrayList<Integer> nodes)
	{
		this.nodes = nodes;
		nodeSet = new UnionFind(nodes.size());
	}
	
	public int getClusterNumber(int minSpace)
	{
		for(int i=0; i<nodes.size(); i++)
		{
			int ni = nodes.get(i);
			for(int j=i; j<nodes.size(); j++)
			{
				int nj = nodes.get(j);
				if(Integer.bitCount(ni^nj)<minSpace)
					nodeSet.union(i, j);
			}
				
		}
		return nodeSet.getNumSet();
	}
	
	public static void main(String[] args)
	{
		//read from file
		ArrayList<Integer> nodes = new ArrayList<Integer>();
		File input=new File("/Users/wwd/Developer/workspace for java/alogrithm/data/clustering_big.txt");
		Scanner scannerLine, scannerRow;
		try 
		{
			scannerLine=new Scanner(input);			
			//scan the first line which contains number of nodes and edges
			scannerLine.nextLine();			
			while(scannerLine.hasNextLine())
			{
				scannerRow = new Scanner(scannerLine.nextLine());
				String result="";
				while(scannerRow.hasNext())
				{
					result += scannerRow.next();
				}
				nodes.add(Integer.parseInt(result, 2));
			}
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//do the algorithm
		BinaryCluster bc = new BinaryCluster(nodes);
		System.out.println(bc.getClusterNumber(3));
			
	}
}
