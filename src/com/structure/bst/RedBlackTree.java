package com.structure.bst;

public class RedBlackTree {

    private final int RED = 0;
    private final int BLACK = 1;

    private class Node {
        int key = -1, color = BLACK;
        Node left = nil, right = nil, parent = nil;

        Node(int key) {
            this.key = key;
        }
    }

    private final Node nil = new Node(-1);
    private Node root = nil;

    public void print(){
        printTree(root);
    }

    private void printTree(Node node) {
        if (node == nil) {
            return;
        }
        printTree(node.left);
        System.out.println(((node.color == RED) ? "Color: RED " : "Color: BLACK ") + "Key: " + node.key + " Parent: " + node.parent.key);
        printTree(node.right);
    }

    private Node findNode(Node findNode, Node node) {
        if (root == nil)
            return null;

        if (findNode.key < node.key) {
            if (node.left != nil)
                return findNode(findNode, node.left);
        } else if (findNode.key > node.key) {
            if (node.right != nil)
                return findNode(findNode, node.right);
        } else if (findNode.key == node.key) {
            return node;
        }

        return null;
    }

    private void insert(int key){
        insertNode(new Node(key));
    }

    private void insertNode(Node node) {
        Node temp = root;

        if (root == nil) {
            root = node;
            node.color = BLACK;
            node.parent = nil;
        } else {
            node.color = RED;

            while (true) {
                if (node.key < temp.key) {
                    if (temp.left == nil) {
                        temp.left = node;
                        node.parent = temp;
                        break;
                    } else
                        temp = temp.left;
                } else if (node.key >= temp.key) {
                    if (temp.right == nil) {
                        temp.right = node;
                        node.parent = temp;
                        break;
                    } else
                        temp = temp.right;

                }

            }

            fixTree(node);


        }
    }

    private void fixTree(Node node) {
        while (node.parent.color == RED) {
            Node uncle = nil;

            // Node is inserted on the left side of the parent
            if (node.parent == node.parent.left) {
                uncle = node.parent.parent.right;

                // if the uncle node is RED just Switch color
                if (uncle != nil && uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                }

                // For LR case
                if (node == node.parent.right) {
                    node = node.parent;
                    rotateLeft(node);
                }

                node.parent.color = BLACK;
                node.parent.parent.color = RED;

                // LL case
                rotateRight(node.parent.parent);


            } else {
                uncle = node.parent.parent.left;

                if (uncle != nil && uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                }

                // RL case
                if (node == node.parent.left) {
                    node = node.parent;
                    rotateRight(node);
                }
                node.parent.color = BLACK;
                node.parent.parent.color = RED;

                //RR case
                rotateLeft(node.parent.parent);

            }

        }

        root.color = BLACK;
    }

    private void rotateRight(Node node) {
        if (node.parent != nil) {
            if (node == node.parent.left)
                node.parent.left = node.left;
            else
                node.parent.right = node.left;


            node.left.parent = node.parent;
            node.parent = node.left;
            if (node.left.right != null) {
                node.left.right.parent = node;
            }
            node.left = node.left.right;
            node.parent.right = node;
        } else {
            Node left = root.left;
            root.left = root.left.right;
            left.right.parent = root;
            root.parent = left;
            left.right = root;
            left.parent = nil;
            root = left;
        }
    }

    private void rotateLeft(Node node) {
        if (node.parent != nil) {
            if (node == node.parent.left) {
                node.parent.left = node.right;
            } else
                node.parent.right = node.right;

            node.right.parent = node.parent;
            node.parent = node.right;

            if (node.right.left != nil) {
                node.right.left.parent = node;
            }

            node.right = node.right.left;
            node.parent.left = node;

        } else {
            Node right = root.right;
            root.right = right.left;
            right.left.parent = root;
            root.parent = right;
            right.left = root;
            right.parent = nil;
            root = right;
        }
    }


    public static void main(String args[]){
        RedBlackTree tree = new RedBlackTree();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        tree.insert(8);

        tree.print();
    }


}
