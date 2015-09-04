package wwd.util;

public class myHeap<T extends Comparable<T>>
{
	public myHeap()
	{
		createArray();
	}
	
	public myHeap(int size)
	{
		arraySize = size;
		createArray();
	}
	
	public int add(T x)
	{
		//check the array size
		if(size>=arraySize)
			expand();
		
		//append it to the right bottom position of the tree
		data[size] = x;		
		//bubble up
		int childIndex = size;
		int parentIndex = getParentIndex(childIndex);
		while(parentIndex>=0 && x.compareTo(get(parentIndex))<0)
		{
			swap(childIndex,parentIndex);
			childIndex = parentIndex;
	 		parentIndex = getParentIndex(childIndex);
		}
		
		return size++;
	}
	
	public void remove(int index)
	{
		//check the size
		if(index>=size)
			return;
		
		//special case the last node
		if(index==size-1)
		{
			data[index]=null;
			size--;
			return;
		}
		
		//move the last element up
		data[index] = data[size-1];
		data[--size] = null;
		
		//bubble down
		int parent = index;
		int minChild = getMinChildIndex(parent);
		T value = get(index);
		while(minChild>=0 && value.compareTo(get(minChild))>0)
		{
			swap(parent,minChild);
			parent = minChild;
			minChild = getMinChildIndex(parent);
		}
	}
	
	public T extractMin()
	{
		if(size==0)
			return null;
		
		T value = get(0);
		remove(0);
		return value;
	}
	
	public T peekMin()
	{
		if(size==0)
			return null;
		
		T value = get(0);
		return value;
	}
	
	public int size()
	{
		return size;
	}
	
	public boolean isEmpty()
	{
		return size==0;
	}
	
	public String toString()
	{
		int lineLength = 1;
		int lineCount = 0;
		String output = "";
		for(int i=0; i<size; i++)
		{
			if(lineCount<lineLength)
			{
				output+=data[i];
				output+=" ";
				lineCount++;
			}
			else
			{
				output+="\n";
				output+=data[i];
				output+=" ";

				lineCount=1;
				lineLength*=2;
			}
		}
		return output;
	}
	
	private int getParentIndex(int index)
	{
		//check
		if(index==0)
			return -1;
		
		if(index%2!=0)
			index++;
		
		return index/2 - 1;
	}
	
	private int getLeftChildIndex(int index)
	{
		return 2*index + 1;
	}
	
	private int getRightChildIndex(int index)
	{
		return 2*index + 2;
	}
	
	private int getMinChildIndex(int index)
	{
		int leftChild = getLeftChildIndex(index);
		int rightChild = getRightChildIndex(index);
		
		if(leftChild<size && rightChild<size)
			return get(leftChild).compareTo(get(rightChild))>0?rightChild:leftChild;
		else
			return -1;
	}
	
	@SuppressWarnings("unchecked")
	protected T get(int index)
	{
		return (T)data[index];
	}
	
	protected void swap(int i, int j)
	{
		T m = get(i);
		data[i]=data[j];
		data[j]=m;
	}
	
	private void expand()
	{
		arraySize*=2;
		Object[] oldData = data;
		createArray();
		
		for(int i=size-1; i>=0; i--)
		{
			data[i] = oldData[i];
		}
	}
	
	private void createArray()
	{
		data = new Object[arraySize];
	}
	
	protected int size=0;
	private int arraySize=100;
	protected Object[] data;	
	
	public static void main(String[] args)
	{
		myHeap<Integer> heap = new myHeap<Integer>();
		
		//insert and extract min test
		for (int i=200; i>0; i--)
		{
			heap.add((int) (1000*Math.random()));
			//System.out.println(heap);
			//System.out.println("---------------");
		}
		
		System.out.println(heap);
		System.out.println("---------------------------------------------");
		
		for (int i=200; i>0; i--)
		{
			System.out.println(heap.extractMin());
			//System.out.println("------");

			//System.out.println(heap);
			//System.out.println("---------------");
		}
	}
}
