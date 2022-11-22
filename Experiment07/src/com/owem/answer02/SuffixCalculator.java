package com.owem.answer02;

import java.util.*;

/**
 * @author Owem
 * @date 2022/11/1 15:48
 * @description TODO
 **/
public class SuffixCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String suffixExpression = scanner.nextLine();
        List<String> list = getListString(suffixExpression);
        System.out.println("rpnList=" + list);
        System.out.println("计算的结果是=" + calculate(list));
    }

    public static List<String> getListString(String suffixExpression) {
        //将 suffixExpression 分割
        String[] split = suffixExpression.split(" ");
        return new ArrayList<>(Arrays.asList(split));
    }

    public static int calculate(List<String> ls) {
        Stack<String> stack = new Stack<>();
        for (String item : ls) {
            if (item.matches("\\d+")) {
                // 匹配的是多位数, 入栈
                stack.push(item);
            } else {
                // pop出两个数，并运算， 再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = switch (item) {
                    case "+" -> num1 + num2;
                    case "-" -> num1 - num2;
                    case "*" -> num1 * num2;
                    case "/" -> num1 / num2;
                    default -> throw new RuntimeException("运算符有误");
                };
                //把res 入栈
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
