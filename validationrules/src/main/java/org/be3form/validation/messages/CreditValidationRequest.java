package org.be3form.validation.messages;

import java.util.List;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import org.be3form.validation.facts.Customer;
import org.be3form.validation.creditfacts.CreditApplication;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="CreditValidationRequest", namespace="http://validation.be3form.org")
@XmlType(name="CreditValidationRequest", namespace="http://validation.be3form.org")
@XmlAccessorType(XmlAccessType.FIELD)
public class CreditValidationRequest {
  
  @XmlType(namespace="http://validation.be3form.org")
  public enum RULEGROUP {
    PRESCORING,
    SCORING,
  }
  // group of rules to execute, PRESCORING or SCORING
  @XmlElement(name="RuleGroup", required=false)
  @Getter @Setter private RULEGROUP ruleGroup;

  // credit application could have more than one requestor
  @XmlElementWrapper(name="CustomerList")
  @XmlElement(name="Customer")
  @Getter @Setter private List<Customer> customerList;

  // credit application data
  @XmlElement(name="CreditApplication")
  @Getter @Setter private CreditApplication creditApplication;

  public void addCustomer(Customer customer) {
    if (this.customerList == null) {
      this.customerList = new ArrayList<Customer>();
    }
    this.customerList.add(customer);
  }
}
