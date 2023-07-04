package lk.ijse.chatapp.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.ijse.chatapp.bo.BOFactory;
import lk.ijse.chatapp.bo.custom.UserBO;
import lk.ijse.chatapp.dto.UserDTO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;


public class LoginFormController {
    @FXML
    private JFXButton add_pic;

    @FXML
    private ImageView imageview;

    @FXML
    private Circle circle;
    @FXML
    private JFXTextField username_txt;

    @FXML
    private JFXPasswordField password_txt;

    UserBO userBO=(UserBO) BOFactory.getBOFactory().getBO(BOFactory.BOType.USER);
    static File selectedFiled;

    public void add_picOnAction(ActionEvent actionEvent) {
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Choose your image");
        fileChooser.setInitialDirectory(new File("D:\\"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG image","*.jpg"),new FileChooser.ExtensionFilter("PNG image","*.png"),new FileChooser.ExtensionFilter("All images","*.jpog","*.png"));
        selectedFiled = fileChooser.showOpenDialog(new Stage());
        if(selectedFiled != null){
            Image image=new Image(selectedFiled.getPath());
            imageview.setImage(image);
            circle.setFill(new ImagePattern(image));

        }
    }

    public void save_btnOnAction(ActionEvent actionEvent) throws FileNotFoundException, SQLException {
        String user,password,path;
        user=username_txt.getText();
        password=password_txt.getText();
        path=selectedFiled.getAbsolutePath();
        FileInputStream fileInputStream=new FileInputStream(path);
        try {
            boolean saveUser = userBO.isSaveUser(new UserDTO(user, password, fileInputStream));
            if (saveUser) {
                new Alert(Alert.AlertType.CONFIRMATION,"User save successfully!!").show();
            }
        }catch (SQLException e ){
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR,"Something went wrong!!").show();
        }

    }
}
