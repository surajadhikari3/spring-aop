package io.reactivestax.order.service;

import io.reactivestax.order.domain.OrderDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {
    private static final Log log = LogFactory.getLog(OrderService.class);

    public String createOrder(OrderDetails orderDetails) {
        log.info("createOrder: " + orderDetails);
        orderDetails.setOrderId(UUID.randomUUID().toString());
        return orderDetails.getOrderId();
    }
}
