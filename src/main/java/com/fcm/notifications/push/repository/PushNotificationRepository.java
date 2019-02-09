package com.fcm.notifications.push.repository;

import com.fcm.notifications.push.model.PushNotification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PushNotificationRepository extends CrudRepository<PushNotification, Long> {

        @Query(value = "SELECT p FROM PushNotification p WHERE  p.scheduleTime BETWEEN  :minTime AND :maxTime")
        List<PushNotification> getHourlyNotification(@Param("minTime") long minTime, @Param("maxTime") long maxTime);

}
