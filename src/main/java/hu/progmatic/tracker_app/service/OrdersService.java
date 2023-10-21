package hu.progmatic.tracker_app.service;

import hu.progmatic.tracker_app.model.Client;
import hu.progmatic.tracker_app.model.Orders;
import hu.progmatic.tracker_app.repository.OrdersRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {
    private OrdersRepo ordersRepo;

    public OrdersService(OrdersRepo ordersRepo) {
        this.ordersRepo = ordersRepo;
    }

    public List<Orders> getAllOrders() {
        return ordersRepo.findAll();
    }

    public void saveNewOrder(Orders orders) {
        ordersRepo.save(orders);
    }

    public void deleteByClient(Client client) {
        List<Orders> orders = client.getOrders();
        for (Orders order: orders) {
            ordersRepo.deleteById(order.getId());
        }
    }

}
