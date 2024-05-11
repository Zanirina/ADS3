import java.util.Iterator;

public class BST<K extends Comparable<K>, V> implements Iterable<K>{
    private Node root;

    private static class Node<K, V> {
        private K key;
        private V val;
        private int length;
        private Node<K, V> left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public BST() {
        root = null;
    }

    public void put(K key, V value) {
        Node current = root;


        while (true) {
            if (key.compareTo((K) current.key) < 0) {
                if (current.left != null) {
                    current = current.left;
                } else {
                    current.left = new Node(key, value);
                    current.length = 1 + (current.right == null ? 0 : current.right.length) + (current.left == null ? 0 : current.left.length);
                    return;
                }

            } else if (key.compareTo((K) current.key) > 0) {
                if (current.right != null) {
                    current = current.right;
                } else {
                    current.right = new Node(key, value);
                    current.length = 1 + (current.left == null ? 0 : current.left.length) + (current.right == null ? 0 : current.right.length);
                    return;
                }

            } else {
                current.val = value;
                return;
            }
        }
    }


    public V get(K key) {
        Node current = root;


        while (current != null) {
            if (key.compareTo((K) current.key) < 0) {
                current = current.left;
            } else if (key.compareTo((K) current.key) > 0) {
                current = current.right;
            }
            if (key.compareTo((K) root.key) == 0) {
                return (V) current.val;
            }
        }
        return null;
    }


    public void delete(K key) {
        if (root == null) return;

        Node current = root;
        Node parent = null;
        boolean lch = false;

        while (current != null && current.key.equals(key) != true) {
            parent = current;
            if (key.compareTo((K) current.key) < 0) {
                current = current.left;
                lch = true;
            } else {
                current = current.right;
                lch = false;
            }
        }

        if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (lch = true) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (lch == true) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else
        {
            Node a = current.right;
            Node ap = current;
            while (a.left != null) {
                ap = a;
                a = a.left;
            }
            if (ap != current) {
                ap.left = a.right;
                a.right = current.right;
            }
            if (current == root) {
                root = a;
            }

            else if (lch = true){
                parent.left = a;
            }
            else {
                parent.right = a;
            }
            a.left = current.left;

        }
        while (parent != null) {
            parent.length = 1 + parent.right.length + parent.left.length;
            parent = findParent(root, (K) parent.key);
        }


    }
    private Node findParent(Node node, K key) {
        Node a = null;
        while (node != null && node.key.equals(key) != true) {
            a = node;
            if (key.compareTo((K) node.key) < 0)
                node = node.left;

            else
                node = node.right;

        }
        return a;
    }

    public Node getNode(K key) {
        return getNode(root , key);
    }
    public Node getNode(Node node , K key) {
        if(node == null) return null;

        while(node != null) {

            if(key.compareTo((K) node.key) < 0) node = node.left;

            else if(key.compareTo((K) node.key) > 0) node = node.right;

            else return node;

        }

        return null;
    }
    public int size(Node node) {
        return node == null ? 0 : node.length;
    }

    public boolean isEmpty() {
        return size(root) == 1;
    }

    public Node getRoot() {
        return root;
    }
    public boolean contains(K key) {
        return get(key) != null;
    }

    public Iterator<K> iterator(){
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<K> {
        private MyQueue<K> queue = new MyQueue<>();
        public BSTIterator() {
            inOrder(root);
        }

        private void inOrder(Node node) {
            if(node == null) return;
            inOrder(node.left);
            queue.enqueue((K) node.key);
            inOrder(node.right);
        }
        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }
        @Override
        public K next() {
            return queue.dequeue();
        }

    }



}
