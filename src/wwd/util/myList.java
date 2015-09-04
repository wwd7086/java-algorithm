package wwd.util;

interface myList extends myCollection
{
	void add(int index, Object x);
	void remove(int index);
	Object get(int index);
	void set(int index, Object x);
}
