package com.example.desctopparser.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Document {
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

    @XmlElement(name = "MAC")
    private String mac;

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
    private String payerAccount;

    @XmlElement(name = "PAYERBIC")
    private String payerBic;

    @XmlElement(name = "PAYERBANKNAME")
    private String payerBankName;

    @XmlElement(name = "PAYERBANKTYPE")
    private String payerBankType;

    @XmlElement(name = "PAYERCORRACCOUNT")
    private String payerCorrAccount;

    @XmlElement(name = "PAYERINN")
    private String payerInn;

    @XmlElement(name = "PAYERKPP")
    private String payerKpp;

    @XmlElement(name = "PAYERPLACE")
    private String payerPlace;

    @XmlElement(name = "PAYERPLACETYPE")
    private String payerPlaceType;

    @XmlElement(name = "PAYERPROPERTYTYPE")
    private String payerPropertyType;

    @XmlElement(name = "PAYMENTURGENT")
    private String paymentUrgent;

    @XmlElement(name = "RECEIVER")
    private String receiver;

    @XmlElement(name = "RECEIVERACCOUNT")
    private String receiverAccount;

    @XmlElement(name = "RECEIVERBIC")
    private String receiverBic;

    @XmlElement(name = "RECEIVERBANKNAME")
    private String receiverBankName;

    @XmlElement(name = "RECEIVERBANKTYPE")
    private String receiverBankType;

    @XmlElement(name = "RECEIVERCORRACCOUNT")
    private String receiverCorrAccount;

    @XmlElement(name = "RECEIVERINN")
    private String receiverInn;

    @XmlElement(name = "RECEIVERKPP")
    private String receiverKpp;

    @XmlElement(name = "RECEIVERPLACE")
    private String receiverPlace;

    @XmlElement(name = "RECEIVERPLACETYPE")
    private String receiverPlaceType;

    @XmlElement(name = "RECEIVERPROPERTYTYPE")
    private String receiverPropertyType;

    @XmlElement(name = "SENDTYPE")
    private String sendType;

    @XmlElement(name = "SIGNUID3")
    private String signUid3;

    @XmlElement(name = "SIGNNAME3")
    private String signName3;

    @XmlElement(name = "STAT1256")
    private String stat1256;

    @XmlElement(name = "TAXPERIODPARAM1")
    private String taxPeriodParam1;

    @XmlElement(name = "TAXPERIODPARAM2")
    private String taxPeriodParam2;

    @XmlElement(name = "TAXPERIODPARAM3")
    private String taxPeriodParam3;

    @XmlElement(name = "DATETIMERECEIVE")
    private String dateTimeReceive;

    @XmlElement(name = "SERVICE")
    private String service;

    @XmlElement(name = "CODEUIP")
    private String codeUip;

    @XmlElement(name = "STATUS")
    private String status;

    @XmlElement(name = "SENDNUMBER")
    private String sendNumber;

    @XmlElement(name = "RECEIVENUMBER")
    private String receiveNumber;

    @XmlElement(name = "DOCREF")
    private String docRef;

    @XmlElement(name = "DOCUMENTDATE")
    private String documentDate;

    @XmlElement(name = "DOCUMENTNUMBER")
    private String documentNumber;

    @XmlElement(name = "DELIVERYTYPE")
    private String deliveryType;

    @XmlElement(name = "CODEMESSAGE")
    private String codeMessage;

    @XmlElement(name = "MESSAGEFORBANK")
    private String messageForBank;

    @XmlElement(name = "DOCRECID")
    private String docRecId;

    @XmlElement(name = "RESFIELD")
    private String resField;

    @XmlElement(name = "SIGNHASH")
    private String signHash;
}
