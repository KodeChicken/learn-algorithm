package com.zhuyz.algorithm.s5_sort.quick;

import com.zhuyz.algorithm.s5_sort.utils.Utils;
import java.util.Arrays;

/**
 * 快速排序式对冒泡排序的一种改进
 *
 * 1.快速排序思想：
 *      通过一趟排序将要排序的数据分割成独立的两部分，
 *      其中一部分的所有数据都比另外一部分的所有数据都要小，
 *      然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列
 *
 * 2.eg:
 *      arr = {-9,78,0,23,-567,70}
 *
 *      给基准数据找正确索引位置的过程。
 *      假设最开始基准数据为数组第一个元素，首先用一个临时变量存储基准数据，然后分别从数组的两端扫描，设定两个指示标志：low指向起始位置，high指向末尾
 *
 *      1.从数组后面开始，如果扫描到比基准数大的就让high减1，如果发现有元素比基准数小的就将arr[low] = arr[high]
 *      2.从数组前面开始，如果扫描到比基准数小的就让low加1，如果发现有元素比基准数大的就将arr[high] = arr[low]
 *      3.重复1和2步骤，直到low>=high(实际是low=high)，此时low或者high的位置就是该基准数在数组中正确的位置
 *      4.递归调用
 *
 *
 * 3.参考博客
 *  https://blog.csdn.net/nrsc272420199/article/details/82587933
 */
public class QuickSort {


    public static void main(String[] args) {
        //int[] arr = {-9,78,0,23,-567,70};
        //quickSort(arr, 0, arr.length-1);
        //System.out.println(Arrays.toString(arr));

        int[] arr = Utils.randomArr(8000);
        quickSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));

    }

    /**
     * 快速排序
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int index = getIndex(arr, low, high);
            quickSort(arr, low, index - 1);
            quickSort(arr, index + 1, high);
        }
    }

    /**
     * 交换元素，并且找到基准数的正确位置
     */
    public static int getIndex(int[] arr, int low, int high) {
        int tmp = arr[low];
        // while 循环的目的是让
        // 比tmp值小的放到左边
        // 比tmp大的值放到右边
        while (low < high) {
            // 在tmp右边一直找，找到小于tmp的值
            while (low < high && arr[high] >= tmp) {
                high--;
            }
            arr[low] = arr[high];
            // 在tmp左边一直找，找到大于tmp的值
            while (low < high && arr[low] <= tmp) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = tmp;
        return low;
    }

}
