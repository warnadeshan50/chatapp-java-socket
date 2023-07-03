package lk.ijse.chatapp.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;

public class LoginFormController {
    @FXML
    private JFXButton add_pic;

    @FXML
    private ImageView imageview;

    @FXML
    private Circle circle;
    public void add_picOnAction(ActionEvent actionEvent) {
       //DirectoryChooser path =new DirectoryChooser();
        FileChooser file=new FileChooser();

    }
}
