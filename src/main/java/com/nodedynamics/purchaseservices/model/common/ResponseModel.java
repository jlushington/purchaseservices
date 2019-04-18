package com.nodedynamics.purchaseservices.model.common;



import com.nodedynamics.purchaseservices.model.CoreModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@Builder
public class ResponseModel extends CoreModel{
	
	private String MessageType;
	private int MessageTypeID;
	private String Message;

}
