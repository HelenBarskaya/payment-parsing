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
import java.util.List;

public class Validation {
    private Validator initValidator(File xsdFile) throws SAXException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source schemaFile = new StreamSource(xsdFile);
        Schema schema = factory.newSchema(schemaFile);
        return schema.newValidator();
    }

    public boolean isValid(File xsdFile, List<File> xmlFile) throws IOException {
        try {
            Validator validator = initValidator(xsdFile);
            for (File file : xmlFile) {
                validator.validate(new StreamSource(file));
            }
            return true;
        } catch (SAXException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
