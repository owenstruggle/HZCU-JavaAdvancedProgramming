package question01;
import org.junit.Test;

/**
 * @author Owem
 * @date 2022/9/13 15:41
 * @description TODO
 **/
public class GenerisStackTest {
    @Test
    public void stringTest() {
        GenerisStack<String> stringGenerisStack = new GenerisStack<>();
        stringGenerisStack.push("1");
        stringGenerisStack.push("2");
        stringGenerisStack.push("3");
        System.out.println(stringGenerisStack.pop());
        System.out.println(stringGenerisStack.peek());
        System.out.println(stringGenerisStack);
    }

    @Test
    public void IntegerTest() {
        GenerisStack<Integer> stringGenerisStack = new GenerisStack<>();
        stringGenerisStack.push(1);
        stringGenerisStack.push(2);
        stringGenerisStack.push(3);
        System.out.println(stringGenerisStack.pop());
        System.out.println(stringGenerisStack.peek());
        System.out.println(stringGenerisStack);
    }
}
