package ru.job4j.list;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Class DinamicArrayList решение задачи 5.3.2. Создать контейнер на базе связанного списка [#143705]
 * @author vmyaskovskiy
 * @version $Id$
 * @since 0.1
 */
public class SimpleLinkedList<E> implements Iterable<E> {
    private int modCount = 0;
    private SimpleLinkedList.Node<E> first;

    /**
     * Метод вставляет в начало списка данные.
     */
    public void add(E data) {
        SimpleLinkedList.Node<E> newLink = new SimpleLinkedList.Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.modCount++;
    }
    public int getModCount() {
        return this.modCount;
    }

    /**
     * Реализовать метод удаления первого элемент в списке.
     */
    public E delete() {
        SimpleLinkedList.Node<E> oldLink = this.first.next;
        E res =  this.first.data;
        this.first = oldLink;
        this.modCount++;
        return res;
    }
    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        SimpleLinkedList.Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {
        private E data;
        private SimpleLinkedList.Node<E> next;
        private Node(E data) {
            this.data = data;
        }
    }

    @Override
    public Iterator<E> iterator() {
        SimpleLinkedList.Node<E> link = this.first;
        int expectedModCount = this.modCount;
        Iterator<E> iterator = new Iterator<E>() {
            SimpleLinkedList.Node<E> newLink = link;
            SimpleLinkedList.Node<E> tempLink;
            @Override
            public boolean hasNext() {
                return newLink != null;
            }
            @Override
            public E next() {
                if (expectedModCount != getModCount()) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                tempLink = newLink;
                newLink = newLink.next;
                return tempLink.data;
            }
        };
        return iterator;
    }
}
