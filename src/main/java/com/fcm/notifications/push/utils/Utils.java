package com.fcm.notifications.push.utils;

public class Utils {
        public static boolean isEmpty(CharSequence charSequence) {
                if (charSequence == null) {
                        return true;
                }
                return charSequence.toString().equalsIgnoreCase("") ||
                        charSequence.toString().equalsIgnoreCase("null");
        }

        public static <S, T extends Iterable<S>> boolean isEmpty(T argument) {
                return (argument == null) || !argument.iterator().hasNext();
        }

        public static long currentTimeMillis() {
                return System.currentTimeMillis() + 19800000;
        }
}
