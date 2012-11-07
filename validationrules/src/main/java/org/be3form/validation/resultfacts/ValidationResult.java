package org.be3form.validation.resultfacts;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@XmlType(name="ValidationResult", namespace="http://validation.be3form.org")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValidationResult {

  // Define types of validation results
  @XmlType(namespace="http://validation.be3form.org")
  public enum VR_TYPE {
    VR_NOPESEL,
    VR_TOOYOUNG,
    VR_NO_IDENTIFICATION_DOC,
    VR_AGE_NOTFORPRODUCT
  }

  // type of validation result
  @XmlElement(name="Type", required = true)
  @Getter @Setter private VR_TYPE type;

  // description of validation
  @XmlElement(name="Description", required = true)
  @Getter @Setter private String description;
}
