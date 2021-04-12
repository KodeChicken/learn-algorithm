package com.zhuyz.algorithm;

import com.zhuyz.algorithm.SingleLinkedList.Node;

public class SingleLinkedListDemo {


    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(new Node(1, "zhuyz1"));
        singleLinkedList.add(new Node(4, "zhuyz4"));
        singleLinkedList.add(new Node(7, "zhuyz7"));


        singleLinkedList.addByOrder(new Node(2, "zhuyz2"));

        singleLinkedList.addByOrder(new Node(4, "xxxxx"));


        singleLinkedList.update(new Node(2, "liuguowen2"));

        singleLinkedList.update(new Node(3, "liuguowen2"));
        singleLinkedList.update(new Node(10, "liuguowen2"));
        singleLinkedList.update(null);

        singleLinkedList.update(new Node(7, "last"));


        singleLinkedList.del(2);
        singleLinkedList.del(7);
    }
}
