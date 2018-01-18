package inventory;

//value object
public class RecordDefinition {

    //public String itemName;
    public double boughtAt;
    public double soldAt;
    public int availableQty;
    
    
    public RecordDefinition(double boughtAt, double soldAt) {
		super();
		this.boughtAt = boughtAt;
		this.soldAt = soldAt;
	}
    
	public RecordDefinition(double boughtAt, double soldAt, int availableQty) {
		super();
		this.boughtAt = boughtAt;
		this.soldAt = soldAt;
		this.availableQty = availableQty;
	}
/*	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
*/	public double getBoughtAt() {
		return boughtAt;
	}
	public void setBoughtAt(double boughtAt) {
		this.boughtAt = boughtAt;
	}
	public double getSoldAt() {
		return soldAt;
	}
	public void setSoldAt(double soldAt) {
		this.soldAt = soldAt;
	}
	public int getAvailableQty() {
		return availableQty;
	}
	public void setAvailableQty(int availableQty) {
		this.availableQty = availableQty;
	}
    
}
