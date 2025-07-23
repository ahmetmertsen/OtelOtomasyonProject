package model;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class LinkedList<T> implements Iterable<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;


   
    public void insertFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public void insertLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void insertMiddle(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Geçersiz indeks: " + index);
        }
        if (index == 0) {
            insertFirst(data);
            return;
        }
        if (index == size) {
            insertLast(data);
            return;
        }
        Node<T> previous = head;
        for (int i = 0; i < index - 1; i++) {
            previous = previous.next;
        }
        Node<T> newNode = new Node<>(data);
        newNode.next = previous.next;
        previous.next = newNode;
        size++;
    }

    public T search(T value) {
        Node<T> tmp = head;
        while (tmp != null) {
            if (tmp.data.equals(value)) {
                return tmp.data;
            }
            tmp = tmp.next;
        }
        return null;
    }

    public void deleteFirst() {
        if (head != null) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
            size--;
        }
    }

    public void deleteLast() {
        if (head != null) {
            if (head == tail) {
                head = tail = null;
            } else {
                Node<T> tmp = head;
                while (tmp.next != tail) {
                    tmp = tmp.next;
                }
                tmp.next = null;
                tail = tmp;
            }
            size--;
        }
    }

    public void deleteMiddle(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Geçersiz indeks: " + index);
        }
        if (index == 0) {
            deleteFirst();
            return;
        }
        Node<T> previous = head;
        for (int i = 0; i < index - 1; i++) {
            previous = previous.next;
        }
        Node<T> tmp = previous.next;
        previous.next = tmp.next;
        if (tmp == tail) {
            tail = previous;
        }
        size--;
    }
    
    public void remove(T value) {
        if (head == null) {
            return; 
        }
        if (head.data.equals(value)) {
            deleteFirst();
            return;
        }
        
        Node<T> tmp = head;
        while (tmp != null && !tmp.data.equals(value)) {
            tmp = tmp.next;
        }
        
        if (tmp != null) {
            if (tmp == tail) {
                deleteLast();  
            } else {
                Node<T> previous = head;
                while (previous.next != tmp) {
                    previous = previous.next;
                }
                previous.next = tmp.next;
                size--;
            }
        }
    }


    
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> tmp = head;

        @Override
        public boolean hasNext() {
            return tmp != null;
        }

        @Override
        public T next() {
            T data = tmp.data;
            tmp = tmp.next;
            return data;
        }
    }

    public Stream<T> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Geçersiz indeks: " + index);
        }
        Node<T> tmp = head;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        return tmp.data;
    }
    
    
}
