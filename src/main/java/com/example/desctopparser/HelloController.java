package com.example.desctopparser;

import com.example.desctopparser.dto.PayDocRuReq;
import com.example.desctopparser.dto.TotalDocument;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import lombok.Data;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Data
public class HelloController {

    @FXML
    private TextField directoryField;

    @FXML
    private Label resultLabel;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label progressLabel;


    @FXML
    protected void onProcessButtonClick() {
        String directoryPath = directoryField.getText();
        if (directoryPath.isEmpty()) {
            resultLabel.setText("Please enter a directory path.");
            return;
        }

        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            resultLabel.setText("Invalid directory path.");
            return;
        }

        // Поиск XSD-файла в директории
        File[] xsdFiles = directory.getAbsoluteFile().listFiles((dir, name) -> name.toLowerCase().endsWith(".xsd"));
        if (xsdFiles == null || xsdFiles.length == 0) {
            resultLabel.setText("No XSD files found in the specified directory.");
            return;
        }

        // Собираем список XML-файлов для обработки
        File[] xmlFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".xml"));
        if (xmlFiles == null || xmlFiles.length == 0) {
            resultLabel.setText("No XML files found in the specified directory.");
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
            System.out.println(e.getMessage());
        }

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                List<PayDocRuReq> payDocRuReqList = new ArrayList<>();
                int totalFiles = 0;
                for (File xmlFile : fileList) {
                    payDocRuReqList.add(parseFile(xmlFile).orElseThrow());
                    totalFiles++;
                    updateProgress(totalFiles, xmlFiles.length);
                    updateMessage("Processed " + totalFiles + " out of " + xmlFiles.length + " files");
                }
                writeTotalDocument(payDocRuReqList, directoryPath);
                return null;
            }
        };

        progressBar.progressProperty().bind(task.progressProperty());
        progressLabel.textProperty().bind(task.messageProperty());

        new Thread(task).start();
    }

    private void writeTotalDocument(List<PayDocRuReq> payDocRuReqs, String filePath) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(TotalDocument.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            TotalDocument totalDocument = new TotalDocument();
            totalDocument.setPayDocRuReqs(payDocRuReqs);

            marshaller.marshal(totalDocument, new File(filePath +"\\"+ "Total.xml"));

            System.out.println("Total XML сохранен в файл: " + filePath);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


    private Optional<PayDocRuReq> parseFile(File xmlFile) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(PayDocRuReq.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            return Optional.ofNullable((PayDocRuReq) jaxbUnmarshaller.unmarshal(xmlFile));

        } catch (JAXBException e) {
            e.printStackTrace();
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