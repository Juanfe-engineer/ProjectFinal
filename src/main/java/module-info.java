module ProjectFinal {
    requires javax.mail;
    requires javafx.fxml;
    requires javafx.web;
    requires java.logging;
    requires javafx.controls;
    requires javafx.graphics;



    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens co.edu.uniquindio to javafx.fxml;
    exports co.edu.uniquindio;

    opens co.edu.uniquindio.Controller to javafx.fxml;
    opens co.edu.uniquindio.model to javafx.fxml;
    exports co.edu.uniquindio.model;
    exports co.edu.uniquindio.Controller;

    opens co.edu.uniquindio.viewController to javafx.fxml;

}