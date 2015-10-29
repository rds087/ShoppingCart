package com.prod.pos;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.exception.ServiceException;
import com.prod.Product;
import com.prod.repo.ProductRepository;

public class PointOfSaleTerminalImplTest {

	private PointOfSaleTerminal pointOfSaleTerminal;

	private static final String PRODUCT_CODE = "A1";

	private static final BigDecimal PRICE = new BigDecimal("1.25");
	
	private static final BigDecimal UPDATED_PRICE = new BigDecimal("1.45");

	private static final BigDecimal OFFER_PRICE = new BigDecimal("3");

	private static final long QUANTITY = 5L;

	@Mock
	private ProductRepository productRepository;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void testScan() throws ServiceException {
		pointOfSaleTerminal = new PointOfSaleTerminalImpl(productRepository);

		boolean flag = pointOfSaleTerminal.scan(PRODUCT_CODE);

		assertEquals(true, flag);

	}

	@Test
	public void testAddProduct() throws ServiceException{
		pointOfSaleTerminal = new PointOfSaleTerminalImpl(productRepository);
		when(productRepository.createProduct(PRODUCT_CODE, PRICE)).thenReturn(true);
		
		boolean flag = pointOfSaleTerminal.addProduct(PRODUCT_CODE, PRICE);
		assertEquals(true, flag);

	}
	
	@Test
	public void testAddProductFailure() throws ServiceException {
		pointOfSaleTerminal = new PointOfSaleTerminalImpl(productRepository);
		
		when(productRepository.createProduct(PRODUCT_CODE, PRICE)).thenThrow(new ServiceException("error"));
		
		boolean flag = pointOfSaleTerminal.addProduct(PRODUCT_CODE, PRICE);
		assertEquals(false, flag);

	}
	
	@Test
	public void testAddProductWithDetails() throws ServiceException{
		pointOfSaleTerminal = new PointOfSaleTerminalImpl(productRepository);
		when(productRepository.createProduct(PRODUCT_CODE, PRICE, QUANTITY,OFFER_PRICE)).thenReturn(true);
		boolean flag = pointOfSaleTerminal.addProduct(PRODUCT_CODE, PRICE, QUANTITY,OFFER_PRICE);
		assertEquals(true, flag);

	}
	
	@Test
	public void testUpdateProductPrice() throws ServiceException{
		pointOfSaleTerminal = new PointOfSaleTerminalImpl(productRepository);
		when(productRepository.updateProductPrice(PRODUCT_CODE, UPDATED_PRICE)).thenReturn(true);
		boolean flag = pointOfSaleTerminal.updateProductPrice(PRODUCT_CODE, UPDATED_PRICE);
		assertEquals(true, flag);
	}

	@Test
	public void testUpdateProductPricethrowsException() throws ServiceException {
		pointOfSaleTerminal = new PointOfSaleTerminalImpl(productRepository);
		when(pointOfSaleTerminal.addProduct(PRODUCT_CODE, null)).thenThrow(new ServiceException("error"));
		
		boolean flag = pointOfSaleTerminal.updateProductPrice(PRODUCT_CODE, null);
		assertEquals(false, flag);

	}
	
	@Test
	public void testRemoveOfferFromProduct() throws ServiceException{
		pointOfSaleTerminal = new PointOfSaleTerminalImpl(productRepository);
		when(productRepository.removeOfferFromProduct(PRODUCT_CODE)).thenReturn(true);
		boolean flag = pointOfSaleTerminal.removeOfferFromProduct(PRODUCT_CODE);
		assertEquals(true, flag);
	}
	
	@Test
	public void testCheckOut(){
		pointOfSaleTerminal = new PointOfSaleTerminalImpl(productRepository);
		
		boolean flag = pointOfSaleTerminal.checkOut();
		assertEquals(true, flag);
	}
	
	@Test
	public void testCalculateTotal() throws ServiceException{
		pointOfSaleTerminal = new PointOfSaleTerminalImpl(productRepository);

		when(productRepository.checkProductExists(PRODUCT_CODE)).thenReturn(true);
		when(productRepository.getProduct(PRODUCT_CODE)).thenReturn(new Product(PRODUCT_CODE, PRICE));
		
		boolean flag = pointOfSaleTerminal.scan(PRODUCT_CODE);

		assertEquals(true, flag);
		
		BigDecimal value = pointOfSaleTerminal.calculateTotal();
		
		assertEquals(PRICE, value);
		
	}
	
	

}
