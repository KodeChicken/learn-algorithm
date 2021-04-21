package com.zhuyz.algorithm.s5_sort.bubble;

import java.util.Arrays;


/**
 * 冒泡排序时间复杂度：O(n^2)
 *
 * eg：原始数组：int[] arr = new int[]{3,9,-1,10,20}
 *
 * 1.原理：使用两个指针用来指向两个相邻的数据进行比较交换，并且两个指针在递增
 *
 *
 * 2.过程：
 * 第一趟排序
 *      (1) 3,-1,9,10,20    // -1 9  比较交换
 *      (2) 3,-1,9,10,20    // 9 10  比较(未交换)
 *      (3) 3,-1,9,10,20    // 10 20 比较(未交换)
 *      (4) 3,-1,9,10,20
 *
 *
 * 第二趟排序
 *      (1) -1,3,9,10,20    // 3 -1  比较(未交换)
 *      (2) -1,3,9,10,20    // 3 9   比较(未交换)
 *      (3) -1,3,9,10,20    // 9 10  比较(未交换)
 *
 *      最后一个元素是有序的且是最大的，不需要再和前面的进行比较交换
 *
 *
 * 第三趟排序
 *      (1) -1,3,9,10,20    // -1 3 比较(未交换)
 *      (2) -1,3,9,10,20    // 3 9 比较(未交换)
 *
 *      最后两个元素(10,20)是有序的，不需要再和前面的进行比较交换
 *
 * 第四趟排序
 *          (1) -1,3,9,10,20    // -1 3 比较交换(未交换)
 *
 *          最后三个元素(9,10,20)是有序的，不需要再和前面的进行比较交换
 *
 *
 *
 * 3.总结：bubbleSort 规则
 *      1.一共进行arr.length-1次外层的循环
 *      2.每一趟的内层排序次数在递减
 *      3.优化：如果我们发现在某趟的内层排序中，没有发生一次交换，就代表已经是有序的，可以提前结束冒泡排序
 *
 */
public class BubbleSort {

    public static void main(String[] args) {
        //int[] arr = new int[]{3,9,-1,10,20};
        //int[] arr2 = new int[]{3,9,-2,-1,20};
        // 演变
        evolutionBubbleSort();
        // 冒泡
        //bubbleSort(arr);
        // 递归冒泡
        //recursive(arr2, 0);

        System.out.println();

        int[] arr = new int[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = (int) (Math.random() * 100000);
        }
        bubbleSort(arr);

    }

    /**
     * 冒泡排序演变的过程
     */
    public static void evolutionBubbleSort() {
        int[] arr = new int[]{3,9,-1,10,20};

        // 第一趟排序，将最大的数放到最后
        for (int i = 0; i < arr.length - 1 - 0; i++) {
            exchange(i, arr);
        }
        System.out.println("第一趟排序后的数组" + Arrays.toString(arr));

        // 第二趟排序，将第二大的数放到倒数第二位
        for (int i = 0; i < arr.length - 1 - 1; i++) {
            exchange(i, arr);
        }
        System.out.println("第二趟排序后的数组" + Arrays.toString(arr));

        // 第三趟排序，将第三大的数放到倒数第三位
        for (int i = 0; i < arr.length - 1 - 2; i++) {
            exchange(i, arr);
        }
        System.out.println("第三趟排序后的数组" + Arrays.toString(arr));

        // 第四趟排序，将第四大的数放到倒数第四位
        for (int i = 0; i < arr.length - 1 - 3; i++) {
            exchange(i, arr);
        }
        System.out.println("第四趟排序后的数组" + Arrays.toString(arr));

    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = false;
                }
            }
            System.out.println("第" + (i + 1) + "趟排序后的数组" + Arrays.toString(arr));
            // 如果未发生交换，则代表已经有序，直接结束冒泡排序
            if (flag) {
                break;
            }
        }
    }

    /**
     * 递归
     * @param arr
     * @param n
     */
    public static void recursive(int[] arr, int n) {
        if (n == arr.length - 1) {
            return;
        }
        // 第一趟排序，将最大的数放到最后
        for (int i = 0; i < arr.length - 1 - n; i++) {
            exchange(i, arr);
        }
        System.out.println("第" + (n + 1) + "趟排序后的数组" + Arrays.toString(arr));
        recursive(arr, n + 1);
    }

    public static void exchange(int i, int[] arr) {
        if (arr[i] > arr[i + 1]) {
            int tmp = arr[i];
            arr[i] = arr[i + 1];
            arr[i + 1] = tmp;
        }
    }
}
