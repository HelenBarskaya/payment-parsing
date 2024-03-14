package com.example.desctopparser.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "testName")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class TotalDocument {
    @XmlElement(name = "Document")
    List<PayDocRuReq> payDocRuReqs;

}
