package com.example.desctopparser.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class BSRequest {

    @XmlElement(name = "AMOUNT")
    private String amount;

    @XmlElement(name = "CBCCODE")
    private String cbccode;

    @XmlElement(name = "CURRCODE")
    private String currcode;

    @XmlElement(name = "CUSTID")
    private String custid;

    @XmlElement(name = "IP")
    private String ip;

    @XmlElement(name = "DOCDATEPARAM1")
    private String docdateparam1;

    @XmlElement(name = "DOCDATEPARAM2")
    private String docdateparam2;

    @XmlElement(name = "DOCDATEPARAM3")
    private String docdateparam3;

    @XmlElement(name = "DOCNUMPARAM1")
    private String docnumparam1;

    @XmlElement(name = "DOCNUMPARAM2")
    private String docnumparam2;

    @XmlElement(name = "GROUND")
    private String ground;

    @XmlElement(name = "OKATOCODE")
    private String okatocode;

    @XmlElement(name = "OPERTYPE")
    private String opertype;

    @XmlElement(name = "PAYGRNDPARAM")
    private String paygrndparam;

    @XmlElement(name = "PAYTYPEPARAM")
    private String paytypeparam;

    @XmlElement(name = "PAYUNTIL")
    private String payuntil;

    @XmlElement(name = "PAYER")
    private String payer;

    @XmlElement(name = "PAYERACCOUNT")
    private String payeraccount;

    @XmlElement(name = "PAYERBIC")
    private String payerbic;

    @XmlElement(name = "PAYERBANKNAME")
    private String payerbankname;

    @XmlElement(name = "PAYERBANKTYPE")
    private String payerbanktype;

    @XmlElement(name = "PAYERCORRACCOUNT")
    private String payercorraccount;

    @XmlElement(name = "PAYERINN")
    private String payerinn;

    @XmlElement(name = "PAYERKPP")
    private String payerkpp;

    @XmlElement(name = "PAYERPLACE")
    private String payerplace;

    @XmlElement(name = "PAYERPLACETYPE")
    private String payerplacetype;

    @XmlElement(name = "PAYERPROPERTYTYPE")
    private String payerpropertytype;

    @XmlElement(name = "PAYMENTURGENT")
    private String paymenturgent;

    @XmlElement(name = "RECEIVER")
    private String receiver;

    @XmlElement(name = "RECEIVERACCOUNT")
    private String receiveraccount;

    @XmlElement(name = "RECEIVERBIC")
    private String receiverbic;

    @XmlElement(name = "RECEIVERBANKNAME")
    private String receiverbankname;

    @XmlElement(name = "RECEIVERBANKTYPE")
    private String receiverbanktype;

    @XmlElement(name = "RECEIVERCORRACCOUNT")
    private String receivercorraccount;

    @XmlElement(name = "RECEIVERINN")
    private String receiverinn;

    @XmlElement(name = "RECEIVERKPP")
    private String receiverkpp;

    @XmlElement(name = "RECEIVERPLACE")
    private String receiverplace;

    @XmlElement(name = "RECEIVERPLACETYPE")
    private String receiverplacetype;

    @XmlElement(name = "RECEIVERPROPERTYTYPE")
    private String receiverpropertytype;

    @XmlElement(name = "SENDTYPE")
    private String sendtype;

    @XmlElement(name = "STAT1256")
    private String stat1256;

    @XmlElement(name = "TAXPERIODPARAM1")
    private String taxperiodparam1;

    @XmlElement(name = "TAXPERIODPARAM2")
    private String taxperiodparam2;

    @XmlElement(name = "TAXPERIODPARAM3")
    private String taxperiodparam3;

    @XmlElement(name = "DATETIMERECEIVE")
    private String datetimereceive;

    @XmlElement(name = "SERVICE")
    private String service;

    @XmlElement(name = "CODEUIP")
    private String codeuip;

    @XmlElement(name = "SIGNUID1")
    private String signuid1;

    @XmlElement(name = "SIGNNAME1")
    private String signname1;

    @XmlElement(name = "STATUS")
    private String status;

    @XmlElement(name = "SENDNUMBER")
    private String sendnumber;

    @XmlElement(name = "RECEIVENUMBER")
    private String receivenumber;

    @XmlElement(name = "DOCREF")
    private String docref;

    @XmlElement(name = "DOCUMENTDATE")
    private String documentdate;

    @XmlElement(name = "DOCUMENTNUMBER")
    private String documentnumber;

    @XmlElement(name = "DELIVERYTYPE")
    private String deliverytype;

    @XmlElement(name = "CODEMESSAGE")
    private String codemessage;

    @XmlElement(name = "MESSAGEFORBANK")
    private String messageforbank;

    @XmlElement(name = "DOCRECID")
    private String docrecid;

    @XmlElement(name = "CODEPURPOSE")
    private String codepurpose;
}
