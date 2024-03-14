package com.example.desctopparser.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Office {

    @XmlAttribute(name = "Officecode")
    private String officecode;

    @XmlAttribute(name = "Robotuser")
    private String robotuser;
}
