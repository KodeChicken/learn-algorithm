package com.zhuyz.algorithm;

public class SingleLinkedList {

    private Node head = new Node(0, "");

    public void add(Node node) {
        Node tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = node;
    }

    public void addByOrder(Node node) {
        Node tmp = head;
        Node prev = head;
        while (node.val > tmp.val && tmp.next != null) {
            prev = tmp;
            tmp = tmp.next;
        }
        // 编号相同，不能添加，直接return
        if (node.val == tmp.val) {
            System.out.printf("编号[%d]已经存在，不能添加", node.val);
            return;
        }
        prev.next = node;
        node.next = tmp;
    }

    public void update(Node node) {
        if (node == null || head.next == null) {
            System.out.println("error");
            return;
        }
        Node tmp = head;
        while (node.val != tmp.val && tmp.next != null) {
            tmp = tmp.next;
        }
        // 如果不相等
        if (node.val != tmp.val) {
            System.out.printf("不存在编号[%d]\n", node.val);
            return;
        }
        tmp.nickname = node.nickname;
    }

    public void del(int val) {
        Node tmp = head;
        Node prev = head;
        while (val != tmp.val && tmp.next != null) {
            prev = tmp;
            tmp = tmp.next;
        }
        if (val != tmp.val) {
            System.out.printf("不存在编号[%d]\n", val);
            return;
        }
        prev.next = tmp.next;
    }

    public static class Node {

        private int val;
        private String nickname;
        private Node next;

        public Node(int val, String nickname) {
            this.val = val;
            this.nickname = nickname;
        }
    }

}
