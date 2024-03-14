package com.example.desctopparser;

import com.example.desctopparser.exceptions.PaymentParsingException;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

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
        resultLabel.setText(null);
        try {
            //Получаем рабочую директорию
            File workingDirectory = getDirectory(getPath());
            // Создание валидатора из найденного XSD файла
            PaymentParserValidator validator = new PaymentParserValidator(findXSDSchema(workingDirectory));
            // Собираем список XML-файлов для обработки
            List<File> xmlFiles = findAllXmlFiles(workingDirectory);
            AtomicInteger totalFiles = new AtomicInteger(0);
            Task<Void> task = new Task<>() {
                @Override
                protected Void call() throws IOException {
                    try (FileWriter fileWriter = new FileWriter(workingDirectory.getAbsolutePath() + "/Total.xml")) {
                        fileWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<DOCUMENTS>\n");
                        xmlFiles
                                .stream()
                                .peek(file -> System.out.println(LocalDateTime.now() + " Обработка файла: " + file.getName()))
                                .filter(this::isValid)
                                .forEach(file -> {
                                    writeFile(fileWriter, file);
                                    totalFiles.incrementAndGet();
                                    updateMessage("Processed " + totalFiles + " out of " + xmlFiles.size() + " files");
                                    updateProgress(totalFiles.get(), xmlFiles.size());
                                });
                        fileWriter.write("</DOCUMENTS>");
                        updateMessage("Файлы успешно обработаны");
                    } catch (Exception e) {
                        updateMessage("При обработке файлов возникла ошибка");
                        updateProgress(0, xmlFiles.size());
                        Files.delete(Path.of(workingDirectory.getAbsolutePath() + "/Total.xml"));
                    }
                    return null;
                }

                private boolean isValid(File file) {
                    if (validator.isValid(file)) {
                        System.out.println(LocalDateTime.now() + " Файл " + file.getName() + " прошел проверку");
                        return true;
                    }
                    System.out.println(LocalDateTime.now() + " Файл: " + file.getName() + " не прошел проверку");
                    throw new RuntimeException();
                }

                private void writeFile(FileWriter fileWriter, File file) {
                    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
                        bufferedReader.readLine();
                        fileWriter.write("<DOCUMENT>");
                        while (bufferedReader.ready()) {
                            fileWriter.write(bufferedReader.readLine());
                        }
                        fileWriter.write("</DOCUMENT>");
                    } catch (IOException e) {
                        updateMessage("При обработке файлов возникла ошибка");
                    }
                }
            };

            progressBar.progressProperty().bind(task.progressProperty());
            progressLabel.textProperty().bind(task.messageProperty());

            new Thread(task).start();
        } catch (PaymentParsingException e) {
            resultLabel.setText(e.getMessage());
        } catch (Exception e) {
            resultLabel.setText("Internal error.");
        }
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

    private String getPath() {
        return Optional.ofNullable(directoryField.getText())
                .filter(s -> !s.isEmpty())
                .orElseThrow(() -> new PaymentParsingException("Please enter a directory path."));
    }

    private File getDirectory(String path) {
        return Optional.of(path)
                .map(File::new)
                .filter(File::exists)
                .filter(File::isDirectory)
                .orElseThrow(() -> new PaymentParsingException("Invalid directory path."));
    }

    private File findXSDSchema(File workingDirectory) {
        return Optional.ofNullable(workingDirectory.getAbsoluteFile().listFiles(
                        (dir, name) -> name.toLowerCase().endsWith(".xsd")
                ))
                .map(Arrays::stream)
                .map(Stream::findAny)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .orElseThrow(() -> new PaymentParsingException("No XSD files found in the specified directory."));
    }

    private List<File> findAllXmlFiles(File workingDirectory) {
        return Optional.ofNullable(workingDirectory.getAbsoluteFile().listFiles(
                        (dir, name) -> name.toLowerCase().endsWith(".xml")
                ))
                .map(Arrays::stream)
                .map(Stream::toList)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new PaymentParsingException("No XML files found in the specified directory."));
    }
}