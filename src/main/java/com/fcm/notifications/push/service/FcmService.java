package com.fcm.notifications.push.service;

import com.fcm.notifications.push.model.App;
import com.fcm.notifications.push.model.PushNotification;
import com.fcm.notifications.push.utils.Constants;
import com.fcm.notifications.push.utils.HttpCaller;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

@Service
public class FcmService {

    public void send(PushNotification pushNotification) throws JSONException {

        JSONObject body = new JSONObject();
        body.put("to", "/topics/" + Constants.TOPIC);
        body.put("collapse_key", "type_a");

        JSONObject data = new JSONObject();
        data.put("title", pushNotification.getTitle());
        data.put("body", pushNotification.getBody());
        data.put("url", pushNotification.getUrl());
        data.put("type", pushNotification.getType());
        body.put("data", data);

        App selectedApp = App.values()[pushNotification.getApp()];

        if (selectedApp == App.ALL) {
            for (int i = 1; i < App.values().length; i++) {
                sendToFcm(App.values()[i].getServerKey(), body.toString());
            }
            return;
        }

        sendToFcm(selectedApp.getServerKey(), body.toString());
    }

    private HashMap<String, String> getHeaders(String serverKey) {
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Authorization", "key=" + serverKey);
        headerMap.put("Content-Type", "application/json");
        return headerMap;
    }

    private void sendToFcm(String serverKey, String body) {
        try {
            HttpCaller.post(Constants.FIREBASE_API_URL, getHeaders(serverKey), body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
