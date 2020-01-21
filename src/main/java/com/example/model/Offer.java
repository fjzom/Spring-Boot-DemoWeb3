package com.example.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Offer {
	@Autowired 
	private List<Product> productOfferList;

	public List<Product> getProductOfferList() {
		return productOfferList;
	}

	public void setProductOfferList(List<Product> productOfferList) {
		this.productOfferList = productOfferList;
	}
	
}
