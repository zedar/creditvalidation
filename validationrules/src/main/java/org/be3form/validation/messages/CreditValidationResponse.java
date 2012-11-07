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

import org.be3form.validation.resultfacts.ValidationResult;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="CreditValidationResponse", namespace="http://validation.be3form.org")
@XmlType(name="CreditValidationResponse", namespace="http://validation.be3form.org")
@XmlAccessorType(XmlAccessType.FIELD)
public class CreditValidationResponse {

  // list of validation results. could be empty if all validations are positive
  @XmlElementWrapper(name="ValidationResultList")
  @XmlElement(name="ValidationResult")
  @Getter @Setter private List<ValidationResult> validationResultList;
}
