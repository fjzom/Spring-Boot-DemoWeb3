package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.Offer;

@Controller
@RequestMapping("offers")
public class OfferController {
	
	private static final Logger log = LoggerFactory.getLogger(OfferController.class);
	@Autowired
	private Offer offers;
	
	@GetMapping("availableoffers")
	public String availableOffers(Model model) {
		log.info("Available offers");
		model.addAttribute("titutlo", "Available offers");
		model.addAttribute("offers", offers);
		
		return "offers/availableoffers";
	}

}
