package iss.pos;

public class Product {
	private String name;
	private String sku;
	private String style;
	private double price;
	
	public Product(String sku, String name, double price){
		this.name = name;
		this.sku = sku;
		this.price = price;
	}
	
	public Product(String sku, String name, double price, String style){
		this.name = name;
		this.sku = sku;
		this.price = price;
		this.style = style;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String style() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	
	
}