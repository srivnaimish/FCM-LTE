package com.fcm.notifications.push.controller;

import com.fcm.notifications.push.component.EventCreator;
import com.fcm.notifications.push.model.App;
import com.fcm.notifications.push.model.NotificationType;
import com.fcm.notifications.push.model.Push;
import com.fcm.notifications.push.model.PushNotification;
import com.fcm.notifications.push.repository.PushNotificationRepository;
import com.fcm.notifications.push.service.FcmService;
import com.fcm.notifications.push.utils.Utils;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
public class PushNotificationController {


        @Autowired
        PushNotificationRepository pushNotificationRepository;

        @Autowired
        private FcmService fcmService;

        @GetMapping("/push")
        public String pushForm(Model model) {
                model.addAttribute("appNames", App.getAppNames());
                model.addAttribute("notificationTypes", NotificationType.getList());
                model.addAttribute("push", new Push());
                return "pushNotification";
        }

        @PostMapping("/push")
        public String pushSubmit(@ModelAttribute Push push, RedirectAttributes redirectAttributes) {
                if (Utils.isEmpty(push.getTime())) {
                        pushNow(push);
                        return "redirect:/push";
                }

                pushLater(push);
                return "redirect:/push";
        }

        private void pushNow(Push push) {
                int appId = App.getAppIdByName(push.getAppName());
                String type = NotificationType.getKeyByTitle(push.getType());
                PushNotification pushNotification = new PushNotification(appId, type,0, push.getTitle(), push.getBody(), push.getUrl());
            try {
                        fcmService.send(pushNotification);
                } catch (JSONException e) {
                        e.printStackTrace();
                }
        }

        private void pushLater(Push push) {
                int appId = App.getAppIdByName(push.getAppName());
                String type = NotificationType.getKeyByTitle(push.getType());

                Calendar calendar = Calendar.getInstance();

                try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
                        Date date = dateFormat.parse(push.getTime());
                        calendar.setTime(date);
                } catch (Exception ignored) {

                }

                long timeInMillis = calendar.getTimeInMillis() - 19800000;

                PushNotification pushNotification = new PushNotification(appId, type, timeInMillis, push.getTitle(), push.getBody(), push.getUrl());
                pushNotificationRepository.save(pushNotification);
        }
}
