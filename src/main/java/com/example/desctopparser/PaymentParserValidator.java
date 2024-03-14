package com.example.desctopparser;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class PaymentParserValidator {
    private final Validator validator;

    public PaymentParserValidator(File xsdFile) throws SAXException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source schemaFile = new StreamSource(xsdFile);
        Schema schema = factory.newSchema(schemaFile);
        this.validator = schema.newValidator();
    }

    public boolean isValid(File xmlFile) {
        try {
            validator.validate(new StreamSource(xmlFile));
            return true;
        } catch (IOException | SAXException e) {
            return false;
        }
    }
}
