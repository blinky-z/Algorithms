package me.progbloom.datastruct;

public class StackOverflowException extends RuntimeException {

    public StackOverflowException() {
        super("Stack overflow");
    }
}
