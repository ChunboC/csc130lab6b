package lab6b;

import java.util.Comparator;

/**
 * <p>
 * Title: OrderedArrayList.java
 * </p>
 *
 * <p>
 * Description: Represents an ordered list implemented using an array-based data
 * structure. Maintains elements in sorted order based on the provided
 * comparator. Extends the ArrayList class and overrides methods to ensure
 * ordered insertion and retrieval.
 * </p>
 *
 * @author Chunbo Cheng
 */
// T extends Comparable<? super T>
public class OrderedArrayList<T> extends ArrayList<T> {

	Comparator<T> comparator;

	public OrderedArrayList() {
		super();
	}

	public OrderedArrayList(Comparator<T> comp) {
		this.comparator = comp;
	}

	public void add(T insertItem) {
		// System.out.println("Adding " + insertItem);
		// expand the capacity of the array if it is full
		if (size == data.length)
			expandCapacity();
		// use a binary search to find insert location
		int left = 0, mid = 0, right = size;
		int location = -1;
		while (left != right) {
			mid = (left + right) / 2;
			int compare = comparator.compare(insertItem, data[mid]);
			if (compare == 0) {
				location = mid;
				right = mid;
			} else if (compare < 0) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		// shift data if necessary
		for (int i = size - 1; i >= left; i--) {
			data[i + 1] = data[i];
		}
		// insert item at correct location in the list
		data[left] = insertItem;
		// increment the number of items in the list
		size++;
	}

	@Override
	public int indexOf(T searchItem) {
		int left = 0, mid = 0, right = size;
		int location = -1;
		while (left < right) {
			mid = (left + right) / 2;
			int compare = comparator.compare(searchItem, data[mid]);
			if (compare == 0) {
				location = mid;
				right = mid;
			} else if (compare < 0) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return location;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this
	 * list, or <i>-1</i> if the element is not in the list.
	 * 
	 * @param item element to search for
	 * @return the index of the last occurrence of the specified element in this
	 *         list, or <i>-1</i> if the element is not in the list
	 */
	public int lastIndexOf(T searchItem) {
		int index = indexOf(searchItem);
		if (index >= 0) {
			int lastIndex = index;
			for (int i = index; i <= size; i++)
				if (data[i].equals(searchItem))
					lastIndex = i;
				else
					return lastIndex;
		}
		return -1;
	}

	/**
	 * Inserts the specified element into this list. If necessary, shifts the
	 * element currently at that position and any subsequent elements to the right.
	 * 
	 * @param item  the element to be inserted to the list
	 * @param index ignored since this could possibly result in an unordered list
	 */
	public void add(T item, int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		add(item);
	}

	/**
	 * Replaces the element at the specified position in this list with the
	 * specified element and returns the value presently stored.
	 * 
	 * @param index index of the element to replace
	 * @param item  element to be stored at the specified position
	 * @return the element previously at the specified position
	 */
	public T set(int index, T item) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		T info = remove(index);
		add(item);
		return info;
	}
}