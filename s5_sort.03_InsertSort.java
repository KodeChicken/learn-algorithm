package com.zhuyz.algorithm.s5_sort.insert;


import java.util.Arrays;

/**
 *
 * 1.插入排序思想：
 *      n = arr.length
 *      把n个待排序的元素看成一个有序表和一个无序表，开始时有序表只有一个元素，无序表中包含n-1个元素。
 *      排序过程中，每次从无序表中取出第一个元素，把它的排序码依次与有序表元素的排序码进行比较，将它插入到有序表中的适当位置，使之成为新的有序表。
 *
 *      简单来说：维护两个表(有序表/无序表)，从无序表中取出一个元素放置到有序表的适当位置(保证有序即可)
 *
 *
 *
 * 2.eg：
 *      arr = {17, 3, 25, 14, 20, 9}
 *
 *      初始状态：(17) 3 25 14 20 9
 *
 *      第一次插入：
 *          (3, 17) 25 14 20 9
 *
 *      第二次插入：
 *          (3, 17, 25) 14 20 9
 *
 *      第三次插入：
 *          (3, 14, 17, 25) 20 9
 *
 *      第四次插入：
 *          (3, 14, 17, 20, 25) 9
 *
 *      第五次插入：
 *          (3, 9, 14, 17, 20, 25)
 *
 *
 * 3.说明：
 *      1)、插入排序一共有arr.length-1次外层排序
 *      2)、每一轮外层排序又有一层循环：
 *              2.1.获取到无序表中第一个元素用临时变量(insertVal)存储。
 *                并记录所有有序表的最后一个索引下标insertIdx。
 *                (insertVal = arr[i]; insertIdx = i - 1)
 *
 *              2.insertVal 和 从有序表的arr[insertIdx]开始往前遍历 进行比较
 *                      2.2.1.insertVal < 有序表的当前元素(arr[insertIdx])：
 *                              则将arr[insertIdx + 1] = arr[insertIdx]并且insertIdx--.继续循环判断
 *                      2.2.2.insertVal > 有序表的当前元素(arr[insertIdx])：
 *                              则退出循环，并且将arr[insertIdx + 1] = insertVal
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = new int[]{17, 3, 25, 14, 20, 9};
        insertSort(arr);
        System.out.println("===============");
        evolutionInsertSort();
        System.out.println("===============");
        recursiveInsertSort(arr, 1);
    }

    /**
     * 插入排序演变过程
     *
     */
    public static void evolutionInsertSort() {
        int[] arr = new int[]{34, 101, 119, 1};

        // 第一次插入
        int insertVal = arr[1];
        int insertIndex = 1 - 1;
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex + 1] = insertVal;
        System.out.println("第一次插入：" + Arrays.toString(arr));

        // 第二次插入
        insertVal = arr[2];
        insertIndex = 2 - 1;
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex + 1] = insertVal;
        System.out.println("第二次插入：" + Arrays.toString(arr));

        // 第三次插入
        insertVal = arr[3];
        insertIndex = 3 - 1;
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex + 1] = insertVal;
        System.out.println("第三次插入：" + Arrays.toString(arr));
    }

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // 第i次插入
            int insertVal = arr[i];
            int insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertVal;
            System.out.println("第" + i + "次插入：" + Arrays.toString(arr));
        }
    }

    /**
     * 递归版
     */
    public static void recursiveInsertSort(int[] arr, int i) {
        if (i == arr.length) {
            return;
        }
        // 第i次插入
        int insertVal = arr[i];
        int insertIndex = i - 1;
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex + 1] = insertVal;
        System.out.println("第" + i + "次插入：" + Arrays.toString(arr));
        recursiveInsertSort(arr, i + 1);
    }

}
