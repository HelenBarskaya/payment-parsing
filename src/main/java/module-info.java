module com.example.desctopparser {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.xml;

    opens com.example.desctopparser to javafx.fxml;
    exports com.example.desctopparser;
}