package com.qa.interoperability;

import java.net.MalformedURLException;
import java.net.URL;
 
import javax.xml.namespace.QName;
import javax.xml.ws.Service;


public class Client {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/sampleAccount/hello?wsdl");
        QName qname = new QName("http://interoperability.qa.com/", "WebService");
         
        Service service = Service.create(url, qname);
        SOAPImpl soap = service.getPort(SOAPImpl.class);
        System.out.println(soap.hello());
    }

}
