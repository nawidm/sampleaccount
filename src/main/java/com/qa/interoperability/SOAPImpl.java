package com.qa.interoperability;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class SOAPImpl {

	@WebMethod
	public String hello() {
		return "Hello Nawid";
	}

}
