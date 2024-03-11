module com.app.javafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens ru.aston.collector to javafx.fxml;
    exports ru.aston.collector;
}