package model.container;

import model.stmt.IStmt;

import java.util.Stack;

public interface IStack<T> {
    T pop();

    void push(T v);

    boolean isEmpty();

    IStack<T> clone();

    Stack<T> getContent();
}
