package apash.coding.sa.controller;

import apash.coding.sa.entites.Client;
import apash.coding.sa.repository.ClientRepository;
import apash.coding.sa.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "client")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void creer(@RequestBody Client client) {
        this.clientService.creer(client);

    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Client> rechercher() {
        return this.clientService.rechercher();
    }

    @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
    public Client lire(@PathVariable Integer id) {
            return this.clientService.lire(id);
        }


    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping(path = "{id}", consumes = APPLICATION_JSON_VALUE)
        public void modifier(@PathVariable Integer id, @RequestBody Client client) {
            this.clientService.modifier(id, client);
        }

    }

