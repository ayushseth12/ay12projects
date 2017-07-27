package vendingmachinesrc;


import vendingmachinesrc.Supplier;
import vendingmachinesrc.VendingMachine;
import vendingmachinesrc.*;
import vendingmachinesrc.Exceptions.*;
import java.util.*;

/**
 * Created by ayushseth on 27/07/17.
 */
public class VendingMachineImpl extends VendingMachineEntity {



    public Supplier getSupplier() {
        return this.supplier;
    }

    @Override
    public VendingMachineInventory getProductInventory(Supplier supplier) {
        if(supplier.getSupplierId()==this.getSupplier().getSupplierId())
            return this.vendingMachineProductInventory;
        else
            throw new NotAccessibleVendingMachine("Supplier does not own the Vending Machine,Can not get Access");
    }

    @Override
    public VendingMachineInventory getCoinInventory(Supplier supplier) {
        if(supplier.getSupplierId()==this.getSupplier().getSupplierId())
            return this.vendingMachineCoinInventory;
        else
            throw new NotAccessibleVendingMachine("Supplier does not own the Vending Machine,Can not get Access");
    }

    @Override
    public VendingMachineInventory getStatementInventory(Supplier supplier) {
        if(supplier.getSupplierId()==this.getSupplier().getSupplierId())
            return this.vendingMachineCoinInventory;
        else
            throw new NotAccessibleVendingMachine("Supplier does not own the Vending Machine,Can not get Access");
    }


    public VendingMachineImpl(Supplier supplier) {
        this.supplier = supplier;
    }



    @Override
    public int selectProductsAndGetPrice(Map<Product,Integer> productListQuantity) {
        currentProductListquantityMap=productListQuantity;
        Set<Product> set=currentProductListquantityMap.keySet();
        Iterator<Product> it=set.iterator();
        int price=0;
        while (it.hasNext()){
            Product currentProduct=it.next();
            int quantity=currentProductListquantityMap.get(currentProduct);
            if(vendingMachineProductInventory.hasItem(currentProduct) && vendingMachineProductInventory.getQuantity(currentProduct)>=quantity){
                price=price+(currentProduct.getPrice()*quantity);
            }else
                throw new OutOfStockException("Given Products and Quantities are not Available");
        }
        return price;
    }

    @Override
    public void insertCoin(Coin coin) {
        currentBalance=currentBalance+coin.getDenomination();
        vendingMachineCoinInventory.put(coin,1);
    }

    @Override
    public List<Coin> refund() {
        List<Coin> coinList=getCoins(currentBalance);
        updateCashInventory(coinList);
        currentBalance=0;
        currentProductListquantityMap.clear();
        return coinList;
    }

    void updateCashInventory(List<Coin> coins){
        for(Coin c:coins)
            vendingMachineCoinInventory.remove(c);
    }

    List<Coin> getCoins(int amount) throws NoChangeAvailableException{
        List<Coin> coinList= Collections.emptyList();
        if(amount>0){
            coinList=new ArrayList<>();
            int remaining=amount;
            while (remaining>0){
                if(remaining>=0 && vendingMachineCoinInventory.hasItem(Coin.FIVE)){
                        remaining=remaining- Coin.FIVE.getDenomination();
                        coinList.add(Coin.FIVE);
                        continue;
                    }
                    else if(remaining>=0 && vendingMachineCoinInventory.hasItem(Coin.TEN)){
                        remaining=remaining- Coin.TEN.getDenomination();
                        coinList.add(Coin.TEN);
                        continue;
                    }
                    else if(remaining>=0 && vendingMachineCoinInventory.hasItem(Coin.FIFTY)){
                        remaining=remaining- Coin.FIFTY.getDenomination();
                        coinList.add(Coin.FIFTY);
                        continue;
                    }
                    else if(remaining>=0 && vendingMachineCoinInventory.hasItem(Coin.HUNDRED)){
                        remaining=remaining- Coin.HUNDRED.getDenomination();
                        coinList.add(Coin.HUNDRED);
                        continue;
                    }
                    else {

                        throw new NoChangeAvailableException("No change Available at this Machine Try Different Denomination/Product");
                    }
            }
        }
        return coinList;
    }

    @Override
    public int changeQuantitiesAndGetPrice(Product product, int updatedquantity) {

        if(currentProductListquantityMap.containsKey(product))
        {
            currentProductListquantityMap.put(product,updatedquantity);
        }
        Set<Product> set=currentProductListquantityMap.keySet();
        Iterator<Product> it=set.iterator();
        int price=0;
        while (it.hasNext()){
            Product currentProduct=it.next();
            int quantity=currentProductListquantityMap.get(currentProduct);
            if(vendingMachineProductInventory.hasItem(currentProduct) && vendingMachineProductInventory.getQuantity(currentProduct)>=quantity){
                price=price+(currentProduct.getPrice()*quantity);
            }else
                throw new OutOfStockException("Given Products and Quantities are not Available");
        }
        return price;
    }

    @Override
    public void userConfirmation(boolean confirmation) {

        this.userConfirmation=confirmation;

    }

    @Override
    public VendedOutput<Map<Product,Integer>, List<Coin>> collectProductAndChange() {

        if(!userConfirmation){
            System.out.println("Need User Confirmation Please enter True to continue the Transaction");
            return null;
        }
        Map<Product,Integer> productListReturn=collectProducts();
        System.out.println("Product List Return has"+productListReturn.size());
        List<Coin> coinList=collectChange();
        Set<Product> set=currentProductListquantityMap.keySet();
        Iterator<Product> it=set.iterator();
        while (it.hasNext()){
            Product product=it.next();
            VendingMachineStatement vendingMachineStatement=new VendingMachineStatement(product,currentProductListquantityMap.get(product),product.getPrice());
            vendingMachineStatementInventory.put(vendingMachineStatement,1);
            vendingMachineProductInventory.removeItemQuantity(product,currentProductListquantityMap.get(product));
        }
        currentProductListquantityMap.clear();
        VendedOutput<Map<Product,Integer>,List<Coin>> vendedOutput=new VendedOutput<>(productListReturn,coinList);
        userConfirmation=false;
        return vendedOutput;
    }

    List<Coin> collectChange(){
        int balance=currentBalance;
        Set<Product> set=currentProductListquantityMap.keySet();
        Iterator<Product> it=set.iterator();
        while (it.hasNext()) {
            Product product = it.next();
            balance = balance - (product.getPrice()*currentProductListquantityMap.get(product));
        }
        List<Coin> coinList=getCoins(balance);
        updateCashInventory(coinList);
        currentBalance=0;
        return coinList;
    }

    Map<Product,Integer> collectProducts(){
        if(isFullyPaid()){
            if(hasCoinChange()){
                Map<Product,Integer> productReturnList=new HashMap<>();
                Set<Product> set=currentProductListquantityMap.keySet();
                Iterator<Product> iterator=set.iterator();
                while (iterator.hasNext()){
                    Product product=iterator.next();
                    productReturnList.put(product,currentProductListquantityMap.get(product));
                }
                return productReturnList;
            }
            throw new NoChangeAvailableException("Change is Not Available in Vending Machine.Try Different Product.");
        }
        int remainingBalance=currentBalance;
        Set<Product> set=currentProductListquantityMap.keySet();
        Iterator<Product> it=set.iterator();
        while (it.hasNext()) {
            Product product=it.next();
            remainingBalance = remainingBalance -(product.getPrice()*currentProductListquantityMap.get(product));
        }
        throw new NotFullyPaidException("Not enough Money Paid. Balance Remaining is"+Math.abs(remainingBalance));
    }

    boolean isFullyPaid() {
        int balance=currentBalance;
        Set<Product> set=currentProductListquantityMap.keySet();
        Iterator<Product> it=set.iterator();
        while (it.hasNext()) {
            Product product=it.next();
            balance = balance - (product.getPrice()*currentProductListquantityMap.get(product));
        }
        if(balance>=0)
            return true;
        else
            return false;
    }

    boolean hasCoinChange(){
        int balance=currentBalance;
        Set<Product> set=currentProductListquantityMap.keySet();
        Iterator<Product> it=set.iterator();
        while (it.hasNext()){
            Product product=it.next();
            balance=balance-(product.getPrice()*currentProductListquantityMap.get(product));
        }
        try {
            List<Coin> coinList=getCoins(balance);
            if(coinList.isEmpty())
                return false;
            else
                return true;
        }catch (NoChangeAvailableException ex){
            return false;
        }

    }
}
