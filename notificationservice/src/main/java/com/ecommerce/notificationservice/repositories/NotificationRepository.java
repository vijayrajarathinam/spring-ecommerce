package com.ecommerce.notificationservice.repositories;

import com.ecommerce.notificationservice.entities.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification,String> {
}
