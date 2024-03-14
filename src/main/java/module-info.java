module com.example.desctopparser {
    exports com.example.desctopparser;
    exports com.example.desctopparser.dto;

    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.xml;
    requires java.xml.bind;
    requires lombok;

    opens com.example.desctopparser to javafx.fxml, java.xml.bind;
    opens com.example.desctopparser.dto to javafx.fxml, java.xml.bind;
}