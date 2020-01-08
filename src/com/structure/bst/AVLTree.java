package com.structure.bst;

public class AVLTree {

    Node root;

    public int max(int a, int b) {
        return a > b ? a : b;
    }


    public int getHeight(Node n) {
        if (n == null)
            return 0;
        else
            return n.height;
    }

    // Utility function to right rotate the node at n
    public Node rightRotate(Node n) {
        Node x = n.left;
        Node t = x.right;

        x.right = n;
        n.left = t;

        n.height = max(getHeight(n.left), getHeight(n.right)) + 1;
        x.height = max(getHeight(x.right), getHeight(x.left)) + 1;

        return x;
    }

    // Utility function to left rotate the node at n
    public Node leftRotate(Node n) {
        Node x = n.right;
        Node t = x.left;

        x.left = n;
        n.right = t;

        n.height = max(getHeight(n.left), getHeight(n.right)) + 1;
        x.height = max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    public int getBalance(Node n) {
        if (n == null)
            return 0;
        else
            return getHeight(n.left) - getHeight(n.right);
    }

    public Node insert(Node n, int key) {

        if (n == null)
            return new Node(key);
        if (key < n.key)
            n.left = insert(n.left, key);
        else if (key > n.key)
            n.right = insert(n.right, key);
        else
            return n;


        n.height = 1 + max(getHeight(n.left),getHeight(n.right));

        int balance = getBalance(n);

        // Left-Left case
        if (balance > 1 && key < n.left.key)
            return rightRotate(n);

        // Right-Right case
        if (balance < -1 && key > n.right.key)
            return leftRotate(n);

        // Left-Right case
        if (balance > 1 && key > n.left.key) {
            n.left = leftRotate(n.left);
            return rightRotate(n);
        }

        // Right-Left case
        if (balance < -1 && key < n.right.key) {
            n.right = rightRotate(n.right);
            return leftRotate(n);
        }

        return n;

    }

    void preOrder(Node n){
        if(n != null){
            System.out.print(n.key+"   ");
            preOrder(n.left);
            preOrder(n.right);
        }
    }

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();

        /* Constructing tree given in the above figure */
        avlTree.root = avlTree.insert(avlTree.root, 10);
        avlTree.root = avlTree.insert(avlTree.root, 20);
        avlTree.root = avlTree.insert(avlTree.root, 30);
        avlTree.root = avlTree.insert(avlTree.root, 40);
        avlTree.root = avlTree.insert(avlTree.root, 50);
        avlTree.root = avlTree.insert(avlTree.root, 25);

        /* The constructed AVL Tree would be
             30
            /  \
          20   40
         /  \     \
        10  25    50
        */
        System.out.println("Preorder traversal" +
                " of constructed tree is : ");
        avlTree.preOrder(avlTree.root);
    }


}
