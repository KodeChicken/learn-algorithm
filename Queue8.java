package com.zhuyz.learn_algorithm.recursive;

/**
 * 1) 第一个皇后先放第一行第一列
 *
 * 2) 第二个皇后放在第二行第一列、然后判断是否 OK， 如果不 OK，继续放在第二列、第三列、依次把所有列都放完，找到一个合适
 *
 * 3) 继续第三个皇后，还是第一列、第二列……直到第 8 个皇后也能放在一个不冲突的位置，算是找到了一个正确解
 *
 * 4) 当得到一个正确解时，在栈回退到上一个栈时，就会开始回溯，即将第一个皇后，放到第一列的所有正确解，
 *    全部得到. 5) 然后回头继续第一个皇后放第二列，后面继续循环执行 1,2,3,4 的步骤
 *
 *
 * 说明：
 *      理论上应该创建一个二维数组来表示棋盘，但是实际上可以通过算法，用一个一维数组即可解决问题. arr[8] =
 *      {0 , 4, 7, 5, 2, 6, 1, 3} //对应 arr 下标 表示第几行，即第几个皇后，arr[i] = val , val 表示第 i+1 个皇后，放在第 i+1
 *      行的第 val+1 列
 *
 * @author zhuyz
 * @date 2021/4/20 0020 12:23
 * @description
 */
public class Queue8 {

    int max = 8;
    /**
     * 定义数组arr,保存皇后防止位置的结果，比如arr={0,4,7,5,2,6,1,3}
     * <p>
     * 用一维表示二维
     * index表示行，arr[index]表示列
     */
    int[] arr = new int[max];

    // 多少种解法
    static int count;

    // judge判断次数
    static int judge;

    public static void main(String[] args) {
        // 测试 8皇后是否正确
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d种解法", count);
        System.out.printf("一共判断冲突的次数是%d次", judge);
    }

    /**
     * 编写一个方法，放置第n个皇后
     *
     * 特别注意：check 是每一次递归时，进入到check方法中都会有一个for (int i = 0; i < max; i++)，因此会有回溯
     *
     * @param n
     */
    private void check(int n) {
        // n == 8 代表8个皇后放置完毕，任务结束
        if (n == max) {
            print();
            return;
        }
        /**
         * 依次放入皇后，并判断是否冲突
         *
         * n代表第几个皇后，也代表第几行
         *
         */
        for (int i = 0; i < max; i++) {
            // 将皇后放置在第n行的第i列
            arr[n] = i;
            // 判断放置到第n个皇后的第i列时，是否冲突
            if (judge(n)) {
                // 不冲突，继续放置n+1皇后，也就是下一行
                check(n+1);
            }

            // 如果冲突，就继续执行for循环，arr[n] = i+1  。即将第n个皇后放置在后移的列
        }
    }

    /**
     * 查看当前放置的第n个皇后是否和前面的已经摆放的皇后冲突
     *
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n) {
        judge++;
        for (int i = 0; i < n; i++) {
            /**
             * 1.判断列是否相同：arr[i] == arr[n] 表示列相同
             *
             * 2.判断行是否相同：n就代表行，且n在递增，不可能相同，不用判断
             *
             * 3.判断是否同一个斜线：
             *      eg：
             *          0,1  1,2 是
             *          1,1, 2,2 是
             *          1,0  2,1 是
             *          1,1  3,3 是
             *          x,y  x+N,y+N 是
             *          规律：x+N - x == y+N - y
             *
             *      res:
             *          Math.abs(n - i) == Math.abs(arr[n] - arr[i])
             *
             */
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }

    private void print() {
        count++;
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
