package iss.pos.promotion;

import java.util.ArrayList;

public class PromotionThree extends RatePromotion{

	public PromotionThree() {
		
		this.discountRates = new ArrayList<Double>();
		this.discountRates.add(0.1);		
		this.discountRates.add(0.2);
		this.sameSku = false;
		this.sameStyle = true;
		this.pickBiggerDiscountIfApplied = true;
		
	}
}
