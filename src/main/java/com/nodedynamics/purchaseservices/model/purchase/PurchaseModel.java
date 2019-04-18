package com.nodedynamics.purchaseservices.model.purchase;


import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import com.nodedynamics.purchaseservices.model.CoreModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@Builder
public class PurchaseModel extends CoreModel{
	
	@Id
	private String purchaseID;
	private String cartID;
	private String paymentAuthorize;
	private String purchaseSum;
	private LocalDateTime purchaseDateCreated;

}
