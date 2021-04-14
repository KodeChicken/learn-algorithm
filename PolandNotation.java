package com.zhuyz.algorithm.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * 二、后缀表达式计算结果原理：(举例说明)
 *      eg：
 *          中缀表达式：(3+4)*5-6
 *          后缀表达式：3 4 + 5 * 6 -
 *          
 *          1.步骤：
 *              1.从左到右扫描，将3 4 压入栈中，栈顶元素为4
 *              2.遇到+运算符，弹出栈中4 3，计算 3 + 4的值，等于7，再将7压入栈中
 *              3.将5入栈
 *              4.遇到*运算符，弹出栈中5 7，计算 5 * 7的值，等于35，再将35压入栈中
 *              5.将6压入栈
 *              6.遇到-运算符，弹出栈中6 35，计算 35 - 6的值，等于29，再将29压入栈中
 *              7.结束
 *          
 *          2.原理：
 *               1.将后缀表达式拆分成一个list集合进行遍历
 *               2.定义一个stack操作数栈，存放中间计算结果
 *               3.遍历list：
 *                      遇到操作数则压入栈中
 *                      遇到运算符，则弹出stack中两个操作数，进行计算。将计算结果重新压入栈中(注意计算顺序，拿第二个弹出的操作数和第一个弹出的进行运算)
 *               4.遍历list结束，将stack栈顶操作数取出即为运算结果
 */
public class PolandNotation {

    public static void main(String[] args) {
        // 先定义逆波兰表达式
        // (3+4)*5-6   => 3 4 + 5 * 6 -
        // 说明为了方便，逆波兰表达式的数字和符号使用空格隔开

        String str = "1 2 3 + 4 * + 5 -";
        // 4*5-8+60+8/2 => 4 5 * 8 - 60 + 8 2 / +
        String suffixExpr = "4 5 * 8 - 60 + 8 2 / +";
        /**
         * 20 8 - 60 + 8 2 / +
         * 12 60 + 8 2 / +
         * 72 8 2 / +
         * 72 4 +
         * 76
         */
        List<String> listStr = getListStr(suffixExpr);
        List<String> listStr2 = getListStr(str);
        System.out.println(listStr);
        int res = calculate(listStr);
        int res2 = calculate(listStr2);
        System.out.println(res);
        System.out.println(res2);

    }

    public static List<String> getListStr(String suffixExpr) {
        List<String> resList = new ArrayList<>();

        String[] arr = suffixExpr.split(" ");
        for (String s : arr) {
            resList.add(s);
        }
        return resList;
    }

    public static int calculate(List<String> ls) {
        Stack<String> stack = new Stack<>();

        for (String item : ls) {
            if (item.matches("\\d+")) {
                // 操作数
                stack.push(item);
            } else {
                int next = Integer.valueOf(stack.pop());
                int prev = Integer.valueOf(stack.pop());
                int res = cal(next, prev, item);
                stack.push(String.valueOf(res));
            }
        }
        return Integer.valueOf(stack.pop());
    }

    public static int cal(int n1, int n2, String oper) {
        int res = 0; // 存放计算的结果
        switch (oper) {
            case "+":
                res = n1 + n2;
                break;
            case "-":
                res = n2 - n1;
                break;
            case "*":
                res = n1 * n2;
                break;
            case "/":
                res = n2 / n1;
                break;
            default:
                break;
        }
        return res;
    }

}
