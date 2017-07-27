package vendingmachinesrc.test;

import vendingmachinesrc.Coin;
import vendingmachinesrc.Product;
import vendingmachinesrc.VendedOutput;
import vendingmachinesrc.VendingMachineImpl;
import vendingmachinesrc.impl.products.Chips;
import vendingmachinesrc.impl.suppliers.SupplierABC;

import java.util.*;

/**
 * Created by ayushseth on 27/07/17.
 */
public class VendingMachineTest {

    public static void main(String[] args){

        SupplierABC supplier1=new SupplierABC("ABC",1);
        VendingMachineImpl vendingMachine=new VendingMachineImpl(supplier1);
        Product product1=new Chips("Lays",20);
        Product product2=new Chips("Pepsi",30);
        Product product3=new Chips("Coke",50);
        Product product4=new Chips("Juice",25);
        Product product5=new Chips("Water",10);

        supplier1.addProduct(product2,30,vendingMachine);
        supplier1.addProduct(product1,20,vendingMachine);
        supplier1.addProduct(product3,25,vendingMachine);
        supplier1.addProduct(product4,40,vendingMachine);
        supplier1.addProduct(product5,10,vendingMachine);

        supplier1.addCash(vendingMachine, Coin.FIFTY,50);
        supplier1.addCash(vendingMachine, Coin.HUNDRED,30);
        supplier1.addCash(vendingMachine, Coin.FIVE,100);
        supplier1.addCash(vendingMachine, Coin.TEN,500);

        //Selecting Items for Purchase.

        Map<Product,Integer> purchaseList=new HashMap<>();
        purchaseList.put(product1,5);
        purchaseList.put(product3,1);
        purchaseList.put(product5,4);
        int price=vendingMachine.selectProductsAndGetPrice(purchaseList);
        System.out.println("The selected Products Prices are "+price);

        supplier1.resetVendingMachine(vendingMachine);

        try {
            int price1 = vendingMachine.selectProductsAndGetPrice(purchaseList);
            System.out.println("The selected Products Prices are " + price);
        }catch (Exception e){
            System.out.println("Error");

        }
        supplier1.addProduct(product2,30,vendingMachine);
        supplier1.addProduct(product1,20,vendingMachine);
        supplier1.addProduct(product3,25,vendingMachine);
        supplier1.addProduct(product4,40,vendingMachine);
        supplier1.addProduct(product5,10,vendingMachine);

        supplier1.addCash(vendingMachine, Coin.FIFTY,50);
        supplier1.addCash(vendingMachine, Coin.HUNDRED,30);
        supplier1.addCash(vendingMachine, Coin.FIVE,100);
        supplier1.addCash(vendingMachine, Coin.TEN,500);

        price=vendingMachine.selectProductsAndGetPrice(purchaseList);

        System.out.println("The selected Products Prices are "+price);

        vendingMachine.insertCoin(Coin.FIFTY);
        vendingMachine.insertCoin(Coin.FIFTY);
        vendingMachine.insertCoin(Coin.HUNDRED);
        vendingMachine.insertCoin(Coin.HUNDRED);

        try {
            VendedOutput<Map<Product, Integer>, List<Coin>> vendedOutput = vendingMachine.collectProductAndChange();
            List<Coin> coinList=vendedOutput.getCoinList();
            Map<Product,Integer> productList=vendedOutput.getProductList();
            System.out.println("The Products size are :"+productList.size());
            Set<Product> productSet=productList.keySet();
            Iterator<Product> iterator=productSet.iterator();
            while (iterator.hasNext()){
                Product product=iterator.next();
                System.out.println("Product is = "+product.getName() +" and Quantity is = "+productList.get(product));
            }

            int balance=0;
            for(Coin coin:coinList)
                balance=balance+coin.getDenomination();
            System.out.println("The Money Returned is ="+balance);

        }catch (Exception e){

        }

        vendingMachine.userConfirmation(true);


        try {
            VendedOutput<Map<Product, Integer>, List<Coin>> vendedOutput = vendingMachine.collectProductAndChange();
            List<Coin> coinList=vendedOutput.getCoinList();
            Map<Product,Integer> productList=vendedOutput.getProductList();
            System.out.println("The Products size are :"+productList.size());
            Set<Product> productSet=productList.keySet();
            Iterator<Product> iterator=productSet.iterator();
            while (iterator.hasNext()){
                Product product=iterator.next();
                System.out.println("Product is = "+product.getName() +" and Quantity is = "+productList.get(product));
            }

            int balance=0;
            for(Coin coin:coinList)
                balance=balance+coin.getDenomination();
            System.out.println("The Money Returned is ="+balance);

        }catch (Exception e){

        }



    }


}
