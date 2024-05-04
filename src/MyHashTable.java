public class MyHashTable<K, V> {
    private int M = 11;
    private double loadFactor = 0.25;
    private HashNode<K, V>[] buckets;
    private int size = 0;

    public MyHashTable() {
        buckets = new HashNode[M];
    }

    public MyHashTable(int initialCapacity) {
        M = (int) (loadFactor * initialCapacity);
        buckets = new HashNode[M];
    }

    public int hashToIndex(K key) {
        return (key.hashCode() & 0xfffffff) % M;
    }

    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "HashNode{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    public int countElements(int index) {
        int count = 0;
        HashNode<K, V> temp = buckets[index];
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    public int getM() {
        return M;
    }


    public V get(K key) {
        int index = hashToIndex(key);
        HashNode<?, ?> temp = buckets[index];
        while (temp.next != null) {
            if (temp.key.equals(key)) {
                return (V) temp.value;
            }
            temp = temp.next;
        }
        return null;
    }

    public boolean containsK(K key) {
        return get(key) != null;
    }

    public boolean containsV(V value) {
        return getkey(value) != null;
    }

    public K getkey(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<?, ?> temp = buckets[i];
            while (temp.next != null) {
                if (temp.value.equals(value)) {
                    return (K) temp.key;
                }
                temp = temp.next;
            }

        }
        return null;
    }


    public void put(K key, V value) {
        if ((double) size / M > loadFactor) increaseCapacity();
        int index = hashToIndex(key);
        HashNode<K, V> a = new HashNode<>(key, value);

        a.next = buckets[index];
        buckets[index] = a;
        size++;

    }

    public void reput(K key, V value) {
        int index = hashToIndex(key);
        HashNode<K, V> a = new HashNode<>(key, value);

        a.next = buckets[index];
        buckets[index] = a;

    }

    private void increaseCapacity() {
        M = M * 2;
        HashNode<K, V>[] temp = buckets;
        buckets = new HashNode[M];
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != null) {
                HashNode<K, V> node = temp[i];
                while (node != null) {
                    reput(node.key, node.value);
                    node = node.next;
                }
            }

        }

    }

    public V remove(K key) {
        int index = hashToIndex(key);
        HashNode<K, V> temp = buckets[index];
        HashNode<K, V> prev = null;
        while (temp.next != null) {
            if (temp.key.equals(key)) {
                if (prev == null) {
                    buckets[index] = temp.next;
                } else {
                    prev.next = temp.next;
                }
                size--;
                return temp.value;

            }
            prev = temp;
            temp = temp.next;
        }

        return null;
    }


}
