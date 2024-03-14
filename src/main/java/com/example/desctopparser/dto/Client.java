package com.example.desctopparser.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Client {

    @XmlAttribute(name = "RCustID")
    private String rCustID;

    @XmlAttribute(name = "ACustID")
    private String aCustID;

    @XmlAttribute(name = "INN")
    private String inn;
}
