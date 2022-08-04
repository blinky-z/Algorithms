package me.progbloom.collection.stack.exception;

public class StackOverflowException extends RuntimeException {

    public StackOverflowException() {
        super("Stack overflow");
    }
}
