module com.javaflix.javaflix {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires io.github.cdimascio.dotenv.java;
    requires org.postgresql.jdbc;
    requires java.compiler;
    requires java.desktop;
    requires javafx.swing;

    exports com.java.java.application;
    opens com.java.java.application to javafx.fxml;

    exports com.java.java.controllers;
    opens com.java.java.controllers to javafx.fxml;
}