public class HW_4 {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(11);
        tree.insert(22);
        tree.insert(33);
        tree.insert(15);
        tree.insert(18);
        tree.insert(21);

        Node found = tree.find(18);
        if (found != null) {
            System.out.println("Found node with value: " + found.value);
        } else {
            System.out.println("Node not found");
        }

        System.out.println("\n--Tree--");
        tree.print();
    }
}

class Node {
    int value;
    Color color;
    Node left, right, parent;

    enum Color {
        RED,
        BLACK
    }

    public Node(int value) {
        this.value = value;
        color = Color.RED;
        left = right = parent = null;
    }
}

class RedBlackTree {
    private Node root;

    public RedBlackTree() {
        root = null;
    }

    private void rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    private void rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != null) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    private void fixViolation(Node node) {
        Node parent = null;
        Node grandparent = null;
        while (node != root && node.color != Node.Color.BLACK && node.parent.color == Node.Color.RED) {
            parent = node.parent;
            grandparent = node.parent.parent;
            if (parent == grandparent.left) {
                Node uncle = grandparent.right;
                if (uncle != null && uncle.color == Node.Color.RED) {
                    grandparent.color = Node.Color.RED;
                    parent.color = Node.Color.BLACK;
                    uncle.color = Node.Color.BLACK;
                    node = grandparent;
                } else {
                    if (node == parent.right) {
                        rotateLeft(parent);
                        node = parent;
                        parent = node.parent;
                    }
                    rotateRight(grandparent);
                    Node.Color temp = parent.color;
                    parent.color = grandparent.color;
                    grandparent.color = temp;
                    node = parent;
                }
            } else {
                Node uncle = grandparent.left;
                if (uncle != null && uncle.color == Node.Color.RED) {
                    grandparent.color = Node.Color.RED;
                    parent.color = Node.Color.BLACK;
                    uncle.color = Node.Color.BLACK;
                    node = grandparent;
                } else {
                    if (node == parent.left) {
                        rotateRight(parent);
                        node = parent;
                        parent = node.parent;
                    }
                    rotateLeft(grandparent);
                    Node.Color temp = parent.color;
                    parent.color = grandparent.color;
                    grandparent.color = temp;
                    node = parent;
                }
            }
        }
        root.color = Node.Color.BLACK;
    }

    public void insert(int value) {
        Node node = new Node(value);
        Node parent = null, current = root;
        while (current != null) {
            parent = current;
            if (node.value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        node.parent = parent;
        if (parent == null) {
            root = node;
        } else if (node.value < parent.value) {
            parent.left = node;
        } else {
            parent.right = node;
        }
        fixViolation(node);
        if (node.parent == null) {
            node.color = Node.Color.BLACK;
        }
    }

    public Node find(int value) {
        Node current = root;

        while (current != null && current.value != value) {
            if (value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return current;
    }

    public void print() {
        print(root);
    }

    private void print(Node node) {
        if (node == null)
            return;
        System.out.println("Parent: " + node.value);

        if (node.left != null)
            System.out.println("L:" + node.left.value);

        if (node.right != null)
            System.out.println("R:" + node.right.value);

        print(node.left);
        print(node.right);
    }
}