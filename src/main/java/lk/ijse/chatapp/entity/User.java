package lk.ijse.chatapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.image.BufferedImage;

@Data
@AllArgsConstructor
public class User {
    String user_name;
    String password;
    BufferedImage photo;
}
