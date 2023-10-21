package hu.progmatic.tracker_app.controller;

import hu.progmatic.tracker_app.model.Client;
import hu.progmatic.tracker_app.model.Orders;
import hu.progmatic.tracker_app.service.ClientNotFoundException;
import hu.progmatic.tracker_app.service.ClientService;
import hu.progmatic.tracker_app.service.OrdersService;
import hu.progmatic.tracker_app.service.TrackerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TrackerController {
    private ClientService clientService;
    private OrdersService ordersService;
    private TrackerService trackerService;

    public TrackerController(ClientService clientService, OrdersService ordersService, TrackerService trackerService) {
        this.clientService = clientService;
        this.ordersService = ordersService;
        this.trackerService = trackerService;
    }

    @GetMapping({"/home", "/", ""})
    public String getHomePage() {
        return "home";
    }

    @GetMapping("/orders")
    public String getOrdersPage(Model model) {
        model.addAttribute("orders", ordersService.getAllOrders());
        model.addAttribute("maxClient", trackerService.maxClient());
        return "orders";
    }

    @GetMapping("/orders/new")
    public String showNewOrderForm(Model model) {
        model.addAttribute("order", new Orders());
        model.addAttribute("clients", clientService.getAllClient());
        return "order_form";
    }

    @PostMapping("/orders/new/save")
    public String saveNewOrder(@ModelAttribute("order") Orders orders) {
        ordersService.saveNewOrder(orders);
        return "redirect:/orders";
    }

    @GetMapping("/clients")
    public String getClientsPage(Model model) {
        model.addAttribute("clients", clientService.getAllClient());
        return "clients";
    }


    @GetMapping("/clients/new")
    public String showNewClientForm(Model model) {
        model.addAttribute("client", new Client());
        model.addAttribute("pageTitle", "Add New Client");
        return "client_form";
    }

    @PostMapping("/clients/new/save")
    public String saveClient(@ModelAttribute("client") Client client) {
        clientService.saveNewClient(client);
        return "redirect:/clients";
    }

    @GetMapping("/clients/edit/{id}")
    public String showEditFrom(@PathVariable("id") Integer id, Model model) throws ClientNotFoundException {
        Client client = clientService.getClientById(id);
        model.addAttribute("client", client);
        model.addAttribute("pageTitle", "Edit Client (ID: " + id + ")");
        return "client_form";
    }

    @GetMapping("/clients/{id}")
    public String showClient(@PathVariable("id") Integer id, Model model) throws ClientNotFoundException {
        Client client = clientService.getClientById(id);
        model.addAttribute("client", client);
        model.addAttribute("pageTitle", client.getName() + "'s Details and Orders");
        return "client";
    }

    @GetMapping("/clients/delete/{id}")
    public String deleteClient(@PathVariable("id") Integer id) throws ClientNotFoundException {
        Client client = clientService.getClientById(id);
        ordersService.deleteByClient(client);
        clientService.deleteById(id);
        return "redirect:/clients";
    }
}

