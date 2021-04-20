package com.zhuyz.algorithm.s4_recursion;

public class MiGong {


    public static void main(String[] args) {
        int[][] miGong = createMiGong();
        boolean b = setWay(miGong, 1, 1);
        System.out.println(b);
        System.out.println("-----探测的路-----");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(miGong[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static int[][] createMiGong() {

        int[][] map = new int[8][7];
        // 上下置1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        // 左右置1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        map[3][1] = 1;
        map[3][2] = 1;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        return map;
    }

    /**
     * 使用递归回溯来给小球找路
     * 1、map 表示地图
     * 2、i，j表示从地图的哪个位置开始出发找(1,1)
     * 3、如果小球能到map[6][5]位置，则说明通路找到
     * 4、约定：当map[i][j]为0表示该点没有走过
     *      当为1的时候墙；2表示通路可以走；3表示该点已经走过，但是没有走通
     * 5、走迷宫，需要确定一个策略(方法) 下->右->上->左，如果该点走不通，再回溯
     *
     * @param map 地图
     * @param i 起始位置
     * @return 如果找到通路，就返回true，否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            // 通路已经找到
            return true;
        } else {
            // 如果当前节点没有走过
            if (map[i][j] == 0) {
                // 按照策略  下->右->上->左
                // 假定该点是可以走通的
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) {
                    // 向下走 如果能走通
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    // 向右走 如果能走通
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    // 向上走 如果能走通
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    // 向左走 如果能走通
                    return true;
                } else {
                    // 说明该点走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                // 如果map[i][j] != 0，可能是1，2，3
                // 1，2，3代表该节点是墙、或者已经标记为可以走、或者不可达
                return false;
            }
        }

    }


}
