package lab6b;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * A List represents a zero-based ordered sequence of elements. Elements can be
 * inserted at the end or at a stated index. The user can access elements by
 * position in the list, and search for elements in the list.
 *
 * @param <T> the type of elements in this list
 */
public class ArrayList<T> implements List<T>, Comparable<List<T>> {
	/**
	 * ListIterator for the ArrayList class<br>
	 * <i>Removal from the List while iterating will cause a problem.<br>
	 * Use this with caution.</i>
	 */
	private class ListIterator<E> implements Iterator<E> {
		private E[] list;
		private int count, current;
		/**
		 * Constructor for the ListIterator
		 * @param head a reference to the list to iterate over
		 * @param c the number of items in the list
		 * @param start the index to start iterating from (usually 0)
		 */
		public ListIterator(E[] head, int c, int start) {
			list = head;
			count = c;
			current = start;
		}
		/**
		 * Returns true if the iteration has more elements.
		 * @return true if the iteration has more elements
		 */
		public boolean hasNext() {
			return current < count;
		}

		/**
		 * Returns the next element in the iteration.
		 * @return the next element in the iteration
		 */
		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			E data = list[current];
			current++;
			return data;
		}

		/*
		 * add code if you need to delete while traversing
		 * http://docs.oracle.com/javase/7/docs/api/java/util/Iterator.html
		 */
		public void remove() {
		}
	}
	/**
	 * The list - generic array of items
	 */
	protected T[] data;
	/**
	 * The number of items in the list
	 */
	protected int size;
	/**
	 * default maximum capacity
	 */
	private final int CAPACITY = 100;

	/**
	 * default constructor - creates a list that can store 100 items;<br>
	 * the size of the list is initialized to 0
	 */
	public ArrayList() {
		size = 0;
		data = (T[]) new Object[CAPACITY];
	}

	/**
	 * parameterized constructor - allows the user to specify the maximum size of
	 * the list.<br>
	 * The list created can store at most <i>size</i> items; the size of the list is
	 * initialized to 0.
	 * 
	 * @param size
	 *   indicates the maximum size of the list as specified by the user
	 */
	public ArrayList(int size) {
		if (size <= 0) {
			System.err.println("The list size must be positive. " + "Creating a list whose size is " + CAPACITY + ".");
			data = (T[]) new Object[CAPACITY];
		} else
			data = (T[]) new Object[size];
		size = 0;
	}
	
    /**
     * empty method - determines whether or not the list is empty
     * @return <i>true</i> if the list is empty; <i>false</i> otherwise
     */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * getSize method - returns the number of items in the list
	 * 
	 * @return the number of items in the list
	 */
	public int getSize() {
		return size;
	}

	/**
	 * makeEmpty method - makes the list empty and sets the size to 0
	 */
	public void clear() {		
		if(size > 0) {
			size = 0;
			data = (T[]) new Comparable[data.length]; 
			System.gc();
		}
	}

	/**
	 * toString method - returns the state of the object as a String
	 * 
	 * @return a String containing the items in the list
	 */
	public String toString() {
		String str = "The list contains " + size + " items.\n[";
		if (!isEmpty()) {
			for (int i = 0; i < size; i++)
				str += i + "\t" + data[i] + "\n";
		}
		return str + "]";
	}
	/**
	 * Returns <i>true</i> if this list contains the specified element and <i>false</i> otherwise.
	 * @param item element to search for
	 * @return <i>true</i> if this list contains the specified element or <i>false</i> otherwise
	 */
	public boolean contains(T searchItem) {
		return indexOf(searchItem) >= 0;
	}
	/**
	 * Returns the element at the specified position in this list.
	 * @param index index of the element to return
	 * @return the element previously at the specified position
	 */
	public T get(int index) {
		if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
	
		return data[index];
	}
	/**
	 * Removes the element at the specified location from this list and returns the value presently stored.
	 * @param index index of the element to remove
	 * @return the element previously at the specified position
	 */
	public T remove(int index) {
		if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
		T info = null;
		if(index >= 0) {
			info = data[index];
			// shift nodes to the left
			for(int i=index;i < size-1; i++)
				data[i] = data[i+1];
			data[--size] = null;
		}
		return info;
	}
	/**
	 * A utility method that expands the array by doubling its size 
	 * @return <i>true</i> if the item can be expanded, <i>false</i> otherwise
	 */
	protected boolean expandCapacity() {
		if(size * 2 <= Integer.MAX_VALUE) {
			T[] tempData = (T[]) new Comparable[data.length * 2];
			for(int i=0; i < size; i++)
				tempData[i] = data[i];
			data = tempData;
			return true;
		}
		return false;		
	}
	/**
	 * Appends the specified element to the end of this list.
	 * @param item the element to be appended to the list
	 */
	public void add(T item) {
		if (size == data.length) 
			expandCapacity();
			data[size++] = item;
	}
	/**
	 * Removes the first occurrence of the specified element from this list. Shifts any subsequent elements to the left. Returns the element that was removed from the list.
	 * @param item the element to be removed
	 * @return the element previously at the specified position
	 */
	public T remove(T removeItem) {
		int index = indexOf(removeItem);
		if(index >= 0) {
			return remove(index);
		}
		return null;
	}
	/**
	 * Returns the index of the first occurrence of the specified element in this list, or <i>-1</i> if the element is not in the list.
	 * @param item element to search for
	 * @return the index of the first occurrence of the specified element in this list, or <i>-1</i> if the element is not in the list
	 */
	public int indexOf(T searchItem) {
		for(int i = 0; i < size; i++)
			if(data[i].equals(searchItem))
				return i;
		return -1;
	}
	/**
	 * Returns the index of the last occurrence of the specified element in this list, or <i>-1</i> if the element is not in the list.
	 * @param item element to search for
	 * @return the index of the last occurrence of the specified element in this list, or <i>-1</i> if the element is not in the list
	 */
	public int lastIndexOf(T searchItem) {
		for(int i = size-1; i >= 0; i--)
			if(data[i].equals(searchItem))
				return i;
		return -1;
	}
	/**
	 * Inserts the specified element at the specified position in this list. If necessary, shifts the element currently at that position and any subsequent elements to the right.
	 * @param item the element to be inserted to the list
	 * @param index index at which the specified element is to be inserted
	 */
	public void add(T item, int index) {
		if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
		if(size == data.length)
			expandCapacity();
		// shift data, if necessary,  before insert
		for(int i = size; i > index; i--)
			data[i] = data[i-1];
		data[index] = item;
		size++;
	}
	/**
	 * Replaces the element at the specified position in this list with the specified element and returns the value presently stored.
	 * @param index index of the element to replace
	 * @param item element to be stored at the specified position
	 * @return the element previously at the specified position
	 */
	public T set(int index, T item) {
		if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
		T info = data[index];
		data[index] = item;
		return info;
	}
	/**
	 * Replaces an element in this list with a new value and returns the value presently stored.
	 * @param oldItem  element to be replaced
	 * @param newItem  new element to be stored
	 * @return the element previously at the specified position
	 */
	public T set(T oldItem, T newItem) {
		T info = null;
		int index = indexOf(oldItem);
		if(index >= 0)
			info = set(index, newItem);
		return info;
	}

	@Override
	public int compareTo(List<T> otherList) {
		return this.size - otherList.getSize();
	}
	/**
	 * @return an Iterator for this class starting at the beginning of the list
	 */
	public Iterator<T> iterator() {
		return new ListIterator<T>(data, size, 0);
	}
	/**
	 * @return an Iterator for this class starting at position start
	 */
	public Iterator<T> iterator(int start) {
		if(start < 0 || start >= size)
			throw new RuntimeException("Out of bounds exception...");
		return new ListIterator<T>(data, size, start);
	}
	/**
	 * Convert this list to an array
	 * @return an array containing the items in this list
	 */
	public T[] toArray(){
		T[] arr = (T[])new Object[size];
		arr = java.util.Arrays.copyOf(data, size);
		return arr;
	}
	/**
	 * Sorts this list suing the Comparator passed an argument
	 * @param comparator used to sort this list
	 * @return An ArrayList sorted based on the Comparator provided
	 */
	public ArrayList<T> sort(java.util.Comparator<T> comparator){
		ArrayList<T> newList = new ArrayList<T>();
		T[] dataArr = this.toArray();
		int pos = -1;
		for (int i = 1; i < dataArr.length; i++) {
			T temp = dataArr[i];
			pos = i;
			while (pos > 0 && comparator.compare(temp, dataArr[pos - 1]) > 0) {
				dataArr[pos] = dataArr[pos - 1]; // switch with the previous data
				pos--;
			}
			dataArr[pos] = temp;
		}
		// copy the data from dataArr to newList
		for (int i = 0; i < dataArr.length; i++)
			newList.add(dataArr[i]);
		// return newList
		return newList;
	}
}
