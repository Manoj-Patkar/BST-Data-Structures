package com.structure.bst;

public class Node {
    int key;
    int height;
    Node left;
    Node right;

    public Node(int key){
        this.key = key;
        this.height = 1;
    }
}