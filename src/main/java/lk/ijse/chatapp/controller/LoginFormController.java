package lk.ijse.chatapp.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.ijse.chatapp.bo.BOFactory;
import lk.ijse.chatapp.bo.custom.UserBO;
import lk.ijse.chatapp.dto.UserDTO;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class LoginFormController implements Initializable {
    @FXML
    private JFXButton add_pic;

    @FXML
    private JFXButton save_btn;


    @FXML
    private Circle circle;

    @FXML
    private JFXButton login_btn;

    @FXML
    private JFXTextField username_txt;

    @FXML
    private JFXPasswordField password_txt;

    @FXML
    private Label username_lbl;

    @FXML
    private JFXButton create_account_btn;

    @FXML
    private Label password_lbl;

    @FXML
    private JFXButton back_btn;

    UserBO userBO=(UserBO) BOFactory.getBOFactory().getBO(BOFactory.BOType.USER);
    static File selectedFiled;
    static String password,client_name;


    public void add_picOnAction(ActionEvent actionEvent) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose your image");
            fileChooser.setInitialDirectory(new File("D:\\"));
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG image", "*.jpg"), new FileChooser.ExtensionFilter("PNG image", "*.png"), new FileChooser.ExtensionFilter("All images", "*.jpg", "*.png"));
            selectedFiled = fileChooser.showOpenDialog(new Stage());
            if (selectedFiled != null) {
                Image image = new Image(selectedFiled.getPath());
                circle.setFill(new ImagePattern(image));
            }
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Add a photo");
        }
    }

    public void save_btnOnAction(ActionEvent actionEvent) throws FileNotFoundException, SQLException {
        String user,password,path;
        user=username_txt.getText();
        password=password_txt.getText();
        try{
        path=selectedFiled.getAbsolutePath();
        FileInputStream fileInputStream=new FileInputStream(path);

            boolean saveUser = userBO.isSaveUser(new UserDTO(user, password, fileInputStream));
            if (saveUser) {
                new Alert(Alert.AlertType.CONFIRMATION,"User save successfully!!").show();
            }
        }catch (SQLException e ){
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR,"Something went wrong!!").show();
        }

    }

    public void username_txtOnAction(ActionEvent actionEvent) throws FileNotFoundException {
        String username = username_txt.getText();
        try{
            UserDTO userDTO =userBO.searchUser(username);
            if (userDTO!=null){
                password = userDTO.getPassword();
                client_name = userDTO.getUser_name();
                login_btn.setVisible(true);
                password_txt.setVisible(true);
                username_lbl.setVisible(false);
            }else{
                new Alert(Alert.AlertType.ERROR,"No User Founded !!!").show();
                username_lbl.setVisible(true);
                password_txt.setVisible(false);
                password_txt.clear();
                password_lbl.setVisible(false);
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,"Something went wrong!!! ").show();
        }
    }

    public void password_txtOnAction(ActionEvent actionEvent) throws IOException {
        checkpasword();
    }
    void hideTxtNbtn(boolean visible){
        password_txt.setVisible(visible);
        login_btn.setVisible(visible);
        save_btn.setVisible(visible);
        add_pic.setVisible(visible);
        username_lbl.setVisible(visible);
        password_lbl.setVisible(visible);
        back_btn.setVisible(visible);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hideTxtNbtn(false);
    }

    public void login_btnOnAction(ActionEvent actionEvent) throws IOException {
        checkpasword();
    }
    void checkpasword() throws IOException {
        String typePass=password_txt.getText();
        if(typePass.equals(password)){
            password_lbl.setVisible(false);
            System.out.println(password);
            username_txt.clear();
            password_txt.clear();
            Parent parent =FXMLLoader.load(getClass().getResource("/view/client_form.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(parent);

            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle(client_name+" Client Form");
            stage. setResizable(false);
            stage.show();
        }else{
            System.out.println("password is wrong password"+password);
            password_lbl.setVisible(true);
            password_txt.clear();
        }
    }

    public void create_account_btnOnAction(ActionEvent actionEvent) {
        create_account_btn.setVisible(false);
        add_pic.setVisible(true);
        password_txt.setVisible(true);
        save_btn.setVisible(true);
        back_btn.setVisible(true);
        login_btn.setVisible(false);
        username_txt.clear();
        password_txt.clear();
    }

    public void back_btnOnAction(ActionEvent actionEvent) {
        back_btn.setVisible(false);
        create_account_btn.setVisible(true);
        login_btn.setVisible(false);
        add_pic.setVisible(false);
        save_btn.setVisible(false);
        username_txt.clear();
        password_txt.clear();
        password_txt.setVisible(false);
    }
}
