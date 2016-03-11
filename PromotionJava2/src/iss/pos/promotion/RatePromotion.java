package iss.pos.promotion;

import java.util.List;

public class RatePromotion extends Promotion {
protected List<Double> discountRates = null;	

protected boolean pickBiggerDiscountIfApplied = false;

	public List<Double> getDiscountRates() {
		return discountRates;
	}

	public void setDiscountRates(List<Double> discountRates) {
		this.discountRates = discountRates;
	}

}
