package Grade4.Algorithm.week4.tree;


import java.util.*;

public class BinarySearchTree {

    public class Node {
        char key;
        Node parent;
        Node left;
        Node right;

        public Node(char c) {
            key = c;
            parent = null;
            left = null;
            right = null;
        }

        public String toString() {
            String retVal = "";
            return retVal + key + "(" + height(this) + ")";
        }
    }

    Node root;
    int numNode;

    public BinarySearchTree() {
        root = null;
        numNode = 0;
    }

    public void insert(char x) {
        insert(x, null, root);
    }

    protected Node insert(char x, Node parent, Node currentNode) {
        if (currentNode == null) {
            if (parent == null) { //root
                root = insertNode(x, null);
                return root;
            } else {
                if (x < parent.key) {
                    parent.left = insertNode(x, parent);
                    return parent.left;
                }
                if (x > parent.key) {
                    parent.right = insertNode(x, parent);
                    return parent.right;
                } //never happens
                return null;
            }
        } else { // if (rootNode != null)
            if (x < currentNode.key) {
                return insert(x, currentNode, currentNode.left);
            } else if (x > currentNode.key) {
                return insert(x, currentNode, currentNode.right);
            }
            return null;
        }
    }

    private Node insertNode(char x, Node parent) {
        Node newNode = new Node(x);
        newNode.parent = parent;
        numNode++;
        return newNode;
    }

    public Node search(Node startNode, char x) {
        Node p = startNode;
        if (p == null || p.key == x)
            return p;
        else if (x < p.key)
            return search(p.left, x);
        else
            return search(p.right, x);
    }

    public Node delete(char x) {
        Node currentNode = search(root, x);
        if (currentNode != null) {
            numNode--;
            return delete(currentNode);
        } else {
            return null;
        }
    }

    private Node delete(Node currentNode) {
        if (currentNode.parent == null) { //r=root
            root = deleteNode(currentNode);
            return null;
        } else if (currentNode == currentNode.parent.left) {
            currentNode.parent.left = deleteNode(currentNode);
            return currentNode.parent;
        } else {
            currentNode.parent.right = deleteNode(currentNode);
            return currentNode.parent;
        }
    }

    private Node deleteNode(Node currentNode) {

        // case 1 : no child
        if (currentNode.left == null && currentNode.right == null) {
            return null;
        }
        // case 2 : 1 child
        if (currentNode.left == null) {
            currentNode.right.parent = currentNode.parent;
            return currentNode.right;
        } else if (currentNode.right == null) {
            currentNode.left.parent = currentNode.parent;
            return currentNode.left;
        } else { // case 3 : 2 child
            Node successor = getSuccessor(currentNode);
            currentNode.key = successor.key;
            if (successor == successor.parent.left) {
                successor.parent.left = successor.right;
            } else {
                successor.parent.right = successor.right;
            }
            return successor.parent;
        }
    }

    protected Node getSuccessor(Node v) {
        if (v == null)
            return null;

        Node current = v;
       // Node p = v.right;
        while (current.left != null)
            current = current.left;
        return current;
    }

    private Node predecessor(Node v) {
        if (v == null)
            return null;
        Node p = v.left;
        while (p.right != null)
            p = p.right;
        return p;
    }


    public void showTree() {
        if (root == null) {
            return;
        }
        Deque<Node> q = new ArrayDeque<>();
        q.add(root);
        int depthLevel = 0;
        while (q.peek() != null) {
            Deque<Node> temp = new ArrayDeque<Node>();
            System.out.print("Depth-level" + depthLevel + "  :  ");
            while (q.peek() != null) {
                temp.add(q.poll());
            }
            while (temp.peek() != null) {
                Node e = temp.poll();
                System.out.print(e.toString() + " ");
                if (e.left != null)
                    q.add(e.left);
                if (e.right != null)
                    q.add(e.right);
            }
            System.out.println();
            depthLevel++;
        }
        System.out.println(toString(root));
    }

    private String toString(Node t) {
        return inorder(t);
    }

    private String inorder(Node t) {
        if (t == null)
            return "";
        else
            return inorder(t.left) + " " + t.toString() + " " + inorder(t.right);
    }

    public List<List<Character>> levelorderTraversal(Node root) {
        Node currentNode = root;
        List<List<Character>> resultList = new ArrayList<>();
        Queue<Node> queue = new LinkedList<Node>();
        if (root == null) {
            return resultList;
        }
        queue.offer(currentNode);
        while (!queue.isEmpty()) {
            List<Character> sublist = new ArrayList<>();
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node tempNode = queue.peek();
                if (tempNode.left != null) {
                    queue.add(tempNode.left);
                }
                if (tempNode.right != null) {
                    queue.add(tempNode.right);
                }
                sublist.add(queue.poll().key);
            }
            resultList.add(sublist);
        }
        return resultList;
    }

    public List<Character> preorderTraversal(Node root) {
        List<Character> result = new ArrayList<>();
        Deque<Node> stack = new ArrayDeque<>();
        Node currentNode = root;
        while (!stack.isEmpty() || currentNode != null) {
            if (currentNode != null) {
                stack.push(currentNode);
                result.add(currentNode.key);
                currentNode = currentNode.left;
            } else {
                Node node = stack.pop();
                currentNode = node.right;
            }
        }
        return result;
    }

    public List<Character> postorderTraversal(Node root) {
        LinkedList<Character> result = new LinkedList<>();
        Deque<Node> stack = new ArrayDeque<>();
        Node currentNode = root;
        while (!stack.isEmpty() || currentNode != null) {
            if (currentNode != null) {
                stack.push(currentNode);
                result.addFirst(currentNode.key);
                currentNode = currentNode.right;
            } else {
                Node node = stack.pop();
                currentNode = node.left;
            }
        }
        return result;
    }

    //////////////////////////////////////////////////////////////////

    public int height() {
        return height(root);
    }

    protected int height(Node currentNode) {
        if (currentNode == null) {
            return -1;
        } else {
            return 1 + Math.max(height(currentNode.left), height(currentNode.right));
        }
    }

    public int IPL() {
        int count = 0;
        return IPLCount(root, count);
    }

    private int IPLCount(Node r, int count) {
        if (r == null)
            return count;
        count++;
        int lCount = IPLCount(r.left, count);
        int rCount = IPLCount(r.right, count);
        return count + lCount + rCount;
    }


    //////////////////////////////////////////////////////////////////

    public static void main(String[] args) {

        char[] data = {'M', 'Y', 'U', 'N', 'G', 'I', 'S', 'W'};
        BinarySearchTree bt = new BinarySearchTree();

        for (int i = 0; i < data.length; i++) {
            bt.insert(data[i]);
            bt.showTree();
        }
        System.out.print("\nTree Created :");
        bt.showTree();

        bt.delete('S');
        System.out.print("\nAfter deleting 'S' :");
        bt.showTree();
        bt.delete('G');
        System.out.print("\nAfter deleting 'G' :");
        bt.showTree();
        bt.delete('U');
        System.out.print("\nAfter deleting 'U' :");
        bt.showTree();

    }

}

