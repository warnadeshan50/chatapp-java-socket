package lk.ijse.chatapp.bo.custom;

import lk.ijse.chatapp.bo.SuperBO;
import lk.ijse.chatapp.dto.UserDTO;

import java.sql.SQLException;

public interface UserBO extends SuperBO {
    public boolean isSaveUser(UserDTO dto) throws SQLException;
    public UserDTO searchUser(String id)throws SQLException;
}
