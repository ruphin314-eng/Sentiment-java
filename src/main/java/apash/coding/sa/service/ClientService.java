package apash.coding.sa.service;

import apash.coding.sa.entites.Client;
import apash.coding.sa.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private  ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void creer(Client client){
        Client clientDansLaBDD = this.clientRepository.findByEmail(client.getEmail());
        if(clientDansLaBDD == null){
            this.clientRepository.save(client);
        }


    }

    public List<Client> rechercher(){
        return this.clientRepository.findAll();
    }

    public Client lire(Integer id) {
        Optional<Client> optionalClient = this.clientRepository.findById(id);

        return optionalClient.orElse(null);
    }

    public Client lireOuCreer(Client clientAcreer) {

        Client clientDansLaBDD = this.clientRepository.findByEmail(clientAcreer.getEmail());

        if (clientDansLaBDD == null) {
            return this.clientRepository.save(clientAcreer); // ✅ retourne le client avec ID
        }

        return clientDansLaBDD;
    }

    public void modifier(Integer id, Client client) {
        Client clientDansLBDD = this.lire(id);
        if (clientDansLBDD.getId() == client.getId()) {
          clientDansLBDD.setEmail(client.getEmail());
          clientDansLBDD.setTelephone(client.getTelephone());
          this.clientRepository.save(clientDansLBDD);
        }
    }
}
