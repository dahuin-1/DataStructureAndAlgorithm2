package Grade4.Algorithm.week4.tree;

public class AVLTree extends BinarySearchTree {

    public AVLTree(){
        super();
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        y.parent = x.parent;
        if(y.parent == null) {
            root = y;
        }else{
            if(x == x.parent.left) {
                x.parent.left=y;
            }
            else
                x.parent.right = y;
        }
        x.parent = y;
        x.right = y.left;
        if(y.left != null)
            y.left.parent = x;
        y.left = x;
        return y;
    }

    private Node rotateRight(Node x) {
        Node y = x.left;
        y.parent = x.parent;
        if(y.parent == null) {
            root = y;
        }else{
            if(x == x.parent.left) {
                x.parent.left=y;
            }
            else
                x.parent.right = y;
        }
        x.parent = y;
        x.left = y.right;
        if(y.right != null)
            y.right.parent = x;
        y.left = x;
        return y;
    }


    public void insert(char c) {
        Node r = insert(c, null, root);
        //find x
        Node p = r.parent;
        while (p != null) {
            if(!isBalanced(p))
                break;
            p = p.parent;
        }
        Node x = p;
        Node y = null;

        if (x != null) {
            if(c < x.key) {
                y = x.left;
                if(c < y.key) //LL
                    rotateRight(x);
                else {
                    rotateLeft(y);
                    rotateRight(x);
                }
            }
            else {
                y = x.right;
                if(c > y.key)
                    rotateLeft(x);
                else {
                    rotateRight(y);
                    rotateLeft(x);
                }
            }
        }

    }

    private boolean isBalanced(Node p) {
        if(p==null) {
            return true;
        }
        if(Math.abs(height(p.left)-height(p.right))<=1)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        int inputSize = 26;
        char[] data = new char[inputSize];

        for (int i = 0; i < inputSize; i++) {
            data[i] = (char) ((int) 'A' + i);
        }

        BinarySearchTree bt = new BinarySearchTree();
        for (int i = 0; i < inputSize; i++) {
            bt.insert(data[i]);
        }
        System.out.println("Initial Skewed Test");
        bt.showTree();
        System.out.println("Max. Height = "+bt.height());
        System.out.println("IPL = "+bt.IPL());

        AVLTree avlTree = new AVLTree();

        for (int i = 0; i < inputSize; i++) {
            avlTree.insert(data[i]);
        }
        System.out.println("AVLTree");
        avlTree.showTree();
        System.out.println("Max. Height = "+avlTree.height());
        System.out.println("IPL = "+avlTree.IPL());

    }
}
