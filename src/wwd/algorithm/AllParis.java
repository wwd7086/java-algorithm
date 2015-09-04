package wwd.algorithm;

import wwd.util.graph.*;
import java.util.*;

public class AllParis
{
	private myWeightedGraph graph;
	private int numNode;
	private int[][][] subPath; // possible set //source //destination
	private static int inf = Integer.MAX_VALUE;

	public AllParis(myWeightedGraph graph)
	{
		this.graph = graph;
		numNode = graph.getNumNodes();
		subPath = new int[2][numNode][numNode];
	}

	public int[][] getShortPath()
	{
		// initialize
		for (int i = 0; i < numNode; i++)
		{
			for (int j = 0; j < numNode; j++)
			{
				if (i == j)
					subPath[0][i][j] = 0;
				else
				{
					// exist direct edge from i=>j
					if (graph.containsKey(i + 1))
					{
						HashMap<Integer, Integer> edges = graph.get(i + 1);
						if (edges.containsKey(j + 1))
							subPath[0][i][j] = edges.get(j + 1);
						else
							subPath[0][i][j] = inf;
					}
					else
						subPath[0][i][j] = inf;
				}
			}
		}

		// recurrence
		for (int k = 1; k < numNode + 1; k++)
		{
			int thisK = k % 2;
			int lastK = thisK == 1 ? 0 : 1;

			for (int i = 0; i < numNode; i++)
			{
				for (int j = 0; j < numNode; j++)
				{
					int s1 = subPath[lastK][i][j];
					int s21 = subPath[lastK][i][k - 1];
					int s22 = subPath[lastK][k - 1][j];
					int s2;
					if(s21==inf || s22==inf)
						s2 = inf;
					else
						s2 = subPath[lastK][i][k - 1] + subPath[lastK][k - 1][j];
					
					subPath[thisK][i][j] = s1 < s2 ? s1 : s2;
				}
			}
		}

		// check negative cycle
		for (int i = 0; i < numNode; i++)
		{
			if (subPath[numNode % 2][i][i] < 0)
			{
				System.out.println("There is a negative cycle");
				return null;
			}
		}

		// generate final resul
		return subPath[numNode % 2];
	}

	public static void main(String[] args)
	{
		myWeightedGraph graph = new myWeightedGraph();
		graph.readFromSinglePairFile("g1.txt");

		AllParis ap = new AllParis(graph);
		int[][] result = ap.getShortPath();
		System.out.println(result);

		// find the smallest
		int min = inf;
		for (int[] r : result)
		{
			for (int s : r)
			{
				if (s != 0 && s < min)
					min = s;
			}
		}
		System.out.println(min);

	}
}
