package wwd.algorithm;
import wwd.util.graph.*;
import java.util.*;

public class DjstraShortPath
{
	public DjstraShortPath()
	{
	}

	public DjstraShortPath(myWeightedGraph graph)
	{
		this.graph = graph;
	}

	public Map<Integer, Integer> findShortPath(int vert)
	{
		// check input
		if (!graph.containsKey(vert))
			throw new RuntimeException("vertex specified do not exist");

		// initialize all the data structure
		unreachedVert.addAll(graph.keySet());
		unreachedVert.remove(vert);
		solvedVert.put(vert, 0);

		HashMap<Integer, Integer> edges = graph.get(vert);
		Set<Integer> edgeHeads = edges.keySet();
		for (int head : edgeHeads)
		{
			queueVert.add(new Edge(head, vert, edges.get(head)));
			unreachedVert.remove(head);
		}

		// Start the main for loop, to include vertex incrementally
		while (queueVert.size() > 0)
		{
			// extract the smallest vertex in the heap
			Edge e = queueVert.extractMin();
			solvedVert.put(e.getHead(), e.getLength());
			// recover the correctness of heap
			edges = graph.get(e.getHead());
			edgeHeads = edges.keySet();
			for (int head : edgeHeads)
			{
				if (!solvedVert.containsKey(head))
				{
					if (unreachedVert.contains(head))
						unreachedVert.remove(head);

					queueVert.add(new Edge(head, e.getHead(), e.getLength() + edges.get(head)));
				}
			}
		}

		return solvedVert;
	}

	myWeightedGraph graph = new myWeightedGraph();
	Map<Integer, Integer> solvedVert = new HashMap<Integer, Integer>();
	Set<Integer> unreachedVert = new HashSet<Integer>();
	EdgeHeapWithHeadMap queueVert = new EdgeHeapWithHeadMap();

	// test
	public static void main(String[] args)
	{
		myWeightedGraph graph = new myWeightedGraph();
		graph.readFromFile("dijkstraData.txt");
		DjstraShortPath dj = new DjstraShortPath(graph);
		Map<Integer, Integer> result = dj.findShortPath(1);
		System.out.println(result);
	}
}



