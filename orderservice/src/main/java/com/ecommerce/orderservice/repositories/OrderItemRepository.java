package com.ecommerce.orderservice.repositories;


import com.ecommerce.orderservice.entities.OrderItem;
import com.ecommerce.orderservice.responses.OrderItemResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findAllByOrderId(Integer orderId);
}
