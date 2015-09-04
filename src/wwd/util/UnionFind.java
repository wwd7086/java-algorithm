package wwd.util;

public class UnionFind
{
	private int[] data;
	private int[] rank;
	private int numSet;
	private int size;

	public UnionFind(int size)
	{
		this.size = size;
		data = new int[this.size];
		rank = new int[this.size];
		numSet = this.size;

		for (int i = 0; i < size; i++)
		{
			data[i] = i;
			rank[i] = 0;
		}
	}

	public void union(int i, int j)
	{
		if (i < 0 || i >= size || j < 0 || j >= size)
			throw new IllegalArgumentException();

		int iRoot = find(i);
		int jRoot = find(j);

		if (iRoot != jRoot)
		{
			// optimization 1
			if (rank[iRoot] > rank[jRoot])
			{
				// merge j => i
				data[jRoot] = iRoot;
			}
			else if (rank[iRoot] == rank[jRoot])
			{
				// merge j => i
				data[jRoot] = iRoot;
				rank[iRoot]++;
			}
			else
			{
				// merge i => j
				data[iRoot] = jRoot;
			}
			
			numSet--;
		}
	}

	public int find(int i)
	{
		if (i < 0 || i >= size)
			throw new IllegalArgumentException("input is:" + i);

		int leader = i;
		while (leader != data[leader])
		{
			leader = data[leader];
		}

		// optimization 2
		data[i] = leader;
		return leader;
	}
	
	public int getNumSet()
	{
		return numSet;
	}

	public String toString()
	{
		String result = "------------\n";
		for (int i = 0; i < size; i++)
		{
			result += printSingle(i);
			result += "\n";
		}
		return result + "------------";
	}

	private String printSingle(int i)
	{
		String si = Integer.toString(i);
		if (i != data[i])
			return si + "=>" + printSingle(data[i]);
		else
			return si;
	}

	public static void main(String[] args)
	{
		UnionFind uf = new UnionFind(10);
		uf.union(1, 2);
		uf.union(3, 4);
		uf.union(1, 4);
		uf.union(1, 5);
		System.out.println(uf);

		System.out.println(uf.find(5));
		System.out.println(uf);

		System.out.println(uf.find(4));
		System.out.println(uf);

		System.out.println(uf.find(3));
		System.out.println(uf.find(2));

		System.out.println(uf.find(7));
		System.out.println(uf.find(5));

		uf.union(4, 7);
		System.out.println(uf.find(7));

		System.out.println(uf);
	}
}
