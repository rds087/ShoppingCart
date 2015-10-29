package com.prod.repo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.exception.ServiceException;
import com.prod.Product;

/**
 * @author aboora
 * ProductRepositoryImpl will be store the product information as persistence layer is not present in this implementation.
 */
public class ProductRepositoryImpl implements ProductRepository{
	
	private Map<String, Product> prodMap = new HashMap<String, Product>();
	
	public boolean createProduct(String prodCode, BigDecimal price) throws ServiceException
	{
		if(prodCode ==null || price == null)
		{
			throw new ServiceException("Invalid product Config. All required values are not set");
		}
		
		if(prodMap.containsKey(prodCode))
		{
			throw new ServiceException("Product already exists");
		}
		
		Product product = new Product(prodCode, price);
		
		prodMap.put(prodCode, product);
		
		return true;
	}
	
	public boolean createProduct(String prodCode, BigDecimal price, long quantityToAvailOffer, BigDecimal offerPrice) throws ServiceException
	{
		if(quantityToAvailOffer == 0 || offerPrice == null)
		{
			return createProduct(prodCode, offerPrice);
		}
		
		if(quantityToAvailOffer == 0 && offerPrice != null)
		{
			throw new ServiceException("Invalid product Config. All required values are not set");
		}
		
		if(quantityToAvailOffer == 0 && offerPrice != null)
		{
			throw new ServiceException("Invalid product Config. All required values are not set");
		}
		
		if(prodMap.containsKey(prodCode))
		{
			throw new ServiceException("Product already exists");
		}
		
		Product product = new Product(prodCode, price, true, quantityToAvailOffer, offerPrice);
		
		prodMap.put(prodCode, product);
		
		return true;
	}
	
	public boolean updateProductPrice(String prodCode, BigDecimal price) throws ServiceException
	{
		if(prodCode ==null || price == null)
		{
			throw new ServiceException("Invalid product Config. All required values are not set");
		}
		
		if(prodMap.containsKey(prodCode))
		{
			Product product = prodMap.get(prodCode);
			product.setPricePerUnit(price);
		}
		else
		{
			throw new ServiceException("Product doesn't exists");
		}
		
		return true;
		
	}
	
	public boolean updateProduct(String prodCode, BigDecimal price, long quantityToAvailOffer, BigDecimal offerPrice) throws ServiceException
	{
		if(quantityToAvailOffer == 0 || offerPrice == null)
		{
			return updateProductPrice(prodCode, offerPrice);
		}
		
		if(quantityToAvailOffer == 0 && offerPrice != null)
		{
			throw new ServiceException("Invalid product Config. All required values are not set");
		}
		
		if(quantityToAvailOffer == 0 && offerPrice != null)
		{
			throw new ServiceException("Invalid product Config. All required values are not set");
		}
		
		if(prodMap.containsKey(prodCode))
		{
			Product product = prodMap.get(prodCode);
			product.setPricePerUnit(price);
			product.setQuantityToAvailOffer(quantityToAvailOffer);
			product.setOfferPrice(offerPrice);
			product.setOfferAvailable(true);
		}
		
		Product product = new Product(prodCode, price);
		
		prodMap.put(prodCode, product);
		
		return true;
	}
	
	public boolean removeOfferFromProduct(String prodCode) throws ServiceException
	{
		if(prodCode ==null)
		{
			throw new ServiceException("Product code passed is null");
		}
		
		if(prodMap.containsKey(prodCode))
		{
			Product product = prodMap.get(prodCode);
			product.setOfferAvailable(false);
		}
		else
		{
			throw new ServiceException("Product doesn't exists");
		}
		
		return true;
		
	}
	
	public boolean checkProductExists(String prodCode) throws ServiceException
	{
		if(prodCode ==null)
		{
			throw new ServiceException("Product code passed is null");
		}
		
		if(prodMap.containsKey(prodCode))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public Product getProduct(String prodCode) throws ServiceException
	{
		Product product;
		
		if(prodCode ==null)
		{
			throw new ServiceException("Product code passed is null");
		}
		
		if(prodMap.containsKey(prodCode))
		{
			 product = prodMap.get(prodCode);
		}
		else
		{
			throw new ServiceException("Product doesn't exists");
		}
		
		return product;
	}
	
	

}
