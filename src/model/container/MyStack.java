package model.container;

import java.util.HashMap;
import java.util.Stack;

public class MyStack<T> implements IStack<T> {
    Stack<T> stack;

    public MyStack() {
        stack = new Stack<T>();
    }

    public MyStack(MyStack<T> other) {
        stack = (Stack<T>) other.stack.clone();

    }

    public MyStack(IStack<T> other) {
        stack = other.getContent();

    }

    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public void push(T v) {
        stack.push(v);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        return stack.toString();
    }

    public MyStack<T> clone() {
        return new MyStack<T>(this);
    }

    @Override
    public Stack<T> getContent() {
        return stack;
    }
}
