package com.zhuyz.algorithm.s5_sort.shell;


import java.util.Arrays;

/**
 * 希尔排序是简单插入排序改进后更高阶的版本，也称为缩小增量排序，
 * 插入排序一般来说比较抵消，因为插入排序每次只能将数据移动一位
 *
 * 1.希尔排序思想：
 *      分组插入方法，跳跃式比较交换：
 *          1)、比较相隔较远距离（称为增量）的数，使得数移动时能跨过多个元素，则进行一次比较就可能消除多个元素交换。
 *          2)、先将要排序的一组数按某个增量d分成若干组，每组中记录的下标相差d.对每组中全部元素进行排序，然后再用一个较小的增量对它进行分组，在每组中再进行排序。
 *          3)、当增量减到1时，整个要排序的数被分成一组，排序完成。
 *              一般的初次取序列的一半为增量，以后每次减半，直到增量为1。
 *
 *
 * 2.eg：
 *      arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0}
 *      n = arr.length = 10
 *
 *      初始：
 *          {8, 9, 1, 7, 2, 3, 5, 4, 6, 0}
 *
 *      第一轮：5个数为一行(步长：5)
 *
 *          8 9 1 7 2
 *          3 5 4 6 0
 *          对列进行排序，结果如下：
 *          3 5 1 6 0
 *          8 9 4 7 2
 *
 *          最终结果：{3, 5, 1, 6, 0, 8, 9, 4, 7, 2}
 *
 *      第二轮：2个数为一行(步长：2)
 *          3 5
 *          1 6
 *          0 8
 *          9 4
 *          7 2
 *          对列进行排序，结果如下：
 *          0 2
 *          1 4
 *          3 5
 *          7 6
 *          9 8
 *
 *          最终结果：{0, 2, 1, 4, 3, 5, 7, 6, 9, 8}
 *
 *      第三轮：1个数为一行(步长：1)
 *          对列进行排序，结果就是有序的了
 *
 *
 */
public class ShellSort {


    public static void main(String[] args) {
        int[] arr = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        evolutionShellSort();
        System.out.println("=============");
        shellSort(arr);

    }

    /**
     * 希尔排序演变过程
     */
    public static void evolutionShellSort() {
        int[] arr = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        //int[] arr = new int[]{1, 3, 11, 17, 22, 13, 15, 9, 6, 0};

        // 第1轮排序，是将10个数据分成了5组
        for (int i = 5; i < arr.length; i++) {
            // 遍历各组中所有的元素(共5组，每组有2个元素)，步长: 5
            for (int j = i - 5; j >= 0; j -= 5) {
                // 如果当前元素大于加上步长的那个元素，说明需要交换
                exchange(arr, j, 5);
            }
        }
        System.out.println("第1轮排序：" + Arrays.toString(arr));

        // 第2轮排序，是将10个数据分成了2组
        for (int i = 2; i < arr.length; i++) {
            // 遍历各组中所有的元素(共2组，每组有5个元素)，步长: 2
            for (int j = i - 2; j >= 0; j -= 2) {
                exchange(arr, j, 2);
            }
        }
        System.out.println("第2轮排序：" + Arrays.toString(arr));

        // 第3轮排序，是将10个数据分成了1组
        for (int i = 1; i < arr.length; i++) {
            // 遍历各组中所有的元素(共1组，每组有10个元素)，步长: 1
            for (int j = i - 1; j >= 0; j -= 1) {
                exchange(arr, j, 1);
            }
        }
        System.out.println("第3轮排序：" + Arrays.toString(arr));

    }

    public static void shellSort(int[] arr) {
        int n = arr.length/2;
        int l = 1;
        while (n >= 1) {
            for (int i = n; i < arr.length; i++) {
                for (int j = i - n; j >= 0; j -= n) {
                    exchange(arr, j, n);
                }
            }
            System.out.println("第" + (l++) + "轮排序：" + Arrays.toString(arr));
            n /= 2;
        }
    }

    private static void exchange(int[] arr, int j, int step) {
        if (arr[j] > arr[j + step]) {
            int tmp = arr[j];
            arr[j] = arr[j + step];
            arr[j + step] = tmp;
        }
    }


}
