package lk.ijse.chatapp.dao.custom.impl;

import lk.ijse.chatapp.dao.SQLUntil;
import lk.ijse.chatapp.dao.custom.UserDAO;
import lk.ijse.chatapp.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean isSave(User entity) throws SQLException {
        return SQLUntil.execute("INSERT INTO user (user_name,password,photo) VALUES(?, ?, ?)",entity.getUser_name(),entity.getPassword(),entity.getPhoto());
    }

    @Override
    public User search(String id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<User> getAll() throws SQLException {
        return null;
    }
}
