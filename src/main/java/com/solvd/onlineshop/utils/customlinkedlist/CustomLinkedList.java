package com.solvd.onlineshop.utils.customlinkedlist;

import com.solvd.onlineshop.exceptions.EmptyLinkedListException;
import com.solvd.onlineshop.exceptions.ElementNotFoundException;

import java.util.ArrayList;
import java.util.Objects;

public class CustomLinkedList<T> {

    private Node head;
    private int size;

    public CustomLinkedList() {
        size = 0;
    }

    public void addElementAtStart(T data) {
        Node<T> newNode = new Node(data);

        newNode.setNextNode(head);
        head = newNode;
        size++;
    }

    public void insertElement(T data, int index) {
        if (index == 0) {
            addElementAtStart(data);
        } else {
            Node<T> newNode = new Node(data);
            Node<T> currentNode = head;

            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNextNode();
            }

            newNode.setNextNode(currentNode.getNextNode());
            currentNode.setNextNode(newNode);
            size++;
        }
    }

    public ArrayList<T> getAll() throws EmptyLinkedListException {
        Node<T> currentNode = head;
        ArrayList<T> elements = new ArrayList<>();

        if (currentNode == null) {
            throw new EmptyLinkedListException("The LinkedList is empty.");
        }

        while (currentNode != null) {
            elements.add(currentNode.getData());
            currentNode = currentNode.getNextNode();
        }

        return elements;
    }

    public Node<T> getOne(int index) throws ElementNotFoundException {
        if (index == 0) {
            return head;
        }
        Node<T> currentNode = head;

        if (index >= getSize()) {
            throw new ElementNotFoundException("Element not found.");
        }
        for (int i = 0; i < getSize(); i++) {
            if (index == i) {
                return currentNode;
            }
        }
        throw new ElementNotFoundException("Element not found.");
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNextNode();
        }

        return current.getData();
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            head = head.getNextNode();
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNextNode();
            }
            current = current.getNextNode().getNextNode();
        }
        size--;
    }

    public void deleteAt(int index) throws ElementNotFoundException {

        if (index >= getSize()) {
            throw new ElementNotFoundException("Element not found.");
        }
        if (index == 0) {
            head = head.getNextNode();
            size--;
        } else if (index == getSize() - 1) {
            getOne(index - 1).setNextNode(null);
            size--;
        } else {
            Node<T> currentNode = head;


            for (int i = 0; i < index - 1; i++) {

                currentNode = currentNode.getNextNode();
            }
            currentNode.setNextNode(currentNode.getNextNode().getNextNode());
            size--;
        }
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomLinkedList<?> that = (CustomLinkedList<?>) o;
        return Objects.equals(head, that.head);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head);
    }
}
