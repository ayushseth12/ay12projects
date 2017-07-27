package vendingmachinesrc;

/**
 * Created by ayushseth on 27/07/17.
 */
public class VendedOutput<P,C> {

    private P productList;
    private C coinList;

    public VendedOutput(P productList, C coinList) {
        this.productList = productList;
        this.coinList = coinList;
    }

    public P getProductList() {
        return productList;
    }

    public C getCoinList() {
        return coinList;
    }

}
