public class Sem_3 {

    public static void main(String[] args) {

        dList list = new dList();
        for (int i = 1; i <= 10; i++) {
            list.pushFront(i);
        }
        list.print();

        // list.popFront();
        // list.popFront();
        // list.print();
        // System.out.println(list.contains(2));
        // System.out.println(list.contains(5));

        // list.pushBack(7);
        // list.print();
        // list.popBack();
        // list.print();

        list.sort();
        list.print();
    }
}

// FOFO = First In First Out - queue
// LIFO = Last In First Out - stack

class List {

    static Node head;

    static class Node {
        int value;
        Node next;
    }

    public void pushFront(int value) { // 0(1)
        Node node = new Node();
        node.value = value;
        node.next = head;
        head = node;
    }

    public void popFront() { // 0(1)
        if (head != null) {
            head = head.next;
        }
    }

    public void print() {
        Node node = head;
        while (node != null) {
            System.out.printf("%d ", node.value);
            node = node.next;
        }
        System.out.println();
    }

    public boolean contains(int value) {
        Node node = head;
        while (node != null) {
            if (node.value == value) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public void pushBack(int value) { // 0(N)
        Node node = new Node();
        node.value = value;
        if (head == null) {
            head = node;
        } else {
            Node cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = node;
        }
    }

    public void popBack() { // 0(1)
        if (head != null && head.next == null) {
            head = null;
        } else {
            Node cur = head;
            while (cur.next.next != null) {
                cur = cur.next;
            }
            cur.next = null;
        }
    }
}

class dList {

    Node head;
    Node tail;

    class Node {
        int value;
        Node next;
        Node prev;
    }

    public void sort() { // O(N^2)
        boolean needSort = true;
        do {
            needSort = false;
            Node node = head;
            while (node != null && node.next != null) {
                if (node.value > node.next.value) {
                    Node before = node.prev;
                    Node cur = node;
                    Node next = node.next;
                    Node after = next.next;

                    cur.next = after;
                    cur.prev = next;
                    next.next = cur;
                    next.prev = before;

                    if (before != null) {
                        before.next = next;
                    } else {
                        head = next;
                    }

                    if (after != null) {
                        after.prev = cur;
                    } else {
                        tail = cur;
                    }

                    needSort = true;
                }
                node = node.next;
            }
        } while (needSort);
    }

    public void pushFront(int value) { // 0(1)
        Node node = new Node();
        node.value = value;
        if (head == null) {
            tail = node;
        } else {
            node.next = head;
            head.prev = node;
        }
        head = node;
    }

    public void popFront() { // 0(1)
        if (head != null && head.next == null) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
    }

    public void print() {
        Node node = head;
        while (node != null) {
            System.out.printf("%d ", node.value);
            node = node.next;
        }
        System.out.println();
    }

    public boolean contains(int value) {
        Node node = head;
        while (node != null) {
            if (node.value == value) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public void pushBack(int value) { // 0(1)
        Node node = new Node();
        node.value = value;
        if (tail == null) {
            head = node;
        } else {
            node.prev = tail;
            tail.next = node;
        }
        tail = node;
    }

    public void popBack() { // 0(1)
        if (tail != null && tail.prev == null) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
    }
}
