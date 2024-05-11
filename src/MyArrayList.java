import java.util.Iterator;

public class MyArrayList<T extends  Comparable<T>>{

    private Object[] arr; // The array buffer into which the elements of the MyArrayList are stored.
    private int length = 0; // The number of elements in the MyArrayList.
    private static final int DEFAULT_CAPACITY = 10; // The default initial capacity of the MyArrayList.


    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        arr = new Object[initialCapacity];
    }// Constructs an empty list with the specified initial capacity.

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
        length = 0;
    } // Constructs an empty list with an initial capacity of ten.


    private void increaseCapacity() {
        Object[] temp = new Object[arr.length * 2];
        System.arraycopy(arr, 0, temp, 0, arr.length);
        arr = temp;
    }// Increases the capacity of this MyArrayList instance, if necessary, to ensure that it can hold at least the number of elements specified by the minimum capacity argument.


    private void checkCap(int index) {
        if(index < 0|| index >= length)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + length);
    } // Checks if the given index is in the range of the list.

    public void add(T item) {
        if (length == arr.length) {
            increaseCapacity();
        }
        arr[length++] = item;
    }// Appends the specified element to the end of this list.

    public void set(int index, T item) {
        checkCap(index);
        arr[index] = item;
    } // Replaces the element at the specified position in this list with the specified element.

    public void add(int index, T item) {
        checkCap(index);
        if (length == arr.length) {
            increaseCapacity();
        }
        System.arraycopy(arr, index, arr, index + 1, length - index);
        arr[index] = item;
        length++;
    } // Inserts the specified element at the specified position in this list.

    public void addFirst(T item) {
        add(0, item);
    } // Inserts the specified element at the beginning of this list.

    public void addLast(T item) {
        add(item);
    } // Appends the specified element to the end of this list.

    public T get(int index) {
        checkCap(index);
        return (T) arr[index];
    } // Returns the element at the specified position in this list.

    public T getFirst() {
        if(length == 0){
            throw new IndexOutOfBoundsException("List is empty");
        }
        return (T) arr[0];
    } // Returns the first element in this list.

    public T getLast() {
        if(length == 0) {
            throw new IndexOutOfBoundsException("List is empty");
        }
        return (T) arr[length - 1];
    } // Returns the last element in this list.




    public void remove(int index) {
        checkCap(index);

        int numMoved = length - index - 1;
        if (numMoved > 0) {
            System.arraycopy(arr, index + 1, arr, index, numMoved);
        }
        arr[--length] = null;
    } // Removes the element at the specified position in this list.

    public void removeFirst() {
        remove(0);
    } // Removes the first element from this list.

    public void removeLast() {
        remove(length - 1);
    } // Removes the last element from this list.

    public void sort() {
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length-1-i; j++) {
                if (((Comparable)arr[j]).compareTo(arr[j+1]) > 0) {
                    Object temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    } // Sorts this list in ascending order.

    public int indexOf(Object object) {
        for (int i = 0; i < length; i++) {
            if (arr[i].equals(object)) {
                return i;
            }
        }
        return -1;
    } // Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.


    public int lastIndexOf(Object object) {
        for (int i = length - 1; i >= 0; i--) {
            if (arr[i].equals(object)) {
                return i;
            }
        }
        return -1;
    } // Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element.

    public boolean exists(Object object) {
        return indexOf(object) != -1;
    } // Returns true if this list contains the specified element.

    public Object[] toArray() {
        Object[] temp = new Object[length];
        System.arraycopy(arr, 0, temp, 0, length);
        return temp;
    } // Returns an array containing all of the elements in this list in proper sequence.

    public void clear() {
        arr = new Object[DEFAULT_CAPACITY];
    } // Removes all of the elements from this list.

    public int size() {
        return length;
    } // Returns the number of elements in this list.


    public Iterator iterator() {
        return new MyIterator();
    } // Returns an iterator over the elements in this list in proper sequence.

    public class MyIterator implements Iterator<T> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < length;
        }

        @Override
        public T next() {
            return (T) arr[index++];
        }
    } // An iterator over the elements in this list in proper sequence.
}