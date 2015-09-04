package wwd.algorithm;
import wwd.util.graph.myGraph;

import java.util.*;

public class DirectedCycle
{
	// directed unwighted graph
	myGraph graph;
	Set<Integer> searchedVert = new HashSet<Integer>();
	Set<Integer> traversingVert = new HashSet<Integer>();

	public DirectedCycle(myGraph graph)
	{
		this.graph = graph;
	}

	public boolean detectCycle()
	{
		boolean result = false;

		Set<Integer> allVert = graph.keySet();
		for (int vert : allVert)
		{
			if (!searchedVert.contains(vert))
			{
				if (DFS(vert))
					return true;
			}
		}

		return result;
	}

	private boolean DFS(int vert)
	{
		traversingVert.add(vert);

		if (graph.containsKey(vert))
		{
			List<Integer> heads = graph.get(vert);
			for (int head : heads)
			{
				if (!searchedVert.contains(head))
				{
					if (traversingVert.contains(head))
						return true;

					if (DFS(head))
						return true;
				}
			}
		}
		searchedVert.add(vert);

		traversingVert.remove(vert);
		return false;
	}

	public static void main(String[] args)
	{
		myGraph graph = new myGraph();
		graph.readFromFile("/Users/wwd/Developer/workspace for java/alogrithm/DirectCycleSmall.txt");

		DirectedCycle dc = new DirectedCycle(graph);
		System.out.println(dc.detectCycle());
	}
}
