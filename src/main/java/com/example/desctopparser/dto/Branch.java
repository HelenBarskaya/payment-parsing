package com.example.desctopparser.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Branch {

    @XmlAttribute(name = "RBranchID")
    private String rBranchID;

    @XmlAttribute(name = "ABranchID")
    private String aBranchID;

    @XmlAttribute(name = "BIC")
    private String bic;
}
