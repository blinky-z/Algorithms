package me.progbloom.datastruct.stack;

public class StackOverflowException extends RuntimeException {

    public StackOverflowException() {
        super("Stack overflow");
    }
}
