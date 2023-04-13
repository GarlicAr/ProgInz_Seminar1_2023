package lv.venta.services;
import java.util.ArrayList;

import lv.venta.modules.*;


public interface IFilteringProductService {
	
	ArrayList<Product> filterByPrice(float price);
	
}
