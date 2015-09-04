package wwd.algorithm;
import java.util.*;

public class Prime
{
	private static Integer[] primeCache = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 
	                                   59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 
	                                   127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 
	                                   191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 
	                                   257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331};
	
	public static List<Integer> getPrime(int bound)
	{
		if(bound<=1)
			return null;
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		//check the cache
		int index = primeCacheSearch(bound);
		if(index!=-1)
		{
			for(int i=0; i<=index; i++)
				result.add(primeCache[i]);
			return result;
		}
		
		//no cache is available
		boolean[] primeTest = new boolean[bound-1]; //false means is a Prime	
		int number=2;
		for(boolean isNotPrime:primeTest)
		{
			if(!isNotPrime)
			{
				result.add(number);
				for(int i=2; i*number<=bound; i++)
					primeTest[i*number-2] = true;
			}
			number++;	
		}
		
		//refresh the cache;
		primeCache = result.toArray(primeCache);
		return result;
	}
	
	public static boolean isPrime(int number)
	{
		if(number<=1)
			return false;
		
		if(number==2 || number==3)
			return true;
		
		int sroot = (int) Math.sqrt(number);
		for(int a:getPrime(sroot))
		{
			if(number%a==0)
				return false;
		}
		return true;
	}
	
	public static int nextPrime(int number)
	{
		//check the common case
		if(number<=1)
			return 2;
		
		if(number<=3)
			return 3;
		
		//check the cache
		int index=primeCacheSearch(number);
		if(index!=-1)
		{
			if(primeCache[index]==number)
				return number;
			else
				return primeCache[index+1];
		}
		
		//check whether the number self is already a prime number
		if(isPrime(number))
			return number;
		
		//worst case:
		//calculate according to following theorem:
		//if n > 3 is an integer, 
		//then there always exists at least one prime number p with n < p < 2n - 2
		getPrime(2*number-2);
		return primeCache[primeCacheSearch(number)+1];
	}
	
	private static int primeCacheSearch(int number)
	{
		int cacheSize = primeCache.length;
		if(number<=primeCache[cacheSize-1])
		{
			//find out the bound using binary search
			int rBound=cacheSize-1; int lBound=0;
			int index=0;
			while(true)
			{
				if(rBound-lBound<2)
				{
					index = lBound;
					break;
				}
				
				index = (rBound+lBound)/2;
				if(primeCache[index]==number)
					break;
				else if(primeCache[index]>number)
					rBound=index;
				else
					lBound=index;
			}
			return index;
		}
		return -1;
		
	}
	
	public static void main(String[] args)
	{
		//System.out.println(getPrime(88888));
		//System.out.println(getPrime(88888));
		//System.out.println(isPrime(4));
		//System.out.println(primeCache);		
		System.out.println(nextPrime(9888));

	}
}
