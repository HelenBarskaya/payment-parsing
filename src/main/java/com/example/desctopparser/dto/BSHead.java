package com.example.desctopparser.dto;


import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
class BSHead {
    @XmlElement(name = "Client")
    private Client client;

    @XmlElement(name = "Branch")
    private Branch branch;

    @XmlElement(name = "Office")
    private Office office;

    @XmlAttribute(name = "RSys")
    private String rSys;

    @XmlAttribute(name = "ASys")
    private String aSys;

    @XmlAttribute(name = "route")
    private String route;
}
