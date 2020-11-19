package org.nuomark.algorithm.linkedList;

import java.util.Scanner;

/**
 * @program: algorithm-base-app
 * @description: 最近最少使用策略(链表实现)
 *
 * 实现细节(仅仅对外提供添加API)
 * 1. 首先查询是否链表存在要添加的数据
 *      1. 如果存在就删除添加到头部
 *      2.如果不存在就直接添加到头部
 * @author: NuoMark
 * @create: 2020-11-13 17:03
 **/
public class LRULinkedList<T> {
    //存储链表
    private volatile SinglyNode linked;

    //头节点
    private SinglyNode<T> head;

    //默认容量大小(阀值控制：主要用于模拟内存你能存储多少数据)
    private static final Integer DEFAULT_CAPACITY = 10;

    //记录链表当前长度
    private Integer length;

    //记录链表容量
    private Integer capacity;

    /**
     * 构造初始化
     */
    public LRULinkedList() {
        this.head = new SinglyNode<>();
        this.capacity = DEFAULT_CAPACITY;
        this.length = 0;
    }

    /**
     * 根据容量初始化
     * @param capacity
     */
    public LRULinkedList(Integer capacity) {
        this.capacity = capacity;
        this.length = 0;
        this.head = new SinglyNode<>();
    }

    /**
     * 添加数据
     * @param data
     */
    public void add(T data){
        //去空处理
        data= (T) String.valueOf(data).trim();

        //查询链表是否存在data数据的节点，如果存在就返回data数据节点的前一个节点
        SinglyNode preNode=findNode(data);

        //链表存在data数据节点，删除原来位置的data数据节点，再将data数据插入到链表的头部
        if (preNode!=null){
            deleteElemOptim(preNode);
            intsertElemAtBegin(data);
        }else {//data元素不存在链表中
            //链表存储的数量大于等于链表阀值容量时候
            if(length>=this.capacity){
                //删除位节点
                deleteElemAtEnd();
            }
            //将数据添加到链表头部
            intsertElemAtBegin(data);
        }
    }

    /**
     * 删除链表中存在的数据节点
     * @param preNode
     */
    private void deleteElemOptim(SinglyNode preNode){
        //获取到即将被删除的节点
        SinglyNode temp=preNode.getNext();
        //直接将 被删除的节点 前一个节点指向 被删除的节点 的下一个节点
        preNode.setNext(temp.getNext());
        //被节点设置为空
        temp=null;
        length--;
    }

    /**
     * 将数据添加到链表头部
     * @param data
     */
    private  void intsertElemAtBegin(T data){
        SinglyNode next=head.getNext();
        //将头部的空节点指向新插入的节点，新节点指向以前的链表
        head.setNext(new SinglyNode(data,next));
        length++;
    }

    /**
     * 查询元素
     * @param data
     * @return 返回查找数据节点的前一个节点
     */
    public SinglyNode findNode(T data){
        SinglyNode node=head;

        if (node.getNext()==null) return null;

        //从链表头部开始遍历，判断条件是是否存在下一个节点
        while (node.getNext()!=null){
            if (data.equals(node.getNext().getElement())){
                return node;
            }
          node=node.getNext();
        }
        return null;
    }

    /**
     * 删除尾节点
     */
    private  void deleteElemAtEnd(){
        SinglyNode ptr=head;
        //只有哨兵节点(没有存储数据)则直接返回
        if (ptr.getNext()==null) return;

        while (ptr.getNext().getNext()!=null){
            ptr=ptr.getNext();
        }
        SinglyNode tmp=ptr.getNext();
        ptr.setNext(null);
        tmp=null;
        length--;
    }

    /**
     * 打印链表
     */
    private void printAll(){
        SinglyNode node = head.getNext();
        while (node != null) {
            System.out.print(node.getElement() + ",");
            node = node.getNext();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LRULinkedList lru=new LRULinkedList(10);
        while (true) {
            Scanner sc = new Scanner(System.in);
            while (true) {
                lru.add(sc.nextInt());
                lru.printAll();
            }
        }
    }
}
