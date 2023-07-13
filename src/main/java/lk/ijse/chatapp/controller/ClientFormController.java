package lk.ijse.chatapp.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;



public class ClientFormController extends Thread {
    @FXML
    private JFXTextArea client_txtArea;

    @FXML
    private Label client_lbl;

    @FXML
    private JFXTextField client_msg_txt;

    @FXML
    private JFXButton send_btn;

    @FXML
    private JFXButton image_send_btn;

    @FXML
    VBox vBox;

    Socket socket;
    BufferedReader bufferedReader;
    PrintWriter printWriter;
    File filePath;


    public void client_msg_txt(ActionEvent actionEvent) throws IOException {
        send_btnOnAction(actionEvent);
    }

    public void send_btnOnAction(ActionEvent actionEvent) throws IOException {
        String client_msg = client_msg_txt.getText();
        printWriter.println(client_lbl.getText() + ": "+client_msg);
        client_msg_txt.clear();
        if (client_msg.equalsIgnoreCase("END CHAT")){
            System.exit(0);
        }
    }
    public void initialize(){
        String user = LoginFormController.client_name;
        client_lbl.setText(user);
        try{
            socket = new Socket("localhost",3004);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(),true);
            this.start();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true){

                String msg = bufferedReader.readLine();
                String[] tokens = msg.split( " ");
                String cmd = tokens[0];

                StringBuilder fullMsg = new StringBuilder();
                for (int i = 1; i < tokens.length ; i++) {
                    fullMsg.append(tokens[i]+" ");
                }

                String[] msgToAr = msg.split(" ");
                String st = "";
                for (int i = 0; i < msgToAr.length-1; i++) {
                    st += msgToAr[i+1]+" ";
                }

                Text text = new Text(st);
                String firstChars = "";
                if(st.length() > 3){
                    firstChars = st.substring(0, 3);
                }

                //for Images
                if(firstChars.equalsIgnoreCase("img")){
                    st = st.substring(3, st.length()-1);

                    File file = new File(st);
                    Image image = new Image(file.toURI().toString());

                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(150);
                    imageView.setFitHeight(200);

                    HBox hbox = new HBox(10);
                    hbox.setAlignment(Pos.BOTTOM_RIGHT);

                    if(!cmd.equalsIgnoreCase(client_lbl.getText())){
                        vBox.setAlignment(Pos.TOP_LEFT);
                        hbox.setAlignment(Pos.CENTER_LEFT);
                        Text text1 = new Text(" "+ cmd + " :");
                        hbox.getChildren().add(text1);
                        hbox.getChildren().add(imageView);
                    } else {
                        hbox.setAlignment(Pos.BOTTOM_RIGHT);
                        hbox.getChildren().add(imageView);
                        Text text1 = new Text(": Me ");
                        hbox.getChildren().add(text1);
                    }
                    Platform.runLater(() -> vBox.getChildren().addAll(hbox));
                } else {
                    TextFlow tempFlow = new TextFlow();
                    if (!cmd.equalsIgnoreCase(client_lbl.getText() + " :")){
                        Text textName = new Text(cmd + " ");
                        textName.getStyleClass().add(cmd + " ");
                        tempFlow.getChildren().add(textName);
                    }
                    tempFlow.getChildren().add(text);
                    tempFlow.setMaxWidth(200);
                    TextFlow flow = new TextFlow(tempFlow);
                    HBox hbox = new HBox(12);

                    if (!cmd.equalsIgnoreCase(client_lbl.getText() + ":")){
                        vBox.setAlignment(Pos.BOTTOM_LEFT);
                        hbox.setAlignment(Pos.CENTER_LEFT);
                        hbox.getChildren().add(flow);
                    } else {
                        Text text2 = new Text(fullMsg+": Me");
                        TextFlow flow2 = new TextFlow(text2);
                        hbox.setAlignment(Pos.BOTTOM_RIGHT);
                        hbox.getChildren().add(flow2);
                    }
                    Platform.runLater(() -> vBox.getChildren().addAll(hbox));
                }
            }
        }catch (Exception e) {
            System.out.println(e+"ftcytf");
        }
    }

    public void image_send_btnOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        this.filePath = fileChooser.showOpenDialog(stage);
        printWriter.println(client_lbl.getText() + " " + "img" +filePath.getPath());
    }
}
