package vendingmachinesrc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by ayushseth on 27/07/17.
 */
public class VendingMachineInventory<T> {

    private HashMap<T,Integer> inventoryHashMap=new HashMap<T, Integer>();

    public int getQuantity(T item){
        return inventoryHashMap.getOrDefault(item,0);
    }

    public HashMap<T,Integer> getInventoryHashMap(){
        return this.inventoryHashMap;
    }

    public void add(T item){
        if(inventoryHashMap.get(item)!=null) {
            int count = inventoryHashMap.get(item);
            inventoryHashMap.put(item, count + 1);
        }else
         this.put(item,1);
    }

    public void remove(T item){
        int count=inventoryHashMap.get(item);
        inventoryHashMap.put(item,count-1);
    }

    public void removeItemQuantity(T item,int quantity){
        int count=inventoryHashMap.get(item);
        inventoryHashMap.put(item,count-quantity);
    }

    public boolean hasItem(T item){
        return getQuantity(item)>0;
    }

    public void clear(){
        inventoryHashMap.clear();
    }

    public void put(T item,int quantity){
        inventoryHashMap.put(item,quantity);
    }

    public int getAllQuantity(){
        int quantity=0;
        Set set=inventoryHashMap.keySet();
        Iterator iterator=set.iterator();
        while (iterator.hasNext())
        {
            quantity=quantity+(inventoryHashMap.get(iterator.next()));
        }
        return quantity;
    }
}
