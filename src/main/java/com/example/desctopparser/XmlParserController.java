package com.example.desctopparser;


import com.example.desctopparser.dto.BSMessage;
import com.example.desctopparser.dto.Document;
import com.example.desctopparser.dto.Documents;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Data
public class XmlParserController {

    @FXML
    private TextField directoryField;

    @FXML
    private Label resultLabel;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label progressLabel;

    private static final Logger logger = LoggerFactory.getLogger(XmlParserController.class);

    @FXML
    protected void onProcessButtonClick() {
        String directoryPath = directoryField.getText();
        if (directoryPath.isEmpty()) {
            resultLabel.setText("Пожалуйста введите путь к директории.");
            return;
        }

        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            resultLabel.setText("Неверный путь директории.");
            return;
        }

        // Поиск XSD-файла в директории
        File[] xsdFiles = directory.getAbsoluteFile().listFiles((dir, name) -> name.toLowerCase().endsWith(".xsd"));
        if (xsdFiles == null || xsdFiles.length == 0) {
            resultLabel.setText("В данной директории не найдена XSD схема.");
            return;
        }

        // Собираем список XML-файлов для обработки
        File[] xmlFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".xml"));
        if (xmlFiles == null || xmlFiles.length == 0) {
            resultLabel.setText("В данной директории отсутствуют XML файлы.");
            return;
        }
        if (xmlFiles.length > 10) {
            resultLabel.setText("Одна выгрузка не может содержать более 10 файлов.");
            return;
        }

        List<File> fileList = List.of(xmlFiles);
        Validation validator = new Validation();
        try {
            if (!validator.isValid(xsdFiles[0], fileList)) {
                resultLabel.setText("При обработке файлов возникла ошибка");
                return;
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                List<BSMessage> BSMessageList = new ArrayList<>();
                int totalFiles = 0;
                for (File xmlFile : fileList) {
                    BSMessageList.add(parseFile(xmlFile).orElseThrow());
                    totalFiles++;
                    updateProgress(totalFiles, xmlFiles.length);
                    updateMessage("Обработано" + totalFiles + " из " + xmlFiles.length + " файлов");
                }
                writeTotalDocument(BSMessageList, directoryPath);
                return null;
            }
        };

        progressBar.progressProperty().bind(task.progressProperty());
        progressLabel.textProperty().bind(task.messageProperty());

        task.setOnSucceeded(event -> resultLabel.setText("Файлы успешно обработаны"));

        new Thread(task).start();
    }

    private void writeTotalDocument(List<BSMessage> bsMessages, String filePath) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BSMessage.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            BSMessage first = bsMessages.get(0);
            BSMessage bsMessageTotal = new BSMessage();
            bsMessageTotal.setVersion(first.getVersion());
            bsMessageTotal.setId(first.getId());
            bsMessageTotal.setDateTime(first.getDateTime());
            bsMessageTotal.setBsHead(first.getBsHead());
            bsMessageTotal.setDocuments(getTotalDocuments(bsMessages));


            marshaller.marshal(bsMessageTotal, new File(filePath + "\\" + "Total.xml"));

            logger.info("Total XML сохранен в файл: " + filePath);

        } catch (JAXBException e) {
            logger.error(e.getMessage());
        }
    }

    private Documents getTotalDocuments(List<BSMessage> bsMessages) {
        Documents documents = new Documents();
        List<Document> collected = bsMessages.stream()
                .flatMap(message -> message.getDocuments().getDocument().stream())
                .collect(Collectors.toList());
        documents.setDocument(collected);
        return documents;
    }


    private Optional<BSMessage> parseFile(File xmlFile) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BSMessage.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            return Optional.ofNullable((BSMessage) jaxbUnmarshaller.unmarshal(xmlFile));

        } catch (JAXBException e) {
            logger.error(e.getMessage());
        }
        return Optional.empty();
    }

    @FXML
    protected void onChooseDirectoryButtonClick() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);

        if (selectedDirectory != null) {
            directoryField.setText(selectedDirectory.getAbsolutePath());
        }
    }

}