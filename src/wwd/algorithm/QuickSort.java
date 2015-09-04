package wwd.algorithm;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
public class QuickSort 
{
	@SuppressWarnings("rawtypes")
	private List<Comparable> in = new ArrayList<Comparable>();
	
	@SuppressWarnings("rawtypes")
	public void qsortOrigin(List<Comparable> in)
	{
		this.in = in;
		mediumElementSort(0, in.size());
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List qsort(Collection in)
	{
		this.in.addAll(in);
		mediumElementSort(0, in.size());
		return this.in;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void mediumElementSort( int start, int end)
	{
		if((end-start)<=1)
			return;
		
		//choose the pivot and put it at the first
		int p2,index;
		Comparable pivot,c1,c2,c3;
		
		c1=in.get(start);
		c3=in.get(end-1);
		if((end-start)%2==0)
		{
			p2=start+((end-start)/2)-1;
			c2=in.get(p2);
		}
		else
		{
			p2=start+(end-start)/2;
			c2=in.get(p2);
		}
		
		if(c1.compareTo(c2)<0)
		{
			if(c2.compareTo(c3)<0)
			{
				pivot=c2;
				index = p2;
			}
			else
			{
				if(c1.compareTo(c3)<0)
				{
					pivot=c3;
					index=end-1;
				}
				else
				{
					pivot=c1;
					index=start;
				}
			}
		}
		else
		{
			if(c3.compareTo(c2)<0)
			{
				pivot=c2;
				index=p2;
			}
			else
			{
				if(c1.compareTo(c3)<0)
				{
					pivot=c1;
					index=start;
				}
				else
				{
					pivot=c3;
					index=end-1;
				}
			}
		}
		
		//System.out.println(in.subList(start, end));
		//System.out.println("c1="+c1+"  c2="+c2+"  p2="+p2+"  c3="+c3+"  pivot="+pivot);
		
		in.set(index, in.get(start));
		in.set(start, pivot);
		
		
		//order the array
		int i=start+1;
		int j=start+1;
		Comparable swap;
		
		for(;j<end;j++)
		{
			if(in.get(j).compareTo(pivot)<0)
			{
				swap=in.get(i);
				in.set(i, in.get(j));
				in.set(j, swap);
				i++;
			}
		}
		
		//restore pivot position
		in.set(start, in.get(i-1));
		in.set(i-1, pivot);
		
		//recursive call
		mediumElementSort(start, i-1);
		mediumElementSort(i, end);
	}
	
	public static void main(String[] Args)
	{
		QuickSort qs = new QuickSort();
	    List<Integer> data = TextReader.readSingle("IntegerArray.txt");
		System.out.println(data);
		System.out.println(qs.qsort(data));
		
	}
}
