package lk.ijse.chatapp.bo.custom.impl;

import lk.ijse.chatapp.bo.custom.UserBO;
import lk.ijse.chatapp.dao.DAOFactory;
import lk.ijse.chatapp.dao.custom.UserDAO;
import lk.ijse.chatapp.dao.custom.impl.UserDAOImpl;
import lk.ijse.chatapp.dto.UserDTO;
import lk.ijse.chatapp.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBOImpl implements UserBO {
    UserDAO userDAO =(UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean isSaveUser(UserDTO dto) throws SQLException {
        return userDAO.isSave(new User(dto.getUser_name(), dto.getPassword(), dto.getPhoto()));
    }

    @Override
    public UserDTO searchUser(String id) throws SQLException {
        User user =userDAO.search(id);
        if(user!=null){
            return new UserDTO(user.getUser_name(),user.getPassword(), user.getPhoto());
        }
        return null;
    }
}
