package wwd.util;

public class myArrayList implements myList, myIterable
{
	private Object[] data;
	private int totalCap;
	private int addIndex=0;
	
	public myArrayList() 
	{
		data = new Object[20];
		totalCap=20;
	}
	
	public myArrayList(int initCap)
	{
		if(initCap<=0)
			throw new IllegalArgumentException();
		data=new Object[initCap];
		totalCap = initCap;
	}

	@Override
	public void add(Object x) 
	{
		add(addIndex, x);		
	}

	@Override
	public void add(int index, Object x) 
	{
		if(index>addIndex || index<0)
			throw new IndexOutOfBoundsException();

		if(addIndex == totalCap)
			expand();
		
		for(int i=addIndex; i>index; i--)
			data[addIndex]=data[addIndex-1];
		
		data[index]=x;
		addIndex++;	
	}
	
	@Override
	public boolean remove(Object x) 
	{
		for(int i=0; i<addIndex; i++)
		{
			if(data[i].equals(x))
			{
				remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public void remove(int index) 
	{	
		if(index>=addIndex || index<0)
			throw new IndexOutOfBoundsException();					
		for(int k=index+1; k<addIndex; k++)
			data[k-1]=data[k];
		addIndex=addIndex-1;
	}

	@Override
	public Object get(int index) 
	{
		if(index>=addIndex || index<0)
			throw new IndexOutOfBoundsException();
		return data[index];
	}

	@Override
	public void set(int index, Object x) 
	{
		if(index>=addIndex || index<0)
			throw new IndexOutOfBoundsException();
		data[index]=x;
	}
	
	@Override
	public boolean contain(Object x) 
	{
		for(int i=0; i<addIndex; i++)
		{
			if(data[i].equals(x))
				return true;
		}
		return false;
	}

	@Override
	public boolean isEmpty() 
	{
		if(addIndex==totalCap)
			return true;
		else 
			return false;
	}

	@Override
	public int size() 
	{
		return addIndex;
	}
	
	@Override
	public myIterator myIterator() 
	{
		return new myIterator() 
		{
			private int index =0;
			@Override
			public void remove() 
			{
				myArrayList.this.remove(--index);
			}
			
			@Override
			public Object next() 
			{
				return data[index++];
			}
			
			@Override
			public boolean hasNext() 
			{
				return index<addIndex;
			}
		};
	}
	
	@Override
	public String toString()
	{
		StringBuilder string = new StringBuilder();
		string.append("{ ");
		for(int i=0; i<addIndex; i++)
		{
			if(i==addIndex-1)
				string.append(data[i]);
			else
				string.append(data[i]+",");
		}
		string.append(" }");
		
		return string.toString();
	}
	
	@Override
	public void clear() 
	{
		addIndex =0;
	}

	@Override
	public Object[] toArray() 
	{
		Object[] dataArray = new Object[addIndex];
		for(int i=0; i<addIndex; i++)
			dataArray[i]=data[i];
		return dataArray;
	}
	
	private void expand()
	{
		//double the size of the array
		totalCap = 2*totalCap;
		Object[] newData = new Object[totalCap];
		for(int i=0; i<addIndex; i++)
		{
			newData[i]=data[i];
		}
		data = newData;
	}
	
	public static void main(String[] Args)
	{
		myArrayList test = new myArrayList();
		test.add(1); test.add(2); test.add(3);
		System.out.println(test);
		test.add(2,5);
		System.out.println(test);
		test.remove(new Integer(1));
		System.out.println(test);
		test.add(7); test.add(8); test.add(9);
		test.remove(3);
		System.out.println(test);
		myIterator iterator = test.myIterator();
		while(iterator.hasNext())
		{
			System.out.println(iterator.next());
			//iterator.remove();
		}
		test.clear();
		System.out.println(test.toArray()[3]);
		
			
	}

	

}
