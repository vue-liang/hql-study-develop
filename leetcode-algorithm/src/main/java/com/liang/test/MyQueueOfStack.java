package com.liang.test;

import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

@Slf4j
public class MyQueueOfStack<T> {
    private final Stack<T> leftStack; // 入栈
    private final Stack<T> rightStack;// 出栈
    private int size;
    private final int capacity;
    public MyQueueOfStack(int capacity) {
        this.capacity = capacity;
        this.leftStack = new Stack<>();
        this.rightStack = new Stack<>();
    }
    public int size() {
        return size;
    }
    public void push(T t) {
        if (size == capacity) {
            log.info("队列已满");
        }
        size++;
        leftStack.push(t);
    }
    public T pop() {
        if (rightStack.isEmpty()&&leftStack.isEmpty()) {
            log.info("队列为空");
            return null;
        }
        if (rightStack.isEmpty()) {
            while (!leftStack.isEmpty()) {
                rightStack.push(leftStack.pop());
            }
        }
        size--;
        return rightStack.pop();
    }
    public T peek() {
        if (rightStack.isEmpty()&&leftStack.isEmpty()) {
            log.info("队列为空");
            return null;
        }
        if (rightStack.isEmpty()) {
            while (!leftStack.isEmpty()) {
                rightStack.push(leftStack.pop());
            }
        }
        return rightStack.peek();
    }
    public String toString(){
        return leftStack +","+ rightStack;
    }
}
@Slf4j
class TestQueue {
    public static void main(String[] args) {
        MyQueueOfStack<Integer> queue = new MyQueueOfStack<>(6);
        for (int i = 1; i <= 7; i++) {
            queue.push(i);
        }
        for (int i = 0; i < 3; i++) {
            log.info("出队:{}",queue.pop());
        }
        System.out.println(queue);
    }
}
