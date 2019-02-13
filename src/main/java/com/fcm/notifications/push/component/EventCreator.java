package com.fcm.notifications.push.component;

import com.fcm.notifications.push.model.PushNotification;
import com.fcm.notifications.push.repository.PushNotificationRepository;
import com.fcm.notifications.push.service.FcmService;
import com.fcm.notifications.push.utils.Utils;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class EventCreator {
        private static final Logger LOG = LoggerFactory.getLogger(EventCreator.class);

        @Autowired
        private PushNotificationRepository pushNotificationRepository;

        @Autowired
        private FcmService fcmService;

        @Scheduled(cron = "0 0/30 * * * *")
        public void create() {
                long minTime = Utils.currentTimeMillis() - 900000;
                long maxTime = Utils.currentTimeMillis() + 900000;

                List<PushNotification> pushNotifications = pushNotificationRepository.getHourlyNotification(minTime, maxTime);
                if (pushNotifications == null || pushNotifications.size() == 0) {
                        return;
                }

                for (PushNotification p : pushNotifications) {
                        try {
                                fcmService.send(p);
                        } catch (JSONException e) {
                                e.printStackTrace();
                                return;
                        }
                        pushNotificationRepository.delete(p);
                }
        }
}
