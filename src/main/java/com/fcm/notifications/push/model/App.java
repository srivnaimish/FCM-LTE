package com.fcm.notifications.push.model;

import java.util.ArrayList;
import java.util.List;

public enum App {
        ALL("All", ""),
        EASY_MESSENGER("Easy Messenger", "AIzaSyDzAS9Ne8fQKeCSY1rD39v8_2KHQY5M0uU"),
        MESSENGER("Messenger", "AIzaSyAMAhfDFHPqSQN015NgDTvMMgmtjJoBy3A"),
        NOTIKEEP("Notikeep", "AIzaSyCbkzhxRA9pqsE9bDD5eMAWs6DRHECOZeY");

        //STICKERS("Stickers", "AIzaSyA4xNaQ-Xgi19Ykfb_Yg8EzBUsS4t1rAow");

        String appName;
        String serverKey;

        App(String appName, String serverKey) {
                this.appName = appName;
                this.serverKey = serverKey;
        }

        public static List<String> getAppNames() {
                List<String> appNames = new ArrayList<>();
                for (App app : values()) {
                        appNames.add(app.getAppName());
                }
                return appNames;
        }

        public static int getAppIdByName(String appName) {
                switch (appName) {
                        case "Easy Messenger":
                                return EASY_MESSENGER.ordinal();
                        case "Notikeep":
                                return NOTIKEEP.ordinal();
                        case "Messenger":
                                return MESSENGER.ordinal();
                        default:
                                return ALL.ordinal();
                }
        }

        public String getAppName() {
                return appName;
        }

        public String getServerKey() {
                return serverKey;
        }
}
