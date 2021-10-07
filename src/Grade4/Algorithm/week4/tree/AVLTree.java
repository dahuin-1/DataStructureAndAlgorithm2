package Grade4.Algorithm.week4.tree;

public class AVLTree extends BinarySearchTree {

    public AVLTree() {
        super();
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        y.parent = x.parent;
        if (y.parent == null) {
            root = y;
        } else {
            if (x == x.parent.left) {
                x.parent.left = y;
            } else
                x.parent.right = y;
        }
        x.parent = y;
        x.right = y.left;
        if (y.left != null)
            y.left.parent = x;
        y.left = x;
        return y;
    }

    private Node rotateRight(Node x) {
        Node y = x.left;
        y.parent = x.parent;
        if (y.parent == null) {
            root = y;
        } else {
            if (x == x.parent.left) {
                x.parent.left = y;
            } else
                x.parent.right = y;
        }
        x.parent = y;
        x.left = y.right;
        if (y.right != null)
            y.right.parent = x;
        y.left = x;
        return y;
    }


    public void insert(char c) {
        Node r = insert(c, null, root);
        //find x
        Node p = r.parent;
        while (p != null) {
            if (!isBalanced(p))
                break;
            p = p.parent;
        }
        Node x = p;
        Node y = null;

        if (x != null) {
            if (c < x.key) {
                y = x.left;
                if (c < y.key) //LL
                    rotateRight(x);
                else {
                    rotateLeft(y);
                    rotateRight(x);
                }
            } else {
                y = x.right;
                if (c > y.key)
                    rotateLeft(x);
                else {
                    rotateRight(y);
                    rotateLeft(x);
                }
            }
        }

    }

    Node deleteNode(Node root, int key) {
        if (root == null)
            return root;

        //만약 삭제되어야하는 키가 루트의 키보다 작다면, 왼쪽
        if (key < root.key)
            root.left = deleteNode(root.left, key);
            //만약 삭제되어야하는 키가 루트의 키보다 크다면, 오른쪽
        else if (key > root.key)
            root.right = deleteNode(root.right, key);
            //루트의 키와 같다면 이게 삭제되어야한다.
        else {
            // 1. 1 child or no child
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left) //left == null이라면
                    temp = root.right; //right == temp
                else //right == null이라면
                    temp = root.left; //left == temp

                // no child
                if (temp == null) { //temp에 담긴 값이 없다면 no child라는 의미
                    temp = root;
                    root = null; //root == null
                } else // 1 child
                    root = temp;  //1 child가 root가 된다.
            }
            //2. 2 child
            else {
                // Get successor (오른쪽에서 젤 작은애)
                Node temp = getSuccessor(root.right);

                // Copy
                root.key = temp.key;

                // Delete
                root.right = deleteNode(root.right, temp.key);
            }
        }

        if (root == null)
            return root;

        //balance check
        int balance = getBalance(root);

        //밸런스 안맞으면 4case로 나눔
        // LL
        if (balance > 1 && getBalance(root.left) >= 0)
            return rotateRight(root);

        // LR
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }

        // RR
        if (balance < -1 && getBalance(root.right) <= 0)
            return rotateLeft(root);

        //RL
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }

        return root;
    }

    protected Node getSuccessor(Node v) { //BST class 에서 교수님이 설명하신 코드를 필요에 맞게 변형 했습니다.
        if (v == null)
            return null;

        Node current = v;
        while (current.left != null)
            current = current.left;
        return current;
    }

    private int getBalance(Node p) {
        if (p == null) return 0;
        return height(p.left) - height(p.right);
    }


    private boolean isBalanced(Node p) {
        if (p == null) {
            return true;
        }
        if (Math.abs(height(p.left) - height(p.right)) <= 1)
            return true;
        else
            return false;
    }

    private void AVLdelete(char c) {
        Node x = delete(c);
        Node y = null;
        Node z = null;
        Node w = null;
        while (x != null) {
            if (!isBalanced(x)) {
                if (height(x.left) >= height(x.right)) {
                    y = x.left;
                    if (y.left != null) { //LL
                        z = y.left;
                        w = rotateRight(x);
                    } else { //LR
                        z = y.right;
                        rotateLeft(y);
                        w = rotateRight(x);
                    }
                } else {
                    y = x.right;
                    if (y.left != null) { //RL
                        z = y.left;
                        rotateRight(y);
                        w = rotateLeft(x);
                    } else { //RR
                        z = y.right;
                        w = rotateLeft(x);
                    }
                }
                if (w.parent == null) {
                    root = w;
                }
                x = w.parent;
            }
            else x = x.parent;
        }
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
        System.out.println("Max. Height = " + bt.height());
        System.out.println("IPL = " + bt.IPL());

        AVLTree avlTree = new AVLTree();

        for (int i = 0; i < inputSize; i++) {
            avlTree.insert(data[i]);
        }
        System.out.println("AVLTree");
        avlTree.showTree();
        System.out.println("Max. Height = " + avlTree.height());
        System.out.println("IPL = " + avlTree.IPL());

    }
}
