package com.service.fact;

import com.prod.pos.PointOfSaleTerminal;
import com.prod.pos.PointOfSaleTerminalImpl;
import com.prod.repo.ProductRepositoryImpl;

public class ServiceFactoryImpl implements ServiceFactory{
	
	

	private static volatile ServiceFactory _instance;

	private ServiceFactoryImpl() {
		super();
	}

	public static ServiceFactory getInstance() {
		if (_instance == null) {
			synchronized (ServiceFactoryImpl.class) {
				if (_instance == null)
					_instance = new ServiceFactoryImpl();
			}
		}
		return _instance;
	}
	

	public PointOfSaleTerminal getPointOfSaleTerminal() {
		return new PointOfSaleTerminalImpl(new ProductRepositoryImpl());
	}
	
	

}
