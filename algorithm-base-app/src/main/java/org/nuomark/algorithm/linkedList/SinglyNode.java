package org.nuomark.algorithm.linkedList;

/**
 * @program: algorithm-base-app
 * @description: 单向链表节点
 * @author: NuoMark
 * @create: 2020-11-16 10:26
 **/
public class SinglyNode<T> {
    /**
     * 存储的数据
     */
    T element;
    /**
     * 下一个节点的指针
     */
    SinglyNode next=null;

    public SinglyNode() {
    }

    public SinglyNode(T element) {
        this.element = element;
    }

    public SinglyNode(T element, SinglyNode next) {
        this.element = element;
        this.next = next;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public SinglyNode getNext() {
        return next;
    }

    public void setNext(SinglyNode next) {
        this.next = next;
    }
}
