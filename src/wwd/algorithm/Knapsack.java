package wwd.algorithm;
import java.util.*;
import wwd.util.*;

public class Knapsack
{
	//a->value b->weight
	private List<Tuple> fullSet;
	
	//for iterative version
	private int[][] subProb;
	
	//for recursive version
	Map<Otuple,Integer> probCache = new HashMap<Otuple, Integer>();
	
	public Knapsack(List<Tuple> fullSet)
	{
		this.fullSet = fullSet;
	}
	
	public int findSet(int bound)
	{
		//initialize subProb
		int numSet = fullSet.size();
		subProb = new int[numSet+1][bound+1];
		for(int i=0; i<=bound; i++)
			subProb[0][i]=0;
		for(int i=0; i<=numSet; i++)
			subProb[i][0]=0;
		
		//iterations
		for(int i=1; i<=numSet; i++)
		{
			for(int j=1; j<=bound; j++)
			{
				int s1 = subProb[i-1][j];
				int reduceCap = j-fullSet.get(i-1).b;
				if(reduceCap>=0)
				{
					int s2 = subProb[i-1][reduceCap] + fullSet.get(i-1).a;
					if(s1<s2)
						subProb[i][j]=s2;
					else
						subProb[i][j]=s1;
				}
				else
					subProb[i][j]=s1;
			}
		}
		
		return subProb[numSet][bound];
	}
	
	public int findSetR(int numSet, int bound)
	{
		//simple case
		if(numSet==0 || bound==0)
			return 0;
		
		//check the cache 
		Otuple key = new Otuple(numSet,bound);
		if(probCache.containsKey(key))
			return probCache.get(key);
		
		//normal case
		Tuple tp = fullSet.get(numSet-1);
		int s1 = findSetR(numSet-1, bound);
		if(bound-tp.b>=0)
		{
			int s2 = findSetR(numSet-1, bound-tp.b)+tp.a;
			if(s2>s1)
			{
				probCache.put(key, s2);
				return s2;
			}
		}
		probCache.put(key, s1);
		return s1;
	}
	
	public static void main(String[] args)
	{
		testSmall();
		//testBig();
	}
	
	public static void testSmall()
	{
		Knapsack ks = new Knapsack(TextReader.readPair("knapsack1.txt"));
		System.out.println(ks.findSet(10000));
		System.out.println(ks.findSetR(100,10000));
	}
	
	public static void testBig()
	{
		Knapsack ks = new Knapsack(TextReader.readPair("knapsack_big.txt"));
		//System.out.println(ks.findSet(2000000));
		System.out.println(ks.findSetR(2000,2000000));
	}
}
