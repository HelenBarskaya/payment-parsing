package com.example.desctopparser.dto;



import lombok.Data;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class BSHead {
    @XmlAttribute(name = "RSys")
    private String rSys;

    @XmlAttribute(name = "ASys")
    private String aSys;

    @XmlAttribute(name = "route")
    private String route;

    @XmlElement(name = "Client")
    private Client client;

    @XmlElement(name = "Branch")
    private Branch branch;

    @XmlElement(name = "Office")
    private Office office;

}
