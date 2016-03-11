package iss.pos.promotion;

import java.util.List;

import iss.pos.Order;
import iss.pos.OrderItem;

public class DiscountCalculator {

	Promotion promo = null;

	public DiscountCalculator(Promotion promo) {
		this.promo = promo;
	}

	/**
	 * Compute Discount
	 * @param order
	 * @return
	 */
	public Order calculateDiscount(Order order) {

		double dVal = 0.0;
		if (promo instanceof RatePromotion) {
			dVal = this.performRateDiscount(order);
		} else if (promo instanceof BulkPromotion) {
			dVal = this.performBulkDiscount(order);
		}
		order.setDiscountValue(dVal);
		return order;
	}

	/**
	 * Perform rate-based discount
	 * @param order
	 * @return
	 */
	private double performRateDiscount(Order order) {
		List<OrderItem> items;

		double dVal = 0.0;

		RatePromotion rp = (RatePromotion) promo;
		if (promo.sameSku) {
			items = order.getOrderItems();
			for (OrderItem item : items) {
				if (!rp.pickBiggerDiscountIfApplied) {
					dVal += this
							.computeDiscountBasedOnIndDiscount(item, rp);
				} else {
					dVal += computeDiscountBasedOnLargerDiscountIfApplied(
							item, rp);
				}
			}
		} else if (promo.sameStyle == true) {
			items = order.getStyleOrderItems();
			for (OrderItem item : items) {
				if (!rp.pickBiggerDiscountIfApplied) {
					dVal += this
							.computeDiscountBasedOnIndDiscount(item, rp);
				} else {
					dVal += computeDiscountBasedOnLargerDiscountIfApplied(
							item, rp);
				}

			}
		}
		
		return dVal;
	}
	
	/**
	 * Perform bulk discount
	 * @param order
	 * @return
	 */
	private double performBulkDiscount(Order order) {
		List<OrderItem> items;

		double dVal = 0.0;
		BulkPromotion bp = (BulkPromotion) promo;
		
		// Only applies on same item. Not same item, no discount
		if (promo.sameSku) {
			items = order.getOrderItems();
			for (OrderItem item : items) {
				double prodPrice = item.getProduct().getPrice();

				// Only apply if meet mininum quantity
				if (item.getQuantity() >= bp.minQuantity) {

					// Number of time discount applied
					int num = (int) Math.floor(item.getQuantity()
							/ bp.minQuantity);

					// Price without discount for items to be discounted
					double originalPrice = num * bp.minQuantity * prodPrice;

					// Compute discount value for consistency with other
					// implementation
					dVal = originalPrice - (num * bp.price);

				}
			}
		}
		
		return dVal;
	}
	
	private double computeDiscountBasedOnIndDiscount(OrderItem item,
			RatePromotion promo) {
		double dVal = 0.0;

		double prodPrice = item.getProduct().getPrice();

		int promoSet = promo.discountRates.size();
		for (int i = 0; i < item.getQuantity(); i++) {
			dVal += prodPrice * promo.discountRates.get(i % promoSet);
		}

		return dVal;
	}

	private double computeDiscountBasedOnLargerDiscountIfApplied(
			OrderItem item, RatePromotion promo) {
		double dVal = 0.0;

		// More or Equal required quantity
		if (item.getQuantity() >= promo.getDiscountRates().size()) {
			double dis = promo.getDiscountRates().get(
					promo.getDiscountRates().size() - 1); // assume last item
															// largest discount
			double prodPrice = item.getProduct().getPrice();

			for (int i = 0; i < item.getQuantity(); i++) {
				dVal += prodPrice * dis;
			}
		} else { // Apply discount individually based on discount rate
			double prodPrice = item.getProduct().getPrice();

			int promoSet = promo.discountRates.size();
			for (int i = 0; i < item.getQuantity(); i++) {
				dVal += prodPrice * promo.discountRates.get(i % promoSet);
			}

		}

		return dVal;
	}
}
