package wwd.util.graph;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import wwd.util.Importable;
import wwd.util.myHeap;

public class EdgeHeap extends myHeap<Edge>
					  implements Importable
{
	public void readFromFile(String fileName)
	{
		File input=new File(path+fileName);
		Scanner scannerLine, scannerRow;
		
		try 
		{
			scannerLine=new Scanner(input);
			
			//scan the first line which contains number of nodes and edges
			scannerRow = new Scanner(scannerLine.nextLine());
			numNodes = scannerRow.nextInt();
			if(scannerRow.hasNextInt())
				numEdges = scannerRow.nextInt();
			
			while(scannerLine.hasNextLine())
			{
				scannerRow = new Scanner(scannerLine.nextLine());
				Edge edge = new Edge(scannerRow.nextInt(),  //head
									 scannerRow.nextInt(),  //tail
									 scannerRow.nextInt()); //length
				this.add(edge);
			}
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
	
	public static void main(String[] args)
	{
		EdgeHeap heap = new EdgeHeap();
		heap.readFromFile("edges.txt");
		
		System.out.println(heap.extractMin());
	}
}
