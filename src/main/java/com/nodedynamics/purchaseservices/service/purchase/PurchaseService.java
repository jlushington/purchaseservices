package com.nodedynamics.purchaseservices.service.purchase;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.WebSession;

import com.google.gson.Gson;
import com.nodedynamics.purchaseservices.common.Global;
import com.nodedynamics.purchaseservices.model.purchase.PurchaseModel;
import com.nodedynamics.purchaseservices.repo.PurchaseRepository;
import com.nodedynamics.purchaseservices.service.BaseService;
import com.nodedynamics.purchaseservices.model.common.ResponseModel;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import reactor.core.publisher.Mono;



@Service
public class PurchaseService implements BaseService<PurchaseModel>{
	
	Logger log = LoggerFactory.getLogger(PurchaseService.class);
	
	//private WebSession Session;
	
	@Autowired
	private PurchaseRepository repo;
	
	@Autowired
	Gson gson = new Gson();
	

	@Override
	public void Init(WebSession session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Mono<String> Store(PurchaseModel Model) {
		//RETURN RESPONSE MODEL
		String ReturnMessage = null;
		
		//PAYPAL PURCHASE - SHOULD BE UPDATE WITH MORE ABSTRACTION
		Amount amount = new Amount();
		amount.setCurrency(Global.PayPalData.CURRENCYCND.key);
		amount.setTotal(Model.getPurchaseSum());
		
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);
		
		Payer payer = new Payer();
		payer.setPaymentMethod(Global.PayPalData.PAYMENTMETHOD.key);
		
		Payment payment = new Payment();
		payment.setIntent(Global.PayPalData.PAYMENTINTENTSALE.key);
		payment.setPayer(payer);
		payment.setTransactions(transactions);
		
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl(Global.PayPalData.CANCELURL.key);
		redirectUrls.setReturnUrl(Global.PayPalData.RETURNURL.key);
		
		payment.setRedirectUrls(redirectUrls);
		Payment createdPayment;
		
		try {
			String redirectUrl="";
			
			APIContext context = new APIContext(Global.PayPalData.CLIENTID.key, Global.PayPalData.SECRET.key, Global.PayPalData.ENVSTANDBOX.key);
			createdPayment = payment.create(context);
			
			if(createdPayment !=null) {
				List<Links>links = createdPayment.getLinks();
				
				for(Links link:links) {
					if(link.getRel().equals("approval_url")) {
						redirectUrl = link.getHref();
						break;
					}
				}
				
				//SAVE MODEL
				repo.save(Model);
				
				ReturnMessage=gson.toJson(ResponseModel.builder()
						.MessageTypeID(Global.MessageTypeID.SUCCESS.key)
						.MessageType(Global.MessageType.SUCCESS.key)
						.Message("Redirect URL: "+ redirectUrl)
						.build());
			}
			
		}catch(PayPalRESTException e) {
			log.info("Error happened during payment creation! :" + e);
		}
					
		return Mono.just(ReturnMessage);
	}

	@Override
	public Mono<String> Update(PurchaseModel Model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<String> DeleteAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<String> Delete(PurchaseModel Model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<String> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<String> Get(PurchaseModel Model) {
		// TODO Auto-generated method stub
		return null;
	}

}
