package wwd.algorithm;
import java.util.HashSet;

import wwd.util.graph.*;
import wwd.util.*;

public class KruskalMST
{
	private EdgeHeap edges;
	private UnionFind vertUnion;
	private myArrayList spannTree = new myArrayList();

	public KruskalMST(EdgeHeap edges)
	{
		this.edges = edges;
		this.vertUnion = new UnionFind(edges.getNumNodes() + 1); // vertex
																	// starting
																	// from 0
	}

	public myArrayList findSpannTree()
	{
		while (!edges.isEmpty())
			buildTree();

		return spannTree;
	}

	public UnionFind doCluster(int clusterSize)
	{
		while (!edges.isEmpty())
		{
			if (clusterSize >= vertUnion.getNumSet() - 1)
				break;

			buildTree();
		}

		return vertUnion;
	}

	private void buildTree()
	{
		Edge minEdge = edges.extractMin();
		if (vertUnion.find(minEdge.getHead()) != vertUnion.find(minEdge.getTail()))
		{
			spannTree.add(minEdge);
			vertUnion.union(minEdge.getHead(), minEdge.getTail());
		}
	}

	@SuppressWarnings("unused")
	private int MaxSpace()
	{
		HashSet<Tuple> leaderPair = new HashSet<Tuple>();
		int maxSpace=0;
		
		while (leaderPair.size() < 6)
		{
			Edge e = edges.extractMin();
			int hl = vertUnion.find(e.getHead());
			int tl = vertUnion.find(e.getTail());
			if(hl!=tl)
			{
				leaderPair.add(new Tuple(hl, tl));
				if(e.getLength()>maxSpace)
					maxSpace = e.getLength();
			}
		}
		System.out.println(leaderPair);
		return maxSpace;
	}
	
	private int MinSpace()
	{
		Edge minEdge = edges.extractMin();
		if (vertUnion.find(minEdge.getHead()) != vertUnion.find(minEdge.getTail()))
			return minEdge.getLength();
		else 
			return MinSpace();
	}

	public static void main(String[] args)
	{
		// testMST();

		testCluster();
	}

	static void testMST()
	{
		EdgeHeap edges = new EdgeHeap();
		edges.readFromFile("edges.txt");

		// calculate the spanning tree
		KruskalMST mst = new KruskalMST(edges);
		myArrayList st = mst.findSpannTree();
		System.out.println(st);

		// calculate the sum of cost of spanning tree
		myIterator it = st.myIterator();
		int edgeSum = 0;
		while (it.hasNext())
		{
			Edge e = (Edge) it.next();
			edgeSum += e.getLength();
		}
		System.out.println(edgeSum);

		// check the union structure
		System.out.println(mst.vertUnion);
		System.out.println(mst.vertUnion.getNumSet());
	}

	static void testCluster()
	{
		EdgeHeap edges = new EdgeHeap();
		edges.readFromFile("clustering1.txt");

		// calculate the spanning tree
		KruskalMST mst = new KruskalMST(edges);
		UnionFind uf = mst.doCluster(4);
		System.out.println(uf);

		System.out.println(mst.MinSpace());
	}
}
