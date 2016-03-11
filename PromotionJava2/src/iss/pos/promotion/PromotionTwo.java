package iss.pos.promotion;

import java.util.ArrayList;

public class PromotionTwo extends RatePromotion{

	public PromotionTwo() {
		
		this.discountRates = new ArrayList<Double>();
		this.discountRates.add(0.0);
		this.discountRates.add(0.5);
		this.sameSku = false;
		this.sameStyle = true;
		
	}
}
