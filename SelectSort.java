package com.zhuyz.algorithm.s5_sort.select;

import java.util.Arrays;


/**
 * 选择排序时间复杂度：O(n^2)
 * 
 * 1.选择排序基本思想：
 *      n = arr.length
 *      第一轮(i=0)：
 *          获取 arr[0]~arr[n-1] 最小值，与arr[0]交换
 *
 *      第二轮(i=1)：
 *          获取 arr[1]~arr[n-1] 最小值，与arr[1]交换
 *
 *      第三轮(i=2)：
 *          获取 arr[2]~arr[n-1] 最小值，与arr[2]交换
 *
 *      第x轮(i=x-1)：
 *          获取 arr[x-1]~arr[n-1] 最小值，与arr[x-1]交换
 *
 *      第n-1轮(i=n-2)：
 *          获取 arr[n-2]~arr[n-1] 最小值，与arr[n-2]交换
 *
 *      总共通过n-1次，得到一个从小到大排列的有序数组
 *
 * 2.eg：
 *      arr = {101, 34, 119, 1}
 *
 *      第一轮：1 34 119 101    // 101 1   交换
 *      第二轮：1 34 119 101    // 34 34   交换
 *      第三轮：1 34 101 119    // 119 101 交换
 *
 *
 * 3.说明：
 *      1)、选择排序一共有arr.length-1轮外层排序
 *      2)、每一轮外层排序，又有一个循环，循环的规则如下：
 *              2.1)、假定当前数是最小数
 *              2.2)、然后和后面的每一个数进行比较，如果发现有比当前数更小的数，就重新赋值最小数，并得到索引下标
 *              2.3)、遍历完成，得到最小数和实际索引下标
 *              2.4)、进行交换(和当前轮对应的数进行交换)
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] arr = new int[]{101, 34, 119, 1, -1, 90, 123};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
        evolutionSelectSort();
        System.out.println("===================");

        recursiveSelectSort(arr, arr.length, 0);

    }

    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                // 如果arr[j] 比 min还小，就重置min minIdx
                if (arr[j] < min) {
                    // 重置 min mindIdx
                    min = arr[j];
                    minIdx = j;
                }
            }
            // 将最小值放在arr[i]处
            exchange(i, minIdx, min, arr);
            System.out.println("第" + (i + 1) + "轮后：" + Arrays.toString(arr));
        }
    }

    public static void evolutionSelectSort() {
        int[] arr = new int[]{101, 34, 119, 1};
        int n = arr.length;

        int min = arr[0];
        int minIdx = 0;
        // 第1轮
        for (int i = 0 + 1; i < n; i++) {
            // 如果arr[j] 比 min还小，就重置min minIdx
            if (arr[i] < min) {
                // 重置 min mindIdx
                min = arr[i];
                minIdx = i;
            }
        }
        // 将最小值放在 arr[0] 处
        exchange(0, minIdx, min, arr);
        System.out.println("第1轮后：" + Arrays.toString(arr));

        min = arr[1];
        minIdx = 1;
        // 第2轮
        for (int i = 1 + 1; i < n; i++) {
            // 如果arr[j] 比 min还小，就重置min minIdx
            if (arr[i] < min) {
                // 重置 min mindIdx
                min = arr[i];
                minIdx = i;
            }
        }
        // 将最小值放在 arr[1] 处
        exchange(1, minIdx, min, arr);
        System.out.println("第2轮后：" + Arrays.toString(arr));

        min = arr[2];
        minIdx = 2;
        // 第3轮
        for (int i = 2 + 1; i < n; i++) {
            // 如果arr[j] 比 min还小，就重置min minIdx
            if (arr[i] < min) {
                // 重置 min mindIdx
                min = arr[i];
                minIdx = i;
            }
        }
        // 将最小值放在 arr[2]处
        exchange(2, minIdx, min, arr);
        System.out.println("第3轮后：" + Arrays.toString(arr));

    }

    /**
     * 递归版
     */
    public static void recursiveSelectSort(int[] arr, int n, int cur) {
        // cur是最后一个元素
        if (cur == n - 1) {
            return;
        }
        int min = arr[cur];
        int minIdx = cur;
        // 第cur+1轮
        for (int i = cur + 1; i < n; i++) {
            // 如果arr[j] 比 min还小，就重置min minIdx
            if (arr[i] < min) {
                // 重置 min mindIdx
                min = arr[i];
                minIdx = i;
            }
        }
        // 将最小值放在 arr[cur]处
        exchange(cur, minIdx, min, arr);
        System.out.println("第" + (cur + 1) + "轮后：" + Arrays.toString(arr));
        recursiveSelectSort(arr, n, cur + 1);
    }

    /**
     * 将最小值放在 arr[i]处
     */
    public static void exchange(int i, int minIdx, int min, int[] arr) {
        if (minIdx != i) {
            int tmp = arr[i];
            arr[i] = min;
            arr[minIdx] = tmp;
        }
    }

}
