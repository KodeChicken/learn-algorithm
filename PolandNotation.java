package com.zhuyz.algorithm.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 一、中缀表达式转后缀表达式原理：
 * 1) 初始化两个栈：运算符栈 s1 和储存中间结果的栈 s2；
 * 2) 从左至右扫描中缀表达式；
 * 3) 遇到操作数时，将其压 s2；
 * 
 * 4) 遇到括号时：
 *      1.如果是左括号“(”，则直接压入 s1
 *      2.如果是右括号“)”，则依次弹出 s1 栈顶的运算符，并压入 s2，直到遇到左括号为止，此时将这一对括号丢弃
 * 
 * 5) 遇到运算符时，比较其与 s1 栈顶运算符的优先级：
 *       1.如果 s1 为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
 *       2.否则，若优先级比栈顶运算符的高，也将运算符压入 s1；
 *       3.否则，将 s1 栈顶的运算符弹出并压入到 s2 中，再次转到4)步骤[递归] 与 s1 中新的栈顶运算符相比较；
 * 
 * 6) 重复步骤 2 至 5，直到表达式的最右边
 * 7) 将 s1 中剩余的运算符依次弹出并压入 s2
 * 8) 依次弹出 s2 中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式 
 *
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

        String middlExpr1 = "1+((2+3)*4)-5";
        String middlExpr2 = "1+((2+3)*4)-5+6";
        String middlExpr3 = "(3+4)*5-6";
        String middlExpr4 = "1+(((2+3)*4)/4)-5+6";
        String middlExpr5 = "1+2+3+4+5-6";
        String middlExpr6 = "1+2+3+4+5-6/2";

        String s1 = middle2Suffix(middlExpr1);
        System.out.println(s1);
        System.out.println(calculate(getListStr(s1)));

        String s2 = middle2Suffix(middlExpr2);
        System.out.println(s2);
        System.out.println(calculate(getListStr(s2)));

        String s3 = middle2Suffix(middlExpr3);
        System.out.println(s3);
        System.out.println(calculate(getListStr(s3)));

        String s4 = middle2Suffix(middlExpr4);
        System.out.println(s4);
        System.out.println(calculate(getListStr(s4)));

        String s5 = middle2Suffix(middlExpr5);
        System.out.println(s5);
        System.out.println(calculate(getListStr(s5)));

        String s6 = middle2Suffix(middlExpr6);
        System.out.println(s6);
        System.out.println(calculate(getListStr(s6)));
    }

    public static String middle2Suffix(String middle) {
        Stack<String> operStack = new Stack<>();
        Stack<String> tmpStack = new Stack<>();

        for (int i = 0; i < middle.length(); i++) {
            String curStr = String.valueOf(middle.charAt(i));
            if (curStr.matches("\\d+")) {
                // 是操作数
                tmpStack.push(curStr);
            } else {
                // 是运算符
                // 如果operStack为空，或者curStr等于左括号，直接压入operStack
                if (operStack.size() == 0 || "(".equals(curStr)) {
                    // 如果为空，直接压入运算符栈
                    operStack.push(curStr);
                    continue;
                }
                // 如果是右括号，直接弹出operStack两个字符串
                if (")".equals(curStr)) {
                    // 弹出运算符，并压入到tmpStack中
                    String oper = operStack.pop();
                    tmpStack.push(oper);
                    // 弹出左括号
                    operStack.pop();
                    continue;
                }
                compareAndSwap(curStr, operStack, tmpStack);
            }
        }
        // 将剩余的运算符弹出并压入tmpStack中
        while (operStack.size() != 0) {
            tmpStack.push(operStack.pop());
        }
        List<String> resList = new ArrayList<>();
        while (tmpStack.size() != 0) {
            resList.add(tmpStack.pop());
        }
        Collections.reverse(resList);
        return String.join(" ", resList);
    }

    public static void compareAndSwap(String curStr, Stack<String> operStack, Stack<String> tmpStack) {
        // operStack不为空，curStr为 +-*/ 某个字符。curStr和operStack栈顶中比较优先级
        if (operStack.size() == 0) {
            return;
        }
        String top = operStack.peek();
        if (priority(curStr) > priority(top)) {
            // >：将curStr压入operStack中
            operStack.push(curStr);
            return;
        } else {
            // <=：取出operStack栈顶的运算符压入tmpStack中，并继续curStr和operStack栈顶中比较优先级
            tmpStack.push(operStack.pop());
            compareAndSwap(curStr, operStack, tmpStack);
            operStack.push(curStr);
        }
    }



    // 返回运算符的优先级
    public static int priority(String oper) {
        if ("*".equals(oper) || "/".equals(oper)) {
            return 2;
        } else if ("+".equals(oper) || "-".equals(oper)) {
            return 1;
        } else {
            return -0;
        }
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
