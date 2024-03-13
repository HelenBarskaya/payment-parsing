package com.example.desctopparser;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

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

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws IOException, SAXException {
                int totalFiles = 0;
                for (File xmlFile : xmlFiles) {
                    totalFiles += processFiles(xmlFile, xsdFiles[0]);
                    updateProgress(totalFiles, xmlFiles.length);
                    updateMessage("Processed " + totalFiles + " out of " + xmlFiles.length + " files");
                }
                return null;
            }
        };

        progressBar.progressProperty().bind(task.progressProperty());
        progressLabel.textProperty().bind(task.messageProperty());

        new Thread(task).start();
    }


    private int processFiles(File xmlFile, File xsdFile) throws IOException, SAXException {
        System.out.println(xsdFile.getName());
        System.out.println(xmlFile.getName());

        Validation validator = new Validation();
        System.out.println(validator.isValid(xsdFile, xmlFile));
        // temp mock
        return 0;
    }

    @FXML
    protected void onChooseDirectoryButtonClick() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);

        if (selectedDirectory != null) {
            directoryField.setText(selectedDirectory.getAbsolutePath());
        }
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}