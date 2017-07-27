package vendingmachinesrc.Exceptions;

/**
 * Created by ayushseth on 27/07/17.
 */
public class NotAccessibleVendingMachine extends RuntimeException {

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public NotAccessibleVendingMachine(String message) {
        this.message = message;
    }
}
