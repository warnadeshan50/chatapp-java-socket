package lk.ijse.chatapp.bo;

import lk.ijse.chatapp.bo.custom.impl.UserBOImpl;

public class BOFactory {
        private static BOFactory boFactory;

        private BOFactory() {

        }

        public static BOFactory getBOFactory() {
            if (boFactory == null) {
                return new BOFactory();
            } else {
                return boFactory;
            }
        }

        public enum BOType {
            USER
        }

        public SuperBO getBO(BOType type) {
            switch (type) {
                case USER:
                    return new UserBOImpl();
                default:
                    return null;
            }
        }
}
