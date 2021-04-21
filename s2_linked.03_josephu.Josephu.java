package com.zhuyz.algorithm.linked.josephu;

public class Josephu {

    public static void main(String[] args) {
        Josephu josephu = new Josephu();
        CircleSingleLinkedList circleSingleLinkedList = josephu.circleSingleLinkedList();
        circleSingleLinkedList.addBoy(25);
        circleSingleLinkedList.showBoy();
        System.out.println();

        circleSingleLinkedList.count(1, 3, 25);

    }


    private CircleSingleLinkedList circleSingleLinkedList() {
        return new CircleSingleLinkedList();
    }

    private class CircleSingleLinkedList {

        private Boy first = new Boy(-1);

        private Boy last;

        public void addBoy(int nums) {
            // validation
            if (nums < 1) {
                System.out.println("error");
                return;
            }
            // 创建环形链表
            // 使用辅助指针指向first的前一个节点，也就是尾节点
            Boy curBoy = null;
            for (int i = 1; i <= nums; i++) {
                Boy boy = new Boy(i);
                if (i == 1) {
                    first = boy;
                    first.setNext(first);   // next指向自己,构成环状
                    curBoy = first;         // 让curBoy指向第一个小朋友
                } else {
                    curBoy.setNext(boy);    // 让curBoy(尾节点)指向新建的boy
                    boy.setNext(first);     // 指向头节点
                    curBoy = boy;           // 右移辅助指针curBoy，重新指向尾节点
                }
            }
            last = curBoy;
        }

        public void showBoy() {
            if (first == null) {
                System.out.println("error");
                return;
            }
            Boy curBoy = first;
            while (true) {
                System.out.printf("Boy[%d]\n", curBoy.getNo());
                if (curBoy.getNext() == first) {
                    break;
                }
                curBoy = curBoy.getNext();
            }
        }

        /**
         * 根据用户的输入，计算出小孩出圈的顺序
         *
         * @param startNo 从第几个小孩数数
         * @param countNum 数几次
         * @param nums 最初有多少小孩在圈中
         */
        public void count(int startNo, int countNum, int nums) {
            if (first == null || startNo < 1 || startNo > nums) {
                System.out.println("error");
                return;
            }
            // 创建辅助指针，用来保存当前遍历节点的上一个节点。
            Boy prev = first;
            while (true) {
                if (prev.getNext() == first) {
                    break;
                }
                prev = prev.getNext();
            }
            // 移动k-1次
            for (int i = 0; i < startNo - 1; i++) {
                first = first.getNext();
                prev = prev.getNext();
            }
            // 小孩报数时，让first和helper指针移动m-1次，然后出圈
            // 这里是一个循环的操作
            while (true) {
                if (prev == first) {    // 说明圈中只有一个节点
                    break;
                }
                // 让prev first 移动m-1：countNum-1
                for (int i = 0; i < countNum -1 ; i++) {
                    first = first.getNext();
                    prev = prev.getNext();
                }
                // 当前first指向的节点就是要出圈的节点
                System.out.printf("Boy[%d]出圈\n", first.getNo());
                first = first.getNext();
                prev.setNext(first);
            }
            System.out.println("最后留在圈中的小孩:" + first.getNo());
        }
    }

    private class Boy {

        private int no;
        private Boy next;

        public Boy(int no) {
            this.no = no;
        }


        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public Boy getNext() {
            return next;
        }

        public void setNext(Boy next) {
            this.next = next;
        }
    }


}
