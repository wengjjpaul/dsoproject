package iss.pos.promotion;

public class BulkPromotion extends Promotion {

	protected int minQuantity = 3;
	protected double price = 0.0;
		
	public int getMinQuantity() {
		return minQuantity;
	}
	public void setMinQuantity(int minQuantity) {
		this.minQuantity = minQuantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
