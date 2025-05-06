module edu.miracosta.cs112.finalproject.finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens edu.miracosta.cs112.finalproject.finalproject to javafx.fxml;
    exports edu.miracosta.cs112.finalproject.finalproject;
    exports edu.miracosta.cs112.finalproject.finalproject.Models;
    opens edu.miracosta.cs112.finalproject.finalproject.Models to javafx.fxml;
}