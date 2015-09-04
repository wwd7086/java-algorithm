package wwd.util.graph;

public class Edge implements Comparable<Edge>
{
	private int head;
	private int tail;
	
	// can have multiple meanings
	//1. the shortest length towards head
	//2. the length between head and tail
	private int length;

	public Edge(int head, int tail, int length)
	{
		this.head = head;
		this.tail = tail;
		this.length = length;
	}

	public int getLength()
	{
		return length;
	}

	public int getHead()
	{
		return head;
	}

	public int getTail()
	{
		return tail;
	}
	
	public boolean isSameEdge(Edge other)
	{
		return isSameEdge(other,true);
	}
	
	public boolean isSameEdge(Edge other, boolean isDirected)
	{
		boolean result;
		if(isDirected)
			result = head==other.head && tail==other.head && length==other.length;
		else
		{
			result = ((head==other.head && tail==other.tail)||
					 (head==other.tail && tail==other.head))&&
					 length==other.length;
		}	
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return isSameEdge((Edge) obj);
	}

	@Override
	public int compareTo(Edge o)
	{
		int result = 1;
		if (o.length == this.length)
			result = 0;
		else if (o.length > this.length)
			result = -1;

		return result;
	}

	@Override
	public String toString()
	{
		return "l" + length + "-h" + head + "-t" + tail;
	}
}

