package com.owem;

/**
 * @author Owem
 * @date 2022/11/15 16:18
 * @description TODO
 **/
public interface MyList<E> extends Iterable<E> {
    void add(E e);
    void add(int index, E e);
    Object[] toArray();
    <T> T[] toArray(T[] a);
    void clear();
    boolean contains(E e);
    E get(int index);
    int indexOf(E e);
    boolean isEmpty();
    int lastIndexOf(E e);
    boolean remove(E e);
    E remove(int index);
    Object set(int index, E e);
    int size();
    boolean addAll(MyList<E> otherList);
    boolean removeAll(MyList<E> otherList);
    boolean retainAll(MyList<E> otherList);
}
