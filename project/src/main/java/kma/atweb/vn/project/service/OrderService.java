package kma.atweb.vn.project.service;

import kma.atweb.vn.project.dao.OrderDAO;
import kma.atweb.vn.project.entity.Order;
import kma.atweb.vn.project.utils.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderDAO orderDAO;

    // admin xác nhận để giao hàng
    public boolean confirmOrder(String orderId) {
        Order currentOrder = orderDAO.findOrder(orderId);
        if(currentOrder == null) {
            return false;
        }

        currentOrder.setStatus(OrderStatus.CONFIRM.name());
        return orderDAO.updateOrder(currentOrder);
    }

    // xác nhận đã hoàn thành đơn hàng
    public boolean completeOrder(String orderId) {
        Order currentOrder = orderDAO.findOrder(orderId);
        if(currentOrder == null) {
            return false;
        }

        currentOrder.setStatus(OrderStatus.COMPLETE.name());
        return orderDAO.updateOrder(currentOrder);
    }
}
