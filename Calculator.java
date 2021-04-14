package com.zhuyz.algorithm.stack;


/**
 * 3+2*6-2
 *
 * 1.循环遍历获取每个字符
 * 2.当前字符为数字，则压入操作数栈
 * 3.当前字符为符号，则压入运算符栈
 *      如果运算符栈为空，直接压入
 *      如果运算符栈有值，则比较当前字符<==>栈中运算符的优先级：
 *          <=：取出运算符栈字符和操作数栈中的两个操作数进行计算，计算完的结果重新压入操作数栈
 *          >：将当前字符压入运算符栈 4.循环结束，顺序从操作数栈和运算符栈中pop出相应的操作数和运算符进行运算 5.最后数栈只有一个数字，就是结果。
 */
public class Calculator {

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
            // 递归
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
