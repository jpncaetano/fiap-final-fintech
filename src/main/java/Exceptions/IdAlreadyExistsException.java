package Exceptions;

public class IdAlreadyExistsException extends Exception {
    public IdAlreadyExistsException() {
        super("Error: Id is already used!");
    }
}
