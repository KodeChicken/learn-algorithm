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
        //String expr = "3+2*6-2";
        //String expr2 = "3+2*6-2+8/2*3-3+1*3";
        // 实现多位数字的四则运算
        String expr3 = "70+2*6-4";
        String expr4 = "70+2*60-4";
        //int res = calulate(expr);
        //int res2 = calulate(expr2);
        int res3 = calulate(expr3);
        int res4 = calulate(expr4);
        //System.out.println(res);
        //System.out.println(res2);
        System.out.println(res3);
        System.out.println(res4);
    }

    public static int calulate(String expr) {
        ArrayStack numStack = new ArrayStack(expr.length());
        ArrayStack operStack = new ArrayStack(expr.length());
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            boolean oper = ArrayStack.isOper(c);
            if (!oper) {
                // 操作数
                StringBuilder sb = new StringBuilder();
                sb.append(c);
                // 个位数没有问题，但是多位数的情况下会出现问题
                while (true) {
                    // 如果下标越界，直接break
                    if (i + 1 >= expr.length()){
                        break;
                    }
                    // 如果下一位数是运算符，就break；
                    if (ArrayStack.isOper(expr.charAt(i + 1))) {
                        break;
                    } else {
                        // 如果还是数字就拼接
                        sb.append(expr.charAt(++i));
                    }
                }
                numStack.push(Integer.valueOf(sb.toString()));
            } else {
                // 运算符
                // 1.判断运算符栈是否有值
                if (operStack.top > -1) {
                    // 有值
                    // 判断优先级
                    // <=：取出运算符栈字符和操作数栈中的两个操作数进行计算，计算完的结果重新压入操作数栈
                    // >：将当前字符压入运算符栈
                    int pop = operStack.pop();
                    if (ArrayStack.priority(c) <= ArrayStack.priority(pop)) {
                        int next = numStack.pop();
                        int prev = numStack.pop();
                        int cal = ArrayStack.cal(next, prev, pop);
                        numStack.push(cal);
                    } else {
                        operStack.push(pop);
                    }
                    operStack.push(c);
                } else {
                    // 没值，直接压入运算符栈
                    operStack.push(c);
                }
            }
        }
        int len = operStack.top + 1;
        for (int i = 0; i < len; i++) {
            int next = numStack.pop();
            int prev = numStack.pop();
            int pop = operStack.pop();
            numStack.push(ArrayStack.cal(next, prev, pop));
        }
        return numStack.pop();
    }


    // 扩展功能
    public static class ArrayStack {

        private int maxSize; // 栈大小
        private int[] arr;
        private int top = -1; // 表示栈顶

        public ArrayStack(int maxSize) {
            this.maxSize = maxSize;
            arr = new int[maxSize];
        }

        public boolean isFull() {
            return top == maxSize - 1;
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public void push(int num) {
            if (isFull()) {
                System.out.println("is full");
                return;
            }
            arr[++top] = num;
        }

        public int pop() {
            if (isEmpty()) {
                System.out.println("is empty");
                throw new RuntimeException("error");
            }
            return arr[top--];
        }

        public void list() {
            if (isEmpty()) {
                System.out.println("is empty");
            }
            for (int i = top; i >= 0; i--) {
                System.out.printf("arr[%d]=%d\n", i, arr[i]);
            }
        }

        // 返回运算符的优先级
        public static int priority(int oper) {
            if (oper == '*' || oper == '/') {
                return 1;
            } else if (oper == '*' || oper == '-') {
                return 0;
            } else {
                return -1;
            }
        }

        // 判断是否是运算符
        public static boolean isOper(char val) {
            return val == '+' || val == '-' || val == '*' || val == '/';
        }

        public static int cal(int n1, int n2, int oper) {
            int res = 0; // 存放计算的结果
            switch (oper) {
                case '+':
                    res = n1 + n2;
                    break;
                case '-':
                    res = n2 - n1;
                    break;
                case '*':
                    res = n1 * n2;
                    break;
                case '/':
                    res = n2 / n1;
                    break;
                default:
                    break;
            }
            return res;
        }
    }
}
