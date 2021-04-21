package com.zhuyz.algorithm.s3_stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PrefixExpr {


    public static void main(String[] args) {
        // (3+4)*5-6  =>  - * + 3 4 5 6
        String prefixExpr = "- * + 3 4 5 6";
        List<String> listStr = getListStr(prefixExpr);
        System.out.println(listStr);
        int res = calculate(listStr);
        System.out.println(res);


    }

    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String item : list) {
            if (item.matches("\\d+")) {
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
                res = n1 - n2;
                break;
            case "*":
                res = n1 * n2;
                break;
            case "/":
                res = n1 / n2;
                break;
            default:
                break;
        }
        return res;
    }


    public static List<String> getListStr(String prefixExpr) {
        List<String> resList = new ArrayList<>();

        String[] arr = prefixExpr.split(" ");
        for (int i = 0; i < arr.length; i++) {
            resList.add(arr[arr.length - 1 - i]);
        }
        return resList;
    }


}
