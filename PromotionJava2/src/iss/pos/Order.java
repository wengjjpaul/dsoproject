package iss.pos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Order {
	private List<OrderItem> items;
	
	private List<OrderItem> items_style;
	
	private double discountValue = 0.0;

	public Order() {
		items = new ArrayList<OrderItem>();
		items_style = new ArrayList<OrderItem>();
	}

	public void add(Product prod, int qty) {

		OrderItem oi = getOrderItem(prod.getSku());
		if (oi == null) {
			oi = new OrderItem();
			oi.setProduct(prod);
			oi.setQuantity(qty);
			items.add(oi);			
			this.sortBySKU();
		} else {
			oi.setQuantity(oi.getQuantity() + qty);
		}

		OrderItem ois = getOrderItemByStyle(prod.style());
		if (ois == null) {
			ois = new OrderItem();
			ois.setProduct(prod);
			ois.setQuantity(qty);
			items_style.add(ois);
			//this.sortByStyle();
		} else {
			ois.setQuantity(ois.getQuantity() + qty);
		}

	}
	
	public void printOrder() {
		Iterator<OrderItem> itr = items.iterator();

		while (itr.hasNext()) {
			OrderItem oi = itr.next();

			System.out.println(oi.getProduct().getName() + " " + oi.getProduct().getPrice() + " " + oi.getQuantity());
		}

	}
	
	public OrderItem getOrderItem(String sku) {
		Iterator<OrderItem> itr = items.iterator();

		while (itr.hasNext()) {
			OrderItem oi = itr.next();

			if (oi.getProduct().getSku() == sku) {
				return oi;
			}
		}
		return null;
	}

	public OrderItem getOrderItemByStyle(String style) {
		Iterator<OrderItem> itr = items_style.iterator();

		while (itr.hasNext()) {
			OrderItem oi = itr.next();

			if (oi.getProduct().style().equalsIgnoreCase(style)) {
				return oi;
			}
		}
		return null;
	}

	public void sortBySKU() {
		items.sort(new Comparator<OrderItem>() {

			@Override
			public int compare(OrderItem item1, OrderItem item2) {
				return item1.getProduct().getSku()
						.compareToIgnoreCase(item2.getProduct().getSku());
			}

		});
	}

//	public void sortByStyle() {
//		items.sort(new Comparator<OrderItem>() {
//
//			@Override
//			public int compare(OrderItem item1, OrderItem item2) {
//				return item1.getProduct().style()
//						.compareToIgnoreCase(item2.getProduct().style());
//			}
//
//		});
//	}

	public double getTotalPrice() {
		double total = 0;
		Iterator<OrderItem> itr = items.iterator();

		while (itr.hasNext()) {
			OrderItem oi = itr.next();

			total += oi.getQuantity() * oi.getProduct().getPrice();
		}

		return total;
	}

	public List<OrderItem> getOrderItems() {
		return this.items;
	}

	public List<OrderItem> getStyleOrderItems() {
		return this.items_style;
	}

	public double getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(double discountValue) {
		this.discountValue = discountValue;
	}

	public double getDiscountedPrice() {
		return getTotalPrice() - this.discountValue;
	}
}
