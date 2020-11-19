package org.nuomark.algorithm.linkedList;

/**
 * @program: algorithm-base-app
 * @description: 回文链表
 * 正读和反读都相同的字符序列为“回文”，如“abba”、“abccba”是“回文”，“abcde”和“ababab”则不是“回文”。
 * 实现思路：
 * 1. 寻找中心点
 * 2. 左边(右边)链表倒转
 * 3. 左右边链表比较
 * @author: NuoMark
 * @create: 2020-11-17 12:59
 **/
public class PalindromeLinkedList<T> {
    private SinglyNode<T> head = null;
    /**
     * 字符串转数组
     *
     * @param str
     * @return
     */
    private String[] strToArray(String str) {
        String[] strings = new String[str.length()];

        for (int i = 0; i < str.length(); i++) {
            strings[i] = str.substring(i, i + 1);
        }
        return strings;
    }

    /**
     * 从链表尾部插入数据
     *
     * @param value
     */
    public void insertTail(String value) {
        SinglyNode node = new SinglyNode(value, null);
        if (head == null) {
            head = node;
        } else {
            SinglyNode q = head;
            while (q.next != null) {
                q = q.next;
            }
            //node.next = q.next; //为什么需要这句？
            q.next = node;
        }
    }

    /**
     * 打印链表
     */
    private void printAll() {
        SinglyNode tmp = head;
        while (tmp != null) {//只有在判断 最后一个节点为空的时候才结束
            System.out.print(tmp.getElement() + ",");
            tmp = tmp.next;
        }
        System.out.println();
    }

    /**
     * 判断链表是否为回文
     *
     * @return
     */
    private boolean handlePalindrome() {
        if (head == null) {
            return false;
        } else {
            System.out.println("开始寻找中心节点");

            SinglyNode center = head;

            SinglyNode q = head;
            //寻找中心位置
            if (center.next == null) {
                System.out.println("只有一个元素");
                return true;
            }

            //
            //思路: 中心n位置*2+1=链表总长度 (奇数)
            // 中心n位置*2=链表总长度(偶数)
            //p=q=0,1,2,3,4,3,2,1,0
            //p=2 p=4 p=2 p=0
            //q=1 q=2 q=3 q=4
            while (q.next != null && q.next.next != null) {
                center = center.next;
                q = q.next.next;
            }

            System.out.println("中间节点" + center.getElement());

            //中线点左边链表
            SinglyNode leftLink = null;
            //中线点右边链表
            SinglyNode rightLink = null;

            if (q.next == null) {
                System.out.println("奇数链表");
                //为什么为奇数？因为寻找中心点的时候是从 第三个数据开始寻找的
                leftLink = inverseLinkList(center).next;
                rightLink = center.next;

            } else {
                System.out.println("偶数链表");
                //p q　均为中点
                rightLink = center.next;
                leftLink = inverseLinkList(center);
            }
            System.out.println("左边第一个节点" + leftLink.getElement());
            System.out.println("右边第一个节点" + rightLink.getElement());
            return TFResult(leftLink, rightLink);
        }

    }

    /**
     * 比对
     *
     * @param leftLink  左边链表
     * @param rightLink 右边链表
     * @return
     */
    private boolean TFResult(SinglyNode leftLink, SinglyNode rightLink) {
        SinglyNode l = leftLink;
        SinglyNode r = rightLink;

        boolean flag=true;
        while (l != null & r != null) {
            if (l.next.equals(r.element)){
                l=l.next;
                r=r.next;
                continue;
            }else {
                flag=false;
                break;
            }
        }

        return flag;
    }

    /**
     * 链表翻转
     *
     * @param p 中心位置及后续节点链表
     * @return
     */
    public SinglyNode inverseLinkList(SinglyNode p) {
        SinglyNode pre = null;
        SinglyNode r = head;
        //中间缓存节点
        SinglyNode next = null;

        //链表反转
        while (r != p) {
            next = r.next;
            r.next = pre;
            pre = r;
            r = next;
        }

        r.next = pre;
        //　返回左半部分的中点之前的那个节点
        return r;

    }

    public static void main(String[] args) {
        PalindromeLinkedList linkedList = new PalindromeLinkedList();
        String str = "012343210";
        String[] data = linkedList.strToArray(str);
        for (int i = 0; i < data.length; i++) {
            linkedList.insertTail(data[i]);
        }
        linkedList.printAll();

        if (linkedList.handlePalindrome()) {
            System.out.println("回文");
        } else {
            System.out.println("不是回文");
        }
    }


}
