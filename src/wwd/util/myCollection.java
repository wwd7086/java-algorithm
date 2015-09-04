package wwd.util;

interface myCollection 
{
	void add(Object x);
	boolean remove(Object x);
	boolean contain(Object x);
	boolean isEmpty();
	int size();
	void clear();
	Object[] toArray();
}
