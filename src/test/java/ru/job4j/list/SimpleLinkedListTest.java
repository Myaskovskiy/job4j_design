package ru.job4j.list;

import org.hamcrest.core.Is;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class SimpleLinkedListTest {

    @Test
    public void deleteAll() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.delete();
        assertThat(list.get(0), is(3));
        list.delete();
        list.delete();
        assertThat(list.get(0), is(1));
        list.delete();
    }

    @Test
    public void add5() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        assertThat(list.get(0), is(5));
        assertThat(list.getModCount(), is(5));
        list.add(6);
        assertThat(list.getModCount(), is(6));
        assertThat(list.get(0), is(6));
        list.delete();
        assertThat(list.get(0), is(5));
        assertThat(list.getModCount(), is(7));
    }

    @Test
    public void nestHasNext() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenAddAndGet() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        assertThat(list.get(0), Is.is(2));
        assertThat(list.get(1), Is.is(1));
    }

    @Test
    public void whenAddIterHasNextTrue() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), Is.is(true));
    }

    @Test
    public void whenAddIterNextOne() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertThat(it.next(), Is.is(1));
    }

    @Test
    public void whenEmptyIterHashNextFalse() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), Is.is(false));
    }

    @Test
    public void whenAddIterMultiHasNextTrue() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), Is.is(true));
        assertThat(it.hasNext(), Is.is(true));
    }

    @Test
    public void whenAddIterNextOneNextTwo() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> it = list.iterator();
        assertThat(it.next(), Is.is(2));
        assertThat(it.next(), Is.is(1));
    }

    @Test
    public void whenGetIteratorTwiceThenEveryFromBegin() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> first = list.iterator();
        assertThat(first.hasNext(), Is.is(true));
        assertThat(first.next(), Is.is(2));
        assertThat(first.hasNext(), Is.is(true));
        assertThat(first.next(), Is.is(1));
        assertThat(first.hasNext(), Is.is(false));
        Iterator<Integer> second = list.iterator();
        assertThat(second.hasNext(), Is.is(true));
        assertThat(second.next(), Is.is(2));
        assertThat(second.hasNext(), Is.is(true));
        assertThat(second.next(), Is.is(1));
        assertThat(second.hasNext(), Is.is(false));
    }
}
