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
 *
 */
public class PolandNotation {

    public static void main(String[] args) {
        String middlExpr1 = "1+((2+3)*4)-5";
        String middlExpr2 = "(3+4)*5-6";
        String middlExpr3 = "1+(((2+3)*4)/4)-5+6";
        String middlExpr4 = "10+(((18+2)*2)/4)+6";
        String middlExpr5 = "10+(18+2)*2-15";
        String middlExpr6 = "10+(18+2-((1*2-2)/1))*2-15";
        test(middlExpr1);
        test(middlExpr2);
        test(middlExpr3);
        test(middlExpr4);
        test(middlExpr5);
        test(middlExpr6);

    }

    /**
     * 测试运算结果
     */
    private static void test(String middlExpr) {
        List<String> transformList = transformList(middlExpr);
        Stack<String> suffixStack = middle2Suffix(transformList);
        int calculate = calculate(suffixStack);
        System.out.printf("transformList: %s\n", transformList);
        System.out.printf("suffixStack: %s\n", suffixStack);
        System.out.printf("计算结果为：[%d]\n", calculate);
        System.out.println("========end========");
    }

    /**
     * 将中缀表达式转换成一个list 并且兼容多位数
     */
    public static List<String> transformList(String middlExpr) {
        List<String> resList = new ArrayList<>();

        int len = middlExpr.length();
        for (int i = 0; i < len; i++) {
            String curStr = String.valueOf(middlExpr.charAt(i));
            StringBuilder sb = new StringBuilder(curStr);
            if (curStr.matches("\\d+")) {
                // 是操作数
                // 个位数没有问题，但是多位数的情况下会出现问题
                // 如果下标越界并且如果下一位数是运算符，就退出循环
                while (i + 1 < len && !isOper(middlExpr.charAt(i + 1))) {
                    // 是数字就拼接
                    sb.append(middlExpr.charAt(++i));
                }
            }
            resList.add(sb.toString());
        }
        return resList;
    }

    /**
     * 中缀转后缀
     */
    public static Stack<String> middle2Suffix(List<String> transformList) {
        // 运算符栈
        Stack<String> s1 = new Stack<>();
        // 存储中间结果的栈
        Stack<String> s2 = new Stack<>();

        for (String item : transformList) {
            if (item.matches("\\d+")) {
                // 是操作数，用add方法添加到tmpStack的末尾
                s2.add(item);
            } else if (OperTypeEnum.LEFT.getOperType().equals(item)) {
                // 是左括号，直接push到operStack中
                s1.push(item);
            } else if (OperTypeEnum.RIGHT.getOperType().equals(item)) {
                // 是右括号，循环弹出operStack栈顶的元素，直到遇到左括号break，并且把左括号也弹出丢弃
                while (!OperTypeEnum.LEFT.getOperType().equals(s1.peek())) {
                    s2.add(s1.pop());
                }
                // 把左括号也弹出丢弃
                s1.pop();
            } else {
                // 是运算符
                // 比较item和s1栈顶的运算符优先级大小
                // 大于：直接压入s1栈顶
                // 小于等于：弹出s1栈顶元素到s2中，进入下一次循环继续比较(可用循环或者递归)
                while (s1.size() != 0 && priority(item) <= priority(s1.peek())) {
                    s2.add(s1.pop());
                }
                // 上面while退出，则代表优先级:item 大于s1栈顶元素
                s1.push(item);
            }
        }
        // 将剩余的运算符弹出并压入s2中
        while (s1.size() != 0) {
            s2.push(s1.pop());
        }
        return s2;
    }

    /**
     * 计算后缀表达式的值
     */
    public static int calculate(List<String> ls) {
        Stack<String> resStack = new Stack<>();

        for (String item : ls) {
            if (item.matches("\\d+")) {
                // 是操作数
                resStack.push(item);
            } else {
                // 是运算符
                int next = Integer.valueOf(resStack.pop());
                int prev = Integer.valueOf(resStack.pop());
                int res = cal(next, prev, item);
                resStack.push(String.valueOf(res));
            }
        }
        return Integer.valueOf(resStack.pop());
    }

    /**
     * 返回运算符的优先级
     */
    public static int priority(String oper) {
        if (OperTypeEnum.MUL.getOperType().equals(oper) || OperTypeEnum.DIV.getOperType().equals(oper)) {
            return 2;
        } else if (OperTypeEnum.ADD.getOperType().equals(oper) || SUB.getOperType().equals(oper)) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 获取运算结果
     */
    public static int cal(int n1, int n2, String oper) {
        // 存放计算的结果
        int res = 0;
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

    /**
     * 判断是否是运算符
     */
    public static boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/' || val == ')';
    }

    enum OperTypeEnum {
        LEFT("("),
        RIGHT(")"),
        ADD("+"),
        SUB("-"),
        MUL("*"),
        DIV("/");

        private String operType;

        OperTypeEnum(String operType) {
            this.operType = operType;
        }

        public String getOperType() {
            return operType;
        }
    }

}
