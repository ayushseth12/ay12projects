package vendingmachinesrc;

import java.util.HashMap;

/**
 * Created by ayushseth on 27/07/17.
 */
public interface SupplierActions {

    public abstract void addProduct(Product product,int quantity,VendingMachine vendingMachine);

    public abstract void changeProductQuantity(Product product,int quantity, VendingMachine vendingMachine);

    public abstract void resetVendingMachine(VendingMachine vendingMachine);

    public abstract int getCashInMachine(VendingMachine vendingMachine);

    public abstract HashMap<VendingMachineStatement,Integer> getStatement(VendingMachine vendingMachine);

    public abstract boolean isVendingMachineOfSupplier(VendingMachine vendingMachine);

    public abstract void addCash(VendingMachine vendingMachine,Coin coin,int quantity);

}


