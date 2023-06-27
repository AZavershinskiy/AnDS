public class Sem_4 {
    public static void main(String[] args) {
        HashMap map = new HashMap();

        for (int i = 1; i <= 5; i++) {
            map.push(i, i);
        }

        System.out.println(map.find(3));
        System.out.println(map.find(6));
    }
}

class HashMap {
    private class Entity {
        int key;
        int value;
    }

    private class Basket { // list
        private Node head;

        class Node {
            Entity entity;
            Node next;
        }

        public Integer find(int key) { // O(N) -> O(1) 
            Node node = head;
            while (node != null) {
                if (node.entity.key == key) {
                    return node.entity.value;
                }
                node = node.next;
            }
            return null;
        }

        public boolean push(int key, int value) {
            Node node = new Node();
            node.entity = new Entity();
            node.entity.key = key;
            node.entity.value = value;

            if (head == null) {
                head = node;
            } else {
                Node cur = head;

                while (cur.next != null) {
                    if (cur.entity.key == key) {
                        return false;
                    }
                    cur = cur.next;
                }
                cur.next = node;
            }
            return true;
        }
    }

    final static int INIT_SIZE = 16;
    private Basket[] baskets;

    public HashMap(int size) {
        baskets = new Basket[size];
    }

    public HashMap() {
        this(INIT_SIZE);
    }

    public int getIndex(int key) { // O(1)
        return key % baskets.length; // [0, baskets.length - 1]
    }

    public Integer find(int key) {
        int index = getIndex(key);
        Basket basket = baskets[index];
        if (basket == null)
            return null;
        return basket.find(key);
    }

    public boolean push(int key, int value) {
        int index = getIndex(key);
        Basket basket = baskets[index];
        if (basket == null) {
            Basket b = new Basket();
            boolean res = b.push(key, value);
            baskets[index] = b;
            return res;
        } else {
            return basket.push(key, value);
        }
    }
}

class BinaryTree {
    Node root;

    class Node {
        int value;
        Node left;
        Node right;
    }

    public boolean find(int value) { // O(log N)
        Node cur = root;
        while (cur != null) {
            if (cur.value == value) {
                return true;
            }
            if (cur.value < value) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return false;
    }

    public void print() {
        print(root);
    }

    private void print(Node node) {
        if (node == null)
            return;
        System.out.println(node.value);

        if (node.left != null)
            System.out.println("L:" + node.left.value);

        if (node.right != null)
            System.out.println("R:" + node.right.value);

        print(node.left);
        print(node.right);
    }
}