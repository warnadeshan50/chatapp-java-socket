package lk.ijse.chatapp.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;



public class ClientFormController {
    @FXML
    private JFXTextArea client_txtArea;

    @FXML
    private Label client_lbl;

    @FXML
    private JFXTextField client_msg_txt;

    @FXML
    private JFXButton send_btn;

    Socket socket;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    String msg="";

    public void client_msg_txt(ActionEvent actionEvent) throws IOException {
        send_btnOnAction(actionEvent);
    }

    public void send_btnOnAction(ActionEvent actionEvent) throws IOException {
        String client_msg=client_msg_txt.getText();
        client_txtArea.appendText("\nClient :"+client_msg);
        dataOutputStream.writeUTF(client_msg_txt.getText().trim());
        dataOutputStream.flush();
        client_msg_txt.clear();

    }
    public void initialize(){
        new Thread(()-> {
            try {
                socket=new Socket("localhost",3003);
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream= new DataOutputStream(socket.getOutputStream());
                while (!msg.equals("finsh")){
                    msg = dataInputStream.readUTF();
                    client_txtArea.appendText("\nSever : "+msg);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }).start();
    }
}
