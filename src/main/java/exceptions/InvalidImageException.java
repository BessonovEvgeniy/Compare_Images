package exceptions;

public class InvalidImageException extends Exception {

    public InvalidImageException() {}

    public InvalidImageException(String msg){
        super(msg);
    }
}
