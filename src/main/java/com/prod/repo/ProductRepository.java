package com.prod.repo;

import java.math.BigDecimal;

import com.exception.ServiceException;
import com.prod.Product;

public interface ProductRepository {
	
	boolean createProduct(String prodCode, BigDecimal price) throws ServiceException;
	
	boolean createProduct(String prodCode, BigDecimal price, long quantityToAvailOffer, BigDecimal offerPrice) throws ServiceException;
	
	boolean updateProductPrice(String prodCode, BigDecimal price) throws ServiceException;
	
	boolean updateProduct(String prodCode, BigDecimal price, long quantityToAvailOffer, BigDecimal offerPrice) throws ServiceException;
	
	boolean removeOfferFromProduct(String prodCode) throws ServiceException;
	
	Product getProduct(String prodCode) throws ServiceException;
	
	boolean checkProductExists(String prodCode) throws ServiceException;

}
