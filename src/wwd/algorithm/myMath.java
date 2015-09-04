package wwd.algorithm;

import java.util.*;
public class myMath
{
	public static int combination(int n, int k)
	{
		if(n<0 || k<0)
			throw new IllegalArgumentException("n or k smaller than zero");
		
		if(n<k)
			throw new IllegalArgumentException("n smaller than k");
		
		int fz = 1;
		for(int i=0;i<k;i++)
			fz*=n--;
		
		int fm=1;
		for(;k>0;k--)
			fm*=k;
		
		return fz/fm;
	}
	
	public static ArrayList<TreeSet<Integer>> listCombi(int n, int k)
	{
		ArrayList<TreeSet<Integer>> result = new ArrayList<TreeSet<Integer>>();
		
		if(k==1)
		{
			for(int i=1; i<=n; i++)
			{
				TreeSet<Integer> a = new TreeSet<Integer>();
				a.add(i);
				result.add(a);
			}
		}
		else
		{
			ArrayList<TreeSet<Integer>> lastResult = listCombi(n, k-1);
			for(TreeSet<Integer> res:lastResult)
			{
				int lastBit = res.last();	
				for(int i=lastBit+1; i<=n; i++)
				{
					TreeSet<Integer> a = new TreeSet<Integer>(res);
					a.add(i);
					result.add(a);
				}
			}
		}
		
		return result;
	}
	
	public static int getBitIndex(int number)
	{
		int index=1;
		while(number%2!=1)
		{
			index++;
			number/=2;
		}
		return index;
	}
	
	public static void main(String[] args)
	{
		System.out.println(combination(10,4));
		
		for(TreeSet<Integer> a:listCombi(10,4))
			System.out.println(a);
		
		int k=3, n=15;
		System.out.println(combination(n,k) == listCombi(n,k).size());
		
		//int[][][] test = new int[900][1000][1000];
		System.out.println(getBitIndex(8));
	}
}
