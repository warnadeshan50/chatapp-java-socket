package lk.ijse.chatapp.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<Type> extends SuperDAO {
    public boolean isSave(Type entity) throws SQLException;
    public Type search(String id) throws SQLException;
    public ArrayList<Type> getAll() throws SQLException;

}
