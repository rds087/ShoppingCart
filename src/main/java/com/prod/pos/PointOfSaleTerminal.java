package com.prod.pos;

import java.math.BigDecimal;

public interface PointOfSaleTerminal {
	
	void reset();
	
	boolean scan(String prodCode);
	
	boolean addProduct(String prodCode, BigDecimal price);
	
	boolean addProduct(String prodCode, BigDecimal price, long quantityToAvailOffer, BigDecimal offerPrice);
	
	boolean updateProductPrice(String prodCode, BigDecimal price);
	
	boolean updateProductPrice(String prodCode, BigDecimal price, long quantityToAvailOffer, BigDecimal offerPrice);
	
	boolean removeOfferFromProduct(String prodCode);
	
	BigDecimal calculateTotal();
	
	boolean checkOut();

}
