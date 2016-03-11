package iss.pos.console;

import iss.pos.Order;
import iss.pos.Product;
import iss.pos.promotion.DiscountCalculator;
import iss.pos.promotion.Promotion;
import iss.pos.promotion.PromotionOne;
import iss.pos.promotion.PromotionThree;
import iss.pos.promotion.PromotionTwo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MyConsole {

	static HashMap<String, Product> products = new HashMap<String, Product>();

	public static String INVISIBLESOCKSSKU = "2001.3";

	static {
		products.put("redDress", new Product("1001.1", "Red Dress", 100.0, "1001"));
		products.put("greenDress", new Product("1001.2", "Green Dress", 100.0, "1001"));
		products.put("blueDress", new Product("1001.3", "Blue Dress", 100.0, "1001"));
		products.put("whiteSocks", new Product("2001.1", "White Socks", 10.0, "2001"));
		products.put("redSocks", new Product("2001.2", "Red Socks", 10.0, "2001"));
		products.put("invisibleSocks", new Product(INVISIBLESOCKSSKU,
				"Invisible Socks", 15.0, "2001"));
	}

	public static Product GetProduct(String name) {
		return products.get(name);
	}

	public static void PrintAllProducts() {
		System.out.println("Welcome. Items we have:");
		System.out.println("1. red dress. $100");
		System.out.println("2. green dress. $100");
		System.out.println("3. blue dress. $100");
		System.out.println("4. white socks. $10");
		System.out.println("5. red socks. $10");
		System.out.println("6. invisible socks. $15");
		System.out.println("Command option: ");
		System.out.println("-buy <item number> <qty>");
		System.out.println("-p <promotion id>");
		System.out.println("-c");

	}

	public static void main(String[] args) {

		Order order = new Order();
		List<Promotion> promotions = new ArrayList<Promotion>();
		promotions.add(new PromotionOne());
		promotions.add(new PromotionTwo());
		promotions.add(new PromotionThree());
		Order discountedOrder = null;

		while (true) {

			PrintAllProducts();
			Scanner sc = new Scanner(System.in);
			String inputString = sc.nextLine();
			System.out.println(inputString);
			StringTokenizer st = new StringTokenizer(inputString, " ");
			//try {
				//Runtime.getRuntime().exec("cls");
//			} catch (IOException e1) {
				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}	 
			while (st.hasMoreElements()) {
				String firstElement = st.nextElement().toString();
				if (firstElement.equals("-buy")) {
					// Get item number
					if (st.hasMoreElements()) {
						try {
							int itemNumber = Integer.parseInt((String) st.nextElement());
							System.out.println("itemNumber="+itemNumber);
							int itemQuantity = Integer.parseInt((String) st.nextElement());
							System.out.println("itemQuantity="+itemQuantity);
							Product p = createProduct(itemNumber);
							order.add(p, itemQuantity);
							System.out.println("After add... print details....");
							order.printOrder();
							System.out.println("end of print");
						} catch (Exception e) {
							
							System.out.println("Invalid item number or quantity. Hit enter to continue..");
							System.out.println(e.toString());
							Scanner sc2 = new Scanner(System.in);
							break;
						}
					}
				} else if (firstElement.equals("-p")) {

					// Get item number
					if (st.hasMoreElements()) {
						try {
							int promotionId = Integer.parseInt((String) st.nextElement());
							
							DiscountCalculator dc = new DiscountCalculator(
									promotions.get(promotionId - 1));
							discountedOrder = dc.calculateDiscount(order);
							
							System.out.println("After applying promo... print details....");
							discountedOrder.printOrder();
							System.out.println("discounted price="+discountedOrder.getDiscountedPrice());
							System.out.println("end of print");
						} catch (Exception e) {
							System.out.println("Invalid promotion id");
							break;
						}

					}
				}else if (firstElement.equals("-c")) {
						if(discountedOrder!=null)
						{
							discountedOrder.printOrder();
							System.out.println("Total price="+discountedOrder.getDiscountedPrice());
							order = new Order();
							discountedOrder = null;
						}else{
							System.out.println("Empty cart. Cannot check out...");
						}
					}
				}

			
		}
	}

	private static Product createProduct(int itemNumber) {
		Product p;
		switch (itemNumber) {
		case (1):
			p = new Product("1001.1", "Red Dress", 100.0, "1001");
			break;
		case (2):
			p = new Product("1001.2", "Green Dress", 100.0, "1001");
			break;
		case (3):
			p = new Product("1001.3", "Blue Dress", 100.0, "1001");
			break;
		case (4):
			p = new Product("2001.1", "White Socks", 10.0, "2001");
			break;
		case (5):
			p = new Product("2001.2", "Red Socks", 10.0, "2001");
			break;
		case (6):
			p = new Product(INVISIBLESOCKSSKU, "Invisible Socks", 15.0, "2001");
			break;
		default:
			return null;
		}
		return p;
	}
}
