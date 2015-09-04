package wwd.algorithm;
import java.util.ArrayList;
import java.util.List;
public class CountRevision 
{
	long sum;
		
	List<Integer> countRevision(List<Integer> in)
	{
		int size=in.size();
		if(size==1)
		{
			return in;
		}
		else
		{
			List<Integer> cloneIn = new ArrayList<Integer>(in);
			List<Integer> x;
			List<Integer> y;
			List<Integer> out=new ArrayList<Integer>();
			
			
			cloneIn.subList(size/2, size).clear();
			in.subList(0, size/2).clear();
					
			x=countRevision(cloneIn);
			y=countRevision(in);
			
			//combine
			int i=0,j=0;
			
			if(size%2==0)
			{
				for(int k=0; k<size; k++ )
				{
					if(i==size/2)
					{
						out.add(k, y.get(j));
						j++;
					}
					else if(j==size/2)
					{
						out.add(k, x.get(i));
						i++;
					}
					else
					{
						if(x.get(i)<=y.get(j))
						{
							out.add(k, x.get(i));
							i++;
						}
						else
						{
							out.add(k, y.get(j));
							sum+=size/2-i;
							j++;
						}
					}
				}
			}
			else
			{
				for(int k=0; k<size; k++ )
				{
					if(i==size/2)
					{
						out.add(k, y.get(j));
						j++;
					}
					else if(j==size/2+1)
					{
						out.add(k, x.get(i));
						i++;
					}
					else
					{
						if(x.get(i)<=y.get(j))
						{
							out.add(k, x.get(i));
							i++;
						}
						else
						{
							out.add(k, y.get(j));
							sum+=size/2-i;
							j++;
						}
					}
				}
			}	
			
			return out;
		}
	}
	
	public static void main(String[] Args)
	{
		List<Integer> result= new ArrayList<Integer>();
		CountRevision countrevision= new CountRevision();		
		result=countrevision.countRevision(TextReader.readSingle("IntegerArray.txt"));
		System.out.println(countrevision.sum);
		System.out.println(result);
		
	}
}
