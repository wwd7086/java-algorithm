package wwd.util;

import java.util.NoSuchElementException;

public class myLinkedList implements myList,myIterable
{
	private List start;
	private List end;
	private int size=0;
	

	@Override
	public void add(Object x) 
	{
		if(size==0)
			emptyAdd(x);
		else 
		{
			List newone = new List(x);
			end.tail=newone;
			newone.tail=null;
			newone.head=end;
			end = newone;
			size++;
		}		
	}
	
	public void addFirst(Object x)
	{
		if(size==0)
			emptyAdd(x);
		else 
		{
			List newone = new List(x);
			start.head = newone;
			newone.head=null;
			newone.tail=start;
			start=newone;
			size++;
		}	
	}
	
	public void addLast(Object x)
	{
		add(x);
	}
	
	@Override
	public void add(int index, Object x) 
	{	
		List before, after, newone=new List(x);
		after = find(index);
		
		before = after.head;
		if(before==null)
		{
			after.head=newone;
			newone.head=null;
			newone.tail=after;
			start =newone;
			size++;
		}
		else 
		{
			before.tail=newone;
			after.head=newone;
			newone.head=before;
			newone.tail=after;
			size++;	
		}	
		
	}
	
	private void emptyAdd(Object x)
	{
		List newone=new List(x);
		start = newone;
		end = newone;
		size++;
	}

	
	
	@Override
	public boolean remove(Object x) 
	{
		if(size==0)
			return false;
		for(List i=start; i!=null; i=i.tail)
		{
			if(i.data.equals(x))
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
		List current = find(index);		
		remove(current);   		
	}
	
	public void removeLast()
	{
		remove(end);
	}
	
	public void removeFirst()
	{
		remove(start);
	}
	
	private void remove(List x)
	{
		if(x==null)
			throw new NoSuchElementException();
		
		List before ,after;
		before = x.head;
		after=x.tail;
		
		if(before == null && after == null)
		{
			start=null;
			end=null;
		}
		else if (before==null) 
		{
			after.head=null;
			start=after;
		}
		else if(after==null)
		{
			before.tail=null;
			end=before;
		}
		else
		{
			before.tail=after;
			after.head=before;
		}
		size--;
	}
	
	
	
	public Object pollFirst()
	{
		if(start == null)
			throw new NoSuchElementException();
		Object value = start.data;
		removeFirst();
		return value;
	}
	
	public Object pollLast()
	{
		if(end == null)
			throw new NoSuchElementException();
		Object value = end.data;
		removeLast();
		return value;
	}
	
	public Object poll(int index)
	{
		List current = find(index);
		if(current == null)
			return null;
		else
		{
			Object value = current.data;
			remove(current);
			return value;
		}
	}
	
	
	
	@Override
	public boolean contain(Object x) 
	{
		if(size==0)
			return false;
		for(List i=start; i!=end; i=i.tail)
		{
			if(i.data.equals(x))
				return true;
		}
		return false;
	}
	
	@Override
	public Object get(int index) 
	{	
		List current = find(index);
		if(current==null)
			return null;
		else 
			return current.data;
	}

	@Override
	public void set(int index, Object x) 
	{
		List current = find(index);
		if(current==null)
			return;
		else 
			current.data=x;
	}
	

	
	private List find(int index)
	{
		if(index>=size)
		{
			throw new IndexOutOfBoundsException();
		}
		
		List current;
		if(index<size/2)
		{
			current=start;
			for(int i=0; i<index; i++)
			{
				current = current.tail;
			}
			return current;
		}
		else
		{
			current=end;
			index=size-index-1;
			for(int i=0; i<index; i++)
			{
				current = current.head;
			}
			return current;
		}
	}
	
	
	
	
	@Override
	public String toString()
	{
		StringBuilder string = new StringBuilder();
		string.append("{ ");
		for(List next=start; next!=null; next=next.tail)
		{
			if(next.tail==null)
				string.append(next.data);
			else
				string.append(next.data+",");
		}
		string.append(" }");
		return string.toString();	
	}
	
	@Override
	public boolean isEmpty() 
	{
		if(size==0)
			return true;
		else 
			return false;
	}

	@Override
	public int size() 
	{
		return size;
	}

	@Override
	public myIterator myIterator()
	{
		return new myIterator() 
		{
			List current=start;
			
			@Override
			public void remove() 
			{
				List remove;
				if(current == null)
					remove = end;
				else
					remove = current.head;
				myLinkedList.this.remove(remove);
			}
			
			@Override
			public Object next() 
			{
				Object value= current.data;
				current = current.tail;
				return value;
			}
			
			@Override
			public boolean hasNext() 
			{
				return current!=null;
			}
		};
	}
	
	
	
	
	
	
	public static void main(String[] Args)
	{
		myLinkedList test = new myLinkedList();
		for(int i=0; i<10 ;i++)
			test.addFirst(i);
		
		//test.add(20, 7);
		
	    System.out.println(test);
	    
	    System.out.println(test.contain(2));
		
	    System.out.println(test.contain(7));
		test.remove(7);
		
		test.get(0);
		test.set(0, 10000);
		System.out.println(test.poll(3));
		System.out.println(test);
		
		for(int i=0; i<10; i++)
			test.add(Math.random());
		myIterator iterator = test.myIterator();
		while(iterator.hasNext())
		{
			System.out.println(iterator.next());
			//iterator.remove();
		}
		System.out.println(test);
		//for(int i=0; i<12; i++)
			//System.out.println(test.pollLast());
		
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}
}







class List
{
	List head;
	List tail;
	
	Object data;
	
	List(Object data) 
	{
		this.data = data;
	}
	
	List(Object data, List head, List tail)
	{
		this.data=data;
		this.head=head;
		this.tail=tail;
	}
	
}
