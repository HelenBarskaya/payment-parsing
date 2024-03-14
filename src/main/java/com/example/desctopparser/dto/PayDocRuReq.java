package com.example.desctopparser.dto;




import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BSMessage")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class PayDocRuReq {
    @XmlAttribute(name = "Version")
    private String version;

    @XmlAttribute(name = "ID")
    private String id;

    @XmlAttribute(name = "DateTime")
    private String dateTime;

    @XmlElement(name = "BSHead")
    private BSHead bsHead;

    @XmlElement(name = "BSRequest")
    private BSRequest bsRequest;

    public BSHead getBsHead() {
        return bsHead;
    }

    public void setBsHead(BSHead bsHead) {
        this.bsHead = bsHead;
    }

    public BSRequest getBsRequest() {
        return bsRequest;
    }

    public void setBsRequest(BSRequest bsRequest) {
        this.bsRequest = bsRequest;
    }
}