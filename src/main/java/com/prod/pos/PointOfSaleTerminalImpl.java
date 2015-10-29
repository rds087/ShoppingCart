package com.prod.pos;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.exception.ServiceException;
import com.prod.Product;
import com.prod.repo.ProductRepository;

public class PointOfSaleTerminalImpl implements PointOfSaleTerminal{
	
	final Logger logger = Logger.getLogger(PointOfSaleTerminalImpl.class);
	
	private ProductRepository prodRepo;
	
	private Map<String, Long> productMap = new HashMap<String, Long>();
	
	public ProductRepository getProdRepo() {
		return prodRepo;
	}


	public void setProdRepo(ProductRepository prodRepo) {
		this.prodRepo = prodRepo;
	}


	public PointOfSaleTerminalImpl(ProductRepository prodRepo) {
		super();
		this.prodRepo = prodRepo;
	}
	

	public void reset()
	{
		productMap.clear();
	}

	public boolean scan(String prodCode)
	{
		boolean result = true;
		
		try {
			logger.debug("Scanning Product : " + prodCode);
			result = prodRepo.checkProductExists(prodCode);
		} catch (ServiceException e) {
			logger.error(e);
			result = false;
		}
		
		if(result)
		{
			Long quantity = 1L;
			
			if(productMap.containsKey(prodCode))
			{
				quantity = productMap.get(prodCode);
				quantity++;
			}
			
			
			productMap.put(prodCode, quantity);
		}
		
		
		return true;
		
	}
	
	public boolean addProduct(String prodCode, BigDecimal price)
	{
		boolean result;
				
		try {
			logger.debug("Adding Product : " + prodCode);
			result = prodRepo.createProduct(prodCode, price);
		} catch (ServiceException e) {
			logger.error(e);
			result = false;
		}
		
		return result;
	}
	
	public boolean addProduct(String prodCode, BigDecimal price, long quantityToAvailOffer, BigDecimal offerPrice)
	{
		boolean result;
		
		try {
			logger.debug("Adding Product : " + prodCode);
			result = prodRepo.createProduct(prodCode, price, quantityToAvailOffer, offerPrice);
		} catch (ServiceException e) {
			logger.error(e);
			result = false;
		}
		
		return result;
	}
	
	public boolean updateProductPrice(String prodCode, BigDecimal price)
	{
		boolean result;
		
		try {
			logger.debug("Updating Product " + prodCode + " price to : "+ price);
			result = prodRepo.updateProductPrice(prodCode, price);
		} catch (ServiceException e) {
			logger.error(e);
			result = false;
		}
		
		return result;
	}
	
	public boolean updateProductPrice(String prodCode, BigDecimal price, long quantityToAvailOffer, BigDecimal offerPrice)
	{
		boolean result;
		
		try {
			logger.debug("Updating Product : " + prodCode);
			result = prodRepo.updateProduct(prodCode, price, quantityToAvailOffer, offerPrice);
		} catch (ServiceException e) {
			logger.error(e);
			result = false;
		}
		
		return result;
	}
	
	public boolean removeOfferFromProduct(String prodCode)
	{
		boolean result;
		
		try {
			logger.debug("Removing Product : " + prodCode);
			result = prodRepo.removeOfferFromProduct(prodCode);
		} catch (ServiceException e) {
			logger.error(e);
			result = false;
		}
		
		return result;
	}
	
	public BigDecimal calculateTotal()
	{
		BigDecimal amount = new BigDecimal("0");
		
		for (String prodCode : productMap.keySet()) {
			try {
				Product product = prodRepo.getProduct(prodCode);
				Long quantity = productMap.get(prodCode);
				if(product.isOfferAvailable() && quantity >= product.getQuantityToAvailOffer())
				{
					Long packetsQuantity =  quantity / product.getQuantityToAvailOffer();
					Long openpacketsQuantity = quantity % product.getQuantityToAvailOffer();
					
					BigDecimal prodPrice = product.getOfferPrice();
					BigDecimal prodAmount = prodPrice.multiply(new BigDecimal(packetsQuantity));
					amount = amount.add(prodAmount);
					
					prodPrice = product.getPricePerUnit();
					prodAmount = prodPrice.multiply(new BigDecimal(openpacketsQuantity));
					amount = amount.add(prodAmount);
					
				}
				else
				{
					BigDecimal prodPrice = product.getPricePerUnit();
					BigDecimal prodAmount = prodPrice.multiply(new BigDecimal(quantity));
					amount = amount.add(prodAmount);
				}
				
				
			} catch (ServiceException e) {
				logger.error(e);
				amount = new BigDecimal("-1");
			}
		}
		
		logger.debug("Amount : " + amount);
		
		return amount;
	}
	
	public boolean checkOut()
	{
		productMap.clear();
		logger.debug("Checkout completed ");
		return true;
	}

}
