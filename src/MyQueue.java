public class MyQueue<T extends Comparable<T>> {
    MyArrayList<T> queue = new MyArrayList<>(); // Based on ArrayList
    public MyQueue() { // Null constructor
    }
    public T front() { // first element
        return queue.get(0);
    }
    public T back() {
        return queue.get(queue.size() - 1);
    } // last element
    public T dequeue() { // gets first element and removes it
        T item = queue.get(0);
        queue.remove(0);
        return item;
    }

    public void enqueue(T item) {
        queue.add(item);
    } // adds elements to the first of the queue
    public boolean isEmpty() {
        return queue.size() == 0;
    } // checks if the queue is empty
    public int size() {
        return queue.size();
    } // size function
    public void clear() {
        queue.clear();
    } // clear function
}