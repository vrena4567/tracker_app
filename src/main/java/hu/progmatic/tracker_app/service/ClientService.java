package hu.progmatic.tracker_app.service;

import hu.progmatic.tracker_app.model.Client;
import hu.progmatic.tracker_app.model.Orders;
import hu.progmatic.tracker_app.repository.ClientRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private ClientRepo clientRepo;

    public ClientService(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    public List<Client> getAllClient() {
        return clientRepo.findAll();
    }

    public void saveNewClient(Client client) {
        clientRepo.save(client);
    }

    public Client getClientById(Integer id) throws ClientNotFoundException {
        Optional<Client> result = clientRepo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new ClientNotFoundException("Could not find any clients with " + id);
    }
    @Transactional
    public void deleteById(Integer id){
        clientRepo.deleteById(id);
    }


}
