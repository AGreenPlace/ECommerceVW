package model;

/**
 * Created by Andrea on 27/05/15.
 */
public class InvalidPasswordException extends Exception {
    public InvalidPasswordException(){
        super();
    }

    public InvalidPasswordException(String message){
        super(message);
    }
}
