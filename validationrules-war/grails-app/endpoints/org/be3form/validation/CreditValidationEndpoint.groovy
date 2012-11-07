package org.be3form.validation

import java.io.Writer;
import java.io.StringWriter;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import groovy.xml.XmlUtil;

import org.be3form.validation.resultfacts.ValidationResult;
import org.be3form.validation.messages.CreditValidationRequest;
import org.be3form.validation.messages.CreditValidationResponse;

import org.be3form.validation.RuleRunnerService;

class CreditValidationEndpoint {
	
	def static namespace = "http://validation.be3form.org" 

  def ruleRunnerService
	
	def invoke = { request, response ->
    
    println "CreaditValidation request received"
    println "${request.CreditApplication.CustomerId}"
    println "Data types: ${request.getClass()}, ${request.CreditApplication.getClass()}"
    
    StringWriter writer = new StringWriter();
    XmlUtil.serialize(request, writer);
    println "Request XML: ${writer.toString()}"
    
    JAXBContext jbc = JAXBContext.newInstance(CreditValidationRequest.class);
    Unmarshaller u = jbc.createUnmarshaller();
    ValidationResult[] vresults = null;
    CreditValidationRequest cvr = (CreditValidationRequest)u.unmarshal(new StreamSource(new StringReader(writer.toString())));
    if (cvr != null) {
      println "JAXB data type: ${cvr.getClass()}";
      println "CVR attributes: ${cvr.toString()}";
      vresults = ruleRunnerService.run(cvr)
    }
    else {
      println "JAXB: object not unmarshalled";
    }
    
    response."val:CreditValidationResponse"("xmlns:val": namespace) {
      // Add validation results here
      ValidationResultList {
        for (vr in vresults) {
          ValidationResult {
            Type(vr.type)
            Description(vr.description)
          }
        }
      }
    }
  }
}
