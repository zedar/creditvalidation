package org.be3form.validation.creditfacts;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@ToString(includeFieldNames=true)
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@XmlType(name="CreditApplication", namespace="http://validation.be3form.org")
@XmlAccessorType(XmlAccessType.FIELD)
public class CreditApplication {

  // Reference to customer
  @XmlElement(name="CustomerId")
  @Getter @Setter private String customerId;
  
  // product codename for which application is validated
  @XmlElement(name="ProductCodeName", required = true)
  @Getter @Setter private String productCodeName;
}
