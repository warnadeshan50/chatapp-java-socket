package lk.ijse.chatapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.FileInputStream;
import java.io.InputStream;

@Data
@AllArgsConstructor
public class UserDTO {
    String user_name;
    String password;
    InputStream photo;
}
