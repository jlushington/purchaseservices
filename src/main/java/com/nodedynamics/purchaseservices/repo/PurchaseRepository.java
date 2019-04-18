package com.nodedynamics.purchaseservices.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.nodedynamics.purchaseservices.model.purchase.PurchaseModel;

public interface PurchaseRepository  extends MongoRepository<PurchaseModel, String>{
	
}
