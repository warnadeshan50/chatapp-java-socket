package lk.ijse.chatapp.entity;

import com.mysql.cj.jdbc.Blob;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;

@Data
@AllArgsConstructor
public class User {
    String user_name;
    String password;
    FileInputStream photo;
}
