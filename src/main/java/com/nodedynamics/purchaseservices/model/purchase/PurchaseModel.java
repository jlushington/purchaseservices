package com.nodedynamics.purchaseservices.model.purchase;


import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nodedynamics.purchaseservices.model.CoreModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@Builder
@Document(collection="purchases")
public class PurchaseModel extends CoreModel{
	
	@Id
	private String iD;
	private String paymentID;
	private String paymentToken;
	private String payerID;
	private String cartID;
	private String paymentAuthorize;
	private String purchaseSum;
	private LocalDateTime purchaseDateCreated;

}
