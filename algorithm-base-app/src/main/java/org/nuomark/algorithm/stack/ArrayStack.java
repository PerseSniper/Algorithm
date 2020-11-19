package org.nuomark.algorithm.stack;

/**
 * @program: algorithm-base-app
 * @description: 数组实现栈称之为顺序栈
 * 栈的特性：
 * 先进后出，后进先出
 * 入栈和出栈时间复杂度O(1)
 * @author: NuoMark
 * @create: 2020-11-19 12:43
 **/
public class ArrayStack {
    //数组存储元素
    private  String[] iteams;
    //记录当前存储的元素个数
    private  int count;
    //总容量
    private  int  n;
    //默认容量值
    private static final Integer DEFAULT_CAPACITY = 10;

    public ArrayStack() {
        this.n = DEFAULT_CAPACITY;
        this.iteams=new String[n];
        this.count=0;
    }

    public ArrayStack(int n) {
        this.n = n;
        this.iteams=new String[n];
        this.count=0;
    }

    /**
     * 入栈操作
     * @param value
     * @return
     */
    public boolean push(String value){
        if (count==n){
            return false;
        }

        iteams[count]=value;
        ++count;
        return true;
    }

    /**
     * 出栈操作
     * @return
     */
    public  String pop(){
        if (count == 0){
            return null;
        }
        String tmp=iteams[count-1];
        --count;
        return tmp;
    }

    public static void main(String[] args) {
        ArrayStack stack=new ArrayStack(5);
        //测试空
        System.out.println(stack.pop());
        stack.push("1");
        //测试一个元素
        System.out.println(stack.pop());
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        stack.push("5");
        stack.push("6");
        System.out.println( stack.push("6"));
        System.out.println(stack.pop());
    }
}
