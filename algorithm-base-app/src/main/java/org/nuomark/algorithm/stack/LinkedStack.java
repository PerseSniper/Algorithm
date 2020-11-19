package org.nuomark.algorithm.stack;

import org.nuomark.algorithm.linkedList.SinglyNode;

/**
 * @program: algorithm-base-app
 * @description: 链表实现栈称之为链式栈
 * 基于链表实现栈
 * 入栈和出栈时间复杂度O(1)
 * @author: NuoMark
 * @create: 2020-11-19 13:04
 **/
public class LinkedStack<T> {
    private SinglyNode<T> linked;

    /**
     * 入栈
     *
     * @param value
     * @return
     */
    private void push(String value) {
        SinglyNode newNode = new SinglyNode(value);
        if (linked == null) {
            linked = newNode;
        } else {
            newNode.setNext(linked);
            linked = newNode;

        }
    }

    /**
     * 出栈
     */
    private String pop() {
        if (linked == null) {
            return null;
        }
        String value = (String) linked.getElement();
        linked=linked.getNext();
        return value;
    }

    /**
     * 打印链表
     */
    private void printAll() {
        SinglyNode node = linked.getNext();
        while (node != null) {
            System.out.print(node.getElement() + ",");
            node = node.getNext();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedStack linkedStack = new LinkedStack();
        for (int i = 0; i < 10; i++) {
            linkedStack.push(String.valueOf(i));
        }
        linkedStack.printAll();
        linkedStack.pop();
        linkedStack.printAll();
    }
}
