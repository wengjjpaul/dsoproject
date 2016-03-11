package iss.pos.promotion;

import java.util.ArrayList;

public class PromotionOne extends RatePromotion{

	public PromotionOne() {
		
		
		this.discountRates = new ArrayList<Double>();
		this.discountRates.add(0.0);
		this.discountRates.add(0.3);
		this.sameSku = true;
		this.sameStyle = true;
	}
}
