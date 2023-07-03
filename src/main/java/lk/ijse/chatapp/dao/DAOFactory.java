package lk.ijse.chatapp.dao;

import lk.ijse.chatapp.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){}

    public static DAOFactory getDaoFactory() {
        if (daoFactory == null) {
            return new DAOFactory();
        }
        return daoFactory;
    }
    public enum DAOTypes{
       USER;
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }
}
