package com.example.desctopparser;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Slf4j
public class Validation {
    private static final Logger logger = LoggerFactory.getLogger(Validation.class);

    private Validator initValidator(File xsdFile) throws SAXException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source schemaFile = new StreamSource(xsdFile);
        Schema schema = factory.newSchema(schemaFile);
        return schema.newValidator();
    }

    public boolean isValid(File xsdFile, List<File> xmlFiles) throws IOException {
        File notValidFile = null;
        try {
            Validator validator = initValidator(xsdFile);
            for (File file : xmlFiles) {
                notValidFile = file;
                validator.validate(new StreamSource(file));
                logger.info(String.format("Файл %s прошел проверку", file.getName()));
            }
            return true;
        } catch (SAXException e) {
            logger.error(String.format("Файл %s не прошел проверку", notValidFile.getName()));
            return false;
        }
    }
}
