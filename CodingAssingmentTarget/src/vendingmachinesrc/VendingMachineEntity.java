package vendingmachinesrc;

import java.util.Map;

/**
 * Created by ayushseth on 28/07/17.
 */
public abstract class VendingMachineEntity implements VendingMachine{

     Supplier supplier;

     VendingMachineInventory<Product> vendingMachineProductInventory =new VendingMachineInventory<>();

     VendingMachineInventory<Coin> vendingMachineCoinInventory=new VendingMachineInventory<>();

     VendingMachineInventory<VendingMachineStatement> vendingMachineStatementInventory =new VendingMachineInventory<>();

     int currentBalance;

     Map<Product,Integer> currentProductListquantityMap;

     boolean userConfirmation=false;

    }



