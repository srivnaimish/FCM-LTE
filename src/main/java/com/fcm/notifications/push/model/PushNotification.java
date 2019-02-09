package com.fcm.notifications.push.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PushNotification {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private Integer app;
        private String type;
        private String title;
        private String body;
        private String url;
        private Long scheduleTime;

        public PushNotification(){}

        public PushNotification(int app,String type, long scheduleTime, String title, String body, String url) {
                this.app = app;
                this.type = type;
                this.scheduleTime = scheduleTime;
                this.title = title;
                this.body = body;
                this.url = url;
        }

        public int getApp() {
                return app;
        }

        public void setApp(int app) {
                this.app = app;
        }

        public long getScheduleTime() {
                return scheduleTime;
        }

        public void setScheduleTime(long scheduleTime) {
                this.scheduleTime = scheduleTime;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getBody() {
                return body;
        }

        public void setBody(String body) {
                this.body = body;
        }

        public String getUrl() {
                return url;
        }

        public void setUrl(String url) {
                this.url = url;
        }

        public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = type;
        }
}
