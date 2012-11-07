package org.be3form.validation.facts;

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
@EqualsAndHashCode()
@NoArgsConstructor()
@AllArgsConstructor()
@XmlType(name="IdentificationType", namespace="http://validation.be3form.org")
@XmlAccessorType(XmlAccessType.FIELD)
public class IdentificationType {

  // identification document type
  @XmlElement(name="Type", required = true)
  @Getter @Setter private String type;

  // Identification document value
  @XmlElement(name="Value", required = true)
  @Getter @Setter private String value;
}
