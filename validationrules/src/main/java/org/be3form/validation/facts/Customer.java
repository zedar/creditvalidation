package org.be3form.validation.facts;

import java.util.Calendar;

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

import org.be3form.validation.facts.IdentificationType;

import org.be3form.validation.utils.PeselValidator;

@ToString(includeFieldNames=true)
@EqualsAndHashCode(exclude={"id"})
@NoArgsConstructor
@AllArgsConstructor
@XmlType(name="Customer", namespace="http://validation.be3form.org")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {

  // PID/CIF
  @XmlElement(name="Id", required = true)
  @Getter @Setter private String id;

  // main identification
  @XmlElement(name="Pesel")
  @Getter @Setter private String pesel;

  // document identification
  @XmlElement(name="IdentificationDoc")
  @Getter @Setter private IdentificationType identificationDoc;

  public Integer getAge() {
    if (this.pesel == null) {
      return null;
    }
    
    PeselValidator peselVal = new PeselValidator(this.pesel);
    if (!peselVal.isValid()) {
      return null;
    }

    // get year from pesel
    int y = peselVal.getBirthYear();

    // get current year
    int cy = Calendar.getInstance().get(Calendar.YEAR);

    return new Integer(cy - y);
  }
}
