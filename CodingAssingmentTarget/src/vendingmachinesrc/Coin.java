package vendingmachinesrc;

/**
 * Created by ayushseth on 27/07/17.
 */
public enum Coin {

    FIVE(5) ,TEN(10) , FIFTY(50) ,HUNDRED(100);

    private int denomination;

    Coin(int denomination) {
        this.denomination = denomination;
    }

    public int getDenomination() {
        return denomination;
    }
}
