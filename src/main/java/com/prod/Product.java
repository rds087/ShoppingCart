package com.prod;

import java.math.BigDecimal;

public class Product {

	private String productCode;
	private BigDecimal pricePerUnit;
	private boolean offerAvailable;
	private long quantityToAvailOffer;
	private BigDecimal offerPrice;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(BigDecimal pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public boolean isOfferAvailable() {
		return offerAvailable;
	}

	public void setOfferAvailable(boolean offerAvailable) {
		this.offerAvailable = offerAvailable;
	}

	public long getQuantityToAvailOffer() {
		return quantityToAvailOffer;
	}

	public void setQuantityToAvailOffer(long quantityToAvailOffer) {
		this.quantityToAvailOffer = quantityToAvailOffer;
	}

	public BigDecimal getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(BigDecimal offerPrice) {
		this.offerPrice = offerPrice;
	}

	public Product() {

	}

	/**
	 * @param productCode
	 * @param pricePerUnit
	 * @param offerAvailable
	 * @param quantityToAvailOffer
	 * @param offerPrice
	 */
	public Product(String productCode, BigDecimal pricePerUnit, boolean offerAvailable, long quantityToAvailOffer,
			BigDecimal offerPrice) {
		super();
		this.productCode = productCode;
		this.pricePerUnit = pricePerUnit;
		this.offerAvailable = offerAvailable;
		this.quantityToAvailOffer = quantityToAvailOffer;
		this.offerPrice = offerPrice;
	}

	/**
	 * @param productCode
	 * @param pricePerUnit
	 */
	public Product(String productCode, BigDecimal pricePerUnit) {
		super();
		this.productCode = productCode;
		this.pricePerUnit = pricePerUnit;
	}

}
