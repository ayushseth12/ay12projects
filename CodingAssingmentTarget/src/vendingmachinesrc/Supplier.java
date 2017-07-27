package vendingmachinesrc;

/**
 * Created by ayushseth on 27/07/17.
 */
public abstract class Supplier implements SupplierActions{

    private String supplierName;
    private int supplierId;

    public Supplier(String supplierName, int supplierId) {
        this.supplierName = supplierName;
        this.supplierId = supplierId;

    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

}
