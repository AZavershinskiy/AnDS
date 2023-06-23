public class HW_3 {
    public static void main(String[] args) {

        List list = new List();
        for (int i = 5; i > 0; i--) {
            list.push(i);
        }

        list.print();
        list.revert();
        list.print();
    }
}

class List {

    Node head;

    public class Node {
        int value;
        Node next;
    }

    public void push(int value) {
        Node node = new Node();
        node.value = value;
        node.next = head;
        head = node;
    }

    public void print() {
        Node node = head;
        while (node != null) {
            System.out.printf("%d ", node.value);
            node = node.next;
        }
        System.out.println();
    }

    public void revert() {
        if (head != null && head.next != null) {
            Node temp = head;
            revert(head.next, head);
            temp.next = null;
        }
    }

    private void revert(Node currentNode, Node previousNode) {
        if (currentNode.next == null) {
            head = currentNode;
        } else {
            revert(currentNode.next, currentNode);
        }
        currentNode.next = previousNode;
    }
}
