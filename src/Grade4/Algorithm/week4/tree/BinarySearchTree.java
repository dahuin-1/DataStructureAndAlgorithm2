package Grade4.Algorithm.week4.tree;



public class BinarySearchTree {

    public class Node{
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
            return retVal + key;
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

    private Node insert(char x, Node parent, Node currentNode) {
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
        if (p==null||p.key==x)
            return p;
        else if (x<p.key)
            return search(p.left, x);
        else
            return search(p.right, x);
    }

    public Node delete(char x) {
        Node currentNode = search(root, x);
        if (currentNode != null) {
            numNode--;
            return delete(currentNode);
        }
        else{
            return null;
        }
    }

    private Node delete(Node currentNode) {
        if(currentNode.parent == null) { //r=root
            root = deleteNode(currentNode);
            return null;
        }
        else if (currentNode == currentNode.parent.left) {
            currentNode.parent.left = deleteNode(currentNode);
            return currentNode.parent;
        }
        else {
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

    private Node getSuccessor(Node v) {
        if (v==null)
            return null;
        Node p=v.right;
        while(p.left!=null)
            p=p.left;
        return p;
    }

    private Node predecessor(Node v) {
        if (v==null)
            return null;
        Node p=v.left;
        while(p.right!=null)
            p=p.right;
        return p;
    }

    public void showTree() {
        System.out.println(toString(root));
    }

    private String toString(Node t) {
        return inorder(t);
    }

    private String inorder(Node t) {
        if (t==null)
            return "";
        else
            return inorder(t.left)+" "+t.key+" "+inorder(t.right);
    }

    /////////////////////////////////


    /////////////////////////////////

    public static void main(String[] args) {

        char [] data = {'M','Y','U','N','G','I','S','W'};
        BinarySearchTree bt = new BinarySearchTree();

        for (int i=0;i<data.length;i++) {
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

