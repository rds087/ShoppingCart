package com.shop;

import java.math.BigDecimal;

import com.prod.pos.PointOfSaleTerminal;
import com.service.fact.ServiceFactoryImpl;

public class ShoppingCartService {
	
	
	
	public static void main(String[] args) {
		PointOfSaleTerminal pos = ServiceFactoryImpl.getInstance().getPointOfSaleTerminal();
		
		System.out.println(" ------ Adding Products -------------");
		
		pos.addProduct("A1", new BigDecimal("1.25"), 3, new BigDecimal("3.00"));
		pos.addProduct("3-Q", new BigDecimal("4.25"));
		pos.addProduct("45K11", new BigDecimal("1"), 6, new BigDecimal("5"));
		pos.addProduct("X1", new BigDecimal("0.75"));
		
		System.out.println();
		
		System.out.println(" ------ Scanning Products for first customer -------------");
		
		pos.scan("A1");
		pos.scan("3-Q");
		pos.scan("45K11");
		pos.scan("X1");
		pos.scan("A1");
		pos.scan("3-Q");
		pos.scan("A1");
		System.out.println("Total Amount $" + pos.calculateTotal());
		pos.checkOut();
		
		System.out.println();
		
		System.out.println(" ------ Scanning Products for first customer -------------");
		
		pos.scan("45K11");
		pos.scan("45K11");
		pos.scan("45K11");
		pos.scan("45K11");
		pos.scan("45K11");
		pos.scan("45K11");
		pos.scan("45K11");
		System.out.println("Total Amount $" + pos.calculateTotal());
		pos.checkOut();
		
		System.out.println();
		System.out.println(" ------ Scanning Products for first customer -------------");
		
		pos.scan("A1");
		pos.scan("3-Q");
		pos.scan("45K11");
		pos.scan("X1");
		System.out.println("Total Amount $" + pos.calculateTotal());
		pos.checkOut();
		
	}

}
