package hu.progmatic.tracker_app.service;

import hu.progmatic.tracker_app.model.Client;
import hu.progmatic.tracker_app.model.Orders;
import hu.progmatic.tracker_app.repository.ClientRepo;
import hu.progmatic.tracker_app.repository.OrdersRepo;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TrackerService {
    private ClientRepo clientRepo;
    private OrdersRepo ordersRepo;

    public TrackerService(ClientRepo clientRepo, OrdersRepo ordersRepo) {
        this.clientRepo = clientRepo;
        this.ordersRepo = ordersRepo;
    }

    public Client maxClient(){
        List<Client> clients = clientRepo.findAll();
        Map<Client, Integer> clientsWithPrice = new HashMap<>();
        Client result = null;
        for (Client client : clients) {
            Integer price = 0;
            for (Orders order : client.getOrders() ) {
                price += order.getAmount();
            }
            clientsWithPrice.put(client, price);
        }
        Integer biggest = 0;
        for (Integer actual : clientsWithPrice.values()) {
            if(actual > biggest){
                biggest = actual;
            }
        }
        for (var actual : clientsWithPrice.entrySet()) {
            if(biggest == actual.getValue()) {
                result = actual.getKey();
            }
        }
        return result;
    }

}
