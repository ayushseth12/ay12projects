package vendingmachinesrc.impl.suppliers;

import vendingmachinesrc.Supplier;
import vendingmachinesrc.*;

import java.util.HashMap;

/**
 * Created by ayushseth on 27/07/17.
 */
public class SupplierABC extends Supplier {

    public SupplierABC(String supplierName, int supplierId) {
        super(supplierName, supplierId);
    }

    @Override
    public void addProduct(Product product, int quantity, VendingMachine vendingMachine) {
          VendingMachineInventory vendingMachineInventory=vendingMachine.getProductInventory(this);
          vendingMachineInventory.put(product,quantity);
    }

    @Override
    public void changeProductQuantity(Product product, int quantity, VendingMachine vendingMachine) {
        VendingMachineInventory vendingMachineInventory=vendingMachine.getProductInventory(this);
        vendingMachineInventory.put(product,quantity);
    }

    @Override
    public void resetVendingMachine(VendingMachine vendingMachine) {
        VendingMachineInventory vendingMachineInventory;
        VendingMachineInventory vendingMachineCashInventory;
        VendingMachineInventory vendingMachineStatementInventory;
        vendingMachineInventory=vendingMachine.getProductInventory(this);
        vendingMachineCashInventory=vendingMachine.getCoinInventory(this);
        vendingMachineStatementInventory=vendingMachine.getStatementInventory(this);
        vendingMachineInventory.clear();
        vendingMachineCashInventory.clear();
        vendingMachineStatementInventory.clear();
    }

    @Override
    public int getCashInMachine(VendingMachine vendingMachine) {
        VendingMachineInventory vendingMachineInventory;
        vendingMachineInventory=vendingMachine.getCoinInventory(this);
        int allCash=vendingMachineInventory.getAllQuantity();
        vendingMachineInventory.clear();
        return allCash;
    }

    @Override
    public HashMap<VendingMachineStatement,Integer> getStatement(VendingMachine vendingMachine) {
        VendingMachineInventory vendingMachineInventory;
        vendingMachineInventory= vendingMachine.getStatementInventory(this);
        return vendingMachineInventory.getInventoryHashMap();
    }

    @Override
    public boolean isVendingMachineOfSupplier(VendingMachine vendingMachine) {
        return vendingMachine.getSupplier().getSupplierId()==this.getSupplierId();
    }

    @Override
    public void addCash(VendingMachine vendingMachine, Coin coin, int quantity) {
        VendingMachineInventory vendingMachineInventory;
        vendingMachineInventory=vendingMachine.getCoinInventory(this);
        vendingMachineInventory.put(coin,quantity);
    }


}
