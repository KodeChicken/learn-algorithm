package com.zhuyz.algorithm.s2_linked.doublelinked;

import com.zhuyz.algorithm.s2_linked.doublelinked.DoubleLInkedList.Node;

public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        DoubleLInkedList doubleLInkedList = new DoubleLInkedList();
        doubleLInkedList.add(new Node(1,"zhuyz1"));
        doubleLInkedList.add(new Node(2,"zhuyz2"));
        doubleLInkedList.add(new Node(4,"zhuyz4"));
        doubleLInkedList.add(new Node(7,"zhuyz7"));

        //doubleLInkedList.list();


        //doubleLInkedList.update(new Node(2, "liuguowenxxx"));


        //doubleLInkedList.del(4);

        //doubleLInkedList.del(7);


        doubleLInkedList.addByOrder(new Node(3, "zhuzy3"));
        doubleLInkedList.addByOrder(new Node(6, "zhuzy6"));
        doubleLInkedList.addByOrder(new Node(8, "zhuzy8"));


        System.out.println();


    }


}
