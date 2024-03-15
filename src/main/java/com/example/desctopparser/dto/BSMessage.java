package com.example.desctopparser.dto;




import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BSMessage", namespace = "BS_R_PAYDOCRU")
@XmlAccessorType(XmlAccessType.FIELD)
public class BSMessage {
    @XmlAttribute(name = "Version")
    private String version;

    @XmlAttribute(name = "ID")
    private String id;

    @XmlAttribute(name = "DateTime")
    private String dateTime;

    @XmlElement(name = "BSHead")
    private BSHead bsHead;

    @XmlElement(name = "DOCUMENTS")
    private Documents documents;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public BSHead getBsHead() {
        return bsHead;
    }

    public void setBsHead(BSHead bsHead) {
        this.bsHead = bsHead;
    }

    public Documents getDocuments() {
        return documents;
    }

    public void setDocuments(Documents documents) {
        this.documents = documents;
    }
}