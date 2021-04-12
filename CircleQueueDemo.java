package com.zhuyz.algorithm;

public class CircleQueueDemo {

    public static void main(String[] args) {
        CircleQueueDemo circleQueueDemo = new CircleQueueDemo();
        CircleQueue queue = circleQueueDemo.getCircleQueue(5);
        queue.addQueue(0);
        queue.addQueue(1);
        queue.addQueue(2);
        queue.addQueue(3);
        queue.addQueue(4);

        queue.showQueue();
        System.out.println();
        queue.getQueue();
        queue.showQueue();
        System.out.println();
    }

    public CircleQueue getCircleQueue(int maxSize) {
        return new CircleQueue(maxSize);
    }

    private class CircleQueue {

        private int maxSize;
        private int head;
        private int tail;
        private int[] arr;


        public CircleQueue(int maxSize) {
            this.maxSize = maxSize;
            arr = new int[maxSize];
        }

        private boolean isFull() {
            // (tail + 1) % maxSize == head
            return (tail + 1) % maxSize == head;
        }

        private boolean isEmpty() {
            return head == tail;
        }

        private void addQueue(int n) {
            if (isFull()) {
                System.out.println("is full");
                return;
            }
            arr[tail] = n;
            tail = (tail + 1) % maxSize;
        }

        private int getQueue() {
            if (isEmpty()) {
                System.out.println("is empty");
                return -1;
            }
            int res = arr[head];
            head = (head + 1) % maxSize;
            return res;
        }

        private void showQueue() {
            if (isEmpty()) {
                System.out.println("is empty");
                return;
            }
            for (int i = head; i < head + size(); i++) {
                System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
            }
        }

        private int size() {
            return (tail + maxSize - head) % maxSize;
        }
    }
}
