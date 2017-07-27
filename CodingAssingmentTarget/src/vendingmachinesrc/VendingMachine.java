package vendingmachinesrc;

import java.util.List;
import java.util.Map;

/**
 * Created by ayushseth on 27/07/17.
 *
 * An interface for different types of Vending Machines.
 * There can be Different Types of Vending Machines such as
 * Coke Vending Machine , Chocolate Vending Machines etc.
 *
 *
 */
public interface VendingMachine {


        public Supplier getSupplier();

        public VendingMachineInventory getProductInventory(Supplier supplier);

        public VendingMachineInventory getCoinInventory(Supplier supplier);

        public VendingMachineInventory getStatementInventory(Supplier supplier);

        public int selectProductsAndGetPrice(Map<Product,Integer> productList);

        public void insertCoin(Coin coin);

        public List<Coin> refund();

        public int changeQuantitiesAndGetPrice(Product product,int Quantity);

        public void userConfirmation(boolean confirmation);

        public  VendedOutput<Map<Product,Integer>,List<Coin>> collectProductAndChange();



}
