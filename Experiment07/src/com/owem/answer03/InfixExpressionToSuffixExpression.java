package com.owem.answer03;

import java.util.*;

/**
 * @author Owem
 * @date 2022/11/1 15:56
 * @description TODO
 **/
public class InfixExpressionToSuffixExpression {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> list = getListString(scanner.nextLine());
        for (String str : infixExpToSuffixExp(list)) {
            System.out.print(str + " ");
        }
    }

    public static List<String> infixExpToSuffixExp(List<String> infixExp) {
        Stack<String> operatorStack = new Stack<>();
        List<String> resList = new ArrayList<>();
        for (String item : infixExp) {
            //如果是一个数，加入resList
            if (item.matches("^(\\-|\\+)?\\d+(\\.\\d+)?$")) {
                resList.add(item);
            } else if (item.equals("(")) {
                operatorStack.push(item);
            } else if (item.equals(")")) {
                //如果是又括号，则依次弹出operatorStack中的运算符，并压入resList，直到遇到左括号为止
                while (!operatorStack.peek().equals("(")) {
                    resList.add(operatorStack.pop());
                }
                operatorStack.pop(); // 消除左括号
            } else {
                //当item的优先级小于等于栈顶的优先级时
                while (operatorStack.size() != 0 &&
                        priority(item.charAt(0)) <= priority(operatorStack.peek().charAt(0))) {
                    resList.add(operatorStack.pop());
                }
                //还要将item压入栈
                operatorStack.push(item);
            }
        }
        //将operatorStack中剩余的运算符依次加入resList中
        while (operatorStack.size() != 0) {
            resList.add(operatorStack.pop());
        }
        return resList;
    }

    public static int priority(int operator) {
        if (operator == '*' || operator == '/') {
            return 1;
        }
        if (operator == '+' || operator == '-') {
            return 0;
        }
        return -1;
    }


    public static List<String> getListString(String infixExpression) {
        String[] split = infixExpression.split(" ");
        List<String> list = new ArrayList<>();
        Collections.addAll(list, split);
        return list;
    }
}
