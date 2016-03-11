package iss.pos.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import iss.pos.*;
import iss.pos.promotion.*;

public class DiscountTest {

	private List<Promotion> promotions = null;
	HashMap<String, Product> products = new HashMap<String, Product>();

	
	@Before
	public void setUp() throws Exception {
		promotions = new ArrayList<Promotion>();
		promotions.add(new PromotionOne());
		promotions.add(new PromotionTwo());

	}

	@Test
	// 1A
	public void testCase1ItemsDiffSKUDiffStyleAllPromo() {
		// sky, name, price, style
		products.put("redDress", new Product("1001.1", "Red Dress", 100.0, "1"));

		for (Promotion promo : promotions) {

			DiscountCalculator dc = new DiscountCalculator(promo);

			Order o = new Order();

			o.add(products.get("redDress"), 1);

			Order newOrder = dc.calculateDiscount(o);

			assertEquals(100.0, newOrder.getTotalPrice(), 0.001);
		}
	}

	@Test
	// 1B
	public void testCase2ItemsSameSKUPromoOne() {
		// sky, name, price, style
		products.put("redDress", new Product("1001.1", "Red Dress", 100.0, "1"));

		Promotion promo = new PromotionOne();

		Order o = new Order();

		o.add(products.get("redDress"), 2);

		DiscountCalculator dc = new DiscountCalculator(promo);

		Order newOrder = dc.calculateDiscount(o);

		assertEquals(170.0, newOrder.getDiscountedPrice(), 0.001);
	}

	@Test
	// 1C
	public void testCase3() {
		products.put("redDress", new Product("1001.1", "Red Dress", 100.0, "1"));
		products.put("greenDress", new Product("1001.2", "Green Dress", 100.0,
				"1"));

		Promotion promo = new PromotionTwo();

		Order o = new Order();

		o.add(products.get("redDress"), 1);
		o.add(products.get("greenDress"), 1);

		DiscountCalculator dc = new DiscountCalculator(promo);

		Order newOrder = dc.calculateDiscount(o);

		assertEquals(150.0, newOrder.getDiscountedPrice(), 0.001);

	}

	@Test
	// 1Da
	public void testCaseEvenItemsPromoOne() {
		products.put("redDress", new Product("1001.1", "Red Dress", 100.0, "1"));

		Promotion promo = new PromotionOne();
		DiscountCalculator dc = new DiscountCalculator(promo);

		int N = 10;

		for (int i = 2; i <= N; i += 2) {
			Order o = new Order();
			o.add(products.get("redDress"), i);

			Order newOrder = dc.calculateDiscount(o);

			int num = (int) Math.floor(i / 2.0);

			assertEquals((100.0 * i) - (100.0 * num * 0.3),
					newOrder.getDiscountedPrice(), 0.001);
		}

	}

	@Test
	// 1Db
	public void testCaseOddItemsPromoOne() {
		products.put("redDress", new Product("1001.1", "Red Dress", 100.0, "1"));

		Promotion promo = new PromotionOne();
		DiscountCalculator dc = new DiscountCalculator(promo);

		int N = 9;

		for (int i = 1; i <= N; i += 2) {
			Order o = new Order();
			o.add(products.get("redDress"), i);

			Order newOrder = dc.calculateDiscount(o);

			int num = (int) Math.floor(i / 2.0);

			assertEquals((100.0 * i) - (100.0 * num * 0.3),
					newOrder.getDiscountedPrice(), 0.001);
		}
	}

	@Test
	// 1Dc
	public void testCaseEventemsPromoTwo() {
		products.put("redDress", new Product("1001.1", "Red Dress", 100.0, "1"));
		products.put("greenDress", new Product("1001.2", "Green Dress", 100.0,
				"1"));

		Promotion promo = new PromotionTwo();
		DiscountCalculator dc = new DiscountCalculator(promo);

		int N = 10;

		for (int i = 1; i <= N; i++) {
			Order o = new Order();
			o.add(products.get("redDress"), i);
			o.add(products.get("greenDress"), i);

			Order newOrder = dc.calculateDiscount(o);

			int num = (int) Math.floor((i * 2) / 2.0);

			assertEquals(o.getTotalPrice() - (100.0 * num * 0.5),
					newOrder.getDiscountedPrice(), 0.001);
		}

	}

	@Test
	// 1Dd
	public void testCasOddtemsPromoTwo() {
		products.put("redDress", new Product("1001.1", "Red Dress", 100.0, "1"));
		products.put("greenDress", new Product("1001.2", "Green Dress", 100.0,
				"1"));
		products.put("a", new Product("1001.3", "Green Dress", 100.0, "1"));

		Promotion promo = new PromotionTwo();
		DiscountCalculator dc = new DiscountCalculator(promo);

		int N = 9;

		for (int i = 1; i <= N; i++) {
			Order o = new Order();
			o.add(products.get("redDress"), i);
			o.add(products.get("greenDress"), i);
			o.add(products.get("a"), i);

			Order newOrder = dc.calculateDiscount(o);

			int num = (int) Math.floor((i * 3) / 2.0); // number of items with
														// discount
			double dVal = (100.0 * num * 0.5);

			assertEquals(o.getTotalPrice() - dVal,
					newOrder.getDiscountedPrice(), 0.001);
		}

	}

	@Test
	// 2a
	public void testCase1ItemsSameSKUPromoThree() {
		// sky, name, price, style
		products.put("redDress", new Product("1001.1", "Red Dress", 100.0, "1"));

		Promotion promo = new PromotionThree();

		Order o = new Order();

		o.add(products.get("redDress"), 1);

		DiscountCalculator dc = new DiscountCalculator(promo);

		Order newOrder = dc.calculateDiscount(o);

		assertEquals(90.0, newOrder.getDiscountedPrice(), 0.001);
	}

	@Test
	// 2a
	public void testCase2ItemsSameSKUPromoThree() {
		// sky, name, price, style
		products.put("redDress", new Product("1001.1", "Red Dress", 100.0, "1"));
		products.put("greenDress", new Product("1001.2", "Green Dress", 100.0,
				"1"));

		Promotion promo = new PromotionThree();

		Order o = new Order();

		o.add(products.get("redDress"), 1);
		o.add(products.get("greenDress"), 1);

		DiscountCalculator dc = new DiscountCalculator(promo);

		Order newOrder = dc.calculateDiscount(o);

		assertEquals(160.0, newOrder.getDiscountedPrice(), 0.001);
	}

	@Test
	// 2b,c
	public void testCaseNItemsSameSKUPromoThree() {
		products.put("redDress", new Product("1001.1", "Red Dress", 100.0, "1"));
		products.put("greenDress", new Product("1001.2", "Green Dress", 100.0,
				"1"));

		Promotion promo = new PromotionThree();
		DiscountCalculator dc = new DiscountCalculator(promo);

		int N = 100;

		for (int i = 1; i <= N; i++) {
			Order o = new Order();
			o.add(products.get("redDress"), i);
			o.add(products.get("greenDress"), i);

			Order newOrder = dc.calculateDiscount(o);

			assertEquals(o.getTotalPrice() * 0.8,
					newOrder.getDiscountedPrice(), 0.001);
		}

	}

	@Test
	// 2d
	public void testCase1ItemsDiffSKUDiffStylePromoThree() {
		// sky, name, price, style
		products.put("redDress", new Product("1001.1", "Red Dress", 100.0, "1"));
		products.put("greenDress", new Product("1001.2", "Green Dress", 50.0,
				"2"));

		Promotion promo = new PromotionThree();

		Order o = new Order();

		o.add(products.get("redDress"), 1);
		o.add(products.get("greenDress"), 1);

		DiscountCalculator dc = new DiscountCalculator(promo);

		Order newOrder = dc.calculateDiscount(o);

		assertEquals(135.0, newOrder.getDiscountedPrice(), 0.001);
	}

	@Test
	// 2E
	public void testCaseMixBagPromoThree() {
		// sky, name, price, style
		products.put("redDress", new Product("1001.1", "Red Dress", 100.0, "1"));
		products.put("greenDress", new Product("1001.2", "Green Dress", 50.0,
				"2"));

		Promotion promo = new PromotionThree();

		Order o = new Order();

		o.add(products.get("redDress"), 2);
		o.add(products.get("greenDress"), 1);

		DiscountCalculator dc = new DiscountCalculator(promo);

		Order newOrder = dc.calculateDiscount(o);

		assertEquals(205.0, newOrder.getDiscountedPrice(), 0.001);

	}

	@Test
	// 3E
	public void testCaseBuyLessThanMinQuantityBulkPromotion() {
		// sky, name, price, style
		products.put("socks", new Product("1001.1", "socks", 15.0, "1"));

		Promotion promo = new PromotionFour();

		Order o = new Order();

		o.add(products.get("socks"), 1);

		DiscountCalculator dc = new DiscountCalculator(promo);

		Order newOrder = dc.calculateDiscount(o);

		assertEquals(15.0, newOrder.getDiscountedPrice(), 0.001);

	}

	@Test
	// 3E
	public void testCaseBuy3QuantityBulkPromotion() {
		// sky, name, price, style
		products.put("socks", new Product("1001.1", "socks", 15.0, "1"));

		Promotion promo = new PromotionFour();

		Order o = new Order();

		o.add(products.get("socks"), 3);

		DiscountCalculator dc = new DiscountCalculator(promo);

		Order newOrder = dc.calculateDiscount(o);

		assertEquals(25.0, newOrder.getDiscountedPrice(), 0.001);

	}

	@Test
	// 3Da
	public void testCaseBuyNotModular3QuantityBulkPromotion() {
		// sky, name, price, style
		products.put("socks", new Product("1001.1", "socks", 15.0, "1"));

		Promotion promo = new PromotionFour();
		int N = 10;
		for (int i = 2; i < N; i += 2) {
			Order o = new Order();

			o.add(products.get("socks"), i);

			DiscountCalculator dc = new DiscountCalculator(promo);

			Order newOrder = dc.calculateDiscount(o);

			int num = (int) Math.floor(i/3);

			double originalPrice = num*3*15.0;
			double dVal = originalPrice - ( num * 25.0);
			
			assertEquals(o.getTotalPrice()-  dVal, newOrder.getDiscountedPrice(), 0.001);
		}

	}
	
	
	@Test
	// 3Ea
	public void testCaseBuy3SocksAndOtherItemsBulkPromotion() {
		// sky, name, price, style
		products.put("socks", new Product("1001.1", "socks", 15.0, "1"));
		products.put("redDress", new Product("1002.1", "Red Dress", 100.0, "1"));

		Promotion promo = new PromotionFour();
		
		Order o = new Order();

		o.add(products.get("socks"), 3);
		o.add(products.get("redDress"),2);
		DiscountCalculator dc = new DiscountCalculator(promo);

		Order newOrder = dc.calculateDiscount(o);

			
			
		assertEquals(225.0, newOrder.getDiscountedPrice(), 0.001);
		

	}
	
	@Test
	// 3Eb
	public void testCaseBuy3SocksAnd2OtherDiffItemsBulkPromotion() {
		// sky, name, price, style
		products.put("socks", new Product("1001.1", "socks", 15.0, "1"));
		products.put("redDress", new Product("1002.1", "Red Dress", 100.0, "1"));
		products.put("blueDress", new Product("1003.1", "Blue Dress", 200.0, "1"));
		
		Promotion promo = new PromotionFour();
		
		Order o = new Order();

		o.add(products.get("socks"), 3);
		o.add(products.get("redDress"),1);
		o.add(products.get("blueDress"),1);
		DiscountCalculator dc = new DiscountCalculator(promo);

		Order newOrder = dc.calculateDiscount(o);

			
			
		assertEquals(325.0, newOrder.getDiscountedPrice(), 0.001);
		

	}

}
