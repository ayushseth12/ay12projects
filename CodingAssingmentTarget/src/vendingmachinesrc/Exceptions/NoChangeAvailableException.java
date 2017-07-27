package vendingmachinesrc.Exceptions;

/**
 * Created by ayushseth on 27/07/17.
 */
public class NoChangeAvailableException extends RuntimeException {

    private String message;


    public NoChangeAvailableException(String message) {
        this.message = message;

    }

    @Override
    public String getMessage() {
        return message;
    }

}
