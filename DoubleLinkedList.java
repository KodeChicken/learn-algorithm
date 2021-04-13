package com.zhuyz.algorithm.linked.doublelinked;

public class DoubleLInkedList {

    private Node head = new Node(0, "");


    public void list() {
        Node cur = head.next;
        while (cur != null) {
            System.out.printf("val=>%d, nickname=>%s\n", cur.val, cur.nickname);
            cur = cur.next;
        }
    }

    public void add(Node node) {
        Node tmp = head;
        while (true) {
            if (tmp.next == null) {
                break;
            }
            tmp = tmp.next;
        }
        tmp.next = node;
        node.prev = tmp;
    }

    public void addByOrder(Node node) {
        Node cur = head.next;
        boolean flag = false;
        while (true) {
            if (node.val < cur.val) {
                flag = true;
                break;
            }
            if (cur.next == null) {
                cur.next = node;
                node.prev = cur;
                break;
            } else {
                cur = cur.next;
            }
        }
        if (flag) {
            // 临时保存上一个节点
            Node prev = cur.prev;
            // cur <=> node
            cur.prev = node;
            node.next = cur;

            // prev <=> node
            node.prev = prev;
            prev.next = node;
        }
    }

    public void update(Node node) {
        if (head.next == null) {
            System.out.println("is empty");
            return;
        }
        Node tmp = head.next;
        boolean flag = true;
        while (true) {
            if (tmp == null) {
                break;
            }
            if (tmp.val == node.val) {
                flag = true;
                break;
            }
            tmp = tmp.next;
        }
        if (flag) {
            tmp.nickname = node.nickname;
        } else {
            System.out.println("未找到");
        }
    }

    public void del(int n) {
        Node cur = head.next;
        boolean flag = false;
        while (true) {
            if (cur == null) {
                return;
            }
            if (cur.val == n) {
                flag = true;
                break;
            }
            cur = cur.next;
        }
        if (flag) {
            cur.prev.next = cur.next;
            // 如果不是最后一个节点，防止空指针异常
            if (cur.next != null) {
                cur.next.prev = cur.prev;
            }
        } else {
            System.out.println("未找到");
        }
    }


    public static class Node {

        private int val;
        private String nickname;
        private Node prev;
        private Node next;

        public Node(int val, String nickname) {
            this.val = val;
            this.nickname = nickname;
        }
    }

}
