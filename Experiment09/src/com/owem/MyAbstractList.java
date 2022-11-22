package com.owem;

/**
 * @author Owem
 * @date 2022/11/15 16:18
 * @description TODO
 **/
public abstract class MyAbstractList<E> implements MyList<E> {
    protected int size = 0;

    protected MyAbstractList() {
    }

    protected MyAbstractList(E[] objects) {
        for (E object : objects) {
            add(object);
        }
    }

    @Override
    public void add(E e) {
        add(size, e);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean remove(E e) {
        if (indexOf(e) >= 0) {
            remove(indexOf(e));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean retainAll(MyList<E> otherList) {
        int lastSize = size;

        this.forEach(item -> {
            if (!otherList.contains(item)) {
                remove(item);
            }
        });
        return lastSize != size;
    }

    @Override
    public boolean removeAll(MyList<E> otherList) {
        int lastSize = size;
        otherList.forEach(this::remove);
        return lastSize != size;
    }

    @Override
    public boolean addAll(MyList<E> otherList) {
        int lastSize = size;
        otherList.forEach(this::add);
        return lastSize != size;
    }
}
