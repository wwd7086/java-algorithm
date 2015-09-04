package wwd.util.graph;

import java.util.HashMap;

public class EdgeHeapWithHeadMap extends EdgeHeap
{
	private HashMap<Integer, Integer> reverseIndex = new HashMap<Integer, Integer>();

	public int add(Edge x)
	{
		// check whether the head already exist
		int result;
		int head = x.getHead();
		if (reverseIndex.containsKey(head))
		{
			int headIndex = reverseIndex.get(head);
			// length is smaller than in the heap, update the old one
			if (get(headIndex).getLength() > x.getLength())
			{
				remove(headIndex);
				reverseIndex.remove(head);

				result = add(x);
			}
			// length is bigger than in the heap, do nothing
			else
				result = -1;
		}
		// do not have the vertex yet
		else
		{
			// add to the reverse index
			reverseIndex.put(x.getHead(), size);

			result = super.add(x);
		}
		return result;
	}

	public void remove(int index)
	{
		// check the size
		if (index >= size)
			return;

		// remove from the reverse index
		reverseIndex.remove(get(index).getHead());
		reverseIndex.put(get(size - 1).getHead(), index);

		super.remove(index);

	}

	public void swap(int i, int j)
	{
		// update the reverse index
		reverseIndex.put(get(i).getHead(), j);
		reverseIndex.put(get(j).getHead(), i);

		super.swap(i, j);
	}

	public String printReverseIndex()
	{
		return reverseIndex.toString();
	}
}