package vendingmachinesrc;

/**
 * Created by ayushseth on 27/07/17.
 */
public class VendingMachineStatement {

    Product product;
    int quantity;
    int cost;

    public VendingMachineStatement(Product product, int quantity, int cost) {
        this.product = product;
        this.quantity = quantity;
        this.cost = cost;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCost() {
        return cost;
    }
}
