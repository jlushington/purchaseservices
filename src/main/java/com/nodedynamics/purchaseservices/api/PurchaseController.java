package com.nodedynamics.purchaseservices.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.nodedynamics.purchaseservices.model.purchase.PurchaseModel;
import com.nodedynamics.purchaseservices.service.purchase.PurchaseService;

import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {
	
Logger log = LoggerFactory.getLogger(PurchaseController.class);
	
	@Autowired
	Gson gson;
	
	@Autowired
	PurchaseService service;
	
	@CrossOrigin(origins = "*") //TODO: NEED TO REMOVE AND INIT PROPER CORS
	@PostMapping(value = "/purchase", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<String> Purchase(@RequestBody String request){
    	return service.Store(gson.fromJson(request, PurchaseModel.class));
    }
	
	@CrossOrigin(origins = "*") //TODO: NEED TO REMOVE AND INIT PROPER CORS
	@PostMapping(value = "/purchasesuccessful", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<String> PurchaseSuccessful(@RequestBody String request){
    	return service.PaymentSuccessful(gson.fromJson(request, PurchaseModel.class));
    }

}
