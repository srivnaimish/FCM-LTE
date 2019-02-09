package com.fcm.notifications.push.model;

import java.util.ArrayList;
import java.util.List;

public enum NotificationType {
    WEB_OPEN("Open Web", "webOpen"),
    APP_OPEN("Open App", "appOpen");

    private final String title;
    private final String key;

    NotificationType(String title, String key) {
        this.title = title;
        this.key = key;
    }

    public static List<String> getList() {
        List<String> types = new ArrayList<>();
        for (NotificationType notificationType : values()) {
            types.add(notificationType.getTitle());
        }
        return types;
    }

    public static String getKeyByTitle(String type) {
            switch (type) {
                case "Open App":
                    return APP_OPEN.getKey();
                default:
                    return WEB_OPEN.getKey();
            }
    }

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }
}
