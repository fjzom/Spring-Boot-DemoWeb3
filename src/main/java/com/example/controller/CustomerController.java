package com.example.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.Customer;
import com.example.model.Photo;
import com.example.repository.ICustomerRepo;

@Controller
@RequestMapping("")
public class CustomerController {

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	private ICustomerRepo customerRepo;
	

	@GetMapping(path = "customerform") 
	public String createCustomerForm( Model model  ){
		log.info("Add customer by form");
		Customer c =  new Customer();
		model.addAttribute("titulo", "Customer Form");
		model.addAttribute("customer", c);
		
		return "customerform";
	}
	@PostMapping(path = "customerform") 
	public String createCustomerForm( Customer customer, @RequestParam(name = "file") MultipartFile foto) throws IOException{
		log.info("Add customer by form");
		Date currentAt = new Date();
		customer.setCustomerAt(currentAt);
		if(!foto.isEmpty()) {
			String uniqueFileName = UUID.randomUUID() +"_"+ foto.getOriginalFilename();
			Path rootPath =  Paths.get("C://temp//uploads").resolve(uniqueFileName);
			Path absolutePath =   rootPath.toAbsolutePath();
			Files.copy(foto.getInputStream(), absolutePath.toAbsolutePath());
			customer.setFoto(uniqueFileName);
			Photo pic = new Photo();
			pic.setTitle(uniqueFileName);
			pic.setImage(new Binary(BsonBinarySubType.BINARY, foto.getBytes()));
			customer.setPhoto(pic);
		}
		customerRepo.save(customer);
		return "redirect:customers";
	}
	@PostMapping(path = "addcustomer")
	@ResponseBody
	public void addCustomer(@RequestBody Customer customer){
		log.info("Add customer");
		customerRepo.save(customer);
	}
	@PutMapping(path = "editcustomer")
	@ResponseBody
	public void editCustomer(@RequestBody Customer customer){
		log.info("Edit customer");
		customerRepo.save(customer);
	}
	@GetMapping(value="deletecustomer/{customerId}") 
	public String deleteCustomer(@PathVariable(name = "customerId")String customerId){
		log.info("Remove customer");
		customerRepo.deleteBycustomerId(customerId);
		return "redirect:/customers";
	}
	@GetMapping("customers")
	public String getAllCustomers( Model model){
		log.info("Get All customers");
		model.addAttribute("titulo", "Customers");
		model.addAttribute("customers", customerRepo.findAll());
		return "customers";
	}
	@GetMapping("data")
	@ResponseBody
	public List<Customer> data( Model model){
		log.info("Get All customers");
		return  customerRepo.findAll();
	}
	@GetMapping("view/{customerId}")
	public String view( Model model, @PathVariable(name = "customerId")String customerId){
		log.info("Get All customers");
		model.addAttribute("titulo", "Customer View");
		model.addAttribute("customer", customerRepo.findBycustomerId(customerId));
		model.addAttribute("image", Base64.getEncoder().encodeToString(customerRepo.findBycustomerId(customerId).getPhoto().getImage().getData()));
		return "view";
	}
	

}
