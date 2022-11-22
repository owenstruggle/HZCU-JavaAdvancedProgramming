package question01;

import java.util.Arrays;

/**
 * @author Owem
 * @date 2022/9/13 15:15
 * @description TODO
 **/
public class GenerisStack<E> {
    private Object[] list = new Object[2];
    private int size = 0;

    public int getSize() {
        return size;
    }

    public E peek() {
        if (this.isEmpty()) {
            System.out.println("空数组");
            return null;
        }
        return (E) list[size - 1];
    }

    public void push(E o) {
        if (size == list.length) {
            list = Arrays.copyOf(list, list.length * 2);
        }
        list[size++] = o;
    }

    public E pop() {
        if (this.isEmpty()) {
            System.out.println("空数组");
            return null;
        }
        return (E) list[--size];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("GenerisStack List:{ ");
        for (int i = 0; i < size; i++) {
            result.append(list[i]).append(" ");
        }
        result.append("}");
        return result.toString();
    }
}
