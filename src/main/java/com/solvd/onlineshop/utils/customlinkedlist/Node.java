package com.solvd.onlineshop.utils.customlinkedlist;

public class Node<T> {
    private T data;
    private Node<T> nextNode;

    public Node(T data) {
        this.data = data;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public T getData() {
        return data;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }
}
