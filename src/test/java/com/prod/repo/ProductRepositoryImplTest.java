package com.prod.repo;

import java.math.BigDecimal;

import static org.junit.Assert.*;

import org.junit.Test;

import com.exception.ServiceException;

public class ProductRepositoryImplTest {

	private ProductRepository productRepository;

	private static final String PRODUCT_CODE = "A1";

	private static final BigDecimal PRICE = new BigDecimal("1.25");

	private static final BigDecimal OFFER_PRICE = new BigDecimal("3");

	private static final long QUANTITY = 5L;

	@Test
	public void testCreateProduct() {
		productRepository = new ProductRepositoryImpl();
		try {
			boolean flag = productRepository.createProduct(PRODUCT_CODE, PRICE);
			
			assertEquals(true, flag);
			
			assertEquals(true, productRepository.checkProductExists((PRODUCT_CODE)));

			assertEquals(PRICE, productRepository.getProduct(PRODUCT_CODE).getPricePerUnit());

		} catch (ServiceException e) {
			fail("Unexpected Error occured");
		}

	}

	@Test(expected = ServiceException.class)
	public void testCreateProductThrowsException() throws ServiceException {
		productRepository = new ProductRepositoryImpl();
		productRepository.createProduct(PRODUCT_CODE, null);

		fail("Expected error not thrown");

	}

	@Test
	public void testCreateProductWithDetails() {
		productRepository = new ProductRepositoryImpl();
		try {
			boolean flag = productRepository.createProduct(PRODUCT_CODE, PRICE, QUANTITY, OFFER_PRICE);
			assertEquals(true, flag);
			assertEquals(PRICE, productRepository.getProduct(PRODUCT_CODE).getPricePerUnit());

		} catch (ServiceException e) {
			fail("Unexpected Error occured");
		}

	}

	@Test
	public void testUpdateProductPrice() {
		productRepository = new ProductRepositoryImpl();
		try {
			
			boolean flag = productRepository.createProduct(PRODUCT_CODE, PRICE);
			assertEquals(true, flag);
			flag = productRepository.updateProductPrice(PRODUCT_CODE, PRICE);
			assertEquals(true, flag);
			assertEquals(PRICE, productRepository.getProduct(PRODUCT_CODE).getPricePerUnit());

		} catch (ServiceException e) {
			fail("Unexpected Error occured");
		}
	}

	@Test(expected = ServiceException.class)
	public void testUpdateProductPricethrowsException() throws ServiceException {
		productRepository = new ProductRepositoryImpl();
		boolean flag = productRepository.createProduct(PRODUCT_CODE, PRICE);
		assertEquals(true, flag);
		flag = productRepository.updateProductPrice(PRODUCT_CODE, null);
		
		
		fail("Expected error not thrown");

	}
	
	

}
