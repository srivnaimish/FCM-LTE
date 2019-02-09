package com.fcm.notifications.push.model;

public class Push {
        private String appName;
        private String type;
        private String time;
        private String title;
        private String body;
        private String url;

        public String getAppName() {
                return appName;
        }

        public void setAppName(String appName) {
                this.appName = appName;
        }

        public String getTime() {
                return time;
        }

        public void setTime(String time) {
                this.time = time;
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

        @Override
        public String toString() {
                return "Push{" +
                        "appName='" + appName + '\'' +
                        ", type='" + type + '\'' +
                        ", time='" + time + '\'' +
                        ", title='" + title + '\'' +
                        ", body='" + body + '\'' +
                        ", url='" + url + '\'' +
                        '}';
        }
}
